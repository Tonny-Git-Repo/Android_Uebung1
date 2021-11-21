package de.thm.ap.records.model

import java.io.Serializable

data class Record(var moduleNum: String?= null, var moduleName: String?= null, var year: Int? = 2015, var isSummerTerm: Boolean?= false,
               var isHalfWeighted: Boolean?= false, var crp: Int? = 0 , var mark: Int? = 0): Serializable {
    var id: Int? = null

    override fun toString(): String {
        super.toString()

        return "$moduleName $moduleNum (${ if ( mark!! > 0 ) mark else ""} ${ if ( mark!! > 0 ) "%" else ""} ${crp}crp)"
    }

}