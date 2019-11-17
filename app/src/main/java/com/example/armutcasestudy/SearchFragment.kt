package com.example.armutcasestudy

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment:Fragment(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataset: List<CardModel>
    private lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataset("Saron")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_search,
            container, false
        )

        recyclerView = rootView.findViewById(R.id.search_recyclerview)

        cardAdapter = CardAdapter(dataset, this)
        recyclerView.adapter = cardAdapter


        return rootView
    }


    private fun initDataset(str:String) {

        val call: Call<List<CardModel>> = RetroClient.instance.apiService.getCardList(str)
        dataset = mutableListOf()
        call.enqueue(object : Callback<List<CardModel>> {
            override fun onResponse(call: Call<List<CardModel>>, response: Response<List<CardModel>>) {
                if (response.isSuccessful) {
                    val cardlist: List<CardModel>? = response.body()

                    cardAdapter.dataSet = cardlist!!
                    cardAdapter.notifyDataSetChanged()
                    Log.i("lol123 ", response.raw().toString())

                } else {
                    Toast.makeText(context, response.raw().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onItemClicked(card: CardModel) {
        //fragment
    }
}