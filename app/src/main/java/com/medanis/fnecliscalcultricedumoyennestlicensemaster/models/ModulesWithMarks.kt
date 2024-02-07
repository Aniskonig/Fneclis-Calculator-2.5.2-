package com.medanis.fnecliscalcultricedumoyennestlicensemaster.models

class ModulesWithMarks(moduleName: String, RATE: Double, CourseMARK : Double, TdMARK: Double,  TpMARK: Double) {

    private var moduleName: String? = moduleName
    private var RATE: Double? = RATE
    private var CourseMARK: Double = 25.0
    private var TdMARK: Double = 25.0
    private var TpMARK: Double = 25.0
    private var Coeff = 1
    private var modVal: Double = 0.0
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

    fun getmoduleName(): String? {
        return moduleName
    }

    fun setmoduleName(moduleName: String) {
        this.moduleName = moduleName
    }


    fun getRATE(): Double? {
        return RATE
    }

    fun setRATE(RATE: Double) {
        this.RATE = RATE
    }

    fun getischangedCourseMARK(): Double? {
        return CourseMARK
    }

    fun getischangedTdMARK(): Double? {
        return TdMARK
    }

    fun getischangedTpMARK(): Double? {
        return TpMARK
    }

    fun getCourseMARK(): Double? {
        return CourseMARK * 0.6
    }

    fun setCourseMARK(CourseMARK: Double) {
        this.CourseMARK = CourseMARK
    }

    fun getTdMARK(): Double? {
        return TdMARK * 0.4
    }

    fun setTdMARK(TdMARK: Double) {
        this.TdMARK = TdMARK
    }

    fun getTpMARK(): Double? {
        return TpMARK
    }

    fun setTpMARK(TpMARK: Double) {
        this.TpMARK = TpMARK
    }

    fun getmodVal(): Double? {
        modVal = 0.0
        if (RATE == 3.0) {
            Coeff = 3
            modVal = ((TdMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 2.0) {
            Coeff = 2
            modVal = ((TdMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 2.1) {
            Coeff = 2
            modVal = ((TpMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 2.2) {
            Coeff = 2
            modVal = CourseMARK * Coeff
        } else if (RATE == 1.0) {
            Coeff = 1
            modVal = CourseMARK
        } else if (RATE == 1.1) {
            modVal = TpMARK
        } else if (RATE == 2.3) {
            Coeff = 2
            modVal = TpMARK * Coeff
        } else if (RATE == 1.2) {
            modVal = (TpMARK * 0.4) + (CourseMARK * 0.6)
        } else if (RATE == 1.3) {
            modVal = (TdMARK * 0.4) + (CourseMARK * 0.6)
        } else if (RATE == 3.1) {
            Coeff = 3
            modVal = ((TpMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 3.2) {
            Coeff = 3
            modVal = TpMARK * Coeff
        } else if (RATE == 3.3) {
            Coeff = 3
            modVal = ((CourseMARK * 0.5) + (((TdMARK + TpMARK) / 2) * 0.5)) * Coeff
        } else if (RATE == 3.4) {
            Coeff = 3
            modVal = ((TdMARK * 0.6) + (CourseMARK * 0.4)) * Coeff
        } else if (RATE == 6.0) {
            Coeff = 6
            modVal = ((CourseMARK * 0.5) + (((TdMARK + TpMARK) / 4))) * Coeff
        } else if (RATE == 4.0) {
            Coeff = 4
            modVal = ((CourseMARK * 0.5) + (((TdMARK + TpMARK) / 4))) * Coeff
        } else if (RATE == 3.5) {
            Coeff = 3
            modVal = ((CourseMARK * 0.5) + (((TdMARK + TpMARK) * 0.5) * 0.5)) * Coeff
        } else if (RATE == 3.6) {
            Coeff = 3
            modVal = ((CourseMARK * 0.6) + (((TdMARK + TpMARK) / 2) * 0.4)) * Coeff
        } else if (RATE == 5.0) {
            Coeff = 5
            modVal = ((TdMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 4.1) {
            Coeff = 4
            modVal = ((TdMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 6.1) {
            Coeff = 6
            modVal = ((TdMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        } else if (RATE == 5.1) {
            Coeff = 5
            modVal = TpMARK * Coeff
        } else if (RATE == 4.2) {
            Coeff = 4
            modVal = ((TpMARK * 0.4) + (CourseMARK * 0.6)) * Coeff
        }
        return modVal
    }

    fun setmodVal(modVal: Double) {
        this.modVal = modVal
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
}