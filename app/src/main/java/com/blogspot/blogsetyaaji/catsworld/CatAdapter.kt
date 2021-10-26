package com.blogspot.blogsetyaaji.catsworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

// data dari home fragment di masukkan ke dalam variable adapter
// penangkap data dari home fragment
// deklarasi variable data dari home fragment
internal class CatAdapter(private val listData: ArrayList<HashMap<String, String>>) :
    RecyclerView.Adapter<CatAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) = MyViewHolder(
        // memasang layout item ke dalam adapter
        // memasukkan  layout ke dalam viewhlder
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cat, viewGroup, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtname.text = listData[position][MainActivity.DATA_NAME]
        holder.txtorigin.text = listData[position][MainActivity.DATA_ORIGIN]
    }

    override fun getItemCount() = listData.size

    internal inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var txtname: TextView = itemView.findViewById(R.id.txt_name)
        var txtorigin: TextView = itemView.findViewById(R.id.txt_origin)
    }
}