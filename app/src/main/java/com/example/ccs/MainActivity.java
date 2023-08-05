package com.example.ccs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username,password,repassword;
    private Button signup,login;
    private DBhelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviewbyid();
        login();


    }
    private void findviewbyid(){
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        signup=(Button) findViewById(R.id.btns);
        login=(Button) findViewById(R.id.btnsu);
        Db=new DBhelper(this);

    }
    private void login(){
        signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user= username.getText().toString();
            String pw=password.getText().toString();
            String rpw= repassword.getText().toString();
            if(user.equals("")||pw.equals("")||rpw.equals(""))
                Toast.makeText(MainActivity.this, "Fill All FIELDS", Toast.LENGTH_SHORT).show();
            else{
                if (pw.equals(rpw)){
                    Boolean checkuser=Db.checkusername(user);
                    if (checkuser==false){
                        Boolean insert=Db.insertdata(user,pw);
                        if(insert==true){
                            Toast.makeText(MainActivity.this, "REGISTERED", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent); }
                        else{
                            Toast.makeText(MainActivity.this, "REGISTRATION FAILED!", Toast.LENGTH_SHORT).show(); }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "USER ALREADY EXISTS,LOG IN :)", Toast.LENGTH_SHORT).show(); }
                }
                else{
                    Toast.makeText(MainActivity.this, "PASSWORD NOT MATCHING!", Toast.LENGTH_SHORT).show(); }
            }
            username.setText("");
            password.setText("");
            repassword.setText("");

        }

    });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);

            }
        });

    }
}