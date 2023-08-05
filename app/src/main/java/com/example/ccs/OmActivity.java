package com.example.ccs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OmActivity extends AppCompatActivity {
    DBhelper Db;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om);
        view=(Button) findViewById(R.id.btns22);
        Db=new DBhelper(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=Db.vieworder();
                if(cursor.getCount()==0){


                    return;
                }
                else {

                    StringBuffer buffer=new StringBuffer();
                    while (cursor.moveToNext()){
                        buffer.append("ORDID :"+cursor.getInt(0)+"\n");
                        buffer.append("CUPID:"+cursor.getString(1)+"\n");
                        buffer.append("quantity:"+cursor.getInt(2)+"\n");
                        buffer.append("Username:"+cursor.getString(3)+"\n");
                        buffer.append("FullPrice:"+cursor.getString(4)+"\n");
                        buffer.append("Address:"+cursor.getString(5)+"\n");
                        buffer.append("PHONENUMBER:"+cursor.getString(6)+"\n \n");


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