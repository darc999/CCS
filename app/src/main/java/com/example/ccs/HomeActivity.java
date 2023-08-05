package com.example.ccs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private DBhelper Db;
    private Button s,order;
    private ArrayList<String> id,name,price,stock,caid;
    private RecyclerView rw;
    private CustomAdapter customAdapter;
    private CheckBox ck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Db=new DBhelper(this);
        s=(Button)findViewById(R.id.sss);
        String user=getIntent().getStringExtra("user");
        //Toast.makeText(this, user, Toast.LENGTH_SHORT).show();

        ArrayList<String>getdata=Db.getdata();
        Spinner sp=(Spinner) findViewById(R.id.spincat);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spincat_layout,R.id.txt,getdata);
        sp.setAdapter(adapter);
        //String text = sp.getSelectedItem().toString();
       /* Intent intent=new Intent(this,DBhelper.class);
        intent.putExtra("IDK",text);*/
       // Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        rw=findViewById(R.id.displaydata);
        id=new ArrayList<>();
        name=new ArrayList<>();
        price=new ArrayList<>();
        stock=new ArrayList<>();
        caid=new ArrayList<>();



        //dsply();


        /*customAdapter=new CustomAdapter(HomeActivity.this,name,price,stock);
        rw.setAdapter(customAdapter);
        rw.setLayoutManager(new LinearLayoutManager(HomeActivity.this));*/

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = sp.getSelectedItem().toString();
                passcategoryidtocupcake(text1);
                customAdapter=new CustomAdapter(HomeActivity.this,id,name,price,stock);
                rw.setAdapter(customAdapter);
                rw.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            }
        });
        /*if(ck.isChecked()){
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),orderActivity.class);
                    startActivity(intent);
                }
            });
        }*/







    }
    /*void dsply(){

        Cursor cursor=Db.dspl();
        if(cursor.getCount()==0){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                price.add(cursor.getString(2));
                stock.add(cursor.getString(3));
                caid.add(cursor.getString(4));


            }
        }
    }*/
    void cupcakedetials(Integer text){
        Cursor cursor=Db.getcupcakedetails(text);
        if(cursor.getCount()==0){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                price.add(cursor.getString(2));
                stock.add(cursor.getString(3));
                caid.add(cursor.getString(4));
            }
        }
    }
    void passcategoryidtocupcake(String text){
        Cursor cursor=Db.getcatid(text);
        if(cursor.getCount()==0){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

        }else {
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext()){
                    buffer.append("ID :"+cursor.getInt(0)+"\n");
                    Integer categoryid=cursor.getInt(0);
                cupcakedetials(categoryid);//pass
            }// showitem("Data",buffer.toString());
        }
    }
    public void showitem(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }


    /*void test2(Integer text){
        Cursor cursor=Db.test2(text);
        if(cursor.getCount()==0){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

        }else {
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Name :"+cursor.getString(1)+"\n");
            }
            showitem("Data",buffer.toString());
        }

    }*/


}