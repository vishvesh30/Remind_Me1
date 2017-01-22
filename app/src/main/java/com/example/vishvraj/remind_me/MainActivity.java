package com.example.vishvraj.remind_me;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper_MainCategory my_db;
    ListView listview_category;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_db = new DatabaseHelper_MainCategory(this);
        listview_category = (ListView) findViewById(R.id.listview_category);
        cursor = my_db.getAllData();
        String[] colums = new String[]{DatabaseHelper_MainCategory.COL_ID, DatabaseHelper_MainCategory.COL_NAME};
        int[] giveto = new int[]{R.id.textview_id, R.id.textview_name};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, colums, giveto);
        listview_category.setAdapter(simpleCursorAdapter);
        listview_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                if(pos==0){
                    Intent intent=new Intent("com.example.vishvraj.remind_me.lic_activity");
                    startActivity(intent);
                }
                if(pos==1){
                    Intent intent=new Intent("com.example.vishvraj.remind_me.Mutual_fund_activity");
                    startActivity(intent);
                }
                if(pos==2){
                    Intent intent=new Intent("com.example.vishvraj.remind_me.Mediclaim_activity");
                    startActivity(intent);
                }

            }
        });
        //   addData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.upcoming){
            Toast.makeText(MainActivity.this,"Upcoming Activity",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //for adding data in sqlite
    public void addData() {
        boolean isInserted = my_db.insertData("LIC");
        if (isInserted) {
            Toast.makeText(MainActivity.this, "LIC inserted", Toast.LENGTH_SHORT).show();

        }
        isInserted = my_db.insertData("Mutual Fund");
        if (isInserted) {
            Toast.makeText(MainActivity.this, "Mutual Fund inserted", Toast.LENGTH_SHORT).show();

        }
        isInserted = my_db.insertData("Mediclaim");
        if (isInserted) {
            Toast.makeText(MainActivity.this, "Mediclaim inserted", Toast.LENGTH_SHORT).show();

        }
    }

}