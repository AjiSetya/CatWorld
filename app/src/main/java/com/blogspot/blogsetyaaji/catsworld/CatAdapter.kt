package com.blogspot.blogsetyaaji.catsworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CatAdapter extends RecyclerView.Adapter<CatAdapter.Holder> implements View.OnClickListener {

    // deklarasi variable data dari home fragment
    private ArrayList<HashMap<String, String>> listData;
    private Context context;

    // penangkap data dari home fragment
    CatAdapter(Context activity, ArrayList<HashMap<String, String>> list) {
        // data dari home fragment di masukkan ke dalam variable adapter
        listData = list;
        context = activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // memasang layout item ke dalam adapter
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat, viewGroup, false);
        // memasukkan  layout ke dalam viewhlder
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.txtnamae.setText(listData.get(i).get("nama"));
        holder.txtorigin.setText(listData.get(i).get("asal"));
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtnamae, txtorigin;

        Holder(@NonNull View itemView) {
            super(itemView);

            txtnamae = itemView.findViewById(R.id.txt_name);
            txtorigin = itemView.findViewById(R.id.txt_origin);
        }
    }
}
