package com.medanis.fnecliscalcultricedumoyennestlicensemaster.models

class CoursesData(
    courseName: String,
    coeffiecient: Int,
    percentageCourse: Double,
    percentageTD: Double,
    percentageTP: Double,
    formula: Int
) {

    private var courseName: String? = courseName
    private var formula: Int? = formula
    private var coeffiecient: Int? = coeffiecient
    private var percentageCourse: Double? = percentageCourse
    private var percentageTD: Double? = percentageTD
    private var percentageTP: Double? = percentageTP
//    private var isCourseSelected: Boolean? = isCourseSelected
//    private var isTDSelected: Boolean? = isTDSelected
//    private var isTPSelected: Boolean? = isTPSelected


    private var CourseMARK: Double = 25.0
    private var TdMARK: Double = 25.0
    private var TpMARK: Double = 25.0
    private var courseAverage: Double = 25.0

    private var existCOURS: Boolean = true
    private var existTD: Boolean = true
    private var existTP: Boolean = true

    var viewType: Int = 1
    fun getviewType(): Int? {
        return viewType
    }

    fun setviewType(viewType: Int) {
        this.viewType = viewType
    }

    fun getIsChangedCourseMARK(): Double? {
        return CourseMARK
    }

    fun getIsChangedTdMARK(): Double? {
        return TdMARK
    }

    fun getIsChangedTpMARK(): Double? {
        return TpMARK
    }


    fun getCourseName(): String? {
        return courseName
    }

    fun setCourseName(courseName: String) {
        this.courseName = courseName
    }

    fun getFormula(): Int? {
        return formula
    }

    fun setFormula(formula: Int) {
        this.formula = formula
    }

    fun getCoeffiecient(): Int? {
        return coeffiecient
    }

    fun setCoeffiecient(coeffiecient: Int) {
        this.coeffiecient = coeffiecient
    }

    fun getPercentageCourse(): Double? {
        return percentageCourse
    }

    fun setPercentageCourse(percentageCourse: Double) {
        this.percentageCourse = percentageCourse
    }

    fun getPercentageTD(): Double? {
        return percentageTD
    }

    fun setPercentageTD(percentageTD: Double) {
        this.percentageTD = percentageTD
    }

    fun getPercentageTP(): Double? {
        return percentageTP
    }

    fun setPercentageTP(percentageTP: Double) {
        this.percentageTP = percentageTP
    }

    fun getCourseMARK(): Double? {
        return CourseMARK
    }

    fun setCourseMARK(CourseMARK: Double) {
        this.CourseMARK = CourseMARK
        setCourseAverage()
    }

    fun getTdMARK(): Double? {
        return TdMARK
    }

    fun setTdMARK(TdMARK: Double) {
        this.TdMARK = TdMARK
        setCourseAverage()
    }

    fun getTpMARK(): Double? {
        return TpMARK
    }

    fun setTpMARK(TpMARK: Double) {
        this.TpMARK = TpMARK
        setCourseAverage()
    }

    fun getexistCOURS(): Boolean? {
        return existCOURS
    }

    fun setexistCOURS(existCOURS: Boolean) {
        this.existCOURS = existCOURS
    }

    fun getexistTD(): Boolean? {
        return existTD
    }

    fun setexistTD(existTD: Boolean) {
        this.existTD = existTD
    }

    fun getexistTP(): Boolean? {
        return existTP
    }

    fun setexistTP(existTP: Boolean) {
        this.existTP = existTP
    }

    fun getCourseAverage(): Double? {
        return courseAverage
    }

    fun setCourseAverage(
        formula: Int = getFormula()!!,
        coeffiecient: Int = getCoeffiecient()!!,
        percentageCourse: Double = getPercentageCourse()!!,
        percentageTD: Double = getPercentageTD()!!,
        percentageTP: Double = getPercentageTP()!!,
        courseMark: Double = getCourseMARK()!!,
        tdMark: Double = getTdMARK()!!,
        tpMark: Double = getTpMARK()!!
    ) {
        courseAverage = calculateCourseAverage(
            formula, coeffiecient, percentageCourse, percentageTD,
            percentageTP, courseMark, tdMark, tpMark
        )!!
    }


//    fun getIsCourseSelected(): Boolean? {
//        return isCourseSelected
//    }
//
//    fun setIsCourseSelected(isCourseSelected: Boolean) {
//        this.isCourseSelected = isCourseSelected
//    }
//
//    fun getIsTDSelected(): Boolean? {
//        return isTDSelected
//    }
//
//    fun setIsTDSelected(isTDSelected: Boolean) {
//        this.isTDSelected = isTDSelected
//    }
//
//    fun getIsTPSelected(): Boolean? {
//        return isTPSelected
//    }
//
//    fun setIsTPSelected(isTPSelected: Boolean) {
//        this.isTPSelected = isTPSelected
//    }


    fun calculateCourseAverage(
        formula: Int,
        coeffiecient: Int,
        percentageCourse: Double = 0.0,
        percentageTD: Double = 0.0,
        percentageTP: Double = 0.0,
        courseMark: Double = 0.0,
        tdMark: Double = 0.0,
        tpMark: Double = 0.0
    ): Double? {
        return (when (formula) {
            0 -> {
                courseMark * coeffiecient * percentageCourse/100 + tdMark * coeffiecient*percentageTD/100 + tpMark * coeffiecient*percentageTP/100
            }

            1 -> {
                ((courseMark * percentageCourse / 100) + (tdMark * percentageTD / 100)) * coeffiecient
            }

            2 -> {
                ((courseMark * percentageCourse / 100) + (tpMark * percentageTP / 100)) * coeffiecient
            }

            3 -> {
                ((courseMark * percentageCourse / 100) + (tdMark * percentageTD / 100) + (tpMark * percentageTP / 100)) * coeffiecient
            }

//            4 -> {
//                courseMark * percentageCourse * coeffiecient
//            }
//
//            5 -> {
//                tdMark * percentageTD * coeffiecient
//            }
//
//            6 -> {
//                tpMark * percentageTP * coeffiecient
//            }
            else -> {
                0.0
            }
        }) as Double?
    }


}