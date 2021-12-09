package com.example.retrofit_airways.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.retrofit_airways.R
import com.example.retrofit_airways.database.CartItems
import com.example.retrofit_airways.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPaymentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CartItems.itemsList.clear()
        CartItems.itemsCount.clear()

        val animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        binding.imagePayment.startAnimation(animation)


    }
}