package com.example.retrofit_airways.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_airways.view.ExampleBottomSheetDialog
import com.example.retrofit_airways.R
import com.example.retrofit_airways.model.ModelResponse


class MyAdapter(list1: List<ModelResponse>, context: Context,supportFragmentManager: FragmentManager):RecyclerView.Adapter<MyAdapter.MyHolder>() {
    private var list:List<ModelResponse> = list1
    private val support:FragmentManager=supportFragmentManager
    val c=context

    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            val firstId:TextView=itemView.findViewById(R.id.Id_id)
            val userId:TextView=itemView.findViewById(R.id.user_id_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_design,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.firstId.text=list[position].id.toString()
        holder.userId.text=list[position].name
        holder.itemView.setOnClickListener {
            val bottomSheet= ExampleBottomSheetDialog(list[position])
            bottomSheet.show(support,"example_bottom_sheet")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
