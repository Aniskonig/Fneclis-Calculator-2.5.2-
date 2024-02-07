package com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.transition.TransitionManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.navigation.NavigationView
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var i=0
    var j=0
    var k=0
    var m=0

    var oldspec =""
    var oldyear =""
    var pressedLevel =""
    var studyYEAR =""
    var studySEMESTER =""
    var calledSemYear =""
    private var wifiManager: WifiManager? = null

    private fun isConnected(): Boolean {
        val cnxManager : ConnectivityManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cnxManager.activeNetworkInfo
        if (netInfo !=null){
            return netInfo.isConnected
        }else{
            return false}

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isConnected()){
            wifiManager?.isWifiEnabled = false
        }
    }

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
    
    public override fun onResume() {
        findViewById<Button>(R.id.firstLicense).visibility = View.GONE
        findViewById<Button>(R.id.secondLicense).visibility = View.GONE
        findViewById<Button>(R.id.thirdLicense).visibility = View.GONE
        findViewById<Button>(R.id.semONE).visibility = View.GONE
        findViewById<Button>(R.id.semTWO).visibility = View.GONE
        findViewById<Button>(R.id.masterButton).setBackgroundResource(R.drawable.roundedbutton)
        findViewById<Button>(R.id.licenseButton).setBackgroundResource(R.drawable.roundedbutton)
         i=0
         j=0
        k=0
        m=0
        dropL = false
        dropM = false
         oldspec =""
         oldyear =""
         pressedLevel =""
         studyYEAR =""
         studySEMESTER =""
         calledSemYear =""
        super.onResume()


        val marginInDp50 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50.toFloat(), resources
                .displayMetrics
        ).toInt()
        val marginInDp30 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 30.toFloat(), resources
                .displayMetrics
        ).toInt()

        val  centerLicense: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        centerLicense.setMargins(marginInDp50,0,marginInDp50,0)
        centerLicense.addRule(RelativeLayout.CENTER_HORIZONTAL)
        centerLicense.addRule(RelativeLayout.CENTER_VERTICAL)
        findViewById<Button>(R.id.licenseButton).layoutParams = centerLicense
        val  marginParam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        marginParam.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        marginParam.addRule(RelativeLayout.BELOW, R.id.licenseButton)
        findViewById<Button>(R.id.masterButton).layoutParams = marginParam
        //Refresh your stuff here
    }
    var dropL = false
    var dropM = false
    var wifiResponde : Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //saving data
        fun saveData(wifiResponde : Boolean) {
            Log.i("TAG", "////////////////// it has been saved  \\\\\\\\\\\\\\\\\\\\\\\\\\")

            val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putBoolean("Agree", wifiResponde)
                apply()
            }
        }
        //getting Data
        var getResponde = false
        fun getData() {
            Log.i("TAG", "////////////////// it has been Restarted  \\\\\\\\\\\\\\\\\\\\\\\\\\")

            val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            getResponde = sharedPref.getBoolean("Agree", false)
        }

        prepareAd()

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mydialog = inflater.inflate(R.layout.use_wifi, null)
        builder.setView(mydialog)
        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mydialog.findViewById<Button>(R.id.agreeBtn).setOnClickListener {
            wifiResponde = true
            saveData(wifiResponde)

            dialog.dismiss()
        }


        val builderPol = AlertDialog.Builder(this)
        val inflaterPol = layoutInflater
        val mydialogPol = inflaterPol.inflate(R.layout.policypage, null)
        builderPol.setView(mydialogPol)
        val dialogPol: AlertDialog = builderPol.create()
        dialogPol.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mydialogPol.findViewById<Button>(R.id.agreePolBtn).setOnClickListener {
            dialogPol.dismiss()
        }
        mydialogPol.findViewById<Button>(R.id.privacyBtn).setOnClickListener {
            val url = "https://fneclis.blogspot.com/2019/04/privacy-findViewById<LinearLayout>(R.id.policy).html"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        mydialogPol.findViewById<Button>(R.id.contentBtn).setOnClickListener {
            val url = "https://fneclis.blogspot.com/2019/04/content-findViewById<LinearLayout>(R.id.policy).html"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        findViewById<CardView>(R.id.poliTXTCV).setOnClickListener {
            dialogPol.show()
        }
        findViewById<LinearLayout>(R.id.poliTXT)?.setOnClickListener {
            dialogPol.show()
        }
        getData()
//        if (!getResponde){
//            Handler().postDelayed({
//                dialog.show()
//            }, 2000)
//        }
        if(!isConnected()){
            wifiManager!!.isWifiEnabled = true
        }
        val transitionsContainer = findViewById<RelativeLayout>(R.id.mainRelativeLayout)


        val marginInDp30 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 30.toFloat(), resources
                .displayMetrics
        ).toInt()
        val marginInDp50 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50.toFloat(), resources
                .displayMetrics
        ).toInt()

        val licenseYearsLL = findViewById<RelativeLayout>(R.id.licenseYearsLinearLayout)
        val  licenseParam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        licenseParam.addRule(RelativeLayout.BELOW, R.id.licenseButton)
//        licenseYearsLL.layoutParams = licenseParam
        val  masterParam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        masterParam.addRule(RelativeLayout.BELOW, R.id.masterButton)
//        licenseYearsLL.layoutParams = masterParam
        val  param: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        param.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        param.addRule(RelativeLayout.BELOW, R.id.licenseYearsLinearLayout)
        val  semTwOparam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        semTwOparam.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        semTwOparam.addRule(RelativeLayout.BELOW, R.id.semONE)

        val  marginParam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        marginParam.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        marginParam.addRule(RelativeLayout.BELOW, R.id.licenseButton)
        val  licParam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        licParam.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        licParam.addRule(RelativeLayout.BELOW, R.id.topBAR)
        val  belowMaster: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        belowMaster.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        belowMaster.addRule(RelativeLayout.BELOW, R.id.masterButton)
        val  centerLicense: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        centerLicense.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        centerLicense.addRule(RelativeLayout.CENTER_HORIZONTAL)
        centerLicense.addRule(RelativeLayout.CENTER_VERTICAL)
        val  belowSOMTWO: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        belowSOMTWO.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        belowSOMTWO.addRule(RelativeLayout.BELOW, R.id.semTWO)

        val  belowTOP: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        belowTOP.setMargins(marginInDp50,marginInDp30,marginInDp50,0)
        belowTOP.addRule(RelativeLayout.CENTER_HORIZONTAL)
        showADS()
        fun turnINVISIBLE(){
            TransitionManager.beginDelayedTransition(transitionsContainer)
            findViewById<Button>(R.id.firstLicense).visibility = View.GONE
            findViewById<Button>(R.id.secondLicense).visibility = View.GONE
            findViewById<Button>(R.id.thirdLicense).visibility = View.GONE
            i=0
        }
        fun semINVISIBLE(){
            findViewById<Button>(R.id.semONE).visibility = View.GONE
            findViewById<Button>(R.id.semTWO).visibility = View.GONE
            j=0
        }
        fun showFloatingButton(spec : String){

            if (oldspec != spec){
                turnINVISIBLE()
                semINVISIBLE()
                if (dropL){
                    findViewById<Button>(R.id.licenseButton).setBackgroundResource(R.drawable.roundedbutton)
                    dropL=false
                }else if(dropM){
                    findViewById<Button>(R.id.masterButton).setBackgroundResource(R.drawable.roundedbutton)
                    dropM=false
                }
            }

            if ((i==0)&&(spec != "")){
                TransitionManager.beginDelayedTransition(transitionsContainer)
                findViewById<Button>(R.id.firstLicense).visibility = View.VISIBLE
                findViewById<Button>(R.id.secondLicense).visibility = View.VISIBLE
                if (spec=="License"){
                    findViewById<Button>(R.id.thirdLicense).visibility = View.VISIBLE
                    findViewById<Button>(R.id.licenseButton).setBackgroundResource(R.drawable.downrounded)
                    dropL = true
                }else if(spec=="Master"){
                    findViewById<Button>(R.id.masterButton).setBackgroundResource(R.drawable.downrounded)
                    dropM = true
                }
                i++
            }else {
                turnINVISIBLE()
                semINVISIBLE()
                if (dropL){
                    findViewById<Button>(R.id.licenseButton).setBackgroundResource(R.drawable.roundedbutton)
                    dropL=false
                }else if(dropM){
                    findViewById<Button>(R.id.masterButton).setBackgroundResource(R.drawable.roundedbutton)
                    dropM=false
                }
            }
            oldspec = spec
        }
        fun semONEfun(){

            studySEMESTER="firstSEMSTER"
            calledSemYear = pressedLevel + studyYEAR + studySEMESTER
            if ((pressedLevel== "License")&&(studyYEAR == " firstYEAR ")){
                val intent = Intent(this, CalculatingPage::class.java)
                intent.putExtra("LEVEL+YEAR+SEMSTER",calledSemYear)
                startActivity(intent)
            }else {
                val intent = Intent(this, specialitiesList::class.java)
                intent.putExtra("LEVEL+YEAR+SEMSTER",calledSemYear)
                startActivity(intent)
            }
        }
//        fun showADS(){
//            if (mInterstitialAd.isLoaded ) {
//                Log.i("TAG", "////////////////// it supose to be running  \\\\\\\\\\\\\\\\\\\\\\\\\\")
//
//                mInterstitialAd.show()
//
//                Log.i("TAG", "////////////////// it must be shown now  \\\\\\\\\\\\\\\\\\\\\\\\\\")
//
//            }else {
//                Log.i("TAG", "////////////////// it was not loaded  \\\\\\\\\\\\\\\\\\\\\\\\\\")
//            }
//        }
        fun showSemester(year : String){
            if (pressedLevel == "License"){
                findViewById<Button>(R.id.licenseButton).layoutParams = licParam
                findViewById<Button>(R.id.semONE).layoutParams = param
                findViewById<Button>(R.id.semTWO).layoutParams = semTwOparam
                findViewById<Button>(R.id.masterButton).layoutParams = belowSOMTWO
            }else if (pressedLevel == "Master"){
//                findViewById<Button>(R.id.masterButton).layoutParams = licenseParam
                findViewById<Button>(R.id.licenseButton).layoutParams = licParam
            }

            if (oldyear != year){
                semINVISIBLE()
            }
            if ((j==0)&&(year != "")){

                TransitionManager.beginDelayedTransition(transitionsContainer)
                findViewById<Button>(R.id.semONE).visibility = View.VISIBLE
                if (pressedLevel == "Master" && year==" secondYEAR "){
                    findViewById<Button>(R.id.semONE).visibility = View.GONE
                    findViewById<Button>(R.id.semTWO).visibility = View.GONE
                    semONEfun()
//                    showADS()

                }else {
                    findViewById<Button>(R.id.semTWO).visibility = View.VISIBLE
                }
                j++
            }else {
                semINVISIBLE()
                findViewById<Button>(R.id.licenseButton).layoutParams = centerLicense
            }
            oldyear = year

        }

        fun toggleLM(speciality : String){
            findViewById<Button>(R.id.licenseButton).layoutParams = belowTOP
            if (speciality=="License"){
                licenseYearsLL.layoutParams = licenseParam
                findViewById<Button>(R.id.masterButton).layoutParams = param

            }else if(speciality=="Master"){
                findViewById<Button>(R.id.semONE).layoutParams = param
                findViewById<Button>(R.id.masterButton).layoutParams = licenseParam
                findViewById<Button>(R.id.masterButton).layoutParams = marginParam
                licenseYearsLL.layoutParams = masterParam
            }
        }

        turnINVISIBLE()
        semINVISIBLE()


        //LICENSE OR MASTER
        findViewById<Button>(R.id.licenseButton).setOnClickListener {
            pressedLevel="License"
            showFloatingButton(pressedLevel)
            toggleLM(pressedLevel)

        }

        findViewById<Button>(R.id.masterButton).setOnClickListener {
            pressedLevel="Master"
            showFloatingButton(pressedLevel)
            toggleLM(pressedLevel)

        }

        transitionsContainer.setOnClickListener {
            showFloatingButton("")
            showSemester("")
        }

        findViewById<RelativeLayout>(R.id.RL_CV).setOnClickListener {
//            findViewById<Button>(R.id.licenseButton).layoutParams = centerLicense
            showFloatingButton("")
            showSemester("")
        }

        //Study Year
        findViewById<Button>(R.id.firstLicense).setOnClickListener {
            studyYEAR=" firstYEAR "
            showSemester(studyYEAR)
        }
        findViewById<Button>(R.id.secondLicense).setOnClickListener {
            studyYEAR=" secondYEAR "
            showSemester(studyYEAR)
        }
        findViewById<Button>(R.id.thirdLicense).setOnClickListener {
            studyYEAR=" thirdYEAR "
            showSemester(studyYEAR)
        }
        showADS()
        //Study SEMESTER
        findViewById<Button>(R.id.semONE).setOnClickListener {
            semONEfun()
        }

        /**
         * Menu
         */
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar: Toolbar? = null

        fun initNavigationMenu() {
            val nav_view = findViewById<View>(R.id.nav_view) as NavigationView

            val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            ) {
                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                }
            }
            drawer!!.setDrawerListener(toggle)
            toggle.syncState()

            // open drawer at start
            drawer!!.openDrawer(GravityCompat.START)
        }
        findViewById<Button>(R.id.menu).setOnClickListener {

            initNavigationMenu()

            /**
             * Set Version Text
             */
            val manager = this.packageManager
            val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
            findViewById<TextView>(R.id.versionTV).text = "version: " + info.versionName
        }
        findViewById<CardView>(R.id.savedHistoryCV).setOnClickListener {
            val intent = Intent(this, specialitiesList::class.java)
            intent.putExtra("isHistory",true)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.savedHistory).setOnClickListener {
            val intent = Intent(this, specialitiesList::class.java)
            intent.putExtra("isHistory",true)
            startActivity(intent)
        }

        fun semTWOfun(){
    studySEMESTER="secondSEMSTER"
    calledSemYear = pressedLevel + studyYEAR + studySEMESTER
    if ((pressedLevel== "License")&&(studyYEAR == " firstYEAR ")){
        val intent = Intent(this, CalculatingPage::class.java)
        intent.putExtra("LEVEL+YEAR+SEMSTER",calledSemYear)
        startActivity(intent)
    }else {
        val intent = Intent(this, specialitiesList::class.java)
        intent.putExtra("LEVEL+YEAR+SEMSTER",calledSemYear)
        startActivity(intent)
    }
}
        findViewById<Button>(R.id.semTWO).setOnClickListener {
            semTWOfun()
        }

    }

}
