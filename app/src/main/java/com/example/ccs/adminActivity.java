package com.example.ccs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminActivity extends AppCompatActivity {
   private Button CM,CUPM,ORM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findview();
        categorymanagement();
        cupcakemanagement();
        ordermanagement();


    }
    public void findview(){
        CM=(Button) findViewById(R.id.CM);
        CUPM=(Button) findViewById(R.id.CUPM);
        ORM=(Button) findViewById(R.id.ORM);

    }
    public void categorymanagement(){
        CM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),categoryActivity.class);
                startActivity(intent);
            }
        });

    }
    public void cupcakemanagement(){
        CUPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),cupcakeActivity.class);
                startActivity(intent);
            }
        });

    }
    public void ordermanagement(){
        ORM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),OmActivity.class);
                startActivity(intent);
            }
        });

    }
}