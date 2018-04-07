package com.example.alandevasia.moneymanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class activityIncome extends AppCompatActivity {
    EditText income,source,date;
    private int mYear, mMonth, mDay;
        Button addbtn,viewbtn;
        DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        addbtn=(Button) findViewById(R.id.addinc);
        income=(EditText) findViewById(R.id.addincome);
        date=(EditText)findViewById(R.id.datepick);
        source=(EditText)findViewById(R.id.addsource);
        viewbtn=(Button)findViewById(R.id.viewinc);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(activityIncome.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DatabaseHandler(activityIncome.this);
                db.insertnamedata(income.getText().toString(),date.getText().toString(),source.getText().toString());

                Toast.makeText(activityIncome.this, "Task Added!",
                        Toast.LENGTH_LONG).show();
                clearText();
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activityIncome.this,incomelistviewop.class);
                startActivity(intent);
            }
        });

    }
    public void clearText() {
        income.setText("");
        date.setText("");
        source.setText("");
        date.requestFocus();
    }



}
