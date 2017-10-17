package com.kanchanproseth.retrofit2.model

/**
 * Created by kanchanproseth on 10/17/17.
 */
object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}