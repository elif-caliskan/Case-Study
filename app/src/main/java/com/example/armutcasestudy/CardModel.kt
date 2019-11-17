package com.example.armutcasestudy

import java.io.Serializable

class CardModel(
    val cardId: String?,
    val name: String?,
    val type: String?,
    val cost: String?,
    val playerClass: String?,
    val text: String?,
    val imgUrl: String?
) : Serializable