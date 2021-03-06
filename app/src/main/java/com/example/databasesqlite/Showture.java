package com.example.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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
        listView.setEmptyView(findViewById(R.id.emptyView));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = bid.get(position).toString();
                Intent intent = new Intent(Showture.this,Manager_data.class);
                intent.putExtra("bid",bid.get(position));
                Showture.this.finish();
                startActivity(intent);

                // Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });


    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<String> userslist = new ArrayList<>();

                for (String user : bname){
                    if(user.toLowerCase().contains(s.toLowerCase())){
                        userslist.add(user);
                    }

                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Showture.this,android.R.layout.simple_dropdown_item_1line,userslist);
                listView.setAdapter(adp);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}