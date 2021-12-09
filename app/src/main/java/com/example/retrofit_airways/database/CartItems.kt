package com.example.retrofit_airways.database

import com.example.retrofit_airways.model.ModelResponse

class CartItems {

    companion object{
        var itemsList= mutableListOf<ModelResponse>()

        var itemsCount= mutableListOf<Int>()

    }
}