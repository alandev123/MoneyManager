package com.example.alandevasia.moneymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class activityExpence extends AppCompatActivity {

    EditText expense,purpose,date;
    private int eYear, eMonth, eDay;
    Button addbtn,viewbtn;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence);
        addbtn=(Button) findViewById(R.id.addexp);
        expense=(EditText) findViewById(R.id.addexpense);
        date=(EditText)findViewById(R.id.expdate);
        purpose=(EditText)findViewById(R.id.addpurpose);
        viewbtn=(Button)findViewById(R.id.viewexp);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                eYear= c.get(Calendar.YEAR);
                eMonth = c.get(Calendar.MONTH);
                eDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(activityExpence.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, eYear, eMonth, eDay);
                datePickerDialog.show();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DatabaseHandler(activityExpence.this);
                db.expinsert(expense.getText().toString(),date.getText().toString(),purpose.getText().toString());

                Toast.makeText(activityExpence.this, "Expense Added!",
                        Toast.LENGTH_LONG).show();
                clearText();
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activityExpence.this,ExpenseList.class);
                startActivity(intent);
            }
        });
    }
    public void clearText() {
        expense.setText("");
        date.setText("");
        purpose.setText("");
        date.requestFocus();
    }
}
