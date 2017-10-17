package com.kanchanproseth.retrofit2.Services

import com.kanchanproseth.retrofit2.model.CommuneDTO
import com.kanchanproseth.retrofit2.model.Model
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by kanchanproseth on 10/17/17.
 */
interface GetCommuneById {
    @POST("commune")
    fun getCommuneById(@Query("distrId") distrId: Int): Observable<CommuneDTO>

    companion object {
        fun create(): GetCommuneById {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://192.168.7.8:8181/efinance-honda-webservice/efinance/address/")
                    .build()

            return retrofit.create(GetCommuneById::class.java)
        }
    }
}