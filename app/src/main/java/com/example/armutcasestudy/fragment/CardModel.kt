package com.example.armutcasestudy.fragment

import java.io.Serializable

class CardModel(
    val cardId: String?,
    val name: String?,
    val type: String?,
    val cost: String?,
    val playerClass: String?,
    val text: String?,
    val imgUrl: String?,
    val rarity: String?
) : Serializable