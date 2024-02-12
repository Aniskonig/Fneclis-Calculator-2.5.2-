package com.medanis.fnecliscalcultricedumoyennestlicensemaster.others

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R

class Others {


}
private fun isConnected(context: Context): Boolean {
    val cnxManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cnxManager.activeNetworkInfo
    if (netInfo != null) {
        return netInfo.isConnected
    } else {
        return false
    }

}

fun initEmoji(dialog: View) {
    dialog.findViewById<ImageButton>(R.id.face10).visibility = View.VISIBLE
    dialog.findViewById<ImageButton>(R.id.face11).visibility = View.GONE
    dialog.findViewById<ImageButton>(R.id.face20).visibility = View.VISIBLE
    dialog.findViewById<ImageButton>(R.id.face21).visibility = View.GONE
    dialog.findViewById<ImageButton>(R.id.face30).visibility = View.VISIBLE
    dialog.findViewById<ImageButton>(R.id.face31).visibility = View.GONE
    dialog.findViewById<ImageButton>(R.id.face40).visibility = View.VISIBLE
    dialog.findViewById<ImageButton>(R.id.face41).visibility = View.GONE
    dialog.findViewById<ImageButton>(R.id.face50).visibility = View.VISIBLE
    dialog.findViewById<ImageButton>(R.id.face51).visibility = View.GONE
    dialog.findViewById<ImageView>(R.id.ratedImg).visibility = View.GONE
    dialog.findViewById<TextView>(R.id.rateEx).text = "كيف كانت تجربتك معنا ؟"
}


fun updateTheEmoji(context: Context, rate: Int, dialog: View, alertDialog: AlertDialog, reportAlertDialog: AlertDialog) {
    var mYp = MediaPlayer.create(context, R.raw.click_sound)
    if (rate >= 1) {
        dialog.findViewById<ImageButton>(R.id.face10).visibility = View.GONE
        dialog.findViewById<ImageButton>(R.id.face11).visibility = View.VISIBLE
    }
    if (rate >= 2) {
        dialog.findViewById<ImageButton>(R.id.face20).visibility = View.GONE
        dialog.findViewById<ImageButton>(R.id.face21).visibility = View.VISIBLE
    }
    if (rate >= 3) {
        dialog.findViewById<ImageButton>(R.id.face30).visibility = View.GONE
        dialog.findViewById<ImageButton>(R.id.face31).visibility = View.VISIBLE
    }
    if (rate >= 4) {
        dialog.findViewById<ImageButton>(R.id.face40).visibility = View.GONE
        dialog.findViewById<ImageButton>(R.id.face41).visibility = View.VISIBLE
    }
    if (rate == 5) {
        dialog.findViewById<ImageButton>(R.id.face50).visibility = View.GONE
        dialog.findViewById<ImageButton>(R.id.face51).visibility = View.VISIBLE
    }
    if (rate == -1) {
        if (dialog.findViewById<ImageButton>(R.id.face21).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face21).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face20).visibility = View.VISIBLE
        }
        if (dialog.findViewById<ImageButton>(R.id.face31).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face31).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face30).visibility = View.VISIBLE

        }
        if (dialog.findViewById<ImageButton>(R.id.face41).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face41).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face40).visibility = View.VISIBLE
        }
        if (dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face51).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face50).visibility = View.VISIBLE
        }
    }
    if (rate == -2) {
        if (dialog.findViewById<ImageButton>(R.id.face31).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face31).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face30).visibility = View.VISIBLE
        }
        if (dialog.findViewById<ImageButton>(R.id.face41).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face41).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face40).visibility = View.VISIBLE
        }
        if (dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face51).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face50).visibility = View.VISIBLE
        }
    }
    if (rate == -3) {
        if (dialog.findViewById<ImageButton>(R.id.face41).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face41).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face40).visibility = View.VISIBLE

        }
        if (dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face51).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face50).visibility = View.VISIBLE

        }
    }
    if (rate == -4) {
        if (dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE) {
            dialog.findViewById<ImageButton>(R.id.face51).visibility = View.GONE
            dialog.findViewById<ImageButton>(R.id.face50).visibility = View.VISIBLE

        }
    }
    if (rate <= 3){
        reportAlertDialog.show()
    }

    dialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationView2).visibility =
        View.GONE
    dialog.findViewById<ImageView>(R.id.ratedImg).visibility = View.VISIBLE

    if (dialog.findViewById<ImageButton>(R.id.face11).visibility == View.VISIBLE) {
        dialog.findViewById<ImageView>(R.id.ratedImg)
            .setImageResource(R.drawable.face11)
        dialog.findViewById<TextView>(R.id.rateEx).text = "ليست جيدة"
    }
    if (dialog.findViewById<ImageButton>(R.id.face21).visibility == View.VISIBLE) {
        dialog.findViewById<ImageView>(R.id.ratedImg)
            .setImageResource(R.drawable.face21)
        dialog.findViewById<TextView>(R.id.rateEx).text = "لا بأس بها"
    }
    if (dialog.findViewById<ImageButton>(R.id.face31).visibility == View.VISIBLE) {
        dialog.findViewById<ImageView>(R.id.ratedImg)
            .setImageResource(R.drawable.face31)
        dialog.findViewById<TextView>(R.id.rateEx).text = "عادية"
    }
    if (dialog.findViewById<ImageButton>(R.id.face41).visibility == View.VISIBLE) {
        dialog.findViewById<ImageView>(R.id.ratedImg)
            .setImageResource(R.drawable.face41)
        dialog.findViewById<TextView>(R.id.rateEx).text = "جيدة"
    }
    if (dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE) {
        dialog.findViewById<ImageView>(R.id.ratedImg)
            .setImageResource(R.drawable.face51)
        dialog.findViewById<TextView>(R.id.rateEx).text = "رائعة"

    }
//        mp.stop()
    mYp.start()
    Handler().postDelayed({
//            mydialog.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.animationview2).visibility = View.VISIBLE
//            mydialog.findViewById<ImageView>(R.id.ratedImg).visibility = View.GONE
        if (isConnected(context) &&  dialog.findViewById<ImageButton>(
                R.id.face41
            ).visibility == View.VISIBLE || dialog.findViewById<ImageButton>(R.id.face51).visibility == View.VISIBLE
        ) {
            val appPackageName =
                "com.medanis.fnecliscalcultricedumoyennestlicensemaster"
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (anfe: android.content.ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
        alertDialog.dismiss()
    }, 2000)

}
