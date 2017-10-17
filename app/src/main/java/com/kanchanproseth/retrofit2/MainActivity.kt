package com.kanchanproseth.retrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.kanchanproseth.retrofit2.Services.GetCommuneById
import com.kanchanproseth.retrofit2.Services.WikiApiService
import com.kanchanproseth.retrofit2.model.CommuneDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_for_row.view.*

class MainActivity : AppCompatActivity() {
    val wikiApiServe by lazy {
        WikiApiService.create()
    }

    val getCommune by lazy {
        GetCommuneById.create()
    }

    var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchBtn.setOnClickListener {
            if (etName.text.toString().isNotEmpty()) {
                beginSearchCommuneById(etName.text.toString().toInt())
            }
        }

    }

    override fun onResume() {
        super.onResume()
    }

//    private fun beginSearch(srsearch: String) {
//        disposable = wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                { result -> tvResult.text = "${result.query.searchinfo.totalhits} result found" },
//                                { error -> Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()}
//                        )
//    }

    private fun beginSearchCommuneById(id: Int){
        disposable = getCommune.getCommuneById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val response: ArrayList<CommuneDTO.RSLT>
                            response = result.RSLT_DATA!!
                            val obj_adapter = CustomAdapter(response)

                            rc.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                            rc.adapter = obj_adapter },
                        { error -> Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()}
                )
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}

class CustomAdapter(val communeList: ArrayList<CommuneDTO.RSLT>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_for_row, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(communeList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return communeList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(commune: CommuneDTO.RSLT) {
            itemView.tvResult_rc.text = "ID:" + commune.ID + "," + commune.DESC_EN

        }
    }

}