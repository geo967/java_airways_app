package com.example.retrofit_airways.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit_airways.R
import com.example.retrofit_airways.database.CartItems
import com.example.retrofit_airways.databinding.CartBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartBottomSheetDialog:BottomSheetDialogFragment() {

    lateinit var binding: CartBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v:View=LayoutInflater.from(context).inflate(R.layout.cart_bottom_sheet,container,false)
        binding= CartBottomSheetBinding.bind(v)
        var totalPrice=0
        for(i in 0 until CartItems.itemsList.size){
           totalPrice+= CartItems.itemsList[i].established.toInt()*CartItems.itemsCount[i]
        }
        binding.priceInCartId.text="Price : $totalPrice"
        return v
    }
}