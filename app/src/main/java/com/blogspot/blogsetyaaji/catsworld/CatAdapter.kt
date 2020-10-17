package com.blogspot.blogsetyaaji.catsworld

import android.content.Context
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
internal class CatAdapter(val context: Context, val listData: ArrayList<HashMap<String, String>>)
    : RecyclerView.Adapter<CatAdapter.MyViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        // memasang layout item ke dalam adapter
        val view = LayoutInflater.from(context).inflate(R.layout.item_cat, viewGroup, false)
        // memasukkan  layout ke dalam viewhlder
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtnamae.text = listData[position]["nama"]
        holder.txtorigin.text = listData[position]["asal"]
    }

    override fun onClick(view: View) {}

    override fun getItemCount(): Int {
        return listData.size
    }

    internal inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var txtnamae: TextView
        var txtorigin: TextView

        init {
            txtnamae = itemView.findViewById(R.id.txt_name)
            txtorigin = itemView.findViewById(R.id.txt_origin)
        }
    }
}