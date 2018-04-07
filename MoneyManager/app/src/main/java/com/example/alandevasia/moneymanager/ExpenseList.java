package com.example.alandevasia.moneymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseList extends AppCompatActivity {
        ListView lv1;
        SQLiteDatabase db;
//        DatabaseHandler dh=new DatabaseHandler(this);
//        dh.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        lv1=(ListView) findViewById(R.id.lv1);

        db=openOrCreateDatabase("moneymanagerDb", Context.MODE_PRIVATE,null);
        view2();
    }
    public void view2() {
        Cursor c = db.rawQuery("SELECT * FROM tableexp", null);


        String[] array1 = new String[c.getCount()];
        String[] array2 = new String[c.getCount()];
        String[] array3 = new String[c.getCount()];
        //String[] array4 = new String[c.getCount()];

        Integer i=0;

        while (c.moveToNext()) {


            array1[i] = c.getString(0);
            array2[i] = c.getString(1);
            array3[i] = c.getString(2);

            i++;
        }
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        for(int j=0;j<c.getCount();j++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("di", "Expense : " +array1[j]);
            hm.put("da","Date : "+ array2[j]);
            hm.put("ti","Purpose : "+ array3[j]);


            aList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = { "di","da","ti","ca" };

        // Ids of views in listview_layout
        int[] to = { R.id.txtdate,R.id.txttime,R.id.txtcaption};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.display_row, from, to);

        // Getting a reference to listview of main.xml layout file


        // Setting the adapter to the listView
        lv1.setAdapter(adapter);


    }


}
