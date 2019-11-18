package com.example.armutcasestudy.fragment

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.armutcasestudy.*
import com.example.armutcasestudy.activity.CardDetailActivity
import com.example.armutcasestudy.util.RetroClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment:Fragment(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataset: MutableList<CardModel>
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
        val searchView = rootView.findViewById<SearchView>(R.id.search_view)
        // perform set on query text listener event
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // do something on text submit
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.length >= 3){
                    initDataset(newText)
                }
                return false
            }
        })
        recyclerView = rootView.findViewById(R.id.search_recyclerview)

        cardAdapter = CardAdapter(dataset, this)
        recyclerView.adapter = cardAdapter


        return rootView
    }


    private fun initDataset(str:String) {

        val call: Call<MutableList<CardModel>> = RetroClient.instance.apiService.cardListSearch(str)
        dataset = mutableListOf()
        call.enqueue(object : Callback<MutableList<CardModel>> {
            override fun onResponse(call: Call<MutableList<CardModel>>, response: Response<MutableList<CardModel>>) {
                if (response.isSuccessful) {
                    val cardlist: MutableList<CardModel>? = response.body()

                    cardAdapter.dataSet = cardlist!!
                    recyclerView.adapter?.notifyDataSetChanged()

                } else {
                    cardAdapter.dataSet = mutableListOf()
                    recyclerView.adapter?.notifyDataSetChanged()
                    Toast.makeText(context, "No card is found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MutableList<CardModel>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onItemClicked(card: CardModel) {
        //activity
        val intent = Intent(context, CardDetailActivity::class.java)
        intent.putExtra("playerClass", card.playerClass)
        intent.putExtra("rarity", card.rarity)
        intent.putExtra("type", card.type)
        intent.putExtra("cost", card.cost)
        intent.putExtra("description", card.text)
        startActivity(intent)
    }
}