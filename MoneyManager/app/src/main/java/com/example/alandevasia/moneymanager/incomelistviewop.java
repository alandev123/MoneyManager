package com.example.alandevasia.moneymanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class incomelistviewop extends AppCompatActivity {
    SQLiteDatabase db;
    ListView userList;
    DatabaseHandler dbhand;

    ArrayList<ListClass> userArray = new ArrayList<ListClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomelistvw);
        Update();
    }
    public void Update(){
        try {
            DatabaseHandler db = new DatabaseHandler(this);
            userArray = db.getListView();

            UserCustomAdapter userAdapter = new UserCustomAdapter(incomelistviewop.this, R.layout.incomecustlv,
                    userArray);
            userList = (ListView) findViewById(R.id.incomelv);
            userList.setItemsCanFocus(false);
            userList.setAdapter(userAdapter);

            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        final int position, long id) {
                    Log.i("List View Clicked", "**********");
                    Toast.makeText(incomelistviewop.this,
                            "List View Clicked:" + position, Toast.LENGTH_LONG)
                            .show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(incomelistviewop.this, "Nothing to show..!",
                    Toast.LENGTH_LONG).show();

        }
    }

}
