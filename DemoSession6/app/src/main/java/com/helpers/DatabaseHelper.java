package com.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.entities.Contact;
import com.entities.Work;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String dbName = "contactdb2";
    private static int dbVersion = 1;

    private static String contactTable = "contact";
    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String phoneColumn = "phone";
    private static String addressColumn = "address";
    private static String emailColumn = "email";
    private static String descriptionColumn = "description";

    private static String workTable = "work";
    private static String idCol = "id";
    private static String nameCol = "name";
    private static String creationDateCol = "creationDate";
    private static String descriptionCol = "description";
    private static String priorityCol = "priority";

    public DatabaseHelper(Context context){
        super(context,dbName,null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + contactTable + "(" +
                idColumn + " INTEGER primary key autoincrement, " +
                nameColumn + " text, " +
                phoneColumn + " text, " +
                addressColumn + " text, " +
                emailColumn + " text, " +
                descriptionColumn + " text " +
                ")");
        db.execSQL("create table if not exists " + workTable + "(" +
                idCol + " integer primary key autoincrement, " +
                nameCol + " text, " +
                creationDateCol + " text, " +
                descriptionCol + " text, " +
                priorityCol + " text " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + contactTable);
        db.execSQL("DROP TABLE IF EXISTS " + contactTable);
    }

    public boolean create(Work work){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameCol,work.getName());
            contentValues.put(creationDateCol,work.getCreationDate());
            contentValues.put(descriptionCol,work.getDescription());
            contentValues.put(priorityCol, work.getPriority());
            return sqLiteDatabase.insert(workTable,null,contentValues) > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean update(Work work){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameCol,work.getName());
            contentValues.put(creationDateCol,work.getCreationDate());
            contentValues.put(descriptionCol,work.getDescription());
            contentValues.put(priorityCol, work.getPriority());
            return sqLiteDatabase.update(workTable,contentValues, idCol + " = ?", new String[]{String.valueOf(work.getId())}) > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean erase(int id){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete(workTable, idCol + " = ?",new String[]{String.valueOf(id)})>0;
        }catch (Exception e){
            return false;
        }
    }

    public List<Work> listAll(){
        try {
            List<Work> works = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + workTable, null);
            if(cursor.moveToFirst()){
                works = new ArrayList<Work>();
                do{
                    Work work = new Work();
                    work.setId(cursor.getInt(0));
                    work.setName(cursor.getString(1));
                    work.setCreationDate(cursor.getString(2));
                    work.setDescription(cursor.getString(3));
                    work.setPriority(cursor.getString(4));
                    works.add(work);
                }while (cursor.moveToNext());
            }
            return works;
        }catch (Exception e){
            return null;
        }
    }

    public List<Work> Search(String keyword){
        try {
            List<Work> works = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + workTable + " where " + nameCol + " like ?", new String[]{"%" + keyword + "%"}, null);
            if(cursor.moveToFirst()){
                works = new ArrayList<Work>();
                do{
                    Work work = new Work();
                    work.setId(cursor.getInt(0));
                    work.setName(cursor.getString(1));
                    work.setCreationDate(cursor.getString(2));
                    work.setDescription(cursor.getString(3));
                    work.setPriority(cursor.getString(4));
                    works.add(work);
                }while (cursor.moveToNext());
            }
            return works;
        }catch (Exception e){
            return null;
        }
    }

    public List<Work> find(String min, String max){
        try {
            List<Work> works = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = simpleDateFormat.parse(min);
            Date end = simpleDateFormat.parse(max);
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + workTable + " where date(" + creationDateCol + ") between date(?) and date(?)",new String[]{min,max},null);
            if(cursor.moveToFirst()){
                works = new ArrayList<Work>();
                do{
                    Work work = new Work();
                    work.setId(cursor.getInt(0));
                    work.setName(cursor.getString(1));
                    work.setCreationDate(cursor.getString(2));
                    work.setDescription(cursor.getString(3));
                    work.setPriority(cursor.getString(4));
                    works.add(work);
                }
                while (cursor.moveToNext());
            }
            return works;
        }catch (Exception e){
            return null;
        }
    }

    public boolean create(Contact contact){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn,contact.getName());
            contentValues.put(phoneColumn,contact.getPhone());
            contentValues.put(addressColumn, contact.getAddress());
            contentValues.put(emailColumn,contact.getEmail());
            contentValues.put(descriptionColumn,contact.getDescription());
            return sqLiteDatabase.insert(contactTable,null,contentValues) > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(int id){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete(contactTable, idColumn + " = ?",new String[]{String.valueOf(id)})>0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean update(Contact contact){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn,contact.getName());
            contentValues.put(phoneColumn,contact.getPhone());
            contentValues.put(addressColumn, contact.getAddress());
            contentValues.put(emailColumn,contact.getEmail());
            contentValues.put(descriptionColumn,contact.getDescription());
            return sqLiteDatabase.update(contactTable,contentValues,idColumn+ " = ?",new String[]{String.valueOf(contact.getId())})> 0;
        }catch (Exception e){
            return false;
        }
    }

    public List<Contact> findAll(){
        try {
            List<Contact> contacts = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + contactTable, null);
            if(cursor.moveToFirst()){
                contacts = new ArrayList<Contact>();
                do{
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contact.setEmail(cursor.getString(4));
                    contact.setAddress(cursor.getString(3));
                    contact.setDescription(cursor.getString(5));
                    contacts.add(contact);
                }while (cursor.moveToNext());
            }
            return contacts;
        }catch (Exception e){
            return null;
        }
    }

    public List<Contact> search(String keyword){
        try {
            List<Contact> contacts = null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + contactTable + " where " + nameColumn + " like ?", new String[]{"%" + keyword + "%"}, null);
            if(cursor.moveToFirst()){
                contacts = new ArrayList<Contact>();
                do{
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contact.setEmail(cursor.getString(4));
                    contact.setAddress(cursor.getString(3));
                    contact.setDescription(cursor.getString(5));
                    contacts.add(contact);
                }while (cursor.moveToNext());
            }
            return contacts;
        }catch (Exception e){
            return null;
        }
    }
}
