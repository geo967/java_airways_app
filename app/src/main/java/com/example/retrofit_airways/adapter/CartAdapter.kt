package com.example.retrofit_airways.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.retrofit_airways.HttpsTrustManager

import com.example.retrofit_airways.R
import com.example.retrofit_airways.database.CartItems
import com.example.retrofit_airways.model.ModelResponse
import com.squareup.picasso.Picasso


class CartAdapter(list:List<ModelResponse>,context: Context):RecyclerView.Adapter<CartAdapter.MyHolder>() {
    val l=list
    val c=context
    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            val name1:TextView=itemView.findViewById(R.id.user_name)
            val est:TextView=itemView.findViewById(R.id.established_id)
           // val web:TextView=itemView.findViewById(R.id.website_id)
            val img:ImageView=itemView.findViewById(R.id.image_In_Cart_id)
            val card:TextView=itemView.findViewById(R.id.count_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.cart_item_design,parent,false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name1.text="Name :${l[position].name}"
     //   holder.web.text=l[position].website
        holder.est.text="Established :${l[position].established}"
        holder.card.text="Count :${CartItems.itemsCount[position].toString()}"

      //  holder.img.setImageResource(R.drawable.ic_shopping_cart)
       // holder.img.setImageURI((l[position].logo).toUri())
      //  Picasso.with(holder.img.context).load(l[position].logo.toUri()).into(holder.img);
        HttpsTrustManager.allowAllSSL()
       Glide.with(holder.img.context)
                .load(l[position].logo)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img)
    }

    override fun getItemCount(): Int {
            return l.size
    }
}