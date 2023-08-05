package com.example.ccs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myholder>{
   private Context context;
    ArrayList id,name,price,stock;

    CustomAdapter(Context context,ArrayList id,ArrayList name,ArrayList price,ArrayList stock){

        this.context=context;
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;



    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row,parent,false);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, int position) {
        holder.namet.setText(String.valueOf(name.get(position)));
        holder.pricet.setText(String.valueOf(price.get(position)));
        holder.stockt.setText(String.valueOf(stock.get(position)));
        holder.idt.setText(String.valueOf(id.get(position)));


        holder.namet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Integer i= id[0];
                Intent intent=new Intent(context.getApplicationContext(),orderActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("price", price);
                intent.putExtra("stock", stock);
                context.startActivity(intent);



            }
        });


    }


    @Override
    public int getItemCount() {
        return name.size();

    }

    public class myholder extends RecyclerView.ViewHolder {
        TextView idt, namet,pricet,stockt;
        public myholder(@NonNull View itemView) {
            super(itemView);
            namet=itemView.findViewById(R.id.namet);
            pricet=itemView.findViewById(R.id.pricet);
            stockt=itemView.findViewById(R.id.stockt);
            idt=itemView.findViewById(R.id.idt);



        }
    }
}