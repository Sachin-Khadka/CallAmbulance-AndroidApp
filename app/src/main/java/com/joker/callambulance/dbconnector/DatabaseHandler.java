package com.joker.callambulance.dbconnector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.joker.callambulance.entities.User;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper
{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "RegisterUser.db";
    public static  final String TABLE_NAME = "Users";
    public static final String COL_ID = "userId";
    public static final String COL_NAME = "userName";
    public static final String COL_EMAIL = "userEmail";
    public static final String COL_PASSWORD = "userPassword";
    public static final String COL_CONFIRM_PASSWORD = "userConfirmPassword";


    public DatabaseHandler(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PASSWORD + " TEXT,"
                + COL_CONFIRM_PASSWORD + " TEXT " +")";
        db.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // For Registration Page

    public long addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getFullName());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_CONFIRM_PASSWORD, user.getRepeatPassword());
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return  insert;
    }


    public boolean addPerson(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getFullName());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_CONFIRM_PASSWORD, user.getRepeatPassword());
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //Second Method to Save data in DB

//    public long registerUser(String fullName, String email, String password, String rePassword)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("username", fullName);
//        values.put("email", email);
//        values.put("pass", password);
//        values.put("c_pass", rePassword);
//        long data = db.insert("register_user", null, values);
//        db.close();
//        return  data;
//    }




    // For Login Page
    public boolean verifyUser(String email, String pass)
    {
        String[] columns = { COL_ID, COL_NAME, COL_CONFIRM_PASSWORD };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_EMAIL + "=?" + " and " + COL_PASSWORD + "=?";
        String[] selectionArgs = { email, pass };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count>0)
            return  true;
        else
            return  false;
    }

    public void deleteUser(String userEmail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_NAME + " = ? ", new String[]{userEmail});
        db.close();
    }


    public List<User> getAllUsers()
    {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
                {
                User user = new User();
                user.set_id(Integer.parseInt(cursor.getString(0)));
                user.setFullName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setRepeatPassword(cursor.getString(4));
                userList.add(user);
              }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }


    //Not used but it is second method fetch all data from db.

    public List<User> getEverybody()

    {
        List<User> customerModelList = new ArrayList<>();
        String queryString = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext())
        {
            do
            {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userEmail = cursor.getString(2);
                String userPassword = cursor.getString(3);
                String userConfirmPassword = cursor.getString(4);
                User newUser = new User(userID, userName,userEmail,userPassword, userConfirmPassword);
                customerModelList.add(newUser);
            }
            while(cursor.moveToNext());

        }
        else
        {
            //if failure to add then nothing will be added
        }
        cursor.close();
        db.close();
        return customerModelList;
    }
}


