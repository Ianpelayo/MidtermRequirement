package com.example.midtermrequirements;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(Context context) {
        super(context, "BikeData.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id integer primary key autoincrement, productId TEXT, bikeBrand TEXT, bikeName TEXT, bikeType TEXT, bikeSize TEXT, bikeColor TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String productId, String bikeBrand, String bikeName, String bikeType, String bikeSize, String bikeColor)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", productId);
        contentValues.put("bikeBrand", bikeBrand);
        contentValues.put("bikeName", bikeName);
        contentValues.put("bikeType", bikeType);
        contentValues.put("bikeSize", bikeSize);
        contentValues.put("bikeColor", bikeColor);

        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String productId, String bikeBrand, String bikeName, String bikeType, String bikeSize, String bikeColor)
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", productId);
        contentValues.put("bikeBrand", bikeBrand);
        contentValues.put("bikeName", bikeName);
        contentValues.put("bikeType", bikeType);
        contentValues.put("bikeSize", bikeSize);
        contentValues.put("bikeColor", bikeColor);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where productId = ?", new String[]{productId});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "productId=?", new String[]{productId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String productId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where productId = ?", new String[]{productId});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "productId=?", new String[]{productId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }


}











