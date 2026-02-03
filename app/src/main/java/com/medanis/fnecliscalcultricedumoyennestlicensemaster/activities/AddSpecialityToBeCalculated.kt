package com.medanis.fnecliscalcultricedumoyennestlicensemaster.activities

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters.CustomSpecialityRVA
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.CoursesData
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.ModFormula
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.initEmoji
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.updateTheEmoji
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale

class AddSpecialityToBeCalculated : AppCompatActivity() {

    var myModulesAdapter: CustomSpecialityRVA? = null

    private var coursesList: MutableList<CoursesData> = ArrayList()



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
        val mIntent = Intent(Intent.ACTION_SEND)/*To send an email you need to specify mailto: as URI using setData() method
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

    private fun calculateTheAverage(): Double {
        val dataList = myModulesAdapter!!.mData
        var totalCoefficient  = 0
        var average = 0.0

        for (data in dataList) {

            totalCoefficient += data.getCoeffiecient()!!



//            average = data.getCourseAverage(data.getFormula()!!, data.getCoeffiecient()!!, data.getPercentageCourse()!!, data.getPercentageTD()!!, data.getPercentageTP()!!, data.getCourseMARK()!!, data.getTdMARK()!!, data.getTpMARK()!!)!!
            average = data.getCourseAverage()!!
        }
        Log.e("totalCoefficient", totalCoefficient.toString())
        Log.e("average", average.toString())

        return average / totalCoefficient
    }

    private fun checkETs(myModulesAdapter: CustomSpecialityRVA? = null): Boolean? {
        val dataList = myModulesAdapter!!.mData

        for (data in dataList) {
            if ((data.getPercentageCourse() != 0.0 && data.getCourseMARK()!! >= 25.0) || (data.getPercentageTD() != 0.0 && data.getTdMARK()!! >= 25.0) || (data.getPercentageTP() != 0.0 && data.getTpMARK()!! >= 25.0)) {
                return false
            }
        }
        return true
    }

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_speciality_to_be_calculated)


        val textViewSPECIALITY = findViewById<TextView>(R.id.textViewSPECIALITY)
        val addCourse = findViewById<Button>(R.id.add_course)
        val myrv = findViewById<RecyclerView>(R.id.rvCalPage)



//        myModulesAdapter = MainRecylerViewAdapter(this@AddSpecialityToBeCalculated, licenseMODULES)
//
//
//        myrv.layoutManager = GridLayoutManager(this, 1)
//        myrv.adapter = myModulesAdapter


        val inflater = layoutInflater
        val builder = AlertDialog.Builder(this@AddSpecialityToBeCalculated)
        val specialityNameDialog = inflater.inflate(R.layout.enter_speciality_name_year, null)
        builder.setView(specialityNameDialog)
        val specialityNameAlertDialog: AlertDialog = builder.create()
        specialityNameAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        specialityNameAlertDialog.setCancelable(false)
        specialityNameAlertDialog.setCanceledOnTouchOutside(false)

        specialityNameAlertDialog.show()
        specialityNameDialog.findViewById<Button>(R.id.send).setOnClickListener {
            val editText = specialityNameDialog.findViewById<EditText>(R.id.editText)
            val textEntred = editText.text.trim().toString()
            if (TextUtils.isEmpty(textEntred)) {
                editText.error =
                    "écrivez-nous votre nom du spécilité"
                editText.requestFocus()
                return@setOnClickListener
            }

            textViewSPECIALITY.text = textEntred.capitalize(Locale.ROOT)

            specialityNameAlertDialog.dismiss()
        }

        addCourse.setOnClickListener {



            var isCourseSelected = false
            var isTDSelected = false
            var isTPSelected = false

            val inflater = layoutInflater
            val builder = AlertDialog.Builder(this@AddSpecialityToBeCalculated)
            val myCourseDialog = inflater.inflate(R.layout.dialog_new_course, null)
            builder.setView(myCourseDialog)
            val courseDialog: AlertDialog = builder.create()
            courseDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//            reportAlertDialog.setCancelable(false)
//            reportAlertDialog.setCanceledOnTouchOutside(false)
            courseDialog.show()

//            val recyclerView = myCourseDialog.findViewById<RecyclerView>(R.id.recyclerView)


            val formulas = listOf(
                ModFormula(1, "Cours/TD/TP seul x coefficient"),
                ModFormula(2, "(Cours * pourcentage + TD x pourcentage) x coefficient"),
                ModFormula(3, "(Cours * pourcentage + TP x pourcentage) x coefficient"),
                ModFormula(4, "(Cours * pourcentage + TD x pourcentage + TP x pourcentage) x coefficient"),


//                ModFormula(2.2, "RATE 2.2  cours x coefficient 2"),
//                ModFormula(2.3, "RATE 2.3  TP x coefficient 2"),
//                ModFormula(2.0, "RATE 2.0  40% x TD + 60% x cours. Résultats x coefficient 2"),
//                ModFormula(2.1, "RATE 2.1  40% x TP + 60% x cours. Résultats x coefficient 2"),
//
//                ModFormula(3.2, "RATE 3.2  TP x coefficient 3"),
//                ModFormula(3.4, "RATE 3.4  60% x TD + 40% x cours. Résultats x coefficient 3"),
//                ModFormula(3.0, "RATE 3.0  40% x TD + 60% x cours. Résultats x coefficient 3"),
//                ModFormula(3.1, "RATE 3.1  40% x TP + 60% x cours. Résultats x coefficient 3"),
//                ModFormula(3.5, "RATE 3.5  50% x cours + 25% x TD + 25% x TP. Résultats x coefficient 3"),
//                ModFormula(3.3, "RATE 3.3  50% x cours + 50% x average of TD and TP. Résultats x coefficient 3"),
//                ModFormula(3.6, "RATE 3.6  60% x cours + 40% x average of TD and TP. Résultats x coefficient 3"),
//
//                ModFormula(4.1, "RATE 4.1  40% x TD + 60% x cours. Résultats x coefficient 4"),
//                ModFormula(4.2, "RATE 4.2  40% x TP + 60% x cours. Résultats x coefficient 4"),
//                ModFormula(4.0, "RATE 4.0  50% x cours + 25% x TD + 25% x TP. Résultats x coefficient 4"),
//
//                ModFormula(5.1, "RATE 5.1  TP x coefficient 5"),
//                ModFormula(5.0, "RATE 5.0  40% x TD + 60% x cours. Résultats x coefficient 5"),
//
//                ModFormula(6.1, "RATE 6.1  40% x TD + 60% x cours. Résultats x coefficient 6"),
//                ModFormula(6.0, "RATE 6.0  50% x cours + 25% x TD + 25% x TP. Résultats x coefficient 6"),
            )
            val cb1 = myCourseDialog.findViewById<CheckBox>(R.id.cb1)
            val cb2 = myCourseDialog.findViewById<CheckBox>(R.id.cb2)
            val cb3 = myCourseDialog.findViewById<CheckBox>(R.id.cb3)
            val cb4 = myCourseDialog.findViewById<CheckBox>(R.id.cb4)

            val checkBoxes = listOf(
                cb1,
                cb2,
                cb3,
                cb4
            )

            var selectedFormula = 0

            checkBoxes.forEach { checkBox ->
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectedFormula = checkBoxes.indexOf(checkBox)

                        checkBoxes.forEach {
                            if (it != checkBox) it.isChecked = false
                        }
                    }
                }
            }


//            var selectedFormula = ""

//            val adapter = ModFormulaAdapter(this@AddSpecialityToBeCalculated, formulas) { formula ->
//
////                val result = getModValNew(
////                    rate = formula.rate,
////                    TD = 0.0,
////                    TP = 0.0,
////                    cours = 0.0
////                )
//
//                selectedFormula = formula.rate.toString()
//
//                Log.e("selectedFormula", selectedFormula)
//            }



//            recyclerView.layoutManager = LinearLayoutManager(this@AddSpecialityToBeCalculated)
//            recyclerView.adapter = adapter


            val courseCB = myCourseDialog.findViewById<CheckBox>(R.id.checkBoxCourse)
            val tdCB = myCourseDialog.findViewById<CheckBox>(R.id.checkBoxTD)
            val tpCB = myCourseDialog.findViewById<CheckBox>(R.id.checkBoxTP)
            val exit = myCourseDialog.findViewById<ImageButton>(R.id.exit)
            val send = myCourseDialog.findViewById<Button>(R.id.send)

            val courseLL = myCourseDialog.findViewById<LinearLayout>(R.id.CourseLL)
            val tDLL = myCourseDialog.findViewById<LinearLayout>(R.id.tDLL)
            val tPLL = myCourseDialog.findViewById<LinearLayout>(R.id.tPLL)


            var courseCoeff = 0
            var coursePer = 0

            var tDCoeff = 0
            var tDPer = 0

            var tPCoeff = 0
            var tPPer = 0



            exit.setOnClickListener {
                courseDialog.dismiss()
            }

            courseCB.setOnClickListener {
                if (courseCB.isChecked) {
                    courseLL.visibility = LinearLayout.VISIBLE
//                    tDLL.visibility = LinearLayout.GONE
//                    tPLL.visibility = LinearLayout.GONE
                } else {
                    courseLL.visibility = LinearLayout.GONE
                }

            }
            tdCB.setOnClickListener {
//                if (tdCB.isChecked && !courseCB.isChecked) {
                if (tdCB.isChecked) {
//                    courseLL.visibility = LinearLayout.GONE
                    tDLL.visibility = LinearLayout.VISIBLE
//                    tPLL.visibility = LinearLayout.GONE
                } else {
                    tDLL.visibility = LinearLayout.GONE
                }
            }

            tpCB.setOnClickListener {
//                if (tpCB.isChecked && !courseCB.isChecked) {
                if (tpCB.isChecked) {
//                    courseLL.visibility = LinearLayout.GONE
//                    tDLL.visibility = LinearLayout.GONE
                    tPLL.visibility = LinearLayout.VISIBLE
                } else {
                    tPLL.visibility = LinearLayout.GONE
                }
            }


            send.setOnClickListener {
                val coureNameET = myCourseDialog.findViewById<EditText>(R.id.editText)
                val coeffiecientET = myCourseDialog.findViewById<EditText>(R.id.editCoef)
                val percentageCourseET = myCourseDialog.findViewById<EditText>(R.id.editPerCourse)
                val percentageTDET = myCourseDialog.findViewById<EditText>(R.id.editPerTD)
                val percentageTPET = myCourseDialog.findViewById<EditText>(R.id.editPerTP)

                var nameOfTheCourse = ""
                var entredCoeff = 0
                var entredPerCourse = 0.0
                var entredPerTD = 0.0
                var entredPerTP = 0.0




                // Check if the text is empty
                if (coureNameET.text.toString().trim().isEmpty()) {
                    coureNameET.error = getString(R.string.this_field_is_required)
                    coureNameET.requestFocus()

                    return@setOnClickListener
                }else {
                    nameOfTheCourse = coureNameET.text.trim().toString()
                }

                if (coeffiecientET.text.toString().trim().isEmpty()) {
                    coeffiecientET.error = getString(R.string.this_field_is_required)
                    coeffiecientET.requestFocus()

                    return@setOnClickListener
                }else {
                    entredCoeff = coeffiecientET.text.trim().toString().toInt()
                }
                if (courseCB.isChecked) {
//                    courseCoeff = myCourseDialog.findViewById<EditText>(R.id.editCoefCourse).text.trim().toString().toInt()
//                coursePer =
//                    myCourseDialog.findViewById<EditText>(R.id.editPerCourse).text.trim().toString()
//                        .toInt()

                    if (percentageCourseET.text.toString().trim().isEmpty()) {
                        percentageCourseET.error = getString(R.string.this_field_is_required)
                        percentageCourseET.requestFocus()

                        return@setOnClickListener
                    }
                    entredPerCourse = percentageCourseET.text.trim().toString().toDouble()

//                    licenseMODULES.add(MODULE("Cours", 3.0))
                }

                if (tdCB.isChecked) {
//                    tDCoeff =
//                        myCourseDialog.findViewById<EditText>(R.id.editCoefTD).text.trim().toString()
//                            .toInt()
//                tDPer = myCourseDialog.findViewById<EditText>(R.id.editPerTD).text.trim().toString()
//                    .toInt()

                    if (percentageTDET.text.toString().trim().isEmpty()) {
                        percentageTDET.error = getString(R.string.this_field_is_required)
                        percentageTDET.requestFocus()

                        return@setOnClickListener
                    }

                    entredPerTD = percentageTDET.text.trim().toString().toDouble()
                }

                if (tpCB.isChecked) {
//                    tPCoeff =
//                        myCourseDialog.findViewById<EditText>(R.id.editCoefTP).text.trim().toString()
//                            .toInt()
//                tPPer = myCourseDialog.findViewById<EditText>(R.id.editPerTP).text.trim().toString()
//                    .toInt()

                    if (percentageTPET.text.toString().trim().isEmpty()) {
                        percentageTPET.error = getString(R.string.this_field_is_required)
                        percentageTPET.requestFocus()

                        return@setOnClickListener
                    }

                    entredPerTP = percentageTPET.text.trim().toString().toDouble()
                }

                if (!courseCB.isChecked && !tdCB.isChecked && !tpCB.isChecked) {

                    Toast.makeText(this,
                        getString(R.string.veuillez_s_lectionner_au_moins_une_option_cours_td_ou_tp), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (!cb1.isChecked && !cb2.isChecked && !cb3.isChecked && !cb4.isChecked) {

                    Toast.makeText(this,
                        getString(R.string.veuillez_s_lectionner_au_moins_une_formule), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                coursesList.add(CoursesData( nameOfTheCourse, entredCoeff, entredPerCourse, entredPerTD, entredPerTP, selectedFormula))

                myModulesAdapter =
                    CustomSpecialityRVA(this@AddSpecialityToBeCalculated, coursesList)


                myrv.layoutManager = GridLayoutManager(this, 1)
                myrv.adapter = myModulesAdapter

                courseDialog.dismiss()
            }
        }


//        val inflater = layoutInflater
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

        val dialog: AlertDialog = builder.create()
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

        findViewById<TextView>(R.id.calculatingTV).setOnClickListener {

        if (coursesList.isNotEmpty()) {

            initEmoji(mydialog)

            // Check if the user has filled all visible ETs
            if (checkETs(myModulesAdapter) == false) {
                Toast.makeText(this,
                    getString(R.string.veuillez_remplir_tous_les_champs), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val average = calculateTheAverage()

            Log.e("average", average.toString())
//            fun testBYelement(allORnot: Boolean) {
////                    Log.e("testBYelement", "testBYelementtestBYelementtestBYelementtestBYelement")
//                for (i in 0 until myModulesAdapter.mData!!.size) {
//                    if ((pojoArrayList[i].getexistCOURS() == true) && (pojoArrayList[i].getexistTD() == true) && (pojoArrayList[i].getexistTP() == false)) {
//                        if (pojoArrayList[i].getCourseMARK() == 15.0 && pojoArrayList[i].getTdMARK() == 10.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقاط الخاصة بدروس و TD لمقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        } else if (pojoArrayList[i].getTdMARK() == 10.0 && pojoArrayList[i].getCourseMARK() != 15.0) {
//                            average = 25.0
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بـ TD مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            voidCOUNTER += 1
//                            if (allORnot) {
//                                break
//                            }
//
//                        } else if (pojoArrayList[i].getTdMARK() != 10.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        }
//                    } else if ((pojoArrayList[i].getexistCOURS() == true) && (pojoArrayList[i].getexistTP() == true) && (pojoArrayList[i].getexistTD() == false)) {
//                        if (pojoArrayList[i].getTpMARK() == 25.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقاط الخاصة بدروس و TP لمقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        } else if (pojoArrayList[i].getTpMARK() == 25.0 && pojoArrayList[i].getCourseMARK() != 15.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بـ TP مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        } else if (pojoArrayList[i].getTpMARK() != 25.0 && pojoArrayList[i].getCourseMARK() == 15.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        }
//                    } else if (pojoArrayList[i].getexistCOURS() == true && pojoArrayList[i].getexistTP() == false && pojoArrayList[i].getexistTD() == false) {
//                        if (pojoArrayList[i].getCourseMARK() == 15.0) {
//                            average = 25.0
//                            voidCOUNTER += 1
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بدروس مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        }
//                    } else if (pojoArrayList[i].getexistTP() == true && pojoArrayList[i].getexistCOURS() == false) {
//                        if (pojoArrayList[i].getTpMARK() == 25.0) {
//                            average = 25.0
//                            voidCOUNTER += 1.0
//                            prbPOS =
//                                "لقد نسيت إدخال النقطة الخاصة بـ TP مقياس " + "\n" + pojoArrayList[i].getmoduleName()
//                                    .toString()
//                            if (allORnot) {
//                                break
//                            }
//                        }
//                    }
//                }
//            }
//            if (session == 0) {
//                testBYelement(false)
//                if (voidCOUNTER > 1.0) {
//                    prbPOS = "يبدو أنك لم تملئ جميع الخانات يا صديقي"
//                }
//                session = 1
//            } else {
//                testBYelement(true)
//            }

            val rvCAL: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
            )
//        rvCAL.addRule(RelativeLayout.ABOVE,R.id.resulat_RL)
            rvCAL.addRule(RelativeLayout.BELOW, R.id.textViewSPECIALITY)
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            if (average == 25.0) {
                findViewById<TextView>(R.id.errorTextView).visibility = View.VISIBLE
                findViewById<TextView>(R.id.errorTextView).setBackgroundColor(Color.BLACK)
                findViewById<TextView>(R.id.errorTextView).text = "ERROR"
                findViewById<TextView>(R.id.calculatingTV).text = getString(R.string.calculer)
            } else {
                findViewById<TextView>(R.id.errorTextView).visibility = View.GONE
                findViewById<TextView>(R.id.calculatingTV).visibility = View.VISIBLE
                findViewById<TextView>(R.id.calculatingTV).text = df.format(average).toString()
            }
            if (average != oldMoy) {
                closeKeyboard()
            }
            if (average >= 10.0 && findViewById<TextView>(R.id.calculatingTV).text != "..." && findViewById<TextView>(
                    R.id.errorTextView
                ).visibility == View.GONE && average != oldMoy
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


            oldMoy = average

        }else {
            Toast.makeText(this@AddSpecialityToBeCalculated,
                getString(R.string.veuillez_remplir_tous_les_champs), Toast.LENGTH_SHORT).show()
        }

        }
    }

    private var oldMoy = 0.0
    private var average = 0.0
    private lateinit var mp: MediaPlayer
    private var imm: InputMethodManager? = null
    override fun onResume() {
        super.onResume()
        oldMoy = 0.0
        average = 0.0
        findViewById<TextView>(R.id.calculatingTV).text = getString(R.string.calculer)
    }
    
    private fun closeKeyboard(): Boolean {
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View? = this.currentFocus
        if (view != null) {
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        } else {
            return false
        }
    }
}