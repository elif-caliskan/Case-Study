package com.example.armutcasestudy.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.armutcasestudy.R
import com.example.armutcasestudy.activity.CardDetailActivity
import com.example.armutcasestudy.util.RetroClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilterFragment:Fragment(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataset: MutableList<CardModel>
    private lateinit var cardAdapter: CardAdapter
    val arr = arrayOf("Druid", "Hunter", "Mage", "Paladin", "Priest", "Rogue", "Shaman", "Warlock", "Warrior")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataset(arr[0])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_filter,
            container, false
        )
        val spinner = rootView.findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item, arr)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                initDataset(arr[position])
            }

        }


        recyclerView = rootView.findViewById(R.id.filter_recyclerview)

        cardAdapter = CardAdapter(dataset, this)
        recyclerView.adapter = cardAdapter


        return rootView
    }


    private fun initDataset(str:String) {

        val call: Call<MutableList<CardModel>> = RetroClient.instance.apiService.cardListFilter(str)
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