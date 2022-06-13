package com.example.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.databasesqlite.Manager_data;
import com.example.databasesqlite.Mydatabase;
import com.example.databasesqlite.R;

import java.util.ArrayList;

public class Showture extends AppCompatActivity {
    ListView listView;
    ArrayList<String> bid=new ArrayList<String>();
    ArrayList<String> bname =new ArrayList<String>();
    ArrayAdapter<String> adpt=null;
    Mydatabase mydb= new Mydatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showture);
        listView =findViewById(R.id.lstv);
        Cursor cursor = mydb.SelectAllData();
        bid.clear();
        bname.clear();
        if(cursor !=null){
            if(cursor.moveToFirst()){
                do{
                    bid.add(cursor.getString(0));
                    bname.add(cursor.getString(1));



                }while (cursor.moveToNext());

            }
        }
        cursor.close();
        adpt =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bname);
        listView.setAdapter(adpt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=bid.get(position).toString();
                Intent intent = new Intent(Showture.this, Manager_data.class);
                intent.putExtra("bid",bid.get(position));
                Showture.this.finish();
                startActivity(intent);

                // Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });



    }
}