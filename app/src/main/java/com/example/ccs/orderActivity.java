package com.example.ccs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class orderActivity extends AppCompatActivity {
private     ArrayList<String> id,price,stock;
private     EditText cname,cname2,cname3,cname4;
private     Button cal,ord;
private     DBhelper Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ArrayList<String>id = getIntent().getStringArrayListExtra("ID");
        ArrayList<String>price = getIntent().getStringArrayListExtra("price");
        ArrayList<String>stock = getIntent().getStringArrayListExtra("stock");
        String i = id.get(0);
        int id1 = Integer.parseInt(i);
        String p=price.get(0);
        int price1 = Integer.parseInt(p);
        String s=stock.get(0);
        int stk=Integer.parseInt(s);

        findview();
         calculateorder(price1);
         addorder(price1,stk,id1);
    }

    private void addorder(Integer price1,Integer stk,Integer id1) {
        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q=cname.getText().toString();
                int quantity = Integer.parseInt(q);
                int fullprice=quantity*price1;
                int fstk=stk-quantity;

                String name=cname2.getText().toString();
                String Address=cname3.getText().toString();
                String Phonenumber=cname4.getText().toString();
                Boolean insert=Db.insertorder(id1,quantity,name,fullprice,Address,Phonenumber);
                Boolean upstk1=Db.upstk(id1,fstk);
                if(insert==true){
                    Toast.makeText(orderActivity.this, "ADDED", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    private void calculateorder(Integer price1) {
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q=cname.getText().toString();
                int quantity = Integer.parseInt(q);
                int fullprice=quantity*price1;
                StringBuffer buffer=new StringBuffer();

                buffer.append("FullPrice :"+fullprice+"\n");
                showitem("Data",buffer.toString());


            }
        });
    }

    private void findview() {
        cname=(EditText)findViewById(R.id.cname);
        cname2=(EditText)findViewById(R.id.cname2);
        cname3=(EditText)findViewById(R.id.cname3);
        cname4=(EditText)findViewById(R.id.cname4);
        cal=(Button)findViewById(R.id.cal);
        ord=(Button)findViewById(R.id.ord);
        Db=new DBhelper(this);
    }

    public void showitem(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }

}