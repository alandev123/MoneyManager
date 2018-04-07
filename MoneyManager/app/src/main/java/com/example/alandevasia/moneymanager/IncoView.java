package com.example.alandevasia.moneymanager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IncoView extends AppCompatActivity {
    EditText seldate,sss;
    Button getdate;
    SQLiteDatabase db;
    private int mYear, mMonth, mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inco_view);
        seldate=(EditText) findViewById(R.id.seldate);


        getdate=(Button) findViewById(R.id.getincome);
        db=openOrCreateDatabase("moneymanagerDb", Context.MODE_PRIVATE,null);
        seldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seldate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(IncoView.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        seldate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
            }
        });
        getdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewdata();
            }
        });
    }
    public void viewdata(){
        Cursor c = db.rawQuery("SELECT * FROM tableincome where Date='"+seldate.getText().toString()+"'", null);
        if(c.getCount() == 0)
        {

            showMessage(" ", "No records found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {

            buffer.append("Income: " +c.getString(0) + "\n");
            buffer.append("Date " +c.getString(1) + "\n");
            buffer.append("Source: " +c.getString(2) + "\n\n");

        }
        showMessage("Income Details", buffer.toString());


    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
