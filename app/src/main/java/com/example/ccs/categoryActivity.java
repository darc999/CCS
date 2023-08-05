package com.example.ccs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class categoryActivity extends AppCompatActivity {
    private DBhelper Db;
    private EditText catname,cid;
    private Button addca,VIEW,caup,cadel,casea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        findviewid();
        addcategory();
        viewcategory();
        updatecategory();
        searchcategory();
        deletecategory();
    }

    private void findviewid(){
        catname=(EditText) findViewById(R.id.cname);
        addca=(Button) findViewById(R.id.cal);
        Db=new DBhelper(this);
        VIEW=(Button)findViewById(R.id.ord);
        caup=(Button)findViewById(R.id.caup);
        cid=(EditText)findViewById(R.id.cid);
        cadel=(Button)findViewById(R.id.cadel);
        casea=(Button)findViewById(R.id.casea);
    }

    private void addcategory(){
        addca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cn= catname.getText().toString();
                Boolean insert=Db.addca(cn);
                if(insert==true){
                    Toast.makeText(categoryActivity.this, "ADDED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void viewcategory(){
        VIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=Db.viewca();
                if(cursor.getCount()==0){
                    return;
                }
                else {
                    StringBuffer buffer=new StringBuffer();
                    while (cursor.moveToNext()){
                        buffer.append("ID :"+cursor.getInt(0)+"\n");
                        buffer.append("NAME:"+cursor.getString(1)+"\n \n");
                    }
                    showitem("Data",buffer.toString());
                }
            }
        });
    }
    public void updatecategory(){
        caup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean caupdate= Db.caupdate(cid.getText().toString(),catname.getText().toString());
                if(caupdate==true){
                    Toast.makeText(categoryActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void deletecategory(){
        cadel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer cadelete=Db.cadelete(cid.getText().toString());
                if(cadelete>0){
                    Toast.makeText(categoryActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(categoryActivity.this, "NOT FOUND!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void searchcategory(){
        casea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=Db.caser(cid.getText().toString());
                if(cursor.getCount()==0)
                {
                    Toast.makeText(categoryActivity.this, "NOT FOUND!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer buffer=new StringBuffer();
                    while (cursor.moveToNext()){
                        buffer.append("ID :"+cursor.getInt(0)+"\n");
                        buffer.append("NAME:"+cursor.getString(1)+"\n \n");
                    }
                    showitem("Data",buffer.toString());
                }
            }
        });
    }
    public void showitem(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }


}
