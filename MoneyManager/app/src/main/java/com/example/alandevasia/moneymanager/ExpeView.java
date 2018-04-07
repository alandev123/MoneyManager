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

public class ExpeView extends AppCompatActivity {

    EditText selexdate;
    Button getex;
    SQLiteDatabase db;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expe_view);
        selexdate=(EditText) findViewById(R.id.selexdate);
        getex=(Button) findViewById(R.id.getexpense);
        db=openOrCreateDatabase("moneymanagerDb", Context.MODE_PRIVATE,null);


        selexdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selexdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(ExpeView.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        selexdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
            }
        });

        getex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewex();
            }
        });

    }
    public void viewex(){
        Cursor c = db.rawQuery("SELECT * FROM tableexp where Date='"+selexdate.getText().toString()+"'", null);
        if(c.getCount() == 0)
        {

            showMessage(" ", "No records found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {

            buffer.append("Expense: " +c.getString(0) + "\n");
            buffer.append("Date " +c.getString(1) + "\n");
            buffer.append("Purpose: " +c.getString(2) + "\n\n");

        }
        showMessage("Expense Details", buffer.toString());


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
