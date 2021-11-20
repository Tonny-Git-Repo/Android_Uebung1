package de.thm.ap.records.model


data class Record(var moduleNum: String?= null, var moduleName: String?= null, var year: Int? = 2015, var isSummerTerm: Boolean?= false,
               var isHalfWeighted: Boolean?= false, var crp: Int?= null, var mark: Int?= 0) {
    var id: Int? = null
}