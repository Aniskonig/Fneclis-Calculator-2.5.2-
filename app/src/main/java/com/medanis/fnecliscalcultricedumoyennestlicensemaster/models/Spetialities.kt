package com.medanis.fnecliscalcultricedumoyennestlicensemaster.models

class Spetialities(abriNAME : String, specialityFullName: String, levelYearSemester : String) {

//    private var viewType: Int? = viewType
//    fun getviewType(): Int? {
//        return viewType
//    }
//    fun setviewType(viewType: Int) {
//        this.viewType = viewType
//    }
    private var specialityFullName: String? = specialityFullName
    private var levelYearSemester :String? = levelYearSemester
    fun getspecialityFullName(): String? {
        return specialityFullName
    }

    fun setspecialityFullName(specialityFullName: String) {
        this.specialityFullName = specialityFullName
    }
    var abriNAME: String? = abriNAME

    fun getabriNAME(): String? {
        return abriNAME
    }

    fun setabriNAME(abriNAME: String) {
        this.abriNAME = abriNAME
    }

    fun getlevelYearSemester(): String? {
        return levelYearSemester
    }

    fun setlevelYearSemester(levelYearSemester: String) {
        this.levelYearSemester = levelYearSemester
    }
}

