package com.example.armutcasestudy.activity

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.armutcasestudy.R

class CardDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val playerClass: TextView = findViewById(R.id.detail_player)
        val rarity: TextView = findViewById(R.id.detail_rarity)
        val type: TextView = findViewById(R.id.detail_type)
        val cost: TextView = findViewById(R.id.detail_cost)
        val description: TextView = findViewById(R.id.detail_description)

        playerClass.text = "Player Class: "+intent.getStringExtra("playerClass")
        rarity.text = "Rarity: "+intent.getStringExtra("rarity")
        type.text = "Type: "+intent.getStringExtra("type")
        cost.text = "Cost: "+intent.getStringExtra("cost")
        description.text = "Description: "+intent.getStringExtra("text")

    }
}