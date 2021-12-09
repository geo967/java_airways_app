package com.example.retrofit_airways.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.retrofit_airways.R
import com.example.retrofit_airways.database.CartItems
import com.example.retrofit_airways.databinding.BottomSheetLayoutBinding
import com.example.retrofit_airways.model.ModelResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExampleBottomSheetDialog(list:ModelResponse):BottomSheetDialogFragment() {

   // val s=slogan
    //val h=headQ
    val l=list
    lateinit var binding: BottomSheetLayoutBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v:View=LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,container,false)
        binding= BottomSheetLayoutBinding.bind(v)
        binding.textId.text=l.slogan//s.toString()
        binding.bodyId.text=l.head_quaters//h.toString()
        binding.productWebsiteId.text=l.website

        var count=1
        binding.addItemButtonId.setOnClickListener {
            count++
            binding.addItemButtonId.text="$count items"
        }

        binding.buttonShareId.setOnClickListener {
            Toast.makeText(context,"Added to Cart",Toast.LENGTH_SHORT).show()
            if(CartItems.itemsList.size==0){
                CartItems.itemsList.add(l)
                CartItems.itemsCount.add(count)
            }

            var flag=false
            for(i in 0 until CartItems.itemsList.size){
                if(CartItems.itemsList[i].id==l.id){
                    flag=true
                    CartItems.itemsCount[i]=CartItems.itemsCount[i]+count
                }
            }
            if(!flag){
            CartItems.itemsList.add(l)
            CartItems.itemsCount.add(count)
        }

        }
        return v
    }

}