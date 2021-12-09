package com.example.retrofit_airways.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_airways.adapter.CartAdapter
import com.example.retrofit_airways.database.CartItems
import com.example.retrofit_airways.databinding.CartLayoutBinding
import com.example.retrofit_airways.model.ModelResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CartActivity:AppCompatActivity() {
    lateinit var binding:CartLayoutBinding
    lateinit var myCartAdapter:CartAdapter
    lateinit var list:List<ModelResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= CartLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        list=CartItems.itemsList
        val layoutManager = GridLayoutManager(applicationContext, 1)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        myCartAdapter = CartAdapter(list, applicationContext)
        binding.recyclerView.adapter = myCartAdapter

        BottomSheetBehavior.from(binding.sheetId).apply {
            peekHeight=200
            this.state=BottomSheetBehavior.STATE_COLLAPSED
            var totalPrice=0
            for(i in 0 until CartItems.itemsList.size){
                totalPrice+= CartItems.itemsList[i].established.toInt()*CartItems.itemsCount[i]
            }
            binding.priceInCartId.text="Price : $totalPrice"
            binding.placeOrderButtonId.setOnClickListener {
                val intent= Intent(applicationContext, PaymentActivity::class.java)
                startActivity(intent)
            }

        }



       /* val bt=CartBottomSheetDialog()
        bt.isCancelable=false
        bt.show(supportFragmentManager,"cart bt")*/
   /*     val bottomSheetLayout:LinearLayout=findViewById(R.id.cart_bottomSheet_id)
        val bottomSheetBehavior=BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        })*/
    }
}