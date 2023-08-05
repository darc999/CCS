package com.example.ccs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    private EditText username,password;
    private Button login;
    private DBhelper Db;
    protected  String adminusrname,adminpassword="admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findview();
        logginin();

    }
    public void findview(){
        username=(EditText) findViewById(R.id.username1);
        password=(EditText) findViewById(R.id.repassword1);
        login=(Button) findViewById(R.id.btnsu1);
        Db=new DBhelper(this);

    }
    private void logginin(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= username.getText().toString();
                String pw=password.getText().toString();


                if(user.equals("")||pw.equals(""))
                    Toast.makeText(loginActivity.this, "FILL ALL FIELDS!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkup=Db.checkpass(user,pw);
                    if (checkup==true){
                        Toast.makeText(loginActivity.this, "LOGIN SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }else{
                        if(user.equals(adminusrname)||pw.equals(adminpassword)){
                            Intent intent =new Intent(getApplicationContext(),adminActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(loginActivity.this, "INVALID DETAILS", Toast.LENGTH_SHORT).show();
                        }}
                }
                username.setText("");
                password.setText("");


            }
        });
    }
}