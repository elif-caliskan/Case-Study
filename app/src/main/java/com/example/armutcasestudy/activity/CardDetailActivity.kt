package com.example.armutcasestudy.activity

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.example.armutcasestudy.R

class CardDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val playerClassText: TextView = findViewById(R.id.detail_player)
        val rarityText: TextView = findViewById(R.id.detail_rarity)
        val typeText: TextView = findViewById(R.id.detail_type)
        val costText: TextView = findViewById(R.id.detail_cost)
        val descriptionText: TextView = findViewById(R.id.detail_text)
        val healthText: TextView = findViewById(R.id.detail_health)
        val attackText: TextView = findViewById(R.id.detail_attack)
        val imageView: ImageView = findViewById(R.id.detail_image)

        val name = intent.getStringExtra("NAME")
        val playerClass = intent.getStringExtra("PLAYERCLASS")
        val rarity = intent.getStringExtra("RARITY")
        val type = intent.getStringExtra("TYPE")
        val cost = intent.getStringExtra("COST")
        val description = intent.getStringExtra("DESCRIPTION")
        val attack = intent.getStringExtra("ATTACK")
        val health = intent.getStringExtra("HEALTH")
        val imgUrl = intent.getStringExtra("IMAGEURL")

        if(name != null){
            supportActionBar?.title = name
        }
        if(playerClass != null){
            playerClassText.text = Html.fromHtml("<b>Player Class :</b> $playerClass")
        }
        else{
            playerClassText.visibility = View.GONE
        }
        if(rarity != null){
            rarityText.text = Html.fromHtml("<b>Rarity :</b> $rarity")
        }
        else{
            rarityText.visibility = View.GONE
        }
        if(type != null){
            typeText.text = Html.fromHtml("<b>Type :</b> $type")
        }
        else{
            typeText.visibility = View.GONE
        }
        if(cost != null){
            costText.text = Html.fromHtml("<b>Cost :</b> $cost")
        }
        else{
            costText.visibility = View.GONE
        }
        if(description != null){

            descriptionText.text = Html.fromHtml(description)
        }
        else{
            descriptionText.visibility = View.GONE
        }
        if(health != null){
            healthText.text = Html.fromHtml("$health <b>Health</b>")
        }
        else{
            healthText.visibility = View.GONE
        }
        if(attack != null){
            attackText.text = Html.fromHtml("$attack <b>Attack</b>")
        }
        else{
            attackText.visibility = View.GONE
        }
        if(imgUrl != null){
            Picasso.get().load(imgUrl).into(imageView)
        }
        else{
            imageView.setImageResource(R.drawable.ic_img_placeholder)
        }
    }
}