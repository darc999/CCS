package com.example.ccs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class cupcakeActivity extends AppCompatActivity {
    EditText cupname,price,stock,catid,cuppid;
    Button addcup,viewcup,cuppdate,cupdel,cupsear;
    //ImageView imgcup;

    DBhelper Db;
    public static final int CAMERA_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
   /* String[]storagePermission;
    String[]cameraPermission;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcake);
       /* ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);*/
       /* cameraPermission=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};*/
        //imgcup=(ImageView)findViewById(R.id.imgcupc);

        findviewid();
        addcupcakes();
        viewcupcakes();
        updatecupcakes();
        deletecupcakes();
        searchcupcakes();

  /* imgcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int img=0;
                if(img==0){
                    if(!checkCameraPermisson()){
                        requestCameraPermisson();
                    }else{
                        pickFromGallery();
                    }}
                    else if(img==1){
                        if(!checkStoragePermisson()){

                            requestStoragePermisson();
                        }
                        else{
                            pickFromGallery();
                        }

                    }



            }
        });


    }

    private void requestStoragePermisson() {
        requestPermissions(storagePermission,STORAGE_REQUEST);
    }

    private boolean checkStoragePermisson() {
        boolean r= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return r;
    }

    private void pickFromGallery() {
        //CropImage.activity().start(this);
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);*/


    }
/*    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {


        }
    }


    private void requestCameraPermisson() {
        requestPermissions(cameraPermission,CAMERA_REQUEST);
    }

    private boolean checkCameraPermisson() {
        boolean rs= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean rs2= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return rs&&rs2;
    }



    private byte[] ImageViwToByte(ImageView imgcup) {
        Bitmap bitmap=((BitmapDrawable)imgcup.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return  bytes;
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if(grantResults.length>0){
                    boolean camera_a=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storage_a=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(camera_a&storage_a){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Enable Permissons", Toast.LENGTH_SHORT).show();
                    }
                }
            }break;
            case STORAGE_REQUEST:{
                if(grantResults.length>0){
                    boolean storage=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                    if(storage){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Enable Permissons", Toast.LENGTH_SHORT).show();
                    }
                }

        }break;
    }
}*/
public void showitem(String title,String msg){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(msg);
    builder.show();

}
public void findviewid(){
    cupname=(EditText) findViewById(R.id.cupname);
    price=(EditText) findViewById(R.id.cuprice);
    stock=(EditText) findViewById(R.id.cupstock);
    catid=(EditText) findViewById(R.id.catid);
    addcup=(Button) findViewById(R.id.addcup);
    viewcup=(Button) findViewById(R.id.viewcup);
    cuppdate=(Button) findViewById(R.id.cuppdate);
    cuppid=(EditText) findViewById(R.id.cuppid);
    cupdel=(Button) findViewById(R.id.cupdel);
    cupsear=(Button) findViewById(R.id.cupsear);
    Db=new DBhelper(this);
}
public void addcupcakes(){
    addcup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String cn= cupname.getText().toString();
            //String prc=price.getText().
            int prc = Integer.parseInt(price.getText().toString());
            // String ctid=catid.getText().toString();
            int ctid = Integer.parseInt(catid.getText().toString());
            int stocks = Integer.parseInt(stock.getText().toString());


            Boolean insert=Db.addcup(cn,prc,stocks,ctid);
            if(insert==true){
                Toast.makeText(cupcakeActivity.this, "ADDED!!", Toast.LENGTH_SHORT).show();
            }
               /*
                ContentValues contentValues=new ContentValues();
                contentValues.put("IMG",ImageViwToByte(imgcup));
                SQLiteDatabase sqli=Db.getWritableDatabase();
                Long insimg=sqli.insert("cupcakess",null,contentValues);
                if(insimg!=null){
                    Toast.makeText(cupcakeActivity.this, "ADDED IMAGE!", Toast.LENGTH_SHORT).show();
                    imgcup.setImageResource(R.mipmap.ic_launcher);

                }*/

        }
    });

}
public void viewcupcakes(){
    viewcup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cursor cursor=Db.viewcup();
            if(cursor.getCount()==0){


                return;
            }
            else {

                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID :"+cursor.getInt(0)+"\n");
                    buffer.append("NAME:"+cursor.getString(1)+"\n");
                    buffer.append("Price:"+cursor.getInt(2)+"\n");
                    buffer.append("Stock:"+cursor.getInt(3)+"\n");
                    buffer.append("Category ID:"+cursor.getInt(4)+"\n \n");


                }
                showitem("Data",buffer.toString());


            }
        }
    });

}
public void updatecupcakes(){
    cuppdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cid = Integer.parseInt(cuppid.getText().toString());
            String cn= cupname.getText().toString();
            //String prc=price.getText().
            int prc = Integer.parseInt(price.getText().toString());
            // String ctid=catid.getText().toString();
            int ctid = Integer.parseInt(catid.getText().toString());
            int stocks = Integer.parseInt(stock.getText().toString());
            Boolean cuppdate= Db.cuppdate(cid,cupname.getText().toString(),prc,ctid,stocks);
            if(cuppdate==true){
                Toast.makeText(cupcakeActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();
            }
        }
    });

}
public void deletecupcakes(){
    cupdel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cid = Integer.parseInt(cuppid.getText().toString());
            Integer cupdel=Db.cupdelete(cid);
            if(cupdel>0){
                Toast.makeText(cupcakeActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(cupcakeActivity.this, "NOT FOUND!", Toast.LENGTH_SHORT).show();
            }
        }
    });

}
public void searchcupcakes(){
    cupsear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cid = Integer.parseInt(cuppid.getText().toString());
            Cursor cursor=Db.cupsear(cid);
            if(cursor.getCount()==0)
            {
                Toast.makeText(cupcakeActivity.this, "NOT FOUND!", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID :"+cursor.getInt(0)+"\n");
                    buffer.append("NAME:"+cursor.getString(1)+"\n");
                    buffer.append("Price:"+cursor.getInt(2)+"\n");
                    buffer.append("Stock:"+cursor.getInt(3)+"\n");
                    buffer.append("Category ID:"+cursor.getInt(4)+"\n \n");
                }
                showitem("Data",buffer.toString());
            }
        }
    });

}

}