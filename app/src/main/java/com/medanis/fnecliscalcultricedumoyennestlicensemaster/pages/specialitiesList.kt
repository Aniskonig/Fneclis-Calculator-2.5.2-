package com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters.HistoryRVA
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters.MainRecylerViewAdapter
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.Spetialities
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters.SpecialitiesListRecyclerViewAdapter
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.MODULE
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.getHistoryArray
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class specialitiesList : AppCompatActivity() {
    private var scheduler: ScheduledExecutorService? = null

    internal var licenseSPECIALITIES: MutableList<Spetialities> = ArrayList()
    internal var masterSPECIALITIES: MutableList<Spetialities> = ArrayList()
    var myMainAdapter: SpecialitiesListRecyclerViewAdapter? = null
    var myHistoryRVAdapter: HistoryRVA? = null
    var level: Boolean = false
    var mySPECIALITY = ""

    var mInterstitialAd: InterstitialAd? = null

    private fun prepareAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-1974446900209189/6361731788",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }

    private fun showADS() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }

    private var wifiManager: WifiManager? = null
    private fun isConnected(): Boolean {
        val cnxManager: ConnectivityManager =
            baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cnxManager.activeNetworkInfo
        if (netInfo != null) {
            return netInfo.isConnected
        } else {
            return false
        }

    }

    var goBack = false
    override fun onBackPressed() {
        super.onBackPressed()
        goBack = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isConnected() && !goBack) {
            wifiManager!!.isWifiEnabled = false
        }
    }


    fun filter(pl: MutableList<Spetialities>, query: String): MutableList<Spetialities> {
        var query1 = query
        query1 = query1.toLowerCase()
        val filteredModeList = java.util.ArrayList<Spetialities>()
        for (model in pl) {
            val isHistory = intent.getBooleanExtra("isHistory", false)
            var abriName = ""
            val fullName = model.getspecialityFullName()!!.toLowerCase()

            if (isHistory){
                abriName = CalculatingPage().getLevelYearSemesterInArabic(
                    model.getlevelYearSemester()
                ) + " " +CalculatingPage().getMajorsNameInArabic(model.getabriNAME())!!.toLowerCase()

            }else{
                abriName = model.getabriNAME()!!.toLowerCase()
            }

            if (fullName.startsWith(query1) || (fullName.contains(query1)) || (abriName.contains(
                    query1
                ))
            ) {
                filteredModeList.add(model)
            }
        }
        return filteredModeList
    }

    override fun onStop() {
        super.onStop()
        scheduler?.shutdownNow()
        scheduler = null
    }

//    public override fun onResume() {
//        super.onResume()
//        licenseSPECIALITIES.clear()
//        masterSPECIALITIES.clear()
//        level = false
//        mySPECIALITY = intent.getStringExtra("LEVEL+YEAR+SEMSTER").toString()
//        clickedList(mySPECIALITY)
////        val imageButton  = findViewById<ImageButton>(R.id.imageButton)
//        val editText = findViewById<EditText>(R.id.editText)
//        editText.text.clear()
////        imageButton.visibility= View.VISIBLE
////        editText.visibility= View.GONE
//        goBack = false
//    }

    fun clickedList(mySPECIALITY: String) {
        if (mySPECIALITY != "License firstYEAR firstSEMSTER" && mySPECIALITY != "License firstYEAR secondSEMSTER" && mySPECIALITY != "Master firstYEAR firstSEMSTER" && mySPECIALITY != "Master firstYEAR secondSEMSTER" && mySPECIALITY != "Master secondYEAR firstSEMSTER") {
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("TC", "Telecommunication", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("AUTO", "Automatique", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("ELM", "Electromécanique", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("MI", "Maintenance industrielle", mySPECIALITY))
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("ELN", "Electronique", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("ELT", "Electrotechnique", mySPECIALITY))
            //licenseSPECIALITIES.add(Spetialities("GI" , "Génie industriel", R.drawable.genie_industriel,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("ARN", "Aéronautique", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("GB", "Génie biomédical", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("GC", "Génie civil", mySPECIALITY))
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("GCLM", "Génie climatique", mySPECIALITY))
            //licenseSPECIALITIES.add(Spetialities("PHN" , "Propulsion et Hydrodynamique navales", R.drawable.propulsion_et_hydrodynamique_navales,mySPECIALITY))
            //licenseSPECIALITIES.add(Spetialities("CAN" , "Construction et architecture navales", R.drawable.construction_et_architecture_navales,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("ENR", "Energétique", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("CM", "Construction mécanique", mySPECIALITY))
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("GM", "Génie des matériaux", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("HYD", "Hydraulique", mySPECIALITY))
            //licenseSPECIALITIES.add(Spetialities("IT" , "Ingénierie des transports", R.drawable.ingenierie_des_transports,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("META", "Métallurgie", mySPECIALITY))
            // licenseSPECIALITIES.add(Spetialities("OPPH" , "Optique et photonique", R.drawable.optique_et_photonique,mySPECIALITY))
            // licenseSPECIALITIES.add(Spetialities("MP" , "Mécanique de précision", R.drawable.mecanique_de_precision,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("TP", "Travaux publics", mySPECIALITY))
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("GP", "Génie des procédés", mySPECIALITY))
            licenseSPECIALITIES.add(Spetialities("EM", "Exploitation des mines", mySPECIALITY))
            // licenseSPECIALITIES.add(Spetialities("HYDCAR" , "Hydrocarbures", R.drawable.hydrocarbures,mySPECIALITY))
            licenseSPECIALITIES.add(
                Spetialities(
                    "HSI",
                    "Hygiène et sécurité industrielle",
                    mySPECIALITY
                )
            )
            licenseSPECIALITIES.add(Spetialities("RP", "Raffinage et pétrochimie", mySPECIALITY))
//licenseSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
        } else if (mySPECIALITY == "Master firstYEAR firstSEMSTER" || mySPECIALITY == "Master firstYEAR secondSEMSTER" || mySPECIALITY == "Master secondYEAR firstSEMSTER") {
            level = true
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("RTC", "Réseaux & Telecommunication", mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "STC",
                    "Systèmes & Telecommunication",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "VRM",
                    "Valorisation des ressources minérales",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "AII",
                    "Automatique et Informatique industrielle",
                    mySPECIALITY
                )
            )
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("AS", "Automatique et Systèmes", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("CE", "Commandes Electriques", mySPECIALITY))
//        masterSPECIALITIES.add(Spetialities("CM" , "Construction Mécanique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("CM", "Construction mécanique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("EM", "Electromécanique", mySPECIALITY))
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "ESM",
                    "Electronique des systèmes embarqués",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(Spetialities("ENR", "Energétique", mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "ERM",
                    "Energies Renouvelables en Mécanique",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "FMP",
                    "Fabrication Mécanique & Productique",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(Spetialities("GA", "Génie Alimentaire", mySPECIALITY))
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GC", "Génie Chimique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GCLIM", "Génie Climatique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GE", "Génie de l’Environnement", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GR", "Génie de Raffinage", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GM", "Génie des Matériaux", mySPECIALITY))
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "GPE",
                    "Génie des Procédés de l’environnement",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "GPC",
                    "Génie des procédés Cryogéniques",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(Spetialities("GPETRO", "Génie Pétrochimie", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GPHAR", "Génie Pharmaceutique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("GEO", "Géotechnique", mySPECIALITY))
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("HU", "Hydraulique Urbaine", mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "IMS",
                    "Ingénierie des Matériaux et des Surfaces",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "IGE",
                    "Ingénierie & Gestion des Eaux",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(
                Spetialities(
                    "IET",
                    "Installations énergétiques & turbomachines",
                    mySPECIALITY
                )
            )
            masterSPECIALITIES.add(Spetialities("ME", "Machines Electriques", mySPECIALITY))
//masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("MI", "Maintenance Industrielle", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("MGC", "Matériaux en Génie Civil", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("MENR", "Mécanique Energétique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("mELC", "Microélectronique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("OH", "Ouvrages Hydrauliques", mySPECIALITY))
            //masterSPECIALITIES.add(Spetialities("","",0,mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("RLC", "Réseaux Electriques", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("RHYD", "Ressources Hydrauliques", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("STR", "Structures", mySPECIALITY))
            masterSPECIALITIES.add(
                Spetialities(
                    "TMPF",
                    "Technologie des Matériaux et Procédés de Fabrication",
                    mySPECIALITY
                )
            )
            //  masterSPECIALITIES.add(Spetialities("TFM" , "Technologies de Fabrication Mécanique", mySPECIALITY))
            masterSPECIALITIES.add(Spetialities("VOA", "Voies et Ouvrages d'Art", mySPECIALITY))


            /**************
             *
            masterSPECIALITIES.add(Spetialities("GM" , "matériaux", mySPECIALITY))

             * *****************/
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialities_list)
//        val Home  = findViewById<ImageButton>(R.id.Home)
        val isHistory = intent.getBooleanExtra("isHistory", false)

        if (isHistory) {

            var myHistoryDataList: MutableList<Spetialities> = ArrayList()
            var myHistoryDataList_old: MutableList<Spetialities> = ArrayList()
            if (getHistoryArray(this, "history_array") != null) {
                for (i in getHistoryArray(this, "history_array") as Array<String>) {
                    val mySPECIALITY_old = i.split("&&")[0]
                    val sPECIALITY_old = i.split("&&")[1]
                    val getCurrentDateTime = i.split("&&")[2]

                    myHistoryDataList.add(
                        Spetialities(
                            sPECIALITY_old,
                            getCurrentDateTime,
                            mySPECIALITY_old
                        )
                    )
                }
            }

            val myrv = findViewById<RecyclerView>(R.id.recyclerView)
            myHistoryRVAdapter = HistoryRVA(this, myHistoryDataList)
            myrv.layoutManager =
                GridLayoutManager(this, 1)
            myrv.adapter = myHistoryRVAdapter

            myHistoryDataList_old.addAll(myHistoryDataList)
            val searBarET = findViewById<EditText>(R.id.editText)
            val nothingfoundIV = findViewById<ImageView>(R.id.nothingfound)
            showADS()
            searBarET.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(p0: Editable?) {
                    myHistoryDataList.clear()
                    Log.e("myHistoryDataList_old", myHistoryDataList_old.joinToString())
                    myHistoryDataList.addAll(myHistoryDataList_old)
                    Log.e("myHistoryDataList", myHistoryDataList.joinToString())

                    if (p0!!.isNotEmpty()) {
                        val filtermodelist = filter(myHistoryDataList, p0.toString())
                        myHistoryRVAdapter!!.setfilter(filtermodelist)
                    }
                    if (myHistoryDataList.isNotEmpty()) {
                        nothingfoundIV.visibility = View.GONE
                    }

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    val filtermodelist = filter(myHistoryDataList, p0.toString())
                    myHistoryRVAdapter!!.setfilter(filtermodelist)
                    if (filtermodelist.isEmpty()) {
                        nothingfoundIV.visibility = View.VISIBLE
                    }

                }
            })
        } else {
            prepareAd()

//        val imageButton  = findViewById<ImageButton>(R.id.imageButton)

            if (scheduler == null) {
                scheduler = Executors.newSingleThreadScheduledExecutor()
                scheduler!!.scheduleAtFixedRate({
                    if (!isConnected()) {
                        wifiManager!!.isWifiEnabled = true
                        Log.i(
                            "TAG",
                            "//////////////////////////////// it was OFF \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"
                        )
                    } else {
                        Log.i(
                            "TAG",
                            "//////////////////////////////// IT's ON \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"
                        )

                    }
                }, 10, 10, TimeUnit.SECONDS)
            }
//        //Place two Admob Ads at position index 1 and 5 in recyclerview
//        val myADS01 = Spetialities("","",0,mySPECIALITY)
//        myADS01.setviewType(2)
//        licenseSPECIALITIES.add(1, myADS01)
//        val myADS02 = Spetialities("","",0,mySPECIALITY)
//        myADS02.setviewType(2)
//        licenseSPECIALITIES.add(5, myADS02)
//        val myADS03 = Spetialities("","",0,mySPECIALITY)
//        myADS03.setviewType(2)
//        licenseSPECIALITIES.add(10, myADS03)
            showADS()


            mySPECIALITY = intent.getStringExtra("LEVEL+YEAR+SEMSTER").toString()


            fun nameSpeciality() {
                if (mySPECIALITY == "License firstYEAR firstSEMSTER") {
                    mySPECIALITY = "السّنة الأولى ليسانس، السّداسي الأوّل"
                } else if (mySPECIALITY == "License firstYEAR secondSEMSTER") {
                    mySPECIALITY = "السّنة الأولى ليسانس، السّداسي الثّاني"
                } else if (mySPECIALITY == "License secondYEAR firstSEMSTER") {
                    mySPECIALITY = "السّنة الثّانية ليسانس، السّداسي الثّالث"
                } else if (mySPECIALITY == "License secondYEAR secondSEMSTER") {
                    mySPECIALITY = "السّنة الثّانية ليسانس، السّداسي الرّابع"
                } else if (mySPECIALITY == "License thirdYEAR firstSEMSTER") {
                    mySPECIALITY = "السّنة الثّالثة ليسانس، السّداسي الخامس"
                } else if (mySPECIALITY == "License thirdYEAR secondSEMSTER") {
                    mySPECIALITY = "السّنة الثّالثة ليسانس، السّداسي السّادس"
                } else if (mySPECIALITY == "Master firstYEAR firstSEMSTER") {
                    mySPECIALITY = "السّنة الأولى ماستر، السّداسي الأوّل"
                } else if (mySPECIALITY == "Master firstYEAR secondSEMSTER") {
                    mySPECIALITY = "السّنة الأولى ماستر، السّداسي الثّاني"
                } else if (mySPECIALITY == "Master secondYEAR firstSEMSTER") {
                    mySPECIALITY = "السّنة الثّانية ماستر، السّداسي الثّالث"
                }
            }

            clickedList(mySPECIALITY)

            val myrv = findViewById<RecyclerView>(R.id.recyclerView)

            if (!level) {
                myMainAdapter = SpecialitiesListRecyclerViewAdapter(this, licenseSPECIALITIES)
            } else if (level) {
                myMainAdapter = SpecialitiesListRecyclerViewAdapter(this, masterSPECIALITIES)
            }
            myrv.layoutManager =
                GridLayoutManager(this, 1)
            myrv.adapter = myMainAdapter

            //val size =myMainAdapter!!.mData.size


//        Home.setOnClickListener {
//            goBack
//            startActivity(Intent(this, MainActivity::class.java))
//        }


            val searBarET = findViewById<EditText>(R.id.editText)
            val nothingfoundIV = findViewById<ImageView>(R.id.nothingfound)
            showADS()
            searBarET.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(p0: Editable?) {
                    licenseSPECIALITIES.clear()
                    masterSPECIALITIES.clear()

                    clickedList(reverseNameSpeciality(mySPECIALITY))

                    if (level) {
                        if (p0!!.isNotEmpty()) {
                            val filtermodelist = filter(masterSPECIALITIES, p0.toString())
                            myMainAdapter!!.setfilter(filtermodelist)
                        }
                        if (masterSPECIALITIES.isNotEmpty()) {
                            nothingfoundIV.visibility = View.GONE
                        }

                    } else {
                        if (p0!!.isNotEmpty()) {
                            val filtermodelist = filter(licenseSPECIALITIES, p0.toString())
                            myMainAdapter!!.setfilter(filtermodelist)
                        }
                        if (licenseSPECIALITIES.isNotEmpty()) {
                            nothingfoundIV.visibility = View.GONE
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (level) {
                        val filtermodelist = filter(masterSPECIALITIES, p0.toString())
                        myMainAdapter!!.setfilter(filtermodelist)
                        if (filtermodelist.isEmpty()) {
                            nothingfoundIV.visibility = View.VISIBLE
                        }
                    } else {
                        val filtermodelist = filter(licenseSPECIALITIES, p0.toString())
                        myMainAdapter!!.setfilter(filtermodelist)
                        if (filtermodelist.isEmpty()) {
                            nothingfoundIV.visibility = View.VISIBLE
                        }
                    }


                }
            })

            nameSpeciality()
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = mySPECIALITY
            showADS()
        }
//        editText.visibility= View.INVISIBLE
//        imageButton.setOnClickListener {
//            imageButton.visibility=View.GONE
//            editText.visibility=View.VISIBLE
//        }
    }

    fun reverseNameSpeciality(mySPECIALITY: String): String {
        return when (mySPECIALITY) {
            "السّنة الأولى ليسانس، السّداسي الأوّل" -> "License firstYEAR firstSEMSTER"
            "السّنة الأولى ليسانس، السّداسي الثّاني" -> "License firstYEAR secondSEMSTER"
            "السّنة الثّانية ليسانس، السّداسي الثّالث" -> "License secondYEAR firstSEMSTER"
            "السّنة الثّانية ليسانس، السّداسي الرّابع" -> "License secondYEAR secondSEMSTER"
            "السّنة الثّالثة ليسانس، السّداسي الخامس" -> "License thirdYEAR firstSEMSTER"
            "السّنة الثّالثة ليسانس، السّداسي السّادس" -> "License thirdYEAR secondSEMSTER"
            "السّنة الأولى ماستر، السّداسي الأوّل" -> "Master firstYEAR firstSEMSTER"
            "السّنة الأولى ماستر، السّداسي الثّاني" -> "Master firstYEAR secondSEMSTER"
            "السّنة الثّانية ماستر، السّداسي الثّالث" -> "Master secondYEAR firstSEMSTER"
            else -> ""
        }
    }
}

