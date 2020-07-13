package com.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.entities.Student;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String dbName = "studentdb2";
    private static int dbVersion = 1;

    private static String studentTable = "student";
    private static String MaSVColumn = "MaSV";
    private static String TenSVColumn = "TenSV";
    private static String DiemColumn = "Diem";
    private static String GioitinhColumn = "Gioitinh";
    private static String ChuyennganhColumn = "Chuyennganh";

    public DatabaseHelper(Context context){
        super(context,dbName,null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + studentTable + "(" +
                MaSVColumn + " text, " +
                TenSVColumn + " text, " +
                DiemColumn + " real, " +
                GioitinhColumn + " text, " +
                ChuyennganhColumn + " text " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + studentTable);
    }

    public boolean create(Student student){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MaSVColumn,student.getMaSV());
            contentValues.put(TenSVColumn,student.getTenSV());
            contentValues.put(DiemColumn, student.getDiem());
            contentValues.put(GioitinhColumn, student.getGioitinh());
            contentValues.put(ChuyennganhColumn, student.getChuyennganh());
            return  sqLiteDatabase.insert(studentTable, null, contentValues) > 0;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update(Student student){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MaSVColumn,student.getMaSV());
            contentValues.put(TenSVColumn,student.getTenSV());
            contentValues.put(DiemColumn, student.getDiem());
            contentValues.put(GioitinhColumn, student.getGioitinh());
            contentValues.put(ChuyennganhColumn, student.getChuyennganh());
            return  sqLiteDatabase.update(studentTable, contentValues, MaSVColumn + " = ?", new String[]{student.getMaSV()}) > 0;
        }catch (Exception e){
            return false;
        }
    }
    public boolean delete(String MaSV){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete(studentTable, MaSVColumn + " = ?", new String[]{MaSV})>0;
        }catch (Exception e){
            return  false;
        }
    }
    public List<Student> listAll(){
        try{
            List<Student> students = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + studentTable, null);
            if(cursor.moveToFirst()){
                students = new ArrayList<Student>();
                do{
                    Student student = new Student();
                    student.setMaSV(cursor.getString(0));
                    student.setTenSV(cursor.getString(1));
                    student.setDiem(cursor.getDouble(2));
                    student.setGioitinh(cursor.getString(3));
                    student.setChuyennganh(cursor.getString(4));
                    students.add(student);
                }
                while (cursor.moveToNext());
            }
            return students;
        }
        catch (Exception e){
            return null;
        }
    }
    public List<Student> search(String keyword){
        try{
            List<Student> students = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + studentTable + " where " + TenSVColumn + " like ?",new String[]{"%"+ keyword +"%"} , null);
            if(cursor.moveToFirst()){
                students = new ArrayList<Student>();
                do{
                    Student student = new Student();
                    student.setMaSV(cursor.getString(0));
                    student.setTenSV(cursor.getString(1));
                    student.setDiem(cursor.getDouble(2));
                    student.setGioitinh(cursor.getString(3));
                    student.setChuyennganh(cursor.getString(4));
                    students.add(student);
                }
                while (cursor.moveToNext());
            }
            return students;
        }
        catch (Exception e){
            return null;
        }
    }
}
