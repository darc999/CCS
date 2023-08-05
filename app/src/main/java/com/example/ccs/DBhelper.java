package com.example.ccs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME="login.db";

    public DBhelper( Context context) {
        super(context,"login.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(Username TEXT primary key,Password TEXT)");

        MyDB.execSQL("create Table category(catid INTEGER primary key autoincrement,CategoryName TEXT)");

        MyDB.execSQL("create Table cupcakess(cupid INTEGER primary key autoincrement,cupname TEXT,cuprice INTEGER," +
                "cupstock INTEGER,Catid INTEGER REFERENCES category(catid),IMG blob)");

        MyDB.execSQL("create Table ordr(ordid INTEGER primary key autoincrement,cupid INTEGER REFERENCES cupcakess(cupid)," +
                " quantity INTEGER,Username TEXT REFERENCES users(Username),FullPrice INTEGER,Address TEXT,PHONENUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //MyDB.execSQL("drop Table if exists users");

    }

    public boolean insertdata(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    public boolean checkusername(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkpass(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
       Cursor cursor = MyDB.rawQuery("Select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean addca(String catname){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("CategoryName",catname);
        long result=MyDB.insert("category",null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    public Cursor viewca(){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from category",null);
        return cursor;

    }
    public boolean caupdate(String id,String catname){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("CategoryName",catname);
        contentValues.put("catid",id);
        MyDB.update("category",contentValues,"catid=?",new String[]{id});


        return true;
    }
    public Integer cadelete (String id){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        return MyDB.delete("category","catid=?",new String[]{id});

    }
    public Cursor caser( String id){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from category where catid=?",new String[]{id});
        return cursor;

    }
    public boolean addcup(String name,Integer price,Integer stock,Integer catid){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cupname",name);
        contentValues.put("cuprice",price);
        contentValues.put("cupstock",stock);
        contentValues.put("catid",catid);


        long result=MyDB.insert("cupcakess",null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    public Cursor viewcup(){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from cupcakess",null);
        return cursor;

    }
    public boolean cuppdate(Integer id,String name,Integer price,Integer stock,Integer catid){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cupid",id);
        contentValues.put("cupname",name);
        contentValues.put("cuprice",price);
        contentValues.put("cupstock",stock);
        contentValues.put("catid",catid);

        MyDB.update("cupcakess",contentValues,"cupid=?",new String[]{id.toString()});


        return true;
    }
    public Integer cupdelete (Integer id){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        return MyDB.delete("cupcakess","cupid=?",new String[]{id.toString()});

    }
    public Cursor cupsear( Integer id){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from cupcakess where cupid=?",new String[]{id.toString()});
        return cursor;

    }
   /* public Cursor spn(){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from category",null);
        return cursor;

    }*/
public ArrayList<String>getdata(){
    ArrayList<String> list=new ArrayList<>();
    SQLiteDatabase MyDB=this.getReadableDatabase();
    MyDB.beginTransaction();
    try {


        String slq = "SELECT*FROM category";
        Cursor cursor = MyDB.rawQuery(slq, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Integer cat = cursor.getColumnIndex("CategoryName");
                String catname = cursor.getString(cat);
                list.add(catname);
            }
        }
        MyDB.setTransactionSuccessful();
    }catch (Exception e){
        e.printStackTrace();
    }
    finally {
        MyDB.endTransaction();
        MyDB.close();
    }
    return list;

}
    public Cursor dspl(){

        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from cupcakess",null);
        return cursor;

    }
    public ArrayList<String>getdata2(){
        ArrayList<String> list=new ArrayList<>();
        SQLiteDatabase MyDB=this.getReadableDatabase();
        MyDB.beginTransaction();
        try {


            String slq = "SELECT*FROM category";
            Cursor cursor = MyDB.rawQuery(slq, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Integer cat = cursor.getColumnIndex("catid");
                    String catidd = cursor.getString(cat);
                    list.add(catidd);
                }
            }
            MyDB.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            MyDB.endTransaction();
            MyDB.close();
        }
        return list;

    }
    public Cursor getcatid( String text){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select catid from category where CategoryName=?",new String[]{text});

      // Cursor cursor1 = MyDB.rawQuery("select * from cupcakess where Catid=?", new String[]{cursor.toString()});
        return cursor;


    }
   public  Cursor getcupcakedetails(Integer txt){
       SQLiteDatabase MyDB=this.getWritableDatabase();
       Cursor cursor1 = MyDB.rawQuery("select * from cupcakess where Catid=?", new String[]{txt.toString()});
       return cursor1;
   }
    public boolean insertorder(Integer cupid,Integer quantity,String Username,Integer FullPrice,String Address,String Phonenumber){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cupid",cupid);
        contentValues.put("quantity",quantity);
        contentValues.put("Username",Username);
        contentValues.put("FullPrice",FullPrice);
        contentValues.put("Address",Address);
        contentValues.put("PHONENUMBER",Phonenumber);
        long result=MyDB.insert("ordr",null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    public Cursor vieworder(){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from ordr",null);
        return cursor;

    }
    public boolean upstk(Integer id,Integer stk){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cupid",id);
        contentValues.put("cupstock",stk);


        MyDB.update("cupcakess",contentValues,"cupid=?",new String[]{id.toString()});


        return true;
    }

}
