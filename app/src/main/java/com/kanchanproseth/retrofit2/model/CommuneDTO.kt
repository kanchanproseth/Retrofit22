package com.kanchanproseth.retrofit2.model

import com.google.gson.annotations.SerializedName

/**
 * Created by kanchanproseth on 10/17/17.
 */
class  CommuneDTO{
    @SerializedName("RSLT_MSG")
    var RSLT_MSG: String? = null
    @SerializedName("RSLT_CD")
    var RSLT_CD: Int = 0
    @SerializedName("RSLT_DATA")
    var RSLT_DATA: ArrayList<RSLT>? = null

    class RSLT {
        @SerializedName("ID")
        var ID: Int = 0
        @SerializedName("DESC")
        var DESC: String? = null
        @SerializedName("DESC_EN")
        var DESC_EN: String? = null
        @SerializedName("UPDATE_DATE")
        var UPDATE_DATE: String? = null
        @SerializedName("DISTRICT_ID")
        var DISTRICT_ID: Int = 0
    }
}