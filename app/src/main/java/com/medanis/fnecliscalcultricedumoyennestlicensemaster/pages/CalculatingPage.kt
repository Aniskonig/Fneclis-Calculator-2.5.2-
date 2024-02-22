package com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.MODULE
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters.MainRecylerViewAdapter
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.getModulesFromSharedPreferences
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.initEmoji
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.saveModulesInSharedPreferences
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.updateTheEmoji
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.ScheduledExecutorService
import java.util.Date
import java.text.SimpleDateFormat
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CalculatingPage : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    fun getMajorsNameInArabic(major: String?): String {
        if (major == "Telecommunication") {
            return "تخصّص إتصالات سلكية و لا سلكية"
        } else if (major == "Réseaux & Telecommunication") {
            return "تخصّص شبكات و إتّصالات"
        } else if (major == "Systèmes & Telecommunication") {
            return "تخصّص أنظمة و إتّصالات"
        } else if (major == "Automatique") {
            return "تخصّص آلية"
        } else if (major == "Electromécanique") {
            return "تخصّص كهروميكانيكي"
        } else if (major == "Maintenance industrielle") {
            return "تخصّص صيانة صناعية"
        } else if (major == "Electronique") {
            return "تخصّص إلكترونيك"
        } else if (major == "Electronique") {
            return "تخصّص إلكتروتقني"
        } else if (major == "Génie biomédical") {
            return "تخصّص هندسة طبية حيوية"
        } else if (major == "Aéronautique") {
            return "تخصّص هندسة الطيران"
        } else if (major == "Génie civil") {
            return "تخصّص هندسة مدنية"
        } else if (major == "Génie climatique") {
            return "تخصّص هندسة التكييف"
        } else if (major == "Energétique") {
            return "تخصّص علوم طاقوية"
        } else if (major == "Construction mécanique") {
            return "تخصّص انشاء ميكانيكي"
        } else if (major == "Génie des Matériaux" || major == "Génie des matériaux") {
            return "تخصّص هندسة المواد"
        } else if (major == "Hydraulique") {
            return "تخصّص ري"
        } else if (major == "Métallurgie") {
            return "تخصّص تعدين"
        } else if (major == "Travaux publics") {
            return "تخصّص اشغال عمومية"
        } else if (major == "Génie des procédés") {
            return "تخصّص هندسة الطرائق"
        } else if (major == "Valorisation des ressources minérales") {
            return "تخصّص تثمين الموارد المعدنية"
        } else if (major == "Exploitation des mines") {
            return "تخصّص استغلال المناجم"
        } else if (major == "Hygiène et sécurité industrielle") {
            return "تخصّص نظافة و أمن صناعي"
        } else if (major == "Raffinage et pétrochimie") {
            return "تخصّص تكرير و بيتروكيماوي"
        } else if (major == "Automatique et Informatique industrielle") {
            return "تخصّص آلية و إعلام آلي صناعي"
        } else if (major == "Automatique et Systèmes") {
            return "تخصّص آلية و أنظمة"
        } else if (major == "Commandes Electriques") {
            return "تخصّص تحكم كهربائي"
        } else if (major == "Construction mécanique") {
            return "تخصّص انشاء ميكانيكي"
        } else if (major == "Electromécanique") {
            return "تخصّص كھرومیكانیك"
        } else if (major == "Electronique des systèmes embarqués") {
            return "تخصّص الكترونیك الانظمة المضمنة"
        } else if (major == "Energies Renouvelables en Mécanique") {
            return "تخصّص طاقات متجددة في الميكانيكا"
        } else if (major == "Fabrication Mécanique & Productique") {
            return "تخصّص تصنيع ميكانيكي و إنتاجي"
        } else if (major == "Génie Alimentaire") {
            return "تخصّص هندسة غذائية"
        } else if (major == "Génie Chimique") {
            return "تخصّص هندسة كيميائية"
        } else if (major == "Génie Climatique") {
            return "تخصّص هندسة التكييف"
        } else if (major == "Génie de l’Environnement") {
            return "تخصّص هندسة بيئية"
        } else if (major == "Génie de Raffinage") {
            return "تخصّص هندسة المصافي"
        } else if (major == "Génie des Procédés de l’environnement") {
            return "تخصّص هندسة الطرائق للبيئة"
        } else if (major == "Génie des procédés Cryogéniques") {
            return "تخصّص هندسة العمليات بالبرودة"
        } else if (major == "Génie Pétrochimie") {
            return "تخصّص هندسة البتروكيماويات"
        } else if (major == "Génie Pharmaceutique") {
            return "تخصّص هندسة دوائية"
        } else if (major == "Géotechnique") {
            return "تخصّص جيوتقنية"
        } else if (major == "Hydraulique Urbaine") {
            return "تخصّص الري الحضري"
        } else if (major == "Ingénierie des Matériaux et des Surfaces") {
            return "تخصّص هندسة المواد والسطوح"
        } else if (major == "Ingénierie & Gestion des Eaux") {
            return "تخصّص هندسة وإدارة المياه"
        } else if (major == "Installations énergétiques & turbomachines") {
            return "تخصّص منشآت الطاقة والتوربينات"
        } else if (major == "Machines Electriques") {
            return "تخصّص ماكينات كهربائية"
        } else if (major == "Maintenance Industrielle") {
            return "تخصّص صيانة صناعية"
        } else if (major == "Matériaux en Génie Civil") {
            return "تخصّص مواد الهندسة المدنية"
        } else if (major == "Mécanique Energétique") {
            return "تخصّص ميكانيكا الطاقة"
        } else if (major == "Microélectronique") {
            return "تخصّص ميكرو إلكترونيك"
        } else if (major == "Ouvrages Hydrauliques") {
            return "تخصّص منشآت الري"
        } else if (major == "Réseaux Electriques") {
            return "تخصّص شبكات كهربائیة"
        } else if (major == "Ressources Hydrauliques") {
            return "تخصّص موارد مائية"
        } else if (major == "Structures") {
            return "تخصّص هياكل"
        } else if (major == "Technologie des Matériaux et Procédés de Fabrication") {
            return "تخصّص تكنولوجيا المواد وعمليات التصنيع"
        } else if (major == "Technologies de Fabrication Mécanique") {
            return "تخصّص تقنيات التصنيع الميكانيكية"
        } else if (major == "Voies et Ouvrages d'Art") {
            return "تخصّص طرقات ومنشآت فنية"
        } else if (major == "Instrumentation Biomédicale") {
            return "تخصّص الأجهزة البيوطبية"
        } else if (major == "Industries Pétrochimiques") {
            return "تخصّص صناعات بتروكيمياوية"
        } else {
            return ""
        }
    }

    fun getLevelYearSemesterInArabic(text: String?): String {
        if (text == "License firstYEAR firstSEMSTER" || text == "السّنة الأولى ليسانس، السّداسي الأوّل") {
            return "السّنة الأولى ليسانس، السّداسي الأوّل"
        } else if (text == "License firstYEAR secondSEMSTER" || text == "السّنة الأولى ليسانس، السّداسي الثّاني") {
            return "السّنة الأولى ليسانس، السّداسي الثّاني"

        } else if (text == "License secondYEAR firstSEMSTER" || text == "السّنة الثّانية ليسانس، السّداسي الثّالث") {
            return "السّنة الثّانية ليسانس، السّداسي الثّالث،"
        } else if (text == "License secondYEAR secondSEMSTER" || text == "السّنة الثّانية ليسانس، السّداسي الرّابع") {
            return "السّنة الثّانية ليسانس، السّداسي الرّابع،"

        } else if (text == "License thirdYEAR firstSEMSTER" || text == "السّنة الثّالثة ليسانس، السّداسي الخامس") {
            return "السّنة الثّالثة ليسانس، السّداسي الخامس،"
        } else if (text == "License thirdYEAR secondSEMSTER" || text == "السّنة الثّالثة ليسانس، السّداسي السّادس") {
            return "السّنة الثّالثة ليسانس، السّداسي السّادس،"
        } else if (text == "Master firstYEAR firstSEMSTER" || text == "السّنة الأولى ماستر، السّداسي الأوّل") {
            return "السّنة الأولى ماستر، السّداسي الأوّل،"
        } else if (text == "Master firstYEAR secondSEMSTER" || text == "السّنة الأولى ماستر، السّداسي الثّاني") {
            return "السّنة الأولى ماستر، السّداسي الثّاني،"
        } else if (text == "Master secondYEAR firstSEMSTER" || text == "السّنة الثّانية ماستر، السّداسي الثّالث") {
            return "السّنة الثّانية ماستر، السّداسي الثّالث،"
        }
        return ""
    }

    private fun getCurrentDateTime(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return formatter.format(currentDate).toString()
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

    private var licenseMODULES: MutableList<MODULE> = ArrayList()
    var moy: Double = 0.0
    var oldMoy: Double = 0.0
    private lateinit var mp: MediaPlayer
    private lateinit var mYp: MediaPlayer


    var mInterstitialAd: InterstitialAd? = null

    private fun prepareAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-1974446900209189/6361731788",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(ContentValues.TAG, adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(ContentValues.TAG, "Ad was loaded.")
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

    private var scheduler: ScheduledExecutorService? = null

    var ttCoeff = 17
    var session = 0
    override fun onStop() {
        super.onStop()
        scheduler?.shutdownNow()
        scheduler = null
    }

    public override fun onResume() {
        super.onResume()

        oldMoy = 0.0
        moy = 0.0
        ttCoeff = 17
        session = 0
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (!isConnected()) {
            wifiManager!!.isWifiEnabled = true
        }
        goBack = false
        findViewById<TextView>(R.id.errorTextView).visibility = View.GONE
    }

    //    @BindView(R.id.tapBarMenu)
//    internal var tapBarMenu: TapBarMenu = tapBarMenu
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("ResourceAsColor", "SetTextI18n", "InflateParams", "CutPasteId")
//    private lateinit var dialogADS: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculating_page)
        val builder = AlertDialog.Builder(this)
        val dialog: AlertDialog = builder.create()
        var myModulesAdapter: MainRecylerViewAdapter? = null

        val isHistory = intent.getBooleanExtra("isHistory", false)

        if (isHistory) {

            var mySPECIALITY = intent.getStringExtra("level Year semster")
            if (mySPECIALITY == null) {
                mySPECIALITY = intent.getStringExtra("LEVEL+YEAR+SEMSTER")
            }
            val mySPECIALITY_old = mySPECIALITY
            val sPECIALITY = intent.getStringExtra("SpecialityNAME")?.split("&&")!!
            Log.e("sPECIALITY", sPECIALITY.toString())
            mySPECIALITY = getLevelYearSemesterInArabic(mySPECIALITY)

            findViewById<TextView>(R.id.textViewSPECIALITY).text =
                "$mySPECIALITY ${getMajorsNameInArabic(sPECIALITY[0])}"

            licenseMODULES = getModulesFromSharedPreferences(
                this,
                mySPECIALITY_old,
                sPECIALITY[0],
                sPECIALITY[1]
            )!!
            myModulesAdapter = MainRecylerViewAdapter(this, licenseMODULES)

            val myrv = findViewById<RecyclerView>(R.id.rvCalPage)

            myrv.layoutManager =
                GridLayoutManager(this, 1)
            myrv.adapter = myModulesAdapter

            val saveRetrieveButton = findViewById<Button>(R.id.saveRetrieveData)
            saveRetrieveButton.setOnClickListener {
                saveRetrieveButton.setBackgroundResource(R.drawable.icon_save_filled)
                saveModulesInSharedPreferences(
                    this,
                    myModulesAdapter!!.mData,
                    mySPECIALITY_old,
                    sPECIALITY[0],
                    sPECIALITY[1]
                )

                Toast.makeText(
                    this,
                    "Résultats modifiés avec succès",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {

            wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (!isConnected()) {
                wifiManager!!.isWifiEnabled = true

            }

            findViewById<TextView>(R.id.errorTextView).visibility = View.GONE
            ttCoeff = 17
            val calculatingTV = findViewById<TextView>(R.id.calculatingTV)
            val errorTextView = findViewById<TextView>(R.id.errorTextView)
            val textViewSPECIALITY = findViewById<TextView>(R.id.textViewSPECIALITY)

            var mySPECIALITY = intent.getStringExtra("level Year semster")
            if (mySPECIALITY == null) {
                mySPECIALITY = intent.getStringExtra("LEVEL+YEAR+SEMSTER")
            }
            val mySPECIALITY_old = mySPECIALITY


            var sPECIALITY = intent.getStringExtra("SpecialityNAME")

            val sPECIALITY_old = sPECIALITY
            val fneclisADS = findViewById<ImageButton>(R.id.fneclisADS)
            val timer = findViewById<TextView>(R.id.timer)

            prepareAd()
            showADS()

            fun appInstalledOrNot(uri: String): Boolean {
                val pm: PackageManager = packageManager

                try {
                    pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
                    return true
                } catch (e: PackageManager.NameNotFoundException) {
                    return false
                }
            }

            val isAppInstalled: Boolean = appInstalledOrNot("com.medanis.fneclis")

            val builderADS = AlertDialog.Builder(this, R.style.AppTheme)
            val inflaterADS = layoutInflater
            val mydialogADS = inflaterADS.inflate(R.layout.activity_adspage, null)
            builderADS.setView(mydialogADS)
            val dialogADS: AlertDialog = builderADS.create()

            if (isConnected() && !isAppInstalled) {
                Handler().postDelayed({
                    fneclisADS.visibility = View.VISIBLE
                    mydialogADS.findViewById<ImageButton>(R.id.closeAds).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.timer).visibility = View.VISIBLE

                    object : CountDownTimer(15000, 1000) {
                        @SuppressLint("SetTextI18n")
                        override fun onFinish() {
                            fneclisADS.visibility = View.GONE
                            mydialogADS.findViewById<ImageButton>(R.id.closeAds).visibility =
                                View.GONE
                            findViewById<TextView>(R.id.timer)!!.visibility = View.GONE

                        }

                        @SuppressLint("SetTextI18n")
                        override fun onTick(p0: Long) {
                            findViewById<TextView>(R.id.timer).text = (p0 / 1000).toString()
                        }
                    }.start()

                }, 10000)
            }
            mydialogADS.findViewById<ImageButton>(R.id.closeAds).setOnClickListener {
                if (fneclisADS.visibility == View.VISIBLE) {
                    fneclisADS.visibility = View.GONE
                    mydialogADS.findViewById<ImageButton>(R.id.closeAds).visibility = View.GONE
                    findViewById<TextView>(R.id.timer).visibility = View.GONE
                }
            }
//    val adViewADS = findViewById<AdView>(R.id.dialogAdView)


            mydialogADS.findViewById<TextView>(R.id.exitTXT).setOnClickListener {
                dialogADS.dismiss()
            }
            mydialogADS.findViewById<ImageButton>(R.id.closeAds).setOnClickListener {
                dialogADS.dismiss()
            }
            val adRequestADS = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
            mydialogADS.findViewById<com.google.android.gms.ads.AdView>(R.id.dialogAdView)
                .loadAd(adRequestADS)
            mydialogADS.findViewById<com.google.android.gms.ads.AdView>(R.id.dialogAdView).adListener =
                object : AdListener() {
                    override fun onAdLoaded() {
                        if (!dialog.isShowing) {
                            dialogADS.show()
                        }
                    }
                }
            fneclisADS.setOnClickListener {
                val appPackageName = "com.medanis.fneclis"

                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (anfe: android.content.ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
                showADS()
            }




            fun addMosules() {
                if (mySPECIALITY == "License firstYEAR firstSEMSTER" || mySPECIALITY == "السّنة الأولى ليسانس، السّداسي الأوّل") {
                    mySPECIALITY = "السّنة الأولى ليسانس، السّداسي الأوّل"
                    licenseMODULES.add(MODULE("Mathématiques 1", 3.0))
                    licenseMODULES.add(MODULE("Physique 1", 3.0))
                    licenseMODULES.add(MODULE("Structure de la matière", 3.0))
                    licenseMODULES.add(MODULE("Informatique 1", 2.1))
                    licenseMODULES.add(MODULE("Langue étrangère 1 (Français et/ou anglais)", 2.2))
                    licenseMODULES.add(MODULE("Méthodologie de la rédaction", 1.0))
                    licenseMODULES.add(MODULE("Les métiers en sciences et technologies 1", 1.0))
                    licenseMODULES.add(MODULE("TP Physique 1", 1.1))
                    licenseMODULES.add(MODULE("TP Chimie 1", 1.1))

                } else if (mySPECIALITY == "License firstYEAR secondSEMSTER" || mySPECIALITY == "السّنة الأولى ليسانس، السّداسي الثّاني") {
                    mySPECIALITY = "السّنة الأولى ليسانس، السّداسي الثّاني"
                    licenseMODULES.add(MODULE("Mathématiques 2", 3.0))
                    licenseMODULES.add(MODULE("Physique 2", 3.0))
                    licenseMODULES.add(MODULE("Thermodynamique", 3.0))
                    licenseMODULES.add(MODULE("Informatique 2", 2.1))
                    licenseMODULES.add(MODULE("Langue étrangère 2 (Français et/ou anglais)", 2.2))
                    licenseMODULES.add(MODULE("Méthodologie de la présentation", 1.0))
                    licenseMODULES.add(MODULE("Les métiers en sciences et technologies 2", 1.0))
                    licenseMODULES.add(MODULE("TP Physique 2", 1.1))
                    licenseMODULES.add(MODULE("TP Chimie 2", 1.1))

                } else if (mySPECIALITY == "License secondYEAR firstSEMSTER" || mySPECIALITY == "السّنة الثّانية ليسانس، السّداسي الثّالث") {
                    mySPECIALITY = "السّنة الثّانية ليسانس، السّداسي الثّالث،"
                    if (sPECIALITY == "Telecommunication" || sPECIALITY == "Génie biomédical" || sPECIALITY == "Electrotechnique" || sPECIALITY == "Electronique" || sPECIALITY == "Automatique" || sPECIALITY == "Electromécanique" || sPECIALITY == "Maintenance industrielle") {
                        licenseMODULES.add(MODULE("Mathématiques 3", 3.0))
                        licenseMODULES.add(MODULE("Ondes et vibrations", 2.0))
                        licenseMODULES.add(MODULE("Electronique fondamentale 1", 2.0))
                        licenseMODULES.add(MODULE("Electrotechnique fondamentale 1", 2.0))
                        licenseMODULES.add(MODULE("Probabilités et statistiques", 2.0))
                        licenseMODULES.add(MODULE("Etat de l'art du génie électrique", 1.0))
                        licenseMODULES.add(MODULE("Energies et environnement", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique", 1.0))
                        licenseMODULES.add(MODULE("Tp Informatique 3", 1.1))
                        licenseMODULES.add(MODULE("TP Electronique 1 et électrotechnique 1", 1.1))
                        licenseMODULES.add(MODULE("TP Ondes et vibrations", 1.1))
                    } else if (sPECIALITY == "Aéronautique" || sPECIALITY == "Travaux publics" || sPECIALITY == "Métallurgie" || sPECIALITY == "Hydraulique" || sPECIALITY == "Génie des matériaux" || sPECIALITY == "Construction mécanique" || sPECIALITY == "Energétique" || sPECIALITY == "Génie climatique" || sPECIALITY == "Génie civil") {
                        licenseMODULES.add(MODULE("Mathématiques 3", 3.0))
                        licenseMODULES.add(MODULE("Ondes et vibrations", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des fluides", 2.0))
                        licenseMODULES.add(MODULE("Mécanique rationnelle", 2.0))
                        licenseMODULES.add(MODULE("Probabilités et statistiques", 2.0))
                        licenseMODULES.add(MODULE("Technologie de base", 1.0))
                        licenseMODULES.add(MODULE("Métrologie", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique", 1.0))
                        licenseMODULES.add(MODULE("Tp Informatique 3", 1.1))
                        licenseMODULES.add(MODULE("TP Dessin technique", 1.1))
                        licenseMODULES.add(MODULE("TP Ondes et vibrations", 1.1))
                    } else if (sPECIALITY == "Raffinage et pétrochimie" || sPECIALITY == "Génie des procédés" || sPECIALITY == "Hygiène et sécurité industrielle" || sPECIALITY == "Exploitation des mines") {
                        licenseMODULES.add(MODULE("Mathématiques 3", 3.0))
                        licenseMODULES.add(MODULE("Ondes et vibrations", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des fluides", 2.0))
                        licenseMODULES.add(MODULE("Chimie minérale", 2.0))
                        licenseMODULES.add(MODULE("Probabilités et statistiques", 2.0))
                        licenseMODULES.add(MODULE("HSE Installations industrielles", 1.0))
                        licenseMODULES.add(MODULE("Réglementation et normes", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique", 1.0))
                        licenseMODULES.add(MODULE("Tp Informatique 3", 1.1))
                        licenseMODULES.add(MODULE("TP Dessin technique", 1.1))
                        licenseMODULES.add(MODULE("TP Ondes et vibrations", 1.1))
                    }
                } else if (mySPECIALITY == "License secondYEAR secondSEMSTER" || mySPECIALITY == "السّنة الثّانية ليسانس، السّداسي الرّابع") {
                    mySPECIALITY = "السّنة الثّانية ليسانس، السّداسي الرّابع،"
                    if (sPECIALITY == "Telecommunication" || sPECIALITY == "Génie biomédical" || sPECIALITY == "Electrotechnique" || sPECIALITY == "Electronique" || sPECIALITY == "Automatique" || sPECIALITY == "Electromécanique" || sPECIALITY == "Maintenance industrielle") {
                        fun modTWO() {
                            licenseMODULES.add(MODULE("Logique combinatoire et séquentielle", 2.0))
                            licenseMODULES.add(MODULE("Méthodes numériques", 2.0))
                            if (sPECIALITY != "Maintenance industrielle") {
                                licenseMODULES.add(MODULE("Théorie du signal", 2.0))
                                licenseMODULES.add(
                                    MODULE(
                                        "Mesures électriques et électroniques",
                                        2.1
                                    )
                                )
                            }
                        }

                        fun modTP() {
                            licenseMODULES.add(
                                MODULE(
                                    "Techniques d'expression et de communication",
                                    1.0
                                )
                            )
                            licenseMODULES.add(
                                MODULE(
                                    "TP Logique combinatoire et séquentielle",
                                    1.1
                                )
                            )
                            licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        }


                        if (sPECIALITY == "Telecommunication") {
                            licenseMODULES.add(MODULE("Télécommunications fondamentale", 3.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Droit des télécommunications", 1.0))
                            licenseMODULES.add(MODULE("Télécommunications et applications", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Télécommunications fondamentale", 1.1))
                        } else if (sPECIALITY == "Automatique") {
                            licenseMODULES.add(
                                MODULE(
                                    "Systèmes asservis linéaires et continus",
                                    3.0
                                )
                            )
                            modTWO()
                            licenseMODULES.add(MODULE("Sécurité électrique", 1.0))
                            licenseMODULES.add(MODULE("Architecture des Systèmes automatisés", 1.0))
                            modTP()
                            licenseMODULES.add(
                                MODULE(
                                    "Tp Systèmes asservis linéaires et continus",
                                    1.1
                                )
                            )
                        } else if (sPECIALITY == "Electromécanique" || sPECIALITY == "Electrotechnique" || sPECIALITY == "Electronique") {
                            licenseMODULES.add(MODULE("Electrotechnique fondamentale 2", 3.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Production de l'énergie électrique", 1.0))
                            licenseMODULES.add(MODULE("Sécurité électrique", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("Tp Electrotechnique fondamentale 2", 1.1))
                        } else if (sPECIALITY == "Maintenance industrielle") {
                            licenseMODULES.add(MODULE("Hydraulique et pneumatique", 3.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Résistance des matériaux", 2.0))
                            licenseMODULES.add(MODULE("Dessin Technique", 1.0))
                            licenseMODULES.add(MODULE("Systèmes de conversion de l'énergie", 1.0))
                            licenseMODULES.add(
                                MODULE(
                                    "Notions de mesures électriques et électroniques",
                                    1.0
                                )
                            )
                            modTP()
                            licenseMODULES.add(
                                MODULE(
                                    "Tp Mesures électriques et électroniques",
                                    1.1
                                )
                            )
                            licenseMODULES.add(MODULE("Tp Hydraulique et pneumatique", 1.1))
                        } else if (sPECIALITY == "Génie biomédical") {
                            licenseMODULES.add(MODULE("Capteurs de grandeurs physiques", 3.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Anatomie et physiologie", 1.0))
                            licenseMODULES.add(MODULE("Imagerie médicale", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("Tp Capteurs de grandeurs physiques", 1.1))
                        }
                    } else if (sPECIALITY == "Aéronautique" || sPECIALITY == "Exploitation des mines" || sPECIALITY == "Travaux publics" || sPECIALITY == "Métallurgie" || sPECIALITY == "Hydraulique" || sPECIALITY == "Génie des matériaux" || sPECIALITY == "Construction mécanique" || sPECIALITY == "Energétique" || sPECIALITY == "Génie climatique" || sPECIALITY == "Génie civil") {
                        fun modTWO() {
                            licenseMODULES.add(MODULE("Mathématiques 4", 2.0))
                            licenseMODULES.add(MODULE("Méthodes numériques", 2.0))
                            if (sPECIALITY != "Génie climatique" && sPECIALITY != "Exploitation des mines") {
                                licenseMODULES.add(MODULE("Résistance des matériaux", 2.0))
                            }
                        }

                        fun modTP() {
                            licenseMODULES.add(
                                MODULE(
                                    "Techniques d'expression et de communication",
                                    1.0
                                )
                            )
                            if (sPECIALITY != "Exploitation des mines") {
                                licenseMODULES.add(MODULE("Dessin Assisté par Ordinateur", 1.1))
                            }
                            licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                            if (sPECIALITY != "Génie climatique" && sPECIALITY != "Exploitation des mines") {
                                licenseMODULES.add(MODULE("TP Résistance des matériaux", 1.1))
                            }
                        }
                        if (sPECIALITY == "Aéronautique") {
                            licenseMODULES.add(MODULE("Electronique et Avionique", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Construction Aéronautique", 1.0))
                            licenseMODULES.add(MODULE("Navigation Aérienne", 1.0))
                            licenseMODULES.add(MODULE("Réglementation Aéronautique", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                            licenseMODULES.add(MODULE("TP Electronique", 1.1))
                        } else if (sPECIALITY == "Génie civil" || sPECIALITY == "Travaux publics") {
                            licenseMODULES.add(MODULE("Mécanique des sols", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Matériaux de construction", 1.0))
                            licenseMODULES.add(MODULE("Géologie", 1.0))
                            licenseMODULES.add(MODULE("Topographie", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Mécanique des sols", 1.1))
                            licenseMODULES.add(MODULE("TP Matériaux de construction", 1.1))
                        } else if (sPECIALITY == "Génie climatique") {
                            licenseMODULES.add(MODULE("Chauffage et Climatisation", 2.0))
                            licenseMODULES.add(MODULE("Transfert thermique", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Electricité", 1.0))
                            licenseMODULES.add(MODULE("Notions d'Architecture", 1.0))
                            licenseMODULES.add(MODULE("Notions de Contrôle et Régulation", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Electricité", 1.1))
                            licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                            licenseMODULES.add(MODULE("TP Transfert thermique", 1.1))
                        } else if (sPECIALITY == "Exploitation des mines") {
                            licenseMODULES.add(MODULE("Valorisation des ressources minières", 3.0))
                            licenseMODULES.add(MODULE("Exploitation des mines", 2.0))
                            licenseMODULES.add(MODULE("Hydrogéologie", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Gestion de l'environnement minier", 1.0))
                            licenseMODULES.add(MODULE("Nomenclature des gisements miniers", 1.0))
                            modTP()
                            licenseMODULES.add(
                                MODULE(
                                    "TP Valorisation des ressources minières",
                                    1.1
                                )
                            )
                            licenseMODULES.add(MODULE("TP Exploitation des mines", 1.1))
                        } else if (sPECIALITY == "Energétique" || sPECIALITY == "Génie des matériaux" || sPECIALITY == "Construction mécanique") {
                            licenseMODULES.add(MODULE("Thermodynamique 2", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Fabrication Mécanique", 1.0))
                            licenseMODULES.add(MODULE("Electricité industrielle", 1.0))
                            licenseMODULES.add(MODULE("Sciences des Matériaux", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                            licenseMODULES.add(MODULE("TP Fabrication Mécanique", 1.1))
                        } else if (sPECIALITY == "Hydraulique") {
                            licenseMODULES.add(MODULE("Hydraulique générale I", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Hydrologie I", 1.0))
                            licenseMODULES.add(MODULE("Géologie", 1.0))
                            licenseMODULES.add(MODULE("Topographie", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                            licenseMODULES.add(MODULE("TP Hydrologie", 1.1))
                        } else if (sPECIALITY == "Métallurgie") {
                            licenseMODULES.add(MODULE("Chimie physique", 2.0))
                            modTWO()
                            licenseMODULES.add(MODULE("Minéralogie et cristallographie", 1.0))
                            licenseMODULES.add(MODULE("Propriétés des matériaux", 1.0))
                            licenseMODULES.add(MODULE("Métallurgie Extractive", 1.0))
                            modTP()
                            licenseMODULES.add(MODULE("TP Chimie physique", 1.1))
                            licenseMODULES.add(MODULE("TP Minéralogie et cristallographie", 1.1))
                        }
                    } else if (sPECIALITY == "Génie des procédés") {
                        licenseMODULES.add(MODULE("Chimie des solutions", 2.0))
                        licenseMODULES.add(MODULE("Chimie organique", 2.0))
                        licenseMODULES.add(MODULE("Thermodynamique chimique", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques", 2.0))
                        licenseMODULES.add(MODULE("Cinétique chimique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Introduction au raffinage et à la pétrochimie",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Notions des phénomènes de transfert", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d'expression et de communication",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Tp Chimie des solutions", 1.1))
                        licenseMODULES.add(MODULE("TP Chimie organique", 1.1))
                        licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Cinétique chimique", 1.1))
                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Typologie des risques", 3.0))
                        licenseMODULES.add(MODULE("Appareils de contrôle et de mesures", 3.0))
                        licenseMODULES.add(MODULE("Fiabilité humaine et matérielle", 2.0))
                        licenseMODULES.add(MODULE("Réglementation et normes en HSI", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques", 2.0))
                        licenseMODULES.add(MODULE("Méthodes et outils en HSI", 1.0))
                        licenseMODULES.add(MODULE("Systèmes de management", 1.0))
                        licenseMODULES.add(MODULE("Environnement et hygiène", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d'expression et de communication",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                    } else if (sPECIALITY == "Raffinage et pétrochimie") {
                        licenseMODULES.add(MODULE("Chimie des solutions", 2.0))
                        licenseMODULES.add(MODULE("Chimie organique", 2.0))
                        licenseMODULES.add(MODULE("Thermodynamique chimique", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques", 2.0))
                        licenseMODULES.add(MODULE("Cinétique chimique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Introduction au raffinage et à la pétrochimie",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Notions des phénomènes de transfert", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d'expression et de communication",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Chimie des solutions", 1.1))
                        licenseMODULES.add(MODULE("TP Chimie organique", 1.1))
                        licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Cinétique chimique", 1.1))
                    }
                } else if (mySPECIALITY == "License thirdYEAR firstSEMSTER" || mySPECIALITY == "السّنة الثّالثة ليسانس، السّداسي الخامس") {
                    mySPECIALITY = "السّنة الثّالثة ليسانس، السّداسي الخامس،"
                    if (sPECIALITY == "Telecommunication") {
                        licenseMODULES.add(MODULE("Communications analogiques", 3.0))
                        licenseMODULES.add(MODULE("Traitement du signal", 2.0))
                        licenseMODULES.add(MODULE("Ondes et Propagation", 2.0))
                        licenseMODULES.add(MODULE("Systèmes et réseaux de télécommunication", 2.0))
                        licenseMODULES.add(MODULE("Calculateurs et interfaçage", 2.1))
                        licenseMODULES.add(MODULE("Téléphonie", 1.0))
                        licenseMODULES.add(MODULE("Supports de transmission", 1.0))
                        licenseMODULES.add(MODULE("Capteurs et mesures en télécommunications", 1.0))
                        licenseMODULES.add(MODULE("TP Ondes et Propagation", 1.1))
                        licenseMODULES.add(MODULE("TP Traitement du signal", 1.1))
                        licenseMODULES.add(MODULE("TP Communications analogiques", 1.1))
                    } else if (sPECIALITY == "Automatique") {
                        licenseMODULES.add(MODULE("Micro-processeurs et Microcontrôleurs", 3.0))
                        licenseMODULES.add(MODULE("Commande des systèmes linéaires", 2.0))
                        licenseMODULES.add(MODULE("Electronique de puissance", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et identification des systèmes",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Programmation en C++", 1.0))
                        licenseMODULES.add(MODULE("Normes et Certification", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Energies renouvelables : Production et stockage",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Anglais et Automatique", 1.0))
                        licenseMODULES.add(MODULE("TP Commande des systèmes linéaires", 1.1))
                        licenseMODULES.add(MODULE("TP Micro-processeurs et Microcontrôleurs", 1.1))
                        licenseMODULES.add(MODULE("TP Electronique de puissance", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Modélisation et identification des systèmes",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Programmation en C++", 1.1))
                    } else if (sPECIALITY == "Electromécanique") {
                        licenseMODULES.add(MODULE("Electronique de puissance", 2.0))
                        licenseMODULES.add(MODULE("Machines électriques", 2.0))
                        licenseMODULES.add(MODULE("Transfert thermique", 2.0))
                        licenseMODULES.add(MODULE("Systèmes Asservis", 2.0))
                        licenseMODULES.add(MODULE("Schémas et Appareillage", 2.1))
                        licenseMODULES.add(MODULE("Construction mécanique", 1.0))
                        licenseMODULES.add(MODULE("Production d\'énergie électrique", 1.0))
                        licenseMODULES.add(MODULE("Matériaux électrotechniques", 1.0))
                        licenseMODULES.add(MODULE("Dessin Technique", 1.0))
                        licenseMODULES.add(MODULE("TP Electronique de puissance", 1.1))
                        licenseMODULES.add(MODULE("TP Machines électriques", 1.1))
                        licenseMODULES.add(MODULE("TP Systèmes Asservis", 1.1))
                    } else if (sPECIALITY == "Maintenance industrielle") {
                        licenseMODULES.add(MODULE("Eléments de machines", 3.0))
                        licenseMODULES.add(MODULE("Organisation et méthode de la maintenance", 2.0))
                        licenseMODULES.add(MODULE("Electronique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Electrotechnique appliquée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Gestion de la Maintenance Assistée par Ordinateur",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Eléments de Transfert de chaleur", 1.0))
                        licenseMODULES.add(MODULE("Capteurs et Métrologie", 1.0))
                        licenseMODULES.add(MODULE("Environnement et développement durable", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP d’Electronique et d’Electrotechnique appliquées",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Dessin industriel et DAO", 1.1))
                        licenseMODULES.add(MODULE("TP Métrologie et assemblage", 1.1))
                    } else if (sPECIALITY == "Electronique") {
                        licenseMODULES.add(MODULE("Systèmes à Microprocesseurs", 3.0))
                        licenseMODULES.add(MODULE("Fonctions de l’Électronique", 2.0))
                        licenseMODULES.add(MODULE("Traitement du signal", 2.0))
                        licenseMODULES.add(MODULE("Réseaux informatiques locaux", 2.0))
                        licenseMODULES.add(MODULE("Travaux avant-Projet", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Technologie des composants électroniques 2",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Propagation d’ondes et Antennes", 1.0))
                        licenseMODULES.add(MODULE("Physique des semiconducteurs", 1.0))
                        licenseMODULES.add(MODULE("TP Systèmes à Microprocesseurs", 1.1))
                        licenseMODULES.add(MODULE("TP Fonctions de l’Électronique", 1.1))
                        licenseMODULES.add(MODULE("TP Signal et Réseaux locaux", 1.1))
                    } else if (sPECIALITY == "Electrotechnique") {
                        licenseMODULES.add(MODULE("Réseaux Electriques", 3.0))
                        licenseMODULES.add(MODULE("Electronique de Puissance", 2.0))
                        licenseMODULES.add(MODULE("Systèmes Asservis", 2.0))
                        licenseMODULES.add(MODULE("Théorie du Champ", 2.0))
                        licenseMODULES.add(MODULE("Schémas et Appareillage", 2.1))
                        licenseMODULES.add(MODULE("Capteurs et Métrologie", 1.0))
                        licenseMODULES.add(MODULE("Conception des systèmes électriques", 1.0))
                        licenseMODULES.add(MODULE("Logiciels de simulation", 1.0))
                        licenseMODULES.add(MODULE("TP Réseaux Electriques", 1.1))
                        licenseMODULES.add(MODULE("TP Electronique de Puissance", 1.1))
                        licenseMODULES.add(MODULE("TP Systèmes Asservis/ TP capteurs", 1.1))
                    } else if (sPECIALITY == "Génie biomédical") {
                        licenseMODULES.add(MODULE("Asservissements et régulation", 3.0))
                        licenseMODULES.add(MODULE("Electronique générale", 2.0))
                        licenseMODULES.add(MODULE("Traitement du signal", 2.0))
                        licenseMODULES.add(MODULE("Biophysique", 2.0))
                        licenseMODULES.add(MODULE("Informatique médicale", 2.1))
                        licenseMODULES.add(MODULE("Ondes et applications en Médical", 1.0))
                        licenseMODULES.add(MODULE("Terminologie et normes dans le biomédical", 1.0))
                        licenseMODULES.add(MODULE("Maintenance assistée par ordinateur", 1.0))
                        licenseMODULES.add(MODULE("TP Asservissements et régulation", 1.1))
                        licenseMODULES.add(MODULE("TP Electronique générale", 1.1))
                        licenseMODULES.add(MODULE("TP Biophysique et TP signal", 1.1))
                    } else if (sPECIALITY == "Aéronautique") {
                        licenseMODULES.add(MODULE("Aérodynamique", 2.0))
                        licenseMODULES.add(MODULE("Electronique numérique", 2.0))
                        licenseMODULES.add(MODULE("Structure aéronautique", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des Milieux Continus", 2.0))
                        licenseMODULES.add(MODULE("CAO des Aéronefs", 2.1))
                        licenseMODULES.add(MODULE("Mécanique du vol", 1.0))
                        licenseMODULES.add(MODULE("Aviation légère", 1.0))
                        licenseMODULES.add(MODULE("Transport aérien", 1.0))
                        licenseMODULES.add(MODULE("Environnement et développement durable", 1.0))
                        licenseMODULES.add(MODULE("TP Aérodynamique", 1.1))
                        licenseMODULES.add(MODULE("TP Electronique numérique", 1.1))
                        licenseMODULES.add(MODULE("TP Equipements et circuits aéronautiques", 1.1))
                    } else if (sPECIALITY == "Génie civil") {
                        licenseMODULES.add(MODULE("Résistance des Matériaux 2", 2.0))
                        licenseMODULES.add(MODULE("Béton Armé 1", 2.0))
                        licenseMODULES.add(MODULE("Charpente Métallique 1", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des Sols 2", 2.0))
                        licenseMODULES.add(MODULE("Dessin du BTP", 2.3))
                        licenseMODULES.add(MODULE("Matériaux de Construction 2", 1.0))
                        licenseMODULES.add(MODULE("Topographie 2", 1.0))
                        licenseMODULES.add(MODULE("Hydraulique générale", 1.0))
                        licenseMODULES.add(MODULE("Techniques et règles de construction", 1.0))
                        licenseMODULES.add(MODULE("TP Topographie", 1.1))
                        licenseMODULES.add(MODULE("TP Mécanique des sols 2", 1.1))
                        licenseMODULES.add(MODULE("TP Matériaux de Construction 2", 1.1))
                    } else if (sPECIALITY == "Génie climatique") {
                        licenseMODULES.add(MODULE("Thermodynamique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Installations de chauffage", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Installations sanitaires et Assainissement",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Installations électriques", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques appliquées (CAO+CFD)", 2.1))
                        licenseMODULES.add(MODULE("Ecoulement des fluides", 1.0))
                        licenseMODULES.add(MODULE("Combustion et réseaux de gaz", 1.0))
                        licenseMODULES.add(MODULE("Energies renouvelables", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique", 1.0))
                        licenseMODULES.add(MODULE("TP Chauffage /TP Ecoulement du fluide", 1.1))
                        licenseMODULES.add(MODULE("TP Installations électriques", 1.1))
                        licenseMODULES.add(MODULE("TP Installations sanitaires", 1.1))
                    } else if (sPECIALITY == "Energétique") {
                        licenseMODULES.add(MODULE("Mécanique des fluides 2", 3.0))
                        licenseMODULES.add(MODULE("Transfert de chaleur 1", 2.0))
                        licenseMODULES.add(MODULE("Turbomachines 1", 2.0))
                        licenseMODULES.add(MODULE("Conversion d'énergie", 2.0))
                        licenseMODULES.add(MODULE("Mesure et instrumentation", 2.1))
                        licenseMODULES.add(MODULE("Notion d’éléments de machines", 1.0))
                        licenseMODULES.add(MODULE("Régulation et asservissement", 1.0))
                        licenseMODULES.add(MODULE("Environnement et développement durable", 1.0))
                        licenseMODULES.add(MODULE("TP Transfert de chaleur", 1.1))
                        licenseMODULES.add(MODULE("TP Turbomachines 1", 1.1))
                        licenseMODULES.add(MODULE("TP Conversion d'énergie", 1.1))
                    } else if (sPECIALITY == "Construction mécanique") {
                        licenseMODULES.add(MODULE("Mécanique analytique", 3.0))
                        licenseMODULES.add(MODULE("Construction Mécanique 1", 2.0))
                        licenseMODULES.add(MODULE("Résistance des matériaux 2", 2.0))
                        licenseMODULES.add(MODULE("Elasticité", 2.0))
                        licenseMODULES.add(MODULE("Dessin Industriel", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Conception et Fabrication Assisté par Ordinateur",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Asservissement et Régulation", 1.0))
                        licenseMODULES.add(MODULE("Maintenance", 1.0))
                        licenseMODULES.add(MODULE("Environnement et développement durable", 1.0))
                        licenseMODULES.add(MODULE("TP Métrologie", 1.1))
                    } else if (sPECIALITY == "Génie des matériaux") {
                        licenseMODULES.add(MODULE("Transfert de chaleur et de masse", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des milieux continus", 2.0))
                        licenseMODULES.add(MODULE("Métaux et alliages", 2.0))
                        licenseMODULES.add(MODULE("Céramiques et verres", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes d’analyses et de caractérisations",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Liants et Bétons", 1.0))
                        licenseMODULES.add(MODULE("Assemblage des matériaux", 1.0))
                        licenseMODULES.add(MODULE("Normalisation", 1.0))
                        licenseMODULES.add(MODULE("Anglais scientifique", 1.0))
                        licenseMODULES.add(MODULE("TP Transfert de chaleur et de masse", 1.1))
                        licenseMODULES.add(MODULE("TP Métaux et alliages", 1.1))
                        licenseMODULES.add(MODULE("TP Céramiques, verres et bétons", 1.1))
                    } else if (sPECIALITY == "Hydraulique") {
                        licenseMODULES.add(MODULE("Hydraulique générale II", 2.0))
                        licenseMODULES.add(MODULE("Hydrologie II", 2.0))
                        licenseMODULES.add(MODULE("Ouvrages hydrauliques", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des sols", 2.0))
                        licenseMODULES.add(MODULE("Traitement et épuration des eaux", 2.1))
                        licenseMODULES.add(MODULE("Hydrogéologie", 1.0))
                        licenseMODULES.add(MODULE("Irrigation", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Notions de Système d’informations géographiques",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Législation des eaux", 1.0))
                        licenseMODULES.add(MODULE("TP Topographie", 1.1))
                        licenseMODULES.add(MODULE("TP Mécanique des sols", 1.1))
                        licenseMODULES.add(MODULE("TP Hydraulique", 1.1))
                    } else if (sPECIALITY == "Métallurgie") {
                        licenseMODULES.add(MODULE("Métallurgie physique 1", 3.0))
                        licenseMODULES.add(MODULE("Transfert de chaleur et de masse", 2.0))
                        licenseMODULES.add(MODULE("Élaboration des métaux ferreux", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Comportements mécanique des métaux et alliages",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes d’analyses et de caractérisations",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matériaux non métalliques", 1.0))
                        licenseMODULES.add(MODULE("Normalisation en métallurgie", 1.0))
                        licenseMODULES.add(MODULE("Électricité industrielle", 1.0))
                        licenseMODULES.add(MODULE("Législation des eaux", 1.0))
                        licenseMODULES.add(MODULE("TP Transfert de chaleur et de masse", 1.1))
                        licenseMODULES.add(MODULE("TP Métallurgie physique 1", 1.1))
                    } else if (sPECIALITY == "Travaux publics") {
                        licenseMODULES.add(MODULE("Poutres et treillis", 2.0))
                        licenseMODULES.add(MODULE("Béton armé et précontraint", 2.0))
                        licenseMODULES.add(MODULE("Structures métalliques", 2.0))
                        licenseMODULES.add(MODULE("Routes 1", 2.0))
                        licenseMODULES.add(MODULE("Topographie appliquée", 2.1))
                        licenseMODULES.add(MODULE("Dessin technique appliqué", 2.3))
                        licenseMODULES.add(MODULE("Matériaux routiers", 1.0))
                        licenseMODULES.add(MODULE("Infrastructures ferroviaires", 1.0))
                        licenseMODULES.add(MODULE("Infrastructures souterraines", 1.0))
                        licenseMODULES.add(MODULE("Engins de travaux publics", 1.0))
                        licenseMODULES.add(MODULE("TP Géotechnique routière", 1.1))
                    } else if (sPECIALITY == "Génie des procédés") {
                        licenseMODULES.add(MODULE("Transfert de Chaleur", 2.0))
                        licenseMODULES.add(MODULE("Transfert de Matière", 2.0))
                        licenseMODULES.add(MODULE("Electrochimie", 2.0))
                        licenseMODULES.add(MODULE("Techniques d’analyse", 2.1))
                        licenseMODULES.add(MODULE("Transfert de Quantité de Mouvement", 1.0))
                        licenseMODULES.add(MODULE("Instrumentation  capteurs", 1.0))
                        licenseMODULES.add(MODULE("Cinétique et catalyse homogène", 1.0))
                        licenseMODULES.add(MODULE("procédés pharmaceutiques", 1.0))
                        licenseMODULES.add(MODULE("Procédés agroalimentaires", 1.0))
                        licenseMODULES.add(MODULE("Pollution : Air, eau, sol", 1.0))
                        licenseMODULES.add(MODULE("TP Chimie Physique 1", 1.1))
                        licenseMODULES.add(MODULE("TP Génie chimique 1", 1.1))
                        licenseMODULES.add(MODULE("TP Simulateurs de procédés", 1.1))
                    } else if (sPECIALITY == "Exploitation des mines") {
                        licenseMODULES.add(MODULE("Exploitation à ciel ouvert", 3.0))
                        licenseMODULES.add(MODULE("Exploitation souterraine", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des roches", 2.0))
                        licenseMODULES.add(MODULE("Minéralurgie", 2.0))
                        licenseMODULES.add(MODULE("Topographie", 2.1))
                        licenseMODULES.add(MODULE("Géologie minière", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Environnement minier : normes et législation",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Bonification des granulats", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique", 1.0))
                        licenseMODULES.add(MODULE("TP Mécanique des roches", 1.1))
                        licenseMODULES.add(MODULE("TP Outils informatiques miniers", 1.1))
                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Sécurité incendie", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Sécurité des installations et des équipements industriels",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Toxicologie industrielle", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes qualitatives d’analyse des risques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Acoustique industrielle", 1.0))
                        licenseMODULES.add(MODULE("Protection environnement", 1.0))
                        licenseMODULES.add(MODULE("Système de management intégré en HSI", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Analyse des données et Outils statistiques",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Développement durable", 1.0))
                        licenseMODULES.add(MODULE("Notions d’écologie", 1.0))
                        licenseMODULES.add(MODULE("Etude de cas en HSI", 1.0))
                    } else if (sPECIALITY == "Raffinage et pétrochimie") {
                        licenseMODULES.add(MODULE("Pétrochimie 1", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Phénomènes de surface et catalyse hétérogène",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Opérations unitaires", 2.0))
                        licenseMODULES.add(MODULE("Phénomènes de transfert", 2.0))
                        licenseMODULES.add(MODULE("CAO et usine virtuelle", 2.1))
                        licenseMODULES.add(MODULE("Electrochimie", 1.0))
                        licenseMODULES.add(MODULE("Energie fossile et pollution", 1.0))
                        licenseMODULES.add(MODULE("Economie et Management", 1.0))
                        licenseMODULES.add(MODULE("Instrumentations-capteurs", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Chimie physique (électrochimie, surface)",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP opérations unitaires", 1.1))
                        licenseMODULES.add(MODULE("TP Techniques de traitement des eaux", 1.1))
                    }
                } else if (mySPECIALITY == "License thirdYEAR secondSEMSTER" || mySPECIALITY == "السّنة الثّالثة ليسانس، السّداسي السّادس") {
                    mySPECIALITY = "السّنة الثّالثة ليسانس، السّداسي السّادس،"
                    if (sPECIALITY == "Telecommunication") {
                        licenseMODULES.add(MODULE("Communications numériques", 3.0))
                        licenseMODULES.add(MODULE("Antennes et Lignes de transmissions", 2.0))
                        licenseMODULES.add(MODULE("Réseaux informatiques locaux", 2.0))
                        licenseMODULES.add(MODULE("Codage et Théorie de l’information", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Optoélectronique", 1.0))
                        licenseMODULES.add(MODULE("Sécurité de l’information", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Communications numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Antennes Lignes de transmissions", 1.1))
                        licenseMODULES.add(MODULE("TP Réseaux informatiques locaux", 1.1))
                    } else if (sPECIALITY == "Automatique") {
                        licenseMODULES.add(MODULE("Automates programmables industriels (API)", 3.0))
                        licenseMODULES.add(MODULE("Systèmes Asservis échantillonnés", 2.0))
                        licenseMODULES.add(MODULE("Actionneurs", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Capteurs et chaines de mesure", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Bus de communications et réseaux industriels",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Installations électriques en automatique", 1.0))
                        licenseMODULES.add(MODULE("Maintenance et fiabilité", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Capteurs et Actionneurs", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Automates programmables industriels (API)",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Bus de communications et réseaux industriels",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Electromécanique") {
                        licenseMODULES.add(MODULE("Régulation industrielle", 2.0))
                        licenseMODULES.add(MODULE("Commande des entrainements ELM", 2.0))
                        licenseMODULES.add(MODULE("Automatismes et informatique industrielle", 2.0))
                        licenseMODULES.add(MODULE("Turbomachines", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Capteurs et conditionneurs", 1.0))
                        licenseMODULES.add(MODULE("Maintenance des systèmes ELM", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Introduction au Moteur à combustion interne",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Régulation industrielle", 1.1))
                        licenseMODULES.add(MODULE("TP Commande des entrainements ELM", 1.1))
                        licenseMODULES.add(MODULE("TP Capteurs et conditionneurs", 1.1))
                    } else if (sPECIALITY == "Maintenance industrielle") {
                        licenseMODULES.add(
                            MODULE(
                                "Technologie des machines thermiques et hydrauliques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Dynamique des structures", 2.0))
                        licenseMODULES.add(MODULE("Traitement de signal", 2.0))
                        licenseMODULES.add(MODULE("Systèmes asservis et Régulation", 2.0))
                        licenseMODULES.add(MODULE("Moteur à combustion interne", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Fiabilité", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Outils de maintenance préventive conditionnelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Robotique industrielle", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Réparations et interventions/TP MCI", 1.1))
                    } else if (sPECIALITY == "Electronique") {
                        licenseMODULES.add(MODULE("Asservissements et régulation", 3.0))
                        licenseMODULES.add(MODULE("Capteurs et Instrumentation", 2.0))
                        licenseMODULES.add(MODULE("Electronique de puissance", 2.0))
                        licenseMODULES.add(MODULE("Electronique des impulsions", 2.0))
                        licenseMODULES.add(MODULE("Dispositifs Optoélectroniques", 2.2))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et Gestion d’Entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Asservissements et régulation", 1.1))
                        licenseMODULES.add(MODULE("TP Capteurs et Instrumentation", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Electronique de puissance et impulsions",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Electrotechnique") {
                        licenseMODULES.add(MODULE("Commande des machines", 3.0))
                        licenseMODULES.add(MODULE("Régulation industrielle", 2.0))
                        licenseMODULES.add(MODULE("Automatismes Industriels", 2.0))
                        licenseMODULES.add(MODULE("Matériaux et introduction à la HT", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Protection des réseaux Electriques", 1.0))
                        licenseMODULES.add(MODULE("Maintenance Industrielle", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et Gestion d’Entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Commande des machines", 1.1))
                        licenseMODULES.add(MODULE("TP Régulation industrielle", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Automatismes/ TP Matériaux et introduction à la HT",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie biomédical") {
                        licenseMODULES.add(MODULE("Chaîne d'acquisition numérique", 3.0))
                        licenseMODULES.add(MODULE("Biomatériaux", 2.0))
                        licenseMODULES.add(MODULE("Instrumentation médicale", 2.0))
                        licenseMODULES.add(MODULE("Traitement des signaux physiologiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet de Fin de Cycle (Milieu hospitalier)",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Sécurité des appareils en Biomédical", 1.0))
                        licenseMODULES.add(MODULE("Eléments des systèmes robotisés", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et Gestion d’Entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Maquettes", 1.1))
                        licenseMODULES.add(MODULE("TP Chaîne d'acquisition numérique", 1.1))
                        licenseMODULES.add(MODULE("TP Instrumentation et signal", 1.1))
                    } else if (sPECIALITY == "Aéronautique") {
                        licenseMODULES.add(MODULE("Propulsion", 2.0))
                        licenseMODULES.add(MODULE("Moteurs avions", 2.0))
                        licenseMODULES.add(MODULE("Maintenance aéronautique", 2.0))
                        licenseMODULES.add(MODULE("Opérations aériennes", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Visite sur site", 2.3))
                        licenseMODULES.add(MODULE("Circulation et contrôle aériens", 1.0))
                        licenseMODULES.add(MODULE("Infrastructures aéroportuaires", 1.0))
                        licenseMODULES.add(MODULE("Facteurs humains en aéronautique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Propulsion et moteurs avions", 1.1))
                    } else if (sPECIALITY == "Génie civil") {
                        licenseMODULES.add(MODULE("Béton Armé 2", 3.0))
                        licenseMODULES.add(MODULE("Calcul des Structures", 2.0))
                        licenseMODULES.add(MODULE("Charpente Métallique 2", 2.0))
                        licenseMODULES.add(MODULE("Fondations et ouvrages Géotechniques", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Calcul assisté par ordinateur", 2.3))
                        licenseMODULES.add(MODULE("Métré et Estimation des Prix", 1.0))
                        licenseMODULES.add(MODULE("Voiries et Réseaux Divers", 1.0))
                        licenseMODULES.add(MODULE("Organisation des chantiers", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et Gestion de l\'entreprise",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Génie climatique") {
                        licenseMODULES.add(
                            MODULE(
                                "Installations de Climatisation et conditionnement d' air",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Installations Frigorifiques", 2.0))
                        licenseMODULES.add(MODULE("Régulation des installations", 2.0))
                        licenseMODULES.add(MODULE("Topographie", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Hydraulique Urbaine", 1.0))
                        licenseMODULES.add(MODULE("Acoustique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Régulation", 1.1))
                        licenseMODULES.add(MODULE("TP Topographie", 1.1))
                        licenseMODULES.add(MODULE("TP Climatisation et froid", 1.1))
                    } else if (sPECIALITY == "Energétique") {
                        licenseMODULES.add(MODULE("Turbomachines 2", 3.0))
                        licenseMODULES.add(MODULE("Moteurs à combustion interne", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Machines Frigorifiques et pompes à chaleur",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Transfert de chaleur 2", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Energies renouvelables", 1.0))
                        licenseMODULES.add(MODULE("Cryogénie", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Machines Frigorifiques et pompes à chaleur",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Moteurs à combustion interne", 1.1))
                        licenseMODULES.add(MODULE("TP régulation et asservissement", 1.1))
                    } else if (sPECIALITY == "Construction mécanique") {
                        licenseMODULES.add(MODULE("Construction Mécanique 2", 3.0))
                        licenseMODULES.add(MODULE("Théorie des mécanismes", 2.0))
                        licenseMODULES.add(MODULE("Transfert thermique", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des structures", 2.0))
                        licenseMODULES.add(MODULE("Moteur à combustion interne", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Systèmes hydrauliques et pneumatiques", 1.0))
                        licenseMODULES.add(MODULE("Matériaux non métalliques", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Transferts Thermiques", 1.1))
                    } else if (sPECIALITY == "Génie des matériaux") {
                        licenseMODULES.add(MODULE("Polymères", 2.0))
                        licenseMODULES.add(MODULE("Matériaux composites", 2.0))
                        licenseMODULES.add(MODULE("Rhéologie des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Dégradation et protection des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Le bois et les mousses", 1.0))
                        licenseMODULES.add(MODULE("Initiation aux biomatériaux", 1.0))
                        licenseMODULES.add(MODULE("Impact des Matériaux sur l’Environnement", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Polymères", 1.1))
                        licenseMODULES.add(MODULE("TP Matériaux composites", 1.1))
                        licenseMODULES.add(MODULE("TP Corrosion", 1.1))
                    } else if (sPECIALITY == "Hydraulique") {
                        licenseMODULES.add(MODULE("Aménagements Hydrauliques", 2.0))
                        licenseMODULES.add(MODULE("Alimentation en eau potable", 2.0))
                        licenseMODULES.add(MODULE("Assainissement", 2.0))
                        licenseMODULES.add(MODULE("Pompes et stations de pompage", 2.0))
                        licenseMODULES.add(MODULE("Notions de béton armé", 2.1))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Matériaux de construction", 1.0))
                        licenseMODULES.add(MODULE("Gestion des ressources hydriques", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Technologie des conduites et équipements des réseaux",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Projet Professionnel et gestion d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Hydro-informatique", 1.1))
                    } else if (sPECIALITY == "Métallurgie") {
                        licenseMODULES.add(MODULE("Métallurgie physique 2", 3.0))
                        licenseMODULES.add(MODULE("Corrosion et protection des métaux", 3.0))
                        licenseMODULES.add(MODULE("Aciers et alliages spéciaux", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "TP traitements thermi- ques et thermochimique des métaux",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Procédés de mise en forme des métaux.", 1.0))
                        licenseMODULES.add(MODULE("Mesures et instrumentations", 1.0))
                        licenseMODULES.add(MODULE("Sécurité et environnement", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Procédés de mise en forme des métaux.", 1.1))
                    } else if (sPECIALITY == "Travaux publics") {
                        licenseMODULES.add(MODULE("Routes 2", 3.0))
                        licenseMODULES.add(MODULE("Ponts", 3.0))
                        licenseMODULES.add(MODULE("Fondations et ouvrages en terre", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("T.P. Matériaux routiers", 2.3))
                        licenseMODULES.add(MODULE("Assainissement routier", 1.0))
                        licenseMODULES.add(MODULE("Systèmes d’information géographique", 1.0))
                        licenseMODULES.add(MODULE("Infrastructures aéroportuaires", 1.0))
                        licenseMODULES.add(MODULE("Infrastructures maritimes", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Génie des procédés") {
                        licenseMODULES.add(MODULE("Opérations unitaires", 3.0))
                        licenseMODULES.add(MODULE("Thermodynamique des équilibres", 2.0))
                        licenseMODULES.add(MODULE("Réacteurs homogènes", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Phénomènes de surface et catalyse hétérogène",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Bilans macroscopiques", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Procédés cryogéniques", 1.0))
                        licenseMODULES.add(MODULE("Corrosion", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP chimie physique 2 et génie chimique 2", 1.1))
                    } else if (sPECIALITY == "Exploitation des mines") {
                        licenseMODULES.add(MODULE("Géotechnique", 3.0))
                        licenseMODULES.add(MODULE("Aérage des mines", 2.0))
                        licenseMODULES.add(MODULE("Equipements miniers", 2.0))
                        licenseMODULES.add(MODULE("Creusement des ouvrages souterrains", 2.0))
                        licenseMODULES.add(MODULE("Electrification des mines", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Réhabilitation des sites miniers", 1.0))
                        licenseMODULES.add(MODULE("Sécurité et Environnement", 1.0))
                        licenseMODULES.add(MODULE("Economie minière", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Assurance et tarification des risques", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes quantitatives d’analyse des risques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Etudes de dangers et études d’impacts", 2.0))
                        licenseMODULES.add(MODULE("Traitement des déchets", 2.0))
                        licenseMODULES.add(MODULE("Gestion de crise", 2.1))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("Ergonomie industrielle", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Pathologies professionne- lles et accidents de travail",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Notions de simulation de crise", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Raffinage et pétrochimie") {
                        licenseMODULES.add(MODULE("Pétrochimie 2", 2.0))
                        licenseMODULES.add(MODULE("Raffinage du pétrole", 2.0))
                        licenseMODULES.add(MODULE("Carburants et biocarburants", 2.0))
                        licenseMODULES.add(MODULE("Chimie des polymères", 2.0))
                        licenseMODULES.add(MODULE("Projet de Fin de Cycle", 2.3))
                        licenseMODULES.add(MODULE("TP : pétrochimie, raffinage, analyse,", 2.3))
                        licenseMODULES.add(MODULE("Production des huiles de base", 1.0))
                        licenseMODULES.add(MODULE("Technologie du gaz", 1.0))
                        licenseMODULES.add(MODULE("Corrosion", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Projet professionnel et gestion d'entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Méthodes d'analyse des produits pétroliers",
                                1.1
                            )
                        )
                    }
                } else if (mySPECIALITY == "Master firstYEAR firstSEMSTER" || mySPECIALITY == "السّنة الأولى ماستر، السّداسي الأوّل") {
                    mySPECIALITY = "السّنة الأولى ماستر، السّداسي الأوّل،"
                    if (sPECIALITY == "Réseaux & Telecommunication") {
                        licenseMODULES.add(MODULE("Communications numériques avancées", 3.0))
                        licenseMODULES.add(MODULE("Routage IP", 2.0))
                        licenseMODULES.add(MODULE("Propagation et Antennes", 2.0))
                        licenseMODULES.add(MODULE("Traitement avancé du signal", 2.0))
                        licenseMODULES.add(MODULE("Programmation orientée objets en C++", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( Système Linux )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Normes et Protocoles )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Communications numériques avancées", 1.1))
                        licenseMODULES.add(MODULE("TP Routage IP", 1.1))
                        licenseMODULES.add(MODULE("TP Traitement avancé du signal", 1.1))
                    } else if (sPECIALITY == "Construction mécanique") {
                        licenseMODULES.add(MODULE("Mécanique des milieux continus", 3.0))
                        licenseMODULES.add(MODULE("Résistance des matériaux Avancée", 2.0))
                        licenseMODULES.add(MODULE("Moteurs à combustion interne", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des fluides appliquée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques de fabrication Conventionnelles et avancées",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Automatisation des systèmes industriels", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix ( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix ( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP MDF/RDM", 1.1))
                    } else if (sPECIALITY == "Systèmes & Telecommunication") {
                        licenseMODULES.add(MODULE("Communications numériques avancées", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Signaux aléatoires et Processus stochastiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Radiocommunication", 2.0))
                        licenseMODULES.add(MODULE("Circuits programmables FPGA", 2.0))
                        licenseMODULES.add(MODULE("Programmation orientée objets en C++", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Compatibilité électromagnétique )",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Normes et Protocoles )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Communications numériques avancées", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Signaux aléatoires et Processus stochastiques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Circuits programmables FPGA", 1.1))
                    } else if (sPECIALITY == "Valorisation des ressources minérales") {
                        licenseMODULES.add(MODULE("Préparation des minerais", 3.0))
                        licenseMODULES.add(MODULE("Mines et carrières", 2.0))
                        licenseMODULES.add(MODULE("Classification hydraulique et pneumatique", 2.0))
                        licenseMODULES.add(MODULE("Séparation gravimétrique", 2.0))
                        licenseMODULES.add(MODULE("Minéralogie appliquée", 2.0))
                        licenseMODULES.add(MODULE("Caractérisation des minerais", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP Préparation des minerais", 1.1))
                        licenseMODULES.add(MODULE("TP Séparation gravimétrique", 1.1))
                    } else if (sPECIALITY == "Automatique et Informatique industrielle" || sPECIALITY == "Automatique et Systèmes") {
                        licenseMODULES.add(MODULE("Systèmes Linéaires Multivariables", 3.0))
                        licenseMODULES.add(MODULE("Traitement du signal", 2.0))
                        licenseMODULES.add(MODULE("Association convertisseurs-machines", 2.0))
                        licenseMODULES.add(MODULE("Optimisation", 2.0))
                        if (sPECIALITY == "Automatique et Informatique industrielle") {
                            licenseMODULES.add(
                                MODULE(
                                    "Réseaux et protocoles de communication industrielle",
                                    2.1
                                )
                            )
                        } else if (sPECIALITY == "Automatique et Systèmes") {
                            licenseMODULES.add(MODULE("Techniques d’Identification", 2.1))
                        }
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Systèmes Linéaires Multivariables", 1.1))
                        licenseMODULES.add(MODULE("TP Traitement du signal / TP Optimisation", 1.1))
                        licenseMODULES.add(MODULE("TP Association convertisseurs-machines", 1.1))
                    } else if (sPECIALITY == "Commandes Electriques") {
                        licenseMODULES.add(
                            MODULE(
                                "Réseaux de transport et de distribution d’énergie électrique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Electronique de puissance avancée", 2.0))
                        licenseMODULES.add(MODULE("Machines électriques approfondies", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes numériques appliquées et optimisation",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("μ-processeurs et μ-contrôleurs", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP μ-processeurs et μ-contrôleurs", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Réseaux de transport et de distribution d’énergie électrique",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Electronique de puissance avancée", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Méthodes numériques appliquées et optimisation",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Machines électriques approfondies", 1.1))
                    } else if (sPECIALITY == "Electromécanique") {
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et simulation des machines électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Electronique de puissance avancée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Mécanismes industriels et transmission de puissance",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Machines hydrauliques et pneumatiques", 2.0))
                        licenseMODULES.add(MODULE("Réseaux électriques industriels", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Modélisation et simulation des machines électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Electronique de puissance avancée", 1.1))
                        licenseMODULES.add(MODULE("TP Réseaux électriques industriels", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Mécanismes industriels et transmission de puissance",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Machines hydrauliques et pneumatiques", 1.1))
                    } else if (sPECIALITY == "Electronique des systèmes embarqués") {
                        licenseMODULES.add(
                            MODULE(
                                "Conception des systèmes à microprocesseurs",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Electronique numérique avancée : FPGA et VHDL",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Traitement avancé du signal", 2.0))
                        licenseMODULES.add(MODULE("Systèmes asservis numériques", 2.0))
                        licenseMODULES.add(MODULE("Programmation orientée objet en C++", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Conception des systèmes à microprocesseurs",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP FPGA et VHDL", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Traitement avancé du signal /TP Systèmes asservis numériques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Energétique") {
                        licenseMODULES.add(MODULE("Mécanique des fluides approfondie", 3.0))
                        licenseMODULES.add(MODULE("Machines thermiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transfert de chaleur et de masse approfondi",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Méthodes numériques approfondies", 2.0))
                        licenseMODULES.add(MODULE("Instrumentation et mesures", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Machines thermiques", 1.1))
                        licenseMODULES.add(MODULE("TP MDF", 1.1))
                    } else if (sPECIALITY == "Energies Renouvelables en Mécanique") {
                        licenseMODULES.add(MODULE("Mécanique des Fluides Approfondie", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transferts Thermiques Approfondis et Phénomènes de transport",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Notions de météorologie et Gisements renouvelables",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Méthodes numériques appliquées", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Energies Renouvelables et Enjeux énergétiques",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Gisements renouvelables et météorologie", 2.3))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Méthodes numériques appliquées", 1.1))
                    } else if (sPECIALITY == "Fabrication Mécanique & Productique") {
                        licenseMODULES.add(MODULE("Mécanique des milieux continus", 2.0))
                        licenseMODULES.add(MODULE("Matériaux", 2.0))
                        licenseMODULES.add(MODULE("Coupe des métaux 1", 2.0))
                        licenseMODULES.add(MODULE("Procédés de mise en forme", 2.0))
                        licenseMODULES.add(MODULE("Robotique industrielle", 2.1))
                        licenseMODULES.add(MODULE("Fabrication mécanique", 2.3))
                        licenseMODULES.add(MODULE("Machines Outils", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Métrologie", 1.1))
                    } else if (sPECIALITY == "Génie Alimentaire") {
                        licenseMODULES.add(MODULE("Opérations unitaires du génie alimentaire", 3.0))
                        licenseMODULES.add(MODULE("Process de transformations des aliments 1", 2.0))
                        licenseMODULES.add(MODULE("Biochimie et chimie des aliments", 2.0))
                        licenseMODULES.add(MODULE("Rhéologie des Systèmes Alimentaires", 2.0))
                        licenseMODULES.add(MODULE("Sécurité sanitaire des aliments", 2.0))
                        licenseMODULES.add(MODULE("Biochimie", 2.3))
                        licenseMODULES.add(MODULE("Analyse instrumentale dans les IAA", 2.3))
                        licenseMODULES.add(MODULE("Statistiques appliquées", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                    } else if (sPECIALITY == "Génie Chimique") {
                        licenseMODULES.add(
                            MODULE(
                                "Opérations unitaires I (Absorption-Strippage_ Extraction- mélangeage)",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Génie de la réaction I : réacteurs non-idéaux et bioréacteurs",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Thermodynamique technique", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transfert thermique et Echangeurs de chaleur",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Simulateurs en Génie des procédés", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Opérations unitaires I", 1.1))
                        licenseMODULES.add(MODULE("TP Génie de la réaction I", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Transfert thermique et Echangeurs de chaleur",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie Climatique") {
                        licenseMODULES.add(MODULE("Chauffage des Bâtiments d’Habitation", 2.0))
                        licenseMODULES.add(MODULE("Ventilation et Conditionnement de l’Air I", 2.0))
                        licenseMODULES.add(MODULE("Réseaux Thermiques", 2.0))
                        licenseMODULES.add(MODULE("Technologie de froid industriel", 2.0))
                        licenseMODULES.add(MODULE("Calcul & modélisation Numériques", 2.1))
                        licenseMODULES.add(MODULE("Electrotechnique appliquée", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Electrotechnique", 1.1))
                        licenseMODULES.add(MODULE("TP Machines frigorifiques", 1.1))
                        licenseMODULES.add(MODULE("TP DAO des réseaux climatiques", 1.1))
                    } else if (sPECIALITY == "Génie de l’Environnement") {
                        licenseMODULES.add(MODULE("Traitement des eaux Potables", 3.0))
                        licenseMODULES.add(MODULE("Chimie des milieux aquatiques", 2.0))
                        licenseMODULES.add(MODULE("Génie des Réacteurs Hétérogènes", 2.0))
                        licenseMODULES.add(MODULE("Biochimie", 2.0))
                        licenseMODULES.add(MODULE("Adsorption technique", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Travaux pratiques de Génie de l’environnement 1",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Ecologie Appliquée", 1.2))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                    } else if (sPECIALITY == "Génie de Raffinage") {
                        licenseMODULES.add(MODULE("Du brut aux produits", 3.0))
                        licenseMODULES.add(MODULE("Génie de la réaction chimique avancée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d’analyse du pétrole et des fractions pétrolières",
                                2.2
                            )
                        )
                        licenseMODULES.add(MODULE("Génie des séparations", 2.0))
                        licenseMODULES.add(MODULE("TP chimie du pétrole", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "TP Techniques d’analyse du pétrole et des fractions pétrolières",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Génie des séparations", 1.1))
                    } else if (sPECIALITY == "Génie de Raffinage") {
                        licenseMODULES.add(MODULE("Du brut aux produits", 3.0))
                        licenseMODULES.add(MODULE("Génie de la réaction chimique avancée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d’analyse du pétrole et des fractions pétrolières",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Génie des séparations", 2.0))
                        licenseMODULES.add(MODULE("TP chimie du pétrole", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "TP Techniques d’analyse du pétrole et des fractions pétrolières",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Génie des séparations", 1.1))
                    } else if (sPECIALITY == "Génie des Matériaux") {
                        licenseMODULES.add(
                            MODULE(
                                "Comportement mécanique des matériaux métalliques",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Thermodynamique et diagrammes d’équilibre", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Structure cristallines et Défauts ponctuels",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Méthodes des éléments finis", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Elaboration et caractérisation des matériaux céramiques",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Méthodes des éléments finis", 2.3))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Essais Mécaniques", 1.1))
                    } else if (sPECIALITY == "Génie des Procédés de l’environnement") {
                        licenseMODULES.add(
                            MODULE(
                                "Opérations Unitaires Fluide-Fluide (extraction, distillation, absorption et strippage)",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Chimie des Eaux", 2.0))
                        licenseMODULES.add(MODULE("Pollution Atmosphérique", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transfert thermique et Echangeurs de chaleur",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Simulateurs en génie des procédés", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Chimie des Eaux", 1.1))
                        licenseMODULES.add(MODULE("TP Opérations Unitaires (Fluide-Fluide)", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Transfert thermique et Echangeurs de Chaleur",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie des procédés Cryogéniques") {
                        licenseMODULES.add(MODULE("Dynamique des Fluides Incompressibles", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de réfrigération et de liquéfaction des gaz",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Transfert Thermique II", 2.0))
                        licenseMODULES.add(MODULE("Procédés de Séparation des gaz", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Applications des méthodes numériques aux phénomènes de transfert",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP génie des procédés I", 2.3))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "Evaluation technicoéconomique des procédés",
                                1.3
                            )
                        )
                        licenseMODULES.add(MODULE("Sécurité Spécifique à la cryogénie", 1.0))
                        licenseMODULES.add(MODULE("Méthodes d’analyses avancées", 1.0))
                    } else if (sPECIALITY == "Génie Pétrochimie") {
                        licenseMODULES.add(MODULE("Thermodynamique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Phénomènes de transfert II", 2.0))
                        licenseMODULES.add(MODULE("Procédés de séparation", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Production de matières premières et de monomères",
                                2.2
                            )
                        )
                        licenseMODULES.add(MODULE("TP chimie du pétrole", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "TP Production de matières premières et de monomères",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Chimie du pétrole et du gaz", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP procédés de séparation", 1.1))
                    } else if (sPECIALITY == "Génie Pharmaceutique") {
                        licenseMODULES.add(
                            MODULE(
                                "Opérations Unitaires Fluide- Fluide (extraction, distillation, absorption et strippage)",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Chimie pharmaceutique I : Structure et Conception",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Transfert thermique et Echangeurs de chaleur",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Formes galéniques", 2.0))
                        licenseMODULES.add(MODULE("Pharmacologie générale", 1.0))
                        licenseMODULES.add(MODULE("Pharmacognosie", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Chimie pharmaceutique I", 1.1))
                        licenseMODULES.add(MODULE("TP Opérations Unitaires Fluide-Fluide", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Transfert thermique et Echangeurs de Chaleur",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Géotechnique") {
                        licenseMODULES.add(MODULE("Mécanique des milieux continus", 3.0))
                        licenseMODULES.add(MODULE("Mécanique des sols avancée", 2.0))
                        licenseMODULES.add(MODULE("Fondations", 2.0))
                        licenseMODULES.add(MODULE("Talus et soutènements", 2.0))
                        licenseMODULES.add(MODULE("Géotechnique routière", 2.1))
                        licenseMODULES.add(MODULE("Méthode des différences finies", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Essais géotechniques 1", 1.1))
                    } else if (sPECIALITY == "Hydraulique Urbaine") {
                        licenseMODULES.add(MODULE("Hydraulique appliquée", 3.0))
                        licenseMODULES.add(MODULE("Analyse et modélisation hydrologique", 2.0))
                        licenseMODULES.add(MODULE("Les écoulements à surface libre", 2.0))
                        licenseMODULES.add(MODULE("Les écoulements en charge", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Systèmes d’Informations Géographiques (SIG)",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Hydraulique numérique", 2.3))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Assainissement II", 1.1))
                    } else if (sPECIALITY == "Ingénierie des Matériaux et des Surfaces") {
                        licenseMODULES.add(MODULE("Transformation de phases", 2.0))
                        licenseMODULES.add(MODULE("Matériaux ferreux et non ferreux", 2.0))
                        licenseMODULES.add(MODULE("Propriétés mécaniques des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Mise en forme des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques appliquées", 2.1))
                        licenseMODULES.add(MODULE("Ingénierie de surface", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Propriétés mécaniques des matériaux", 1.1))
                        licenseMODULES.add(MODULE("TP Ingénierie des surfaces", 1.1))
                        licenseMODULES.add(MODULE("TP Mise en forme", 1.1))
                    } else if (sPECIALITY == "Ingénierie & Gestion des Eaux") {
                        licenseMODULES.add(MODULE("Réacteurs poly-phasiques", 3.0))
                        licenseMODULES.add(MODULE("Phénomènes de transport avancés", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Opérations unitaires de traitements des eaux (I)",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Hydrogéologie environnementale", 2.0))
                        licenseMODULES.add(MODULE("Microbiologie fondamentale et appliquée", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Analyse de l’eau et Techniques de Prélèvements et d’échantillonnage",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ressources en eau, pollution et Eco-Toxicologie",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Gestion et politique de l’eau/Droit de l’eau",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP : Traitement des eaux", 1.1))
                    } else if (sPECIALITY == "Installations énergétiques & turbomachines") {
                        licenseMODULES.add(MODULE("Mécanique des fluides approfondie", 3.0))
                        licenseMODULES.add(MODULE("Installations énergétiques 1", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transfert de chaleur et de masse approfondi",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Méthodes numériques approfondies", 2.0))
                        licenseMODULES.add(MODULE("Instrumentation et mesures", 2.1))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Installations énergétiques 1", 1.1))
                        licenseMODULES.add(MODULE("TP Mécanique des fluides", 1.1))
                    } else if (sPECIALITY == "Machines Electriques") {
                        licenseMODULES.add(
                            MODULE(
                                "Réseaux de transport et de distribution d’énergie électrique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Electronique de puissance avancée", 2.0))
                        licenseMODULES.add(MODULE("Machines électriques approfondies", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes numériques appliquées et optimisation",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("μ-processeurs et μ-contrôleurs", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP μ-processeurs et μ-contrôleurs", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Réseaux de transport et de distribution d’énergie électrique",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Electronique de puissance avancée", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Méthodes numériques appliquées et optimisation",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP machines électriques approfondies", 1.1))
                    } else if (sPECIALITY == "Maintenance Industrielle") {
                        licenseMODULES.add(MODULE("Stratégie de maintenance", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des structures", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des milieux continus", 2.0))
                        licenseMODULES.add(MODULE("Thermodynamique Appliquée", 2.0))
                        licenseMODULES.add(MODULE("Introduction aux Matériaux", 2.1))
                        licenseMODULES.add(MODULE("Traitement du signal", 2.1))
                        licenseMODULES.add(MODULE("Méthodes statistiques et échantillonnage", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Dynamique des structures", 1.1))
                    } else if (sPECIALITY == "Matériaux en Génie Civil") {
                        licenseMODULES.add(MODULE("Ouvrages en béton armé", 3.0))
                        licenseMODULES.add(MODULE("Elasticité", 2.0))
                        licenseMODULES.add(MODULE("Matériaux de construction 1", 2.0))
                        licenseMODULES.add(MODULE("Technologie du béton", 2.0))
                        licenseMODULES.add(MODULE("TP Physique des Matériaux", 2.3))
                        licenseMODULES.add(MODULE("TP Liants", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 2 )", 2.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP technologie du béton", 1.1))
                    } else if (sPECIALITY == "Mécanique Energétique") {
                        licenseMODULES.add(MODULE("Mécanique des fluides approfondie", 3.0))
                        licenseMODULES.add(MODULE("Machines thermiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Transfert de chaleur et de masse approfondi",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Méthodes numériques approfondies", 2.0))
                        licenseMODULES.add(MODULE("Instrumentation et mesures", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP Méthodes numériques", 1.1))
                        licenseMODULES.add(MODULE("TP Machines thermiques", 1.1))
                        licenseMODULES.add(MODULE("TP MDF", 1.1))
                    } else if (sPECIALITY == "Microélectronique") {
                        licenseMODULES.add(MODULE("Physique des composants semiconducteurs 1", 3.0))
                        licenseMODULES.add(MODULE("Couches minces", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Procédés d’élaboration des dispositifs semiconducteurs",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Conception des circuits intégrés analogiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Langage de Programmation", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Physique des composants semiconducteurs",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Conception des CI intégrés analogiques", 1.1))
                        licenseMODULES.add(MODULE("TP propriétés optiques des SC", 1.1))
                    } else if (sPECIALITY == "Ouvrages Hydrauliques" || sPECIALITY == "Ressources Hydrauliques") {
                        licenseMODULES.add(MODULE("Hydraulique appliquée", 3.0))
                        licenseMODULES.add(MODULE("Analyse et modélisation hydrologique", 2.0))
                        licenseMODULES.add(MODULE("Les écoulements à surface libre", 2.0))
                        licenseMODULES.add(MODULE("Les écoulements en charge", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Systèmes d’Informations Géographiques (SIG)",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Hydraulique numérique", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP Assainissement II", 1.1))
                    } else if (sPECIALITY == "Réseaux Electriques") {
                        licenseMODULES.add(
                            MODULE(
                                "Réseaux de transport et de distribution d’énergie électrique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Electronique de puissance avancée", 2.0))
                        licenseMODULES.add(MODULE("Machines électriques approfondies", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes numériques appliquées et optimisation",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("μ-processeurs et μ-contrôleurs", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP μ-processeurs et μ-contrôleurs", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Réseaux de transport et de distribution d’énergie électrique",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Electronique de puissance avancée", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Méthodes numériques appliquées et optimisation",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Machines électriques approfondies", 1.1))
                    } else if (sPECIALITY == "Structures") {
                        licenseMODULES.add(MODULE("Structures métalliques", 3.0))
                        licenseMODULES.add(MODULE("Structures en béton armé 1", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des structures 1", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des structures", 2.0))
                        licenseMODULES.add(MODULE("Complément de programmation", 2.1))
                        licenseMODULES.add(MODULE("Matériaux innovants", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP Méthodes expérimentales", 1.1))
                    } else if (sPECIALITY == "Technologie des Matériaux et Procédés de Fabrication") {
                        licenseMODULES.add(MODULE("Solidification et procèdes d’élaboration", 3.0))
                        licenseMODULES.add(MODULE("Matériaux polymères et composites", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Cristallographie et structure des matériaux",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matériaux métalliques", 2.0))
                        licenseMODULES.add(MODULE("Fabrication mécanique", 1.2))
                        licenseMODULES.add(MODULE("Modélisation des procedes 1", 1.2))
                        licenseMODULES.add(MODULE("mise en forme des Céramique et verres", 1.0))
                        licenseMODULES.add(MODULE("Asservissement 1", 1.0))
                        licenseMODULES.add(MODULE("Anglais 1", 1.0))
                        licenseMODULES.add(MODULE("TP Cristallographie", 1.1))
                    } else if (sPECIALITY == "Technologies de Fabrication Mécanique") {
                        ttCoeff = 15
                        licenseMODULES.add(
                            MODULE(
                                "Materials Engineering and Technology : BM1",
                                6.0
                            )
                        )
                        licenseMODULES.add(MODULE("Manufacturing Technology : BM2", 4.0))
                        licenseMODULES.add(MODULE("Product and Process Development : BM5", 3.5))
                        licenseMODULES.add(MODULE("Technical English and Communication : BM7", 1.3))
                        licenseMODULES.add(MODULE("Project and Laboratory Works I : PLW1", 1.1))
                    } else if (sPECIALITY == "Voies et Ouvrages d'Art") {
                        licenseMODULES.add(MODULE("Projet Ouvrages en Béton Armé", 3.6))
                        licenseMODULES.add(MODULE("Dimensionnement des Ponts", 3.0))
                        licenseMODULES.add(MODULE("Théorie de l'Elasticité", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des structures", 2.0))
                        licenseMODULES.add(MODULE("Dimensionnement des Routes", 2.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 2 )", 2.2))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))
                        licenseMODULES.add(MODULE("TP Programmation", 1.1))
                        licenseMODULES.add(MODULE("TP Logiciels Appliqués aux Routes", 1.1))
                    } else if (sPECIALITY == "Instrumentation Biomédicale") {
                        licenseMODULES.add(MODULE("Radiobiologie et radioprotection", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Traitement avancé des signaux physiologiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Fonctions principales de l’électronique", 2.0))
                        licenseMODULES.add(MODULE("Circuits de conditionnement", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Technologies des biomatériaux pour prothèses",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Electronique de puissance", 1.3))
                        licenseMODULES.add(MODULE("TP Fonctions de l’électronique", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Circuits de conditionnement/\n" +
                                        "TP Electronique de puissance", 1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Traitement avancé des signaux /\n" +
                                        "TP Radiobiologie et radioprotection", 1.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))

                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Risques physiques industriels", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Outils math utilisés en Sureté de Fonctionnement",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Métho numé et matric d’analyse du risque", 2.0))
                        licenseMODULES.add(MODULE("Mesure et maitrise du risque", 2.0))
                        licenseMODULES.add(MODULE("Programmation MATLAB", 2.0))

                        licenseMODULES.add(MODULE("TP Dangers vibra/pressions", 1.1))
                        licenseMODULES.add(MODULE("TP Dangers Electriques/Mécaniques", 1.1))
                        licenseMODULES.add(MODULE("TP Levage et manutention", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "Prévention et détection du risque incendie",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Risques majeurs", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))

                    } else if (sPECIALITY == "Industries Pétrochimiques") {
                        licenseMODULES.add(MODULE("Thermodynamique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Phénomènes de transfert II", 2.0))
                        licenseMODULES.add(MODULE("Procédés de séparation", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Production de matières premières et de monomères",
                                2.2
                            )
                        )
                        licenseMODULES.add(MODULE("TP chimie du pétrole", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "TP Production de matières premières et de monomères",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("TP procédés de séparation", 1.1))
                        licenseMODULES.add(MODULE("Chimie du pétrole et du gaz", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Anglais technique et terminologie", 1.0))

                    }
                    /***
                     *
                     *
                     *
                     * ENF 1st SEMSTER
                     *
                     *
                     ****/
                } else if (mySPECIALITY == "Master firstYEAR secondSEMSTER" || mySPECIALITY == "السّنة الأولى ماستر، السّداسي الثّاني") {
                    mySPECIALITY = "السّنة الأولى ماستر، السّداسي الثّاني،"
                    if (sPECIALITY == "Réseaux & Telecommunication") {
                        licenseMODULES.add(MODULE("Administration des services réseaux", 3.0))
                        licenseMODULES.add(MODULE("DSP et FPGA", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Canaux de transmission et Composants optiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Codage et Compression", 2.0))
                        licenseMODULES.add(MODULE("Réseaux Haut-débits", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Réseaux d’opérateurs ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Réseaux satellitaires  ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Administration des services réseaux", 1.1))
                        licenseMODULES.add(MODULE("TP DSP et FPGA", 1.1))
                        licenseMODULES.add(MODULE("TP Codage et Compression", 1.1))
                    } else if (sPECIALITY == "Systèmes & Telecommunication") {
                        licenseMODULES.add(MODULE("Traitement numérique du signal", 3.0))
                        licenseMODULES.add(MODULE("Antennes", 2.0))
                        licenseMODULES.add(MODULE("Canaux de transmission", 2.0))
                        licenseMODULES.add(MODULE("Codage et Compression", 2.0))
                        licenseMODULES.add(MODULE("Traitement d’images", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Systèmes embarqués et télécommunications ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Techniques Radars ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Traitement numérique du signal", 1.1))
                        licenseMODULES.add(MODULE("TP Codage et Compression", 1.1))
                        licenseMODULES.add(MODULE("TP Antennes et Canaux de transmission", 1.1))
                    }
//                else if (sPECIALITY == "Construction mécanique"){
//                    licenseMODULES.add(MODULE("Méthode des éléments finis",3.0))
//                    licenseMODULES.add(MODULE("Dynamique des structures avancée",2.0))
//                    licenseMODULES.add(MODULE("Systèmes mécaniques articulés et robotique",2.0))
//                    licenseMODULES.add(MODULE("Conception de systèmes mécanique",2.0))
//                    licenseMODULES.add(MODULE("CFAO",2.1))
//                    licenseMODULES.add(MODULE("Optimisation",2.1))
//                    licenseMODULES.add(MODULE("Ethique, déontologie et propriété intellectuelle",1.0))
//                    licenseMODULES.add(MODULE("Matière au choix 1",1.0))
//                    licenseMODULES.add(MODULE("Matière au choix 2",1.0))
//                    licenseMODULES.add(MODULE("TP Eléments finis",1.1))
//                }
                    else if (sPECIALITY == "Valorisation des ressources minérales") {
                        licenseMODULES.add(MODULE("Flottation des minerais", 3.0))
                        licenseMODULES.add(MODULE("Procédés d’égouttage", 2.0))
                        licenseMODULES.add(MODULE("Séparation radiométrique et optique", 2.0))
                        licenseMODULES.add(MODULE("Séparation magnétique et électrostatique", 2.0))
                        licenseMODULES.add(MODULE("Analyse Numérique", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("MINI PROJET", 1.1))
                        licenseMODULES.add(MODULE("TP Flottation des minerais", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Séparation MERO(Magnétique,Electrostatique radiométrique et Optique)",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Automatique et Informatique industrielle") {
                        licenseMODULES.add(MODULE("Systèmes non linéaires", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Systèmes Embarqués et systèmes temps réels",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Programmation avancée des API", 2.0))
                        licenseMODULES.add(MODULE("Electronique Appliquée", 2.0))
                        licenseMODULES.add(MODULE("Conception orientée objet", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Systèmes non linéaires", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Systèmes Embarqués et systèmes temps réels",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Programmation avancée des API/TP Electronique Appliquée",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Automatique et Systèmes") {
                        licenseMODULES.add(MODULE("Systèmes non linéaires", 3.0))
                        licenseMODULES.add(MODULE("Commande optimale", 2.0))
                        licenseMODULES.add(MODULE("API et supervision", 2.0))
                        licenseMODULES.add(MODULE("Electronique Appliquée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Concepts et langage de programmation graphique",
                                2.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Systèmes non linéaires/ TP Commande optimale",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Electronique Appliquée", 1.1))
                        licenseMODULES.add(MODULE("TP API et supervision", 1.1))
                    } else if (sPECIALITY == "Commandes Electriques") {
                        licenseMODULES.add(MODULE("Techniques de la commande électrique", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et identification des systèmes électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Asservissements échantillonnés et Régulation numérique",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Diagnostic des défaillances des systèmes de commande",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Techniques de la commande électrique", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Asservissements échantillonnés et Régulation numérique",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Modélisation et identification des systèmes électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Diagnostic des défaillances des systèmes de commande",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Construction mécanique") {
                        licenseMODULES.add(MODULE("Méthode des éléments finis", 3.0))
                        licenseMODULES.add(MODULE("Dynamique des structures avancée", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Systèmes mécaniques articulés et robotique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Conception de systèmes mécanique", 2.0))
                        licenseMODULES.add(MODULE("CFAO", 2.1))
                        licenseMODULES.add(MODULE("Optimisation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Eléments finis", 1.1))
                    } else if (sPECIALITY == "Electromécanique") {
                        licenseMODULES.add(MODULE("Commande des machines électriques", 2.0))
                        licenseMODULES.add(MODULE("Commande hydraulique et pneumatique", 2.0))
                        licenseMODULES.add(MODULE("Thermodynamique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des fluides appliquée", 2.0))
                        licenseMODULES.add(MODULE("Méthodes numériques appliquées", 2.1))
                        licenseMODULES.add(MODULE("Diagnostique et surveillance", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Commande des machines électriques", 1.1))
                        licenseMODULES.add(MODULE("TP Commande hydraulique et pneumatique", 1.1))
                        licenseMODULES.add(MODULE("TP Thermodynamique appliquée", 1.1))
                    } else if (sPECIALITY == "Electronique des systèmes embarqués") {
                        licenseMODULES.add(MODULE("Processeurs des signaux numériques (DSP)", 3.0))
                        licenseMODULES.add(MODULE("Capteurs intelligents et MEMS", 2.0))
                        licenseMODULES.add(MODULE("Systèmes à microcontrôleurs", 2.0))
                        licenseMODULES.add(MODULE("Réseaux et communications industriels", 2.0))
                        licenseMODULES.add(MODULE("Etude et Réalisation des projets", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Processeurs des signaux numériques (DSP)",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Systèmes à microcontrôleurs", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Capteurs intelligents / TP Réseaux industriels",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Energétique") {
                        licenseMODULES.add(MODULE("Combustion", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des gaz", 2.0))
                        licenseMODULES.add(MODULE("Chauffage et climatisation", 2.0))
                        licenseMODULES.add(MODULE("Turbomachines approfondies", 2.0))
                        licenseMODULES.add(MODULE("Méthodes des volumes finis", 2.1))
                        licenseMODULES.add(MODULE("Asservissement et Régulation", 2.1))
                        licenseMODULES.add(MODULE("Le Séchage thermique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Turbomachines", 1.1))
                    } else if (sPECIALITY == "Energies Renouvelables en Mécanique") {
                        licenseMODULES.add(MODULE("Thermodynamique approfondie", 3.0))
                        licenseMODULES.add(MODULE("Rayonnement solaire", 2.0))
                        licenseMODULES.add(MODULE("Solaire Thermique et applications", 2.0))
                        licenseMODULES.add(MODULE("Energie Hydroélectrique et Eolienne", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Asservissement, régulation et métrologie thermique",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Montage et Dimensionnement des projets ER", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Conversion", 1.1))
                    } else if (sPECIALITY == "Fabrication Mécanique & Productique") {
                        licenseMODULES.add(MODULE("Méthode des éléments finis", 3.0))
                        licenseMODULES.add(MODULE("Coupe de métaux 2", 2.0))
                        licenseMODULES.add(MODULE("Eléments des Machines outils", 2.0))
                        licenseMODULES.add(MODULE("Programmation des MOCN", 2.0))
                        licenseMODULES.add(MODULE("Optimisation", 2.1))
                        licenseMODULES.add(MODULE("CFAO", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Eléments finis", 1.1))
                        licenseMODULES.add(MODULE("TP Eléments des Machines-outils", 1.1))
                    } else if (sPECIALITY == "Génie Alimentaire") {
                        licenseMODULES.add(MODULE("Process de transformations des aliments 2", 2.0))
                        licenseMODULES.add(MODULE("Microbiologie industrielle", 2.0))
                        licenseMODULES.add(MODULE("Propriétés physicochimiques des aliments", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Opérations unitaires du génie alimentaire 2",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Génie fermentaire et Biotransformation", 2.3))
                        licenseMODULES.add(MODULE("Techniques Microbiologiques", 2.3))
                        licenseMODULES.add(MODULE("Bioréacteurs", 1.0))
                        licenseMODULES.add(MODULE("Introduction aux biotechnologies", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Hygiène et sécurité dans le Génie Alimentaire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Logiciel et simulation numérique", 1.1))
                    } else if (sPECIALITY == "Génie Chimique") {
                        licenseMODULES.add(
                            MODULE(
                                "Opérations unitaires 2 (Humidification-Séchage-Evaporation-Cristallisation)",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Procédés d’Adsorption et séparation Membranaire",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Génie de la réaction II : réacteurs poly-phasiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Fours et Chaudières", 2.0))
                        licenseMODULES.add(MODULE("Régulation et commande des procédés", 2.0))
                        licenseMODULES.add(MODULE("Analyse Numérique", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Opérations unitaires 2, Procédés d’adsorption et séparation Membranaire",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie Climatique") {
                        licenseMODULES.add(MODULE("Production de Froid et Séchage", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ventilation et Conditionnement de l’Air II",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Régulation des Systèmes climatiques", 2.0))
                        licenseMODULES.add(MODULE("Systèmes de Chauffage", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Utilisation de logiciels appliqués au froid et à la climatisation",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Système de climatisation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Régulation des systèmes climatiques", 1.1))
                    } else if (sPECIALITY == "Génie de l’Environnement") {
                        licenseMODULES.add(MODULE("Traitement des Eaux Usées", 3.0))
                        licenseMODULES.add(MODULE("Procédés d’Oxydation Avancées", 2.0))
                        licenseMODULES.add(MODULE("Pollution atmosphérique", 2.0))
                        licenseMODULES.add(MODULE("Déchets Solides", 2.0))
                        licenseMODULES.add(MODULE("Microbiologie Environnementale", 2.1))
                        licenseMODULES.add(MODULE("Optimisation des Procédés", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP de Génie de l’Environnement II", 1.1))
                    } else if (sPECIALITY == "Génie de Raffinage") {
                        licenseMODULES.add(MODULE("Procédé de raffinage", 3.0))
                        licenseMODULES.add(MODULE("Thermodynamique et procédés", 3.0))
                        licenseMODULES.add(MODULE("Procédés de pétrochimie", 3.0))
                        licenseMODULES.add(MODULE("Méthodes Numériques Avancées", 2.1))
                        licenseMODULES.add(MODULE("TP Caractérisation du Pétrole", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Procédé de raffinage", 1.1))
                    } else if (sPECIALITY == "Génie des Matériaux") {
                        licenseMODULES.add(
                            MODULE(
                                "Comportement mécanique des Matériaux Composites et multi-matériaux",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Propriétés physico-chimiques et Mécaniques des polymères",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Diffusion et Transformation de Phases", 2.0))
                        licenseMODULES.add(MODULE("Mécanique de la rupture", 2.0))
                        licenseMODULES.add(MODULE("Modélisation et simulation des matériaux", 2.1))
                        licenseMODULES.add(MODULE("Traitements Thermiques", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Elaboration et caractérisation des matériaux composites",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie des Procédés de l’environnement") {
                        licenseMODULES.add(MODULE("Production d’eau potable", 3.0))
                        licenseMODULES.add(MODULE("Gestion et Traitement des déchets solides", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Procédés d’Adsorption et séparation Membranaire",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Réacteurs Polyphasiques", 2.0))
                        licenseMODULES.add(MODULE("Milieux Poreux et Dispersés", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Traitement et Conditionnement des Eaux de process",
                                2.3
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP traitement des Eaux et Procédés d’adsorption et Séparation Membranaire",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie des procédés Cryogéniques") {
                        licenseMODULES.add(
                            MODULE(
                                "Techniques de transport et de stockage des liquides cryogéniques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Echangeurs thermiques", 2.0))
                        licenseMODULES.add(MODULE("Technologie du froid", 2.0))
                        licenseMODULES.add(MODULE("Ecoulements Compressibles.", 2.0))
                        licenseMODULES.add(MODULE("TP du génie des procédés II", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "optimisation et Simulation des Procédés cryogéniques",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Anglais Technique", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "Système de commande et de Régulation dans le domaine de la cryogénie",
                                1.3
                            )
                        )
                        licenseMODULES.add(MODULE("Conception des Procédés cryogéniques", 1.0))
                        licenseMODULES.add(MODULE("Calcul des chambres froides", 1.0))
                    } else if (sPECIALITY == "Génie Pétrochimie") {
                        licenseMODULES.add(MODULE("Procédés de gazochimie", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de transformation en pétrochimie",
                                2.2
                            )
                        )
                        licenseMODULES.add(MODULE("Traitement et industrie du gaz naturel", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "Catalyseurs dans l’industrie pétrochimique",
                                2.2
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Procédés de transformation en pétrochimie et gazochimie",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("TP traitement du pétrole et du gaz", 2.3))
                        licenseMODULES.add(MODULE("Production des polymères", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Production des polymères", 1.1))
                    } else if (sPECIALITY == "Génie Pharmaceutique") {
                        licenseMODULES.add(
                            MODULE(
                                "Chimie pharmaceutique II : Classe Thérapeutiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Opérations Unitaires Fluide-Solide (Cristallisation, Centrifugation, Sédimentation, Filtration, Séchage)",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Procédés d’Adsorption et Séparations Membranaires",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Réacteurs Polyphasiques et bioréacteurs", 2.0))
                        licenseMODULES.add(MODULE("Simulateurs en ingénierie pharmaceutique", 2.1))
                        licenseMODULES.add(MODULE("Production du médicament", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Séparations Membranaires", 1.1))
                        licenseMODULES.add(MODULE("TP Opérations Unitaires Fluide-Fluide", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Réacteurs Polyphasiques et bioréacteurs",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Géotechnique") {
                        licenseMODULES.add(MODULE("Mécanique des solides déformables", 3.0))
                        licenseMODULES.add(MODULE("Rhéologie des sols", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des sols", 2.0))
                        licenseMODULES.add(MODULE("Mécanique des roches", 2.0))
                        licenseMODULES.add(MODULE("Géophysique appliquée", 2.1))
                        licenseMODULES.add(MODULE("Méthode des éléments finis", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Essais géotechniques 2", 1.1))
                    } else if (sPECIALITY == "Hydraulique Urbaine") {
                        licenseMODULES.add(MODULE("Traitement et dessalement des eaux", 3.0))
                        licenseMODULES.add(MODULE("Ouvrages Hydrauliques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Machines hydrauliques et stations de pompage",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Hydraulique Souterraine", 2.0))
                        licenseMODULES.add(MODULE("Modélisation et Simulation en hydraulique", 2.3))
                        licenseMODULES.add(MODULE("Organisation et mécanisation des travaux", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Traitement et dessalement des eaux", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Machines hydrauliques et stations de pompage",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Ingénierie des Matériaux et des Surfaces") {
                        licenseMODULES.add(MODULE("Traitements de surface", 3.0))
                        licenseMODULES.add(MODULE("Traitements thermiques et thermochimiques", 2.0))
                        licenseMODULES.add(MODULE("Endommagement et mécanique de la rupture", 2.0))
                        licenseMODULES.add(MODULE("Tribologie", 2.0))
                        licenseMODULES.add(MODULE("simulation des procédés de mise en forme", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques d’analyse et de caractérisation des matériaux",
                                2.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Traitements de surface, Traitements thermiques et thermochimiques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Ingénierie & Gestion des Eaux") {
                        licenseMODULES.add(
                            MODULE(
                                "Opérations unitaires de traitements des eaux (II)",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de traitement biologiques des eaux usées",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Procédés d’oxydation avancée", 2.0))
                        licenseMODULES.add(MODULE("Chimie des eaux", 2.0))
                        licenseMODULES.add(MODULE("Biochimie", 2.1))
                        licenseMODULES.add(MODULE("TP de Traitement des eaux II", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Valorisation des effluents liquides industriels par bioraffinage",
                                1.3
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de traitements et gestion des rejets miniers",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Plan d’expériences", 1.0))
                    } else if (sPECIALITY == "Installations énergétiques & turbomachines") {
                        licenseMODULES.add(MODULE("Combustion", 3.0))
                        licenseMODULES.add(MODULE("Dynamique des gaz", 2.0))
                        licenseMODULES.add(MODULE("Installations énergétiques 2", 2.0))
                        licenseMODULES.add(MODULE("Turbomachines Aprofondies", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Maintenance des installations énergétiques",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Asservissement et Régulation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Turbomachines", 1.1))
                    } else if (sPECIALITY == "Machines Electriques") {
                        licenseMODULES.add(MODULE("Modélisation des machines électriques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Champ magnétique dans les machines électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Asservissements échantillonnés et Régulation numérique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Construction des machines électriques", 2.0))
                        licenseMODULES.add(MODULE("Association machines-convertisseurs", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Matériaux en électrotechnique et technique de haute tension",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Modélisation des machines électriques", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Asservissements échantillonnés et Régulation numérique",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Champ magnétique dans les machines électriques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Maintenance Industrielle") {
                        licenseMODULES.add(MODULE("Méthode des éléments finis", 2.0))
                        licenseMODULES.add(MODULE("Vibration des machines tournantes", 2.0))
                        licenseMODULES.add(MODULE("GMAO", 2.0))
                        licenseMODULES.add(MODULE("Fiabilité des Systèmes Mécaniques", 2.0))
                        licenseMODULES.add(MODULE("Machines outils", 2.1))
                        licenseMODULES.add(MODULE("Techniques de mesures", 2.1))
                        licenseMODULES.add(MODULE("Procédés de fabrication", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP MEF", 1.1))
                    } else if (sPECIALITY == "Matériaux en Génie Civil") {
                        licenseMODULES.add(MODULE("Plasticité et endommagement", 3.0))
                        licenseMODULES.add(MODULE("Matériaux de construction 2", 2.0))
                        licenseMODULES.add(MODULE("Bétons innovants 1", 2.2))
                        licenseMODULES.add(MODULE("Ouvrages en acier", 2.0))
                        licenseMODULES.add(MODULE("Informatique appliquée", 2.3))
                        licenseMODULES.add(MODULE("Méthodes expérimentales", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Etique, déontologie et propriétés intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Mécanique des matériaux", 1.1))
                    } else if (sPECIALITY == "Mécanique Energétique") {
                        licenseMODULES.add(MODULE("Combustion", 3.0))
                        licenseMODULES.add(MODULE("Dynamique des gaz", 2.0))
                        licenseMODULES.add(MODULE("Transfert et stockage de l’énergie", 2.0))
                        licenseMODULES.add(MODULE("Turbomachines approfondies", 2.0))
                        licenseMODULES.add(MODULE("Chauffage et climatisation", 2.1))
                        licenseMODULES.add(MODULE("Asservissement et Régulation", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Etique, déontologie et propriétés intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Turbomachines", 1.1))
                    } else if (sPECIALITY == "Microélectronique") {
                        licenseMODULES.add(MODULE("Physique des composants semiconducteurs 2", 3.0))
                        licenseMODULES.add(MODULE("Outils de simulation", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques de caractérisation des dispositifs semiconducteurs",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Dispositifs photovoltaïques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Conception des circuits intégrés analogiques MOS",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Physique des composants SC 2", 1.1))
                        licenseMODULES.add(MODULE("TP Outils de simulation", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Caractérisation des SC / TP Dispositifs photovoltaïques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Ouvrages Hydrauliques") {
                        licenseMODULES.add(MODULE("Barrage I", 3.0))
                        licenseMODULES.add(MODULE("Géotechnique des ouvrages hydrauliques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Machines hydrauliques et stations de pompage",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Hydraulique Souterraine", 2.0))
                        licenseMODULES.add(MODULE("Modélisation et Simulation en hydraulique", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Géotechnique des ouvrages hydrauliques", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Machines hydrauliques et stations de pompage",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Organisation et mécanisation des travaux",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Réseaux Electriques") {
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et optimisation des réseaux électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Qualité de l’énergie électrique", 2.0))
                        licenseMODULES.add(MODULE("Planification des réseaux électriques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Commande des systèmes électro-énergétiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Techniques de protection des réseaux électriques",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Production centralisée et décentralisée", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Modélisation et optimisation des réseaux électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Qualité de l’énergie électrique", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Commande des systèmes électro-énergétiques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Ressources Hydrauliques") {
                        licenseMODULES.add(MODULE("Hydrochimie et Hydrogéochimie", 3.0))
                        licenseMODULES.add(MODULE("Ouvrages hydrauliques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Machines hydrauliques et stations de pompage",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Hydraulique Souterraine", 2.0))
                        licenseMODULES.add(MODULE("Modélisation et Simulation en hydraulique", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Organisation et mécanisation des travaux", 1.0))
                        licenseMODULES.add(MODULE("TP Hydrochimie et Hydrogéochimie", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Machines hydrauliques et stations de pompage",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Structures") {
                        licenseMODULES.add(MODULE("Elasticité", 3.0))
                        licenseMODULES.add(MODULE("Méthodes des éléments finis", 3.1))
                        licenseMODULES.add(MODULE("Dynamique des structures 2", 2.0))
                        licenseMODULES.add(MODULE("Structures en béton armé 2", 2.0))
                        licenseMODULES.add(MODULE("Fondations et soutènements", 2.1))
                        licenseMODULES.add(MODULE("Projet constructions métalliques", 2.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Technologie des Matériaux et Procédés de Fabrication") {
                        licenseMODULES.add(MODULE("Mise en forme par déformation plastiques", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Mise en forme des polymères et des composites",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Diffraction et analyse structurale", 3.1))
                        licenseMODULES.add(MODULE("Soudage et assemblage", 2.0))
                        licenseMODULES.add(MODULE("Procèdes de fonderie", 2.0))
                        licenseMODULES.add(MODULE("Procédés de métallurgie des poudres", 1.0))
                        licenseMODULES.add(MODULE("Modélisation des procédés 2", 1.2))
                        licenseMODULES.add(MODULE("Asservissement 2", 1.3))
                        licenseMODULES.add(MODULE("Anglais 2", 1.0))
                    } else if (sPECIALITY == "Technologies de Fabrication Mécanique") {
                        ttCoeff = 21
                        licenseMODULES.add(
                            MODULE(
                                "Casting and Polymer Processing-PART I : SM31",
                                5.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Machining and Joining Technology-PART I : SM2",
                                4.0
                            )
                        )
                        licenseMODULES.add(MODULE("Forming Technology PART I : SM1", 4.1))
                        licenseMODULES.add(
                            MODULE(
                                "Production Management and Maintenance : SM4",
                                3.5
                            )
                        )
                        licenseMODULES.add(MODULE("Project and Laboratory Works II : PLW2", 5.1))
                    } else if (sPECIALITY == "Voies et Ouvrages d'Art") {
                        licenseMODULES.add(MODULE("Béton Précontraint", 3.0))
                        licenseMODULES.add(MODULE("Théorie de la Plasticité", 2.0))
                        licenseMODULES.add(MODULE("Dimensionnement des Ponts 2", 2.0))
                        licenseMODULES.add(MODULE("Constructions métalliques", 2.0))
                        licenseMODULES.add(MODULE("Méthodes des éléments finis", 2.0))
                        licenseMODULES.add(MODULE("Projet routes", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 2 )", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété intellectuelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Systèmes d'Information Géographique (S.I.G)",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Instrumentation Biomédicale") {
                        licenseMODULES.add(MODULE("Traitement de l’image médicale", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Dispositifs spéciaux pour l’imagerie médicale",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Rayonnements non ionisants", 2.0))
                        licenseMODULES.add(MODULE("Systèmes à microcontrôleurs", 2.0))
                        licenseMODULES.add(MODULE("Langage de programmation", 2.1))
                        licenseMODULES.add(MODULE("TP Traitement de l’image médicale", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Dispositifs spéciaux /TP\n" +
                                        "Rayonnements non ionisants", 1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Systèmes à microcontrôleurs", 1.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Respect des normes et des règles d’éthique et d’intégrité",
                                1.0
                            )
                        )

                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Analyse du cycle de vie et écoconception", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Sécurité des procédés : risques mécaniques/électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Sûreté de fonctionnement des systèmes 1", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Diagnostic des défaillances des systèmes industriels",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Logiciels informatiques dédiés à la sécurité industrielle",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Management du risque", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Retour d’expérience industrielle et veille informationnelle",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Système d’information en HSI", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ethique, déontologie et propriété industrielle",
                                1.0
                            )
                        )

                    } else if (sPECIALITY == "Industries Pétrochimiques") {
                        licenseMODULES.add(MODULE("Réacteurs chimiques", 3.0))
                        licenseMODULES.add(MODULE("Procédés de raffinage", 2.0))
                        licenseMODULES.add(MODULE("Procédés de pétrochimie", 2.2))
                        licenseMODULES.add(MODULE("TP de pétrochimie", 2.3))
                        licenseMODULES.add(MODULE("TP réacteurs chimiques", 2.3))
                        licenseMODULES.add(MODULE("Production des polymères", 1.0))
                        licenseMODULES.add(MODULE("Traitements et industrie du gaz naturel", 1.0))
                        licenseMODULES.add(MODULE("TP Production des polymères", 1.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Respect des normes et des règles d’éthique et d’intégrité",
                                1.0
                            )
                        )

                    }
                    /***
                     *
                     *
                     *
                     * SEMSTER TWO
                     *
                     *
                     ****/
                } else if (mySPECIALITY == "Master secondYEAR firstSEMSTER" || mySPECIALITY == "السّنة الثّانية ماستر، السّداسي الثّالث") {
                    mySPECIALITY = "السّنة الثّانية ماستر، السّداسي الثّالث،"
                    if (sPECIALITY == "Réseaux & Telecommunication") {
                        licenseMODULES.add(MODULE("Réseaux sans fils et réseaux mobiles", 3.0))
                        licenseMODULES.add(MODULE("Cryptographie et Sécurité Réseaux", 2.0))
                        licenseMODULES.add(MODULE("Vidéo et Audio sur IP", 2.0))
                        licenseMODULES.add(MODULE("Technologies du Web", 2.0))
                        licenseMODULES.add(MODULE("Télévision numérique", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Réseaux de capteurs sans fil ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Réseaux de terrain ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Réseaux sans fils et réseaux mobiles", 1.1))
                        licenseMODULES.add(MODULE("TP Cryptographie et Sécurité Réseaux", 1.1))
                        licenseMODULES.add(MODULE("TP Technologies du Web et VoIP", 1.1))
                    } else if (sPECIALITY == "Systèmes & Telecommunication") {
                        licenseMODULES.add(MODULE("Réseaux sans fil et réseaux mobiles", 3.0))
                        licenseMODULES.add(MODULE("Communications optiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Technologie et Protocoles pour le multimédia",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Dispositifs (Passifs/Actifs) RF et Micro-ondes",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Télévision numérique", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Télécommunication spatiale ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Matière au choix :( Système de radionavigation ou d'autres )",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Réseaux Sans fil et réseaux mobiles", 1.1))
                        licenseMODULES.add(MODULE("TP Communications optiques", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Technologie et Protocoles pour le multimédia",
                                1.1
                            )
                        )
                    }

//                else if (sPECIALITY == "Construction mécanique"){
//                    licenseMODULES.add(MODULE("Matériaux",2.0))
//                    licenseMODULES.add(MODULE("Dynamique des machines tournantes",2.0))
//                    licenseMODULES.add(MODULE("Matériaux composites",2.0))
//                    licenseMODULES.add(MODULE("Mécanique de la rupture et fatigue",2.0))
//                    licenseMODULES.add(MODULE("Bureau des Méthodes",2.1))
//                    licenseMODULES.add(MODULE("Turbomachines",2.1))
//                    licenseMODULES.add(MODULE("Charpente métallique",1.0))
//                    licenseMODULES.add(MODULE("Logiciels de simulation numérique en mécanique",1.0))
//                    licenseMODULES.add(MODULE("Matière au choix 1",1.0))
//                    licenseMODULES.add(MODULE("Matière au choix 2",1.0))
//                    licenseMODULES.add(MODULE("Recherche documentaire et conception de mémoire",1.1))
//                }
                    else if (sPECIALITY == "Valorisation des ressources minérales") {
                        licenseMODULES.add(MODULE("Traitement et gestion des rejets miniers", 3.0))
                        licenseMODULES.add(MODULE("Métallurgie extractive", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Analyse et conception des procédés de séparation",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Automatisation des usines de traitement", 2.0))
                        licenseMODULES.add(MODULE("Réhabilitation des sites miniers", 2.0))
                        licenseMODULES.add(MODULE("Projet de cours", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Métallurgie extractive", 1.1))
                    } else if (sPECIALITY == "Automatique et Informatique industrielle") {
                        licenseMODULES.add(MODULE("Commande avancée", 3.0))
                        licenseMODULES.add(MODULE("Commande de robots de manipulation", 2.0))
                        licenseMODULES.add(MODULE("Systèmes à évènement discrets", 2.0))
                        licenseMODULES.add(MODULE("FPGA et programmation VHDL", 2.0))
                        licenseMODULES.add(MODULE("Supervision industrielle", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Commande avancée", 1.1))
                        licenseMODULES.add(MODULE("TP Commande de robots de manipulation", 1.1))
                        licenseMODULES.add(MODULE("TP FPGA et programmation VHDL", 1.1))
                    } else if (sPECIALITY == "Automatique et Systèmes") {
                        licenseMODULES.add(MODULE("Commande prédictive et adaptative", 3.0))
                        licenseMODULES.add(MODULE("Commande intelligente", 2.0))
                        licenseMODULES.add(MODULE("Diagnostic des systèmes", 2.0))
                        licenseMODULES.add(MODULE("Commande de robots de manipulation", 2.0))
                        licenseMODULES.add(MODULE("Systèmes temps réel", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception du mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Commande prédictive et adaptative / TP Commande intelligente",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Diagnostic des systèmes", 1.1))
                        licenseMODULES.add(MODULE("TP Commande de robots de manipulation", 1.1))
                    } else if (sPECIALITY == "Commandes Electriques") {
                        licenseMODULES.add(
                            MODULE(
                                "Commande électrique des mécanismes industriels",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Commande non linéaire", 2.0))
                        licenseMODULES.add(MODULE("Commandes Avancées", 2.0))
                        licenseMODULES.add(MODULE("Techniques de l'intelligence artificielle", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Commande non linéaire", 1.1))
                        licenseMODULES.add(MODULE("TP Commandes Avancées", 1.1))
                        licenseMODULES.add(MODULE("TP programmation des API", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Commande électrique des mécanismes industriels",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Techniques d'intelligence artificielle /TP Implémentation d’une commande numérique en temps réel",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Construction mécanique") {
                        licenseMODULES.add(MODULE("Matériaux", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des machines tournantes", 2.0))
                        licenseMODULES.add(MODULE("Matériaux composites", 2.0))
                        licenseMODULES.add(MODULE("Mécanique de la rupture et fatigue", 2.0))
                        licenseMODULES.add(MODULE("Bureau des Méthodes", 2.3))
                        licenseMODULES.add(MODULE("Turbomachines", 2.3))
                        licenseMODULES.add(MODULE("Charpente métallique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Logiciels de simulation numérique en mécanique",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Electromécanique") {
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et simulation des systèmes électromécaniques",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Techniques de commande avancée", 2.0))
                        licenseMODULES.add(MODULE("Microprocesseurs et API", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Organisation et gestion de la maintenance industrielle",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Conception Fabrication Assistée par Ordinateur CFAO",
                                2.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Modélisation et simulation des systèmes électromécaniques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Techniques de commande avancée", 1.1))
                        licenseMODULES.add(MODULE("TP Microprocesseurs et API", 1.1))
                    } else if (sPECIALITY == "Electronique des systèmes embarqués") {
                        licenseMODULES.add(MODULE("Systèmes embarqués", 3.0))
                        licenseMODULES.add(MODULE("Systèmes Temps Réel", 2.0))
                        licenseMODULES.add(MODULE("Automates programmables industriels", 2.0))
                        licenseMODULES.add(MODULE("Vision artificielle", 2.0))
                        licenseMODULES.add(MODULE("Langage JAVA", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Systèmes embarqués / TP Systèmes Temps Réel",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Automates programmables industriels", 1.1))
                        licenseMODULES.add(MODULE("TP Vision artificielle", 1.1))
                    } else if (sPECIALITY == "Energétique") {
                        licenseMODULES.add(MODULE("Moteurs à combustion interne approfondi", 3.0))
                        licenseMODULES.add(MODULE("Cryogénie", 2.0))
                        licenseMODULES.add(MODULE("Mécanique de propulsion", 2.0))
                        licenseMODULES.add(MODULE("Echangeurs de chaleur", 2.0))
                        licenseMODULES.add(MODULE("CFD et logiciels", 2.1))
                        licenseMODULES.add(MODULE("Optimisation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Echangeurs de chaleur", 1.1))
                    } else if (sPECIALITY == "Energies Renouvelables en Mécanique") {
                        licenseMODULES.add(
                            MODULE(
                                "Fluide de travail, Matériaux et dispositifs de Stockage",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Efficacité Energétique et thermique du bâtiment",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Solaire photovoltaïque et applications", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Piles à combustibles et production de l’hydrogène",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Etude Technico Economique et Gestion de Projets ER",
                                2.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Maintenance des systèmes à énergies renouvelables",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Froid solaire et climatisation", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Logiciels de Simulations et de dimensionnement des installations ER",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Echangeurs de Chaleur", 1.1))
                    } else if (sPECIALITY == "Fabrication Mécanique & Productique") {
                        licenseMODULES.add(MODULE("Bureau des Méthodes", 2.0))
                        licenseMODULES.add(MODULE("Usinage des surfaces gauches", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des machines tournantes", 2.0))
                        licenseMODULES.add(MODULE("Mécanique de la rupture et fatigue", 2.0))
                        licenseMODULES.add(MODULE("Moulage et injection Plastique", 2.1))
                        licenseMODULES.add(MODULE("Techniques de Soudage", 2.3))
                        licenseMODULES.add(MODULE("Usinage à grande vitesse", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Bureau des Méthodes", 1.1))
                    } else if (sPECIALITY == "Génie Alimentaire") {
                        licenseMODULES.add(MODULE("Application Projet de cours", 3.0))
                        licenseMODULES.add(MODULE("Procédés de conservation des aliments", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Interactions Atmosphère, Emballage, Aliment.",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Conduite de la Production Industrie Agroalimentaire",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Plans d’expériences", 2.0))
                        licenseMODULES.add(MODULE("Physico Chimie des Aliments", 2.3))
                        licenseMODULES.add(
                            MODULE(
                                "Aliments fonctionnels et produits nouveaux",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Traitement des Déchets et Valorisation", 1.0))
                        licenseMODULES.add(MODULE("Planification et politique alimentaire", 1.0))
                    } else if (sPECIALITY == "Génie Chimique") {
                        licenseMODULES.add(MODULE("Distillation", 2.0))
                        licenseMODULES.add(MODULE("Raffinage et pétrochimie", 2.0))
                        licenseMODULES.add(MODULE("Milieux poreux et dispersés", 2.0))
                        licenseMODULES.add(MODULE("Optimisation et Modélisation des procédés", 2.0))
                        licenseMODULES.add(MODULE("Plan d’expériences", 2.1))
                        licenseMODULES.add(MODULE("Intensification des procédés", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Distillation", 1.1))
                        licenseMODULES.add(MODULE("TP Raffinage et pétrochimie", 1.1))
                        licenseMODULES.add(MODULE("TP Milieux poreux et dispersés", 1.1))
                    } else if (sPECIALITY == "Génie Climatique") {
                        licenseMODULES.add(MODULE("Echangeurs de Chaleur", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ventilation Industrielle et Protection de l’Environnement",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Commande et Automatisation des Installations énergétiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Procédés cryogéniques", 2.0))
                        licenseMODULES.add(MODULE("Installation de Froid", 2.1))
                        licenseMODULES.add(MODULE("Recherche opérationnelle", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Bureau d’étude climatisation et froid industriel",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Dépannage des installations frigorifiques & climatiques",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie de l’Environnement") {
                        licenseMODULES.add(MODULE("Bioprocédés", 3.0))
                        licenseMODULES.add(MODULE("Procédés membranaires et dessalement", 2.0))
                        licenseMODULES.add(MODULE("Pollution du sol", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de traitements des effluents gazeux",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Régulation et commande des procédés", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Travaux pratiques de Génie de l’Environnement III",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("Milieux poreux et dispersés", 1.2))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                    } else if (sPECIALITY == "Génie de Raffinage") {
                        licenseMODULES.add(MODULE("Production des huiles", 3.0))
                        licenseMODULES.add(MODULE("Génie thermique, énergie et utilités", 3.0))
                        licenseMODULES.add(MODULE("Optimisation des procédés industriels", 3.0))
                        licenseMODULES.add(MODULE("Contrôle et Instrumentation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Traitement des huiles", 1.1))
                        licenseMODULES.add(MODULE("TP Optimisation des procédés industriels", 1.1))
                        licenseMODULES.add(MODULE("TP Equipement des Unités pétrochimiques", 1.1))
                    } else if (sPECIALITY == "Génie des Matériaux") {
                        licenseMODULES.add(MODULE("Défauts et Déformation Plastique", 3.0))
                        licenseMODULES.add(MODULE("Fatigue des matériaux", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Propriétés physiques et mécaniques des Céramiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Choix des matériaux", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Méthodes expérimentales et contrôle des matériaux",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("Contrôles non destructifs", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Dégradation des polymères", 1.1))
                    } else if (sPECIALITY == "Génie des Procédés de l’environnement") {
                        licenseMODULES.add(
                            MODULE(
                                "Traitements Physico-Chimique et Biologique des eaux usées",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Traitement des Effluents Gazeux", 2.0))
                        licenseMODULES.add(MODULE("Thermodynamique Technique", 2.0))
                        licenseMODULES.add(MODULE("Bioréacteurs", 2.0))
                        licenseMODULES.add(MODULE("Plan d'expériences", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Intensification des procédés", 1.0))
                        licenseMODULES.add(MODULE("Traitement des Sols pollués", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Traitements Physico-Chimique et Biologique des eaux usées",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie des procédés Cryogéniques") {
                        licenseMODULES.add(MODULE("Technologie des Milieux poly phasiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Matériaux à Basse Température et Applications",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Procédés de purification des Gaz industriels",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Thermodynamique appliquée.", 2.0))
                        licenseMODULES.add(MODULE("Technologie du Gaz Naturel Liquéfié", 2.0))
                        licenseMODULES.add(MODULE("TP du génie des procédés III", 2.3))
                        licenseMODULES.add(MODULE("Anglais Technique", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "L’art du séminaire& Initiation à la Recherche",
                                1.2
                            )
                        )
                        licenseMODULES.add(MODULE("Transfert de matière II", 1.0))
                        licenseMODULES.add(MODULE("Maintenance industrielle", 1.0))
                    } else if (sPECIALITY == "Génie Pétrochimie") {
                        licenseMODULES.add(MODULE("Simulation et optimisation des procédés", 2.0))
                        licenseMODULES.add(MODULE("Simulation des procédés chimiques", 2.0))
                        licenseMODULES.add(MODULE("Equipements des unités pétrochimiques", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "TP Préparation des catalyseurs industriels",
                                2.3
                            )
                        )
                        licenseMODULES.add(MODULE("TP corrosion", 2.3))
                        licenseMODULES.add(MODULE("Réacteurs Chimiques", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Technologie environnementale de l’industrie des HC",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Corrosion et protection des installations", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP CAO II :Simulation instationnaire des pro. chim",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Génie Pharmaceutique") {
                        licenseMODULES.add(MODULE("Fabrication industrielle des médicaments", 2.0))
                        licenseMODULES.add(MODULE("Biopharmacie et pharmacocinétique", 2.0))
                        licenseMODULES.add(MODULE("Génie des Formulations pharmaceutiques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Production d’eau pour les industries pharmaceutiques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Régulation et commande des procédés", 2.0))
                        licenseMODULES.add(MODULE("Plans d’expériences", 2.1))
                        licenseMODULES.add(MODULE("Stérilisation", 1.0))
                        licenseMODULES.add(MODULE("Lyophilisation", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                    } else if (sPECIALITY == "Géotechnique") {
                        licenseMODULES.add(MODULE("Amélioration des sols", 3.1))
                        licenseMODULES.add(MODULE("Géostatistique", 2.0))
                        licenseMODULES.add(MODULE("Calcul à la rupture et analyse limite", 2.0))
                        licenseMODULES.add(MODULE("Dynamique des ouvrages géotechniques", 2.0))
                        licenseMODULES.add(MODULE("Tunnels et ouvrages souterrains", 2.0))
                        licenseMODULES.add(MODULE("Systèmes d’information géographique", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Barrages en terre", 1.0))
                    } else if (sPECIALITY == "Hydraulique Urbaine") {
                        licenseMODULES.add(
                            MODULE(
                                "Distribution et collecte des eaux urbaines",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Epuration et réutilisation des eaux résiduaires",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Aménagement des cours d’eau et transport solide",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Management intégré des ressources en eau", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Préservation et Protection contre les crues et inondations",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Management des projets", 1.0))
                        licenseMODULES.add(MODULE("Techniques de reconnaissance et forage", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Logiciels spécialisés", 1.1))
                        licenseMODULES.add(MODULE("TP Epuration des eaux", 1.1))
                    } else if (sPECIALITY == "Ingénierie des Matériaux et des Surfaces") {
                        licenseMODULES.add(MODULE("Adhésion et adhérence des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Procédés de soudage", 2.0))
                        licenseMODULES.add(MODULE("Eléments de machines", 2.0))
                        licenseMODULES.add(MODULE("Sélection des matériaux", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Formation et contrôle des microstructures fritées",
                                2.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Projection Thermique", 2.3))
                        licenseMODULES.add(MODULE("Projection Thermique", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Soudage", 1.1))

                    } else if (sPECIALITY == "Ingénierie & Gestion des Eaux") {
                        licenseMODULES.add(
                            MODULE(
                                "Réutilisation des eaux épurées et Valorisation des boues de stations d’épuration",
                                3.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Gestion des déchets et dépollution des sols et des nappes",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Filières de production d’eaux potables/ eaux de process / Dessalement de l’eau de mer",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Bioréacteurs", 2.0))
                        licenseMODULES.add(MODULE("Hydraulique urbaine/Assainissement", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Mini projet : Dimensionnement des stations de traitement d’eaux potables et d’eaux usées et visite d’usines",
                                3.2
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Evaluation technicoéconomique des procédés et économie d’entreprise",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Gestion et politique de l’eau/Droit de l’eau",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Installations énergétiques & turbomachines") {
                        licenseMODULES.add(MODULE("Mécanique de propulsion", 3.0))
                        licenseMODULES.add(MODULE("Aérodynamique", 2.0))
                        licenseMODULES.add(MODULE("Moteurs à combustion interne interne", 2.0))
                        licenseMODULES.add(MODULE("Audit énergétique", 2.0))
                        licenseMODULES.add(MODULE("Optimisation", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Moteurs à combustion combustion", 1.1))
                        licenseMODULES.add(MODULE("TP CFD et logiciels", 1.1))
                        licenseMODULES.add(MODULE("TP aérodynamique", 1.1))
                    } else if (sPECIALITY == "Machines Electriques") {
                        licenseMODULES.add(MODULE("Machines électriques spéciales", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Régimes transitoires des machines Electriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Commande des machines électriques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Conception assistée par ordinateur des machines électriques",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Identification et diagnostique des machines électriques",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Echauffement et refroidissement des actionneurs électromécaniques",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("TP Machines électriques spéciales", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Régimes transitoires des machines électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Identification et diagnostique des machines électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Conception assistée par ordinateur des machines électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Commande des machines électriques", 1.1))
                    } else if (sPECIALITY == "Maintenance Industrielle") {
                        licenseMODULES.add(
                            MODULE(
                                "Tribologie et Lubrification des systèmes mécaniques",
                                3.0
                            )
                        )
                        licenseMODULES.add(MODULE("Mécanique de la rupture et endommagements", 2.0))
                        licenseMODULES.add(MODULE("Acoustique appliquée", 2.0))
                        licenseMODULES.add(MODULE("Techniques de détection des défaillances", 2.0))
                        licenseMODULES.add(MODULE("Automatismes", 2.1))
                        licenseMODULES.add(MODULE("Diagnostic vibratoire", 2.1))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Techniques de détection des défaillances",
                                1.1
                            )
                        )
                    } else if (sPECIALITY == "Matériaux en Génie Civil") {
                        licenseMODULES.add(MODULE("Matériaux composites", 2.0))
                        licenseMODULES.add(MODULE("Matériaux recyclés", 2.0))
                        licenseMODULES.add(MODULE("Béton précontraint", 2.0))
                        licenseMODULES.add(MODULE("Durabilité des matériaux", 2.2))
                        licenseMODULES.add(MODULE("Eléments finis", 2.1))
                        licenseMODULES.add(MODULE("TP Durabilité des matériaux", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Bétons innovants 2", 1.0))
                        licenseMODULES.add(MODULE("TP bétons innovants", 1.1))
                    } else if (sPECIALITY == "Mécanique Energétique") {
                        licenseMODULES.add(MODULE("Mécanique de propulsion", 3.0))
                        licenseMODULES.add(MODULE("Cryogénie", 2.0))
                        licenseMODULES.add(MODULE("Moteurs à combustion interne approfondi", 2.0))
                        licenseMODULES.add(MODULE("Echangeurs de chaleur", 2.0))
                        licenseMODULES.add(MODULE("Optimisation", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Moteurs à combustion interne approfondi",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP CFD et logiciels", 1.1))
                        licenseMODULES.add(MODULE("TP Echangeurs de chaleur", 1.1))
                    } else if (sPECIALITY == "Microélectronique") {
                        licenseMODULES.add(MODULE("Techniques et Systèmes photovoltaïques", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Conception des CI analogiques/numériques CMOS",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Optoélectronique et Circuits électroniques associés",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Physique des composants semiconducteurs 3", 2.0))
                        licenseMODULES.add(MODULE("Simulation des composants SC", 2.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Techniques et Systèmes photovoltaïques", 1.1))
                        licenseMODULES.add(
                            MODULE(
                                "TP Conception des CI analogiques/numériques CMOS",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Optoélectronique", 1.1))
                    } else if (sPECIALITY == "Ouvrages Hydrauliques") {
                        licenseMODULES.add(MODULE("Barrage II : Barrage en Béton", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Traitement des eaux conventionnelles et non conventionnelles",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Aménagement des cours d’eau et transport solide",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Management intégré des ressources en eau", 2.0))
                        licenseMODULES.add(MODULE("Génie rural", 2.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Management des projets", 1.0))
                        licenseMODULES.add(MODULE("Auscultation et surveillance des barrages", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Logiciels spécialisés", 1.1))
                        licenseMODULES.add(MODULE("TP traitement des eaux", 1.1))
                    } else if (sPECIALITY == "Réseaux Electriques") {
                        licenseMODULES.add(MODULE("Techniques de haute tension", 3.3))
                        licenseMODULES.add(MODULE("Conduite des réseaux électriques", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Stabilité et dynamique des réseaux électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Réseaux électriques industriels", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Intégration des ressources renouvelables aux réseaux électriques",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Réseaux électriques intelligents", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Stabilité et dynamique des réseaux électriques",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("TP Réseaux électriques industriels", 1.1))
                    } else if (sPECIALITY == "Ressources Hydrauliques") {
                        licenseMODULES.add(MODULE("Traitement et dessalement des eaux", 3.0))
                        licenseMODULES.add(
                            MODULE(
                                "Epuration et réutilisation des eaux résiduaires",
                                2.0
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Protection et gestion des ressources en eau",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Management intégré des ressources en eaue", 2.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Techniques de reconnaissance et forage", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Ressources en eau et les changements climatiques",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Management des projets", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Logiciels spécialisés", 1.1))
                        licenseMODULES.add(MODULE("TP Epuration des eaux", 1.1))
                    } else if (sPECIALITY == "Structures") {
                        licenseMODULES.add(MODULE("Béton précontraint", 3.0))
                        licenseMODULES.add(MODULE("Projet structures en béton armé", 3.4))
                        licenseMODULES.add(MODULE("Plasticité et endommagement", 2.0))
                        licenseMODULES.add(MODULE("Génie parasismique", 2.0))
                        licenseMODULES.add(MODULE("Ouvrages spéciaux", 2.0))
                        licenseMODULES.add(MODULE("Modélisation des structures", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Technologie des Matériaux et Procédés de Fabrication") {
                        licenseMODULES.add(MODULE("Endommagement et mécanique de la rupture", 3.0))
                        licenseMODULES.add(MODULE("C.A.O", 3.0))
                        licenseMODULES.add(MODULE("Microscopie analytique et spectroscopie", 3.0))
                        licenseMODULES.add(MODULE("Dégradation des matériaux", 2.0))
                        licenseMODULES.add(MODULE("Mesure et instrumentation", 2.1))
                        licenseMODULES.add(MODULE("Maintenance industrielle", 1.0))
                        licenseMODULES.add(MODULE("Recherche opérationnelle", 1.0))
                        licenseMODULES.add(MODULE("Management et entreprenariat.", 1.3))
                        licenseMODULES.add(
                            MODULE(
                                "Techniques de Communication et d’Expression.",
                                1.0
                            )
                        )
                    } else if (sPECIALITY == "Technologies de Fabrication Mécanique") {
                        ttCoeff = 23
                        licenseMODULES.add(
                            MODULE(
                                "Manufacturing Systems (Robotics/Automation) : BM4",
                                6.1
                            )
                        )
                        licenseMODULES.add(MODULE("Metrology and Quality : BM3", 4.0))
                        licenseMODULES.add(
                            MODULE(
                                "Entrepreneurship and Innovation Management : BM6",
                                4.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Casting and Polymer Processing- PART II : SM32",
                                4.2
                            )
                        )
                        licenseMODULES.add(MODULE("Project and Laboratory Works III : PLW3", 5.1))
                    } else if (sPECIALITY == "Voies et Ouvrages d'Art") {
                        licenseMODULES.add(MODULE("Conceptions avancées de ponts", 2.0))
                        licenseMODULES.add(MODULE("Ouvrages souterrains", 2.0))
                        licenseMODULES.add(MODULE("Chemins de fer", 2.0))
                        licenseMODULES.add(MODULE("Aérodromes", 2.0))
                        licenseMODULES.add(MODULE("Géotechnique avancée", 2.1))
                        licenseMODULES.add(MODULE("Modélisation numérique des Ponts", 2.3))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 2 )", 2.2))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Pathologie et réhabilitation des OA", 1.0))
                        licenseMODULES.add(MODULE("Organisation et visites de chantiers", 1.1))

                    } else if (sPECIALITY == "Instrumentation Biomédicale") {
                        licenseMODULES.add(
                            MODULE(
                                "Instrumentation pour l’exploration fonctionnelle",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Instrumentation de l’imagerie médicale", 2.0))
                        licenseMODULES.add(MODULE("Biocapteurs", 2.0))
                        licenseMODULES.add(MODULE("Systèmes embarqués biomédicaux", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Gestion de projets pour les systèmes de santé",
                                2.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "Modélisation et simulation des systèmes biomédicaux",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Instrumentation / TP Biocapteurs", 1.1))
                        licenseMODULES.add(MODULE("TP Systèmes embarqués biomédicaux", 1.1))
                        licenseMODULES.add(MODULE("TP Simulation des systèmes biomédicaux", 1.1))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )

                    } else if (sPECIALITY == "Hygiène et sécurité industrielle") {
                        licenseMODULES.add(MODULE("Maîtrise statistique des processus", 3.0))
                        licenseMODULES.add(MODULE("Sûreté de fonctionnement des systèmes 2", 2.0))
                        licenseMODULES.add(MODULE("Outils d’aide à la décision", 2.0))
                        licenseMODULES.add(MODULE("Sécurité fonctionnelle", 2.0))
                        licenseMODULES.add(MODULE("Méthodes intégrées d’analyse des risques", 2.0))
                        licenseMODULES.add(MODULE("Culture de sécurité", 1.0))
                        licenseMODULES.add(MODULE("Maintenance industrielle", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Audit, Certification, Accréditation et Mise en conformité des installations et sites industriels",
                                1.0
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )

                    } else if (sPECIALITY == "Industries Pétrochimiques") {
                        licenseMODULES.add(
                            MODULE(
                                "Dimensionnement des équipements pétrochimique",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("Corrosion et protection des installations", 2.0))
                        licenseMODULES.add(MODULE("Simulation et optimisation des procédés", 2.0))
                        licenseMODULES.add(
                            MODULE(
                                "Stockage et transport des produits pétroliers",
                                2.0
                            )
                        )
                        licenseMODULES.add(MODULE("TP Corrosion", 2.3))
                        licenseMODULES.add(MODULE("Catalyseurs industriels", 1.0))
                        licenseMODULES.add(MODULE("Régulation et instrumentation", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "TP Préparation des catalyseurs industriels",
                                1.1
                            )
                        )
                        licenseMODULES.add(
                            MODULE(
                                "TP Simulation et optimisation des procédés",
                                1.1
                            )
                        )
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(MODULE("Matière au choix :( module du coeff = 1 )", 1.0))
                        licenseMODULES.add(
                            MODULE(
                                "Recherche documentaire et conception de mémoire",
                                1.0
                            )
                        )

                    }
                    /***
                     *
                     *
                     *
                     * SEMSTER THREE
                     *
                     *
                     ****/
                }
            }
            addMosules()
            val myrv = findViewById<RecyclerView>(R.id.rvCalPage)
            myModulesAdapter = MainRecylerViewAdapter(this, licenseMODULES)
            myrv.layoutManager =
                GridLayoutManager(this, 1)
            myrv.adapter = myModulesAdapter

            sPECIALITY = getMajorsNameInArabic(sPECIALITY)
            /***
             *
             *
             *
             * SEMSTER NAME
             *
             *
             ****/

            textViewSPECIALITY.text = "$mySPECIALITY $sPECIALITY"

            val saveRetrieveButton = findViewById<Button>(R.id.saveRetrieveData)
            saveRetrieveButton.setOnClickListener {

                // Get the current background drawable resource id
//                val currentBackgroundDrawable = saveRetrieveButton.background.constantState?.newDrawable()
//
//// Check if the current background drawable is equal to @drawable/icon_save
//                if (currentBackgroundDrawable == ContextCompat.getDrawable(this, R.drawable.icon_save)?.constantState) {
                // If the background drawable is @drawable/icon_save, change it to @drawable/icon_save_filled
                saveRetrieveButton.setBackgroundResource(R.drawable.icon_save_filled)
                saveModulesInSharedPreferences(
                    this,
                    myModulesAdapter.mData,
                    mySPECIALITY_old,
                    sPECIALITY_old,
                    getCurrentDateTime()
                )

                Toast.makeText(
                    this,
                    "Résultats enregistrés avec succès le ${getCurrentDateTime()}",
                    Toast.LENGTH_SHORT
                ).show()
//                } else {
//
//                }

            }
            findViewById<Button>(R.id.down).setOnClickListener {
                if (findViewById<LinearLayout>(R.id.LL).visibility != View.VISIBLE) {
                    findViewById<LinearLayout>(R.id.LL).visibility = View.VISIBLE
                    findViewById<Button>(R.id.down).setBackgroundResource(R.drawable.ic_down)
                } else {
                    findViewById<LinearLayout>(R.id.LL).visibility = View.GONE
                    findViewById<Button>(R.id.down).setBackgroundResource(R.drawable.ic_up)
                }
            }
            Handler().postDelayed({
                if (findViewById<LinearLayout>(R.id.LL).visibility == View.VISIBLE) {
                    findViewById<LinearLayout>(R.id.LL).visibility = View.GONE
                    findViewById<Button>(R.id.down).setBackgroundResource(
                        R.drawable.ic_up
                    )
                }
            }, 10000)
//
//            findViewById<Button>(com.google.android.gms.ads.R.id.btn_Help).setOnClickListener{
//                val builderReport = AlertDialog.Builder(this)
//                val inflaterReport = layoutInflater
//                val mydialogReport = inflaterReport.inflate(com.google.android.gms.ads.R.layout.report_dialog, null)
//                builderReport.setView(mydialogReport)
//                val dialogReport: AlertDialog = builderReport.create()
//                dialogReport.window?.setBackgroundDrawableResource(android.R.color.transparent)
//                dialogReport.show()
//                /**
//                 *
//                 */
//                dialogReport.window?.setBackgroundDrawableResource(android.R.color.transparent)
//                mydialogReport.findViewById<ImageButton>(com.google.android.gms.ads.R.id.exit).setOnClickListener {
//                    dialogReport.dismiss()
//                }
//                var questionMessage = ""
//                mydialogReport.findViewById<EditText>(com.google.android.gms.ads.R.id.editText).addTextChangedListener(object :
//                    TextWatcher {
//                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
//                    }
//                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
//                        if (charSequence.isNotEmpty()){
//                            questionMessage=(charSequence.toString())
//                        }
//                    }
//                    override fun afterTextChanged(editable: Editable) {
//                        if (!editable.isEmpty()){
//                            questionMessage=(editable.toString())
//                        }
//                    }
//                })
//                mydialogReport.findViewById<Button>(com.google.android.gms.ads.R.id.send).setOnClickListener {
//                    if(currentUser != null && isConnected()) {
//                        val ref = FirebaseDatabase.getInstance().getReference("Users").child("F3SWYVXV24RWRns55m1xH9ldOxo2")
//
//                        val menuListener = object : ValueEventListener {
//                            override fun onCancelled(databaseError: DatabaseError) {
//                                // handle error
//                            }
//                            @SuppressLint("SetTextI18n")
//                            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                                user = dataSnapshot.getValue(User::class.java)
//                                if(user != null){
//                                    val childUpdates = HashMap<String, Any>()
//                                    val theUser = currentUser.uid
//                                    childUpdates["Users/$theUser"] = "${currentUser.email.toString()} ► the app is : " + packageName +"the error is in the module: "+ url + "\n the error message: " + questionMessage
//                                    ref.child("memberErrors").updateChildren(childUpdates)}
//                            }
//                        }
//                        ref.addListenerForSingleValueEvent(menuListener)
//                        dialogReport.dismiss()
//
//                        Toast.makeText(this , "Merci! ,Nous avons reçu votre problème." , Toast.LENGTH_LONG).show()
//
//                    }else if(!isConnected()){
//                        Toast.makeText(this , "Vous avez besoin d'une connexion Internet pour envoyer votre problème" , Toast.LENGTH_LONG).show()
//                    }else{
//                        Toast.makeText(this , "Erreur!, réessayer SVP!" , Toast.LENGTH_LONG).show()
//                        dialogReport.dismiss()
//                    }
//                }
//            }
        }
        /*
    licenseMODULES.add(MODULE("module TD + CR 3",3.0))
    licenseMODULES.add(MODULE("module TP + CR 3",3.1))
    licenseMODULES.add(MODULE("module TP 3",3.2))
    licenseMODULES.add(MODULE("module TP+TD*0.5 + CR*0.5 3",3.3))
    licenseMODULES.add(MODULE("module TD + CR 2",2.0))
    licenseMODULES.add(MODULE("module TP + CR 2",2.1))
    licenseMODULES.add(MODULE("module CR 2",2.2))
    licenseMODULES.add(MODULE("module TP 2",2.3))
    licenseMODULES.add(MODULE("module CR 1",1.0))
    licenseMODULES.add(MODULE("module TP 1",1.1))
    licenseMODULES.add(MODULE("module CR + TP 1",1.2))
    licenseMODULES.add(MODULE("module CR + TD 1",1.3))
*/


        session = 0
        fun claculation() {
            Log.e("dadadada", myModulesAdapter?.mData.toString())
            findViewById<Button>(R.id.saveRetrieveData).setBackgroundResource(R.drawable.icon_save)
            val pojoArrayList = myModulesAdapter!!.mData
            var prbPOS = ""
            var TOTAL = 0.0
            var voidCOUNTER = 0.0
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            for (i in 0 until myModulesAdapter.mData!!.size) {
                TOTAL += pojoArrayList[i].getmodVal()!!
            }
            moy = 0.0
            moy = (TOTAL / ttCoeff)
            fun testBYelement(allORnot: Boolean) {
                Log.e("testBYelement", "testBYelementtestBYelementtestBYelementtestBYelement")
                for (i in 0 until myModulesAdapter.mData!!.size) {
                    if ((pojoArrayList[i].getexistCOURS() == true) && (pojoArrayList[i].getexistTD() == true) && (pojoArrayList[i].getexistTP() == false)) {
                        if (pojoArrayList[i].getCourseMARK() == 15.0 && pojoArrayList[i].getTdMARK() == 10.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقاط الخاصة بدروس و TD لمقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        } else if (pojoArrayList[i].getTdMARK() == 10.0 && pojoArrayList[i].getCourseMARK() != 15.0) {
                            moy = 25.0
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بـ TD مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            voidCOUNTER += 1
                            if (allORnot) {
                                break
                            }

                        } else if (pojoArrayList[i].getTdMARK() != 10.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        }
                    } else if ((pojoArrayList[i].getexistCOURS() == true) && (pojoArrayList[i].getexistTP() == true) && (pojoArrayList[i].getexistTD() == false)) {
                        if (pojoArrayList[i].getTpMARK() == 25.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقاط الخاصة بدروس و TP لمقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        } else if (pojoArrayList[i].getTpMARK() == 25.0 && pojoArrayList[i].getCourseMARK() != 15.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بـ TP مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        } else if (pojoArrayList[i].getTpMARK() != 25.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        }
                    } else if (pojoArrayList[i].getexistCOURS() == true && pojoArrayList[i].getexistTP() == false && pojoArrayList[i].getexistTD() == false) {
                        if (pojoArrayList[i].getCourseMARK() == 15.0) {
                            moy = 25.0
                            voidCOUNTER += 1
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        }
                    } else if (pojoArrayList[i].getexistTP() == true && pojoArrayList[i].getexistCOURS() == false) {
                        if (pojoArrayList[i].getTpMARK() == 25.0) {
                            moy = 25.0
                            voidCOUNTER += 1.0
                            prbPOS =
                                "لقد نسيت إدخال النقطة الخاصة بـ TP مقياس " + "\n" + pojoArrayList[i].getmoduleName()
                                    .toString()
                            if (allORnot) {
                                break
                            }
                        }
                    }
                }
            }
            if (session == 0) {
                testBYelement(false)
                if (voidCOUNTER > 1.0) {
                    prbPOS = "يبدو أنك لم تملئ جميع الخانات يا صديقي"
                }
                session = 1
            } else {
                testBYelement(true)
            }

            if (moy == 25.0) {
                findViewById<TextView>(R.id.errorTextView).visibility = View.VISIBLE
                findViewById<TextView>(R.id.errorTextView).setBackgroundColor(Color.BLACK)
                findViewById<TextView>(R.id.errorTextView).text = prbPOS
                findViewById<TextView>(R.id.calculatingTV).text = getString(R.string.calculer)
            } else {
                findViewById<TextView>(R.id.errorTextView).visibility = View.GONE
                findViewById<TextView>(R.id.calculatingTV).visibility = View.VISIBLE
                findViewById<TextView>(R.id.calculatingTV).text = df.format(moy).toString()
            }
        }

        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        fun closeKeyboard(): Boolean {
            val view: View? = this.currentFocus
            if (view != null) {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                return true
            } else {
                return false
            }
        }
        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).visibility =
                        View.VISIBLE
                    findViewById<RecyclerView>(R.id.rvCalPage).visibility = View.INVISIBLE
                }

                override fun onAnimationEnd(animation: Animator) {
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })


//        val menuBtn = findViewById<ImageButton>(R.id.menu_btn)
        val calBtn = findViewById<ImageButton>(R.id.cal_btn)
        val resLL = findViewById<LinearLayout>(R.id.res_LL)

        val rvCAL: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
//        rvCAL.addRule(RelativeLayout.ABOVE,R.id.resulat_RL)
        rvCAL.addRule(RelativeLayout.BELOW, R.id.textViewSPECIALITY)


//        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mydialog = inflater.inflate(R.layout.rateus, null)
        builder.setView(mydialog)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mydialog.findViewById<ImageButton>(R.id.exit).setOnClickListener {
            alertDialog.dismiss()
        }

//        val inflater = layoutInflater
        val myReportdialog = inflater.inflate(R.layout.report_dialog, null)
        builder.setView(myReportdialog)
        val reportAlertDialog: AlertDialog = builder.create()
        reportAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        myReportdialog.findViewById<ImageButton>(R.id.exit).setOnClickListener {
            reportAlertDialog.dismiss()
        }
        myReportdialog.findViewById<Button>(R.id.send).setOnClickListener {
            if (TextUtils.isEmpty(myReportdialog.findViewById<EditText>(R.id.editText).text.trim())) {
                myReportdialog.findViewById<EditText>(R.id.editText).error =
                    "écrivez-nous votre problème"
                myReportdialog.findViewById<EditText>(R.id.editText).requestFocus()
                return@setOnClickListener
            }
            sendEmail(
                myReportdialog.findViewById<EditText>(R.id.editText).text.trim().toString()
            )
        }
        mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView)
            .addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView2).visibility =
                        View.GONE
                }

                override fun onAnimationEnd(animation: Animator) {
                    mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView2).visibility =
                        View.VISIBLE
                    mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView2)
                        .playAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })

        var rate: Int = 0
        mydialog.findViewById<ImageButton>(R.id.face10).setOnClickListener {
            rate = 1
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face20).setOnClickListener {
            rate = 2
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face30).setOnClickListener {
            rate = 3
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face40).setOnClickListener {
            rate = 4
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face50).setOnClickListener {
            rate = 5
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face11).setOnClickListener {
            rate = -1
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face21).setOnClickListener {
            rate = -2
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face31).setOnClickListener {
            rate = -3
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face41).setOnClickListener {
            rate = -4
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
        mydialog.findViewById<ImageButton>(R.id.face51).setOnClickListener {
            rate = -5
            updateTheEmoji(this, rate, mydialog, dialog, reportAlertDialog)
        }
//        menuBtn.setOnClickListener {
//            claculation()
//            closeKeyboard()
//            reRateEmojie()
//            if (calculatingTV.text != "..." && errorTextView.visibility== View.GONE){
//                resLL.visibility = View.VISIBLE
//                menuBtn.visibility = View.GONE
//
//            }
//            if (moy >= 10.0 && calculatingTV.text != "..." && errorTextView.visibility== View.GONE){
//                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).playAnimation()
//                mp = MediaPlayer.create (this, R.raw.max_success)
//                mp.start()
//                Handler().postDelayed({
//                    findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).visibility = View.GONE
//                    findViewById<RecyclerView>(R.id.rvCalPage).visibility = View.VISIBLE
//                    findViewById<RecyclerView>(R.id.rvCalPage).layoutParams = rvCAL
////                    showADS()
////                    alertDialog.show()
////                    mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView).playAnimation()
//
//                },3800)
//
//            }
//            oldMoy = moy
//        }


        findViewById<TextView>(R.id.calculatingTV).setOnClickListener {

            initEmoji(mydialog)

            claculation()
            if (moy != oldMoy) {
                closeKeyboard()
            }
            if (moy >= 10.0 && findViewById<TextView>(R.id.calculatingTV).text != "..." && findViewById<TextView>(
                    R.id.errorTextView
                ).visibility == View.GONE && moy != oldMoy
            ) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).playAnimation()
                mp = MediaPlayer.create(this, R.raw.max_success)
                mp.start()
                Handler().postDelayed({
                    findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).visibility =
                        View.GONE
                    findViewById<RecyclerView>(R.id.rvCalPage).visibility = View.VISIBLE
                    findViewById<RecyclerView>(R.id.rvCalPage).layoutParams = rvCAL
//                    showADS()
                    alertDialog.show()
                    mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView)
                        .playAnimation()

                }, 3800)
            }


            oldMoy = moy

        }

        findViewById<TextView>(R.id.btn_Help).setOnClickListener {

            reportAlertDialog.show()

        }
    }

    private fun sendEmail(message: String) {

        val recipientEmail = "oukebdane.ak@gmail.com"
        val subject = "FNECLIS CALCULATOR - REPORTING ERROR"

//        val intent = Intent(Intent.ACTION_SENDTO).apply {
//            data = Uri.parse("mailto:$recipientEmail")
//            putExtra(Intent.EXTRA_SUBJECT, subject)
//            putExtra(Intent.EXTRA_TEXT, message)
//        }
//
//        if (intent.resolveActivity(packageManager) != null) {
//            startActivity(intent)
//        }
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        } catch (e: Exception) {
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}


