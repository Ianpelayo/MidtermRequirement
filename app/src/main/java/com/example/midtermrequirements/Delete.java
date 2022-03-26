package com.example.midtermrequirements;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Delete extends AppCompatActivity {

    EditText prodId;
    Button deleteData, searchData, viewAll;
    MyDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        prodId = (EditText) findViewById(R.id.prod);
        deleteData = findViewById(R.id.delete);
        searchData = findViewById(R.id.search);
        viewAll = findViewById(R.id.viewAllData);

        DB = new MyDatabaseHelper(this);


        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = prodId.getText().toString();
                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("BikeData.db", Context.MODE_PRIVATE,null);
                Cursor cursor = DB.rawQuery("Select * from Userdetails where id ='"+n+"'", null);
                if (cursor.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"No Record",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID :" + cursor.getString(0) + "\n");
                    buffer.append("Product :" + cursor.getString(1) + "\n");
                    buffer.append("Bike Brand :" + cursor.getString(2) + "\n");
                    buffer.append("Bike Name :" + cursor.getString(3) + "\n");
                    buffer.append("Type :" + cursor.getString(4) + "\n");
                    buffer.append("Size :" + cursor.getString(5) + "\n");
                    buffer.append("Color :" + cursor.getString(6) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
                builder.setCancelable(true);
                builder.setTitle("Bike Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });



        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prodText = prodId.getText().toString();

                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("BikeData.db", Context.MODE_PRIVATE,null);
                DB.execSQL("delete from Userdetails where id ='"+prodText+"'");

                Toast.makeText(Delete.this,"Delete Successfully\n",Toast.LENGTH_SHORT).show();

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Delete.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Product :"+res.getString(1)+"\n");
                    buffer.append("Bike Brand :"+res.getString(2)+"\n");
                    buffer.append("Bike Name :"+res.getString(3)+"\n");
                    buffer.append("Type :"+res.getString(4)+"\n");
                    buffer.append("Size :"+res.getString(5)+"\n");
                    buffer.append("Color :"+res.getString(6)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
                builder.setCancelable(true);
                builder.setTitle("Bike Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}