package com.example.databasesqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    private static final String dbname="dbcw";
    private static  final int dbversion=1;
    String createtable="create table tbbook(" +
            "bid TEXT(20) primary key," +
            "bname TEXT(50)," +
            "price INTERGER," +
            "page INTERGER);";
    public Mydatabase(@Nullable Context context) {

        super(context, dbname, null, dbversion);
    }

//    public Mydatabase(Context context) {
//        super(context,dbname,null,dbversion);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtable);

    }


    public Cursor SelectData(String bid){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql="select * from tbbook where bid='"+bid+"'";

            Cursor cur = db.rawQuery(sql,null);

            return cur;

        }catch (Exception ex){
            return null;
        }

    }
    public Cursor SelectAllData(){
        try {
            SQLiteDatabase db =this.getReadableDatabase();
            String sql="Select * from tbbook";
            Cursor cursor =db.rawQuery(sql,null);
            return cursor;
        }catch (Exception ex){
            return null;
        }
    }



//    public Cursor SelectData(String bid){
//        try {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String sql="select * from tbbook where bid="+bid+"";
//            Cursor cur = db.rawQuery(sql,null);
//            return cur;
//
//        }catch (Exception ex){
//            return null;
//        }
//    }
//    public Cursor SelectAllData(){
//        try {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String sql = "select * from tbbook";
//            Cursor cur = db.rawQuery(sql,null);
//            return cur;
//
//        }catch (Exception ex){
//            return null;
//        }
//    }


    public int DeleteData(String bid){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "Delete from tbbook where bid=?";
            SQLiteStatement stm = db.compileStatement(sql);
            stm.bindString(1,bid);

            int r = stm.executeUpdateDelete();
            db.close();
            return  r;


        }catch (Exception ex){
            return -1;
        }
    }
    public long UpdateData(String bid, String dbname, String price,String page){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "Update tbbook set bname=?,price=?,page=? where bid=?";
            SQLiteStatement stm = db.compileStatement(sql);
            stm.bindString(4,bid);
            stm.bindString(1,dbname);
            stm.bindString(2,price);
            stm.bindString(3,page);
            int r = stm.executeUpdateDelete();
            db.close();
            return  r;


        }catch (Exception ex){
            return -1;
        }
    }

    public long EditData(String bid,String dbname,String price,String page){
        try {
            SQLiteDatabase db =this.getWritableDatabase();
            String sql="Update tbbook set bname=?,price=?,page=? where bid=?";
            SQLiteStatement stm = db.compileStatement(sql);
            stm.bindString(1,dbname);
            stm.bindString(2,price);
            stm.bindString(3,page);
            stm.bindString(4,bid);

            long r= stm.executeUpdateDelete();
            db.close();
            return r;


        }catch (Exception ex){
            return -1;
        }

    }




    public long InsertData(String bid, String dbname, String price,String page){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "Insert Into tbbook values(?,?,?,?)";
            SQLiteStatement stm = db.compileStatement(sql);
            stm.bindString(1,bid);
            stm.bindString(2,dbname);
            stm.bindString(3,price);
            stm.bindString(4,page);
            long r = stm.executeInsert();
            db.close();
            return  r;


        }catch (Exception ex){
            return -1;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

