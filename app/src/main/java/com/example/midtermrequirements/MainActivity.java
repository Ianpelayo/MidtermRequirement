package com.example.midtermrequirements;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText productIdText, bikeBrandText, bikeNameText, bikeTypeText, bikeSizeType, bikeColorType;
    Button insertData, updateData, viewData, searchData;
    MyDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView1 );
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);

        productIdText = findViewById(R.id.ProductID);
        bikeBrandText = findViewById(R.id.bikeBrandID);
        bikeNameText = findViewById(R.id.bikeNameID);
        bikeTypeText = findViewById(R.id.bikeTypeID);
        bikeSizeType = findViewById(R.id.bikeSizeID);
        bikeColorType = findViewById(R.id.bikeColorID);

        insertData = findViewById(R.id.Addbtn);
        updateData = findViewById(R.id.Updatebtnbtn);
        viewData = findViewById(R.id.Viewbtn);
        searchData = findViewById(R.id.searchDataButton);

        DB = new MyDatabaseHelper(this);

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ProductIDTXT = productIdText.getText().toString();
                String BrandTXT = bikeBrandText.getText().toString();
                String NameTXT = bikeNameText.getText().toString();
                String TypeTXT = bikeTypeText.getText().toString();
                String SizeTXT = bikeSizeType.getText().toString();
                String ColorTXT = bikeColorType.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(ProductIDTXT, BrandTXT, NameTXT, TypeTXT, SizeTXT, ColorTXT);


                if (TextUtils.isEmpty(ProductIDTXT)) {
                    productIdText.setError("Product ID is Required");
                    return;
                }
                if (TextUtils.isEmpty(NameTXT)) {
                    bikeNameText.setError("Bike Name is Required");
                    return;
                }


                else {
                    if(checkinsertdata == true)
                        Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show(); }
            } });


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ProductIDTXT = productIdText.getText().toString();
                String BrandTXT = bikeBrandText.getText().toString();
                String NameTXT = bikeNameText.getText().toString();
                String TypeTXT = bikeTypeText.getText().toString();
                String SizeTXT = bikeSizeType.getText().toString();
                String ColorTXT = bikeColorType.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(ProductIDTXT, BrandTXT, NameTXT, TypeTXT, SizeTXT, ColorTXT);

                if(checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Bike Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent send = new Intent(MainActivity.this, Delete.class);
                startActivity(send);

            }
        });

    }}




