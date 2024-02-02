package com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {
    fun startAnimation(){
        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).playAnimation()

        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view2).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view2).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view3).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view3).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view4).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view4).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view5).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view5).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view6).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
        findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view6).addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animation_view7).playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })


    }
    private lateinit var mp: MediaPlayer

    var cliCked = false
    private var wifiManager: WifiManager? = null


    private fun isConnected(): Boolean {
        val cnxManager : ConnectivityManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cnxManager.activeNetworkInfo
        if (netInfo !=null){
            return netInfo.isConnected
        }else{
            return false}

    }

    private val ONESIGNAL_APP_ID = "e98f3acd-a714-4202-99ef-56311571f1bb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(!isConnected()){
            wifiManager!!.isWifiEnabled = true
        }
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        startAnimation()

        mp = MediaPlayer.create (this, R.raw.splash_sound)
        mp.start()

        //2.63second splash time

        findViewById<ImageButton>(R.id.close_btn).setOnClickListener {
            cliCked = true
            mp.stop ()
            startActivity(Intent(this, MainActivity::class.java))
        }
        Handler().postDelayed({

        findViewById<LinearLayout>(R.id.madeWith)!!.visibility = View.VISIBLE

        },4600)
            Handler().postDelayed({
            //start main activity
            if (!cliCked){
            startActivity(Intent(this, MainActivity::class.java))
            //finish this activity
//            mp.stop ()
            finish()}
        },9200)

    }
//    override fun onDestroy() {
//        super.onDestroy()
//        if (isConnected()){ wifiManager?.isWifiEnabled = false}
//    }
}
