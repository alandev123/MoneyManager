package com.example.alandevasia.moneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan Devasia on 12-03-2018.
 */

class DatabaseHandler extends SQLiteOpenHelper{

    public static final String INCOME_TB ="tableincome" ;
    public static final String EXPENSE_TB ="tableexp";
    private static String DATABASE_NAME     = "moneymanagerDb";
    private static int DATABASE_VERSION     = 1;
   // private String INCOME_TB       = "tableincome";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String create_incometb = "CREATE TABLE "+INCOME_TB+"(Income TEXT,Date TEXT,Source TEXT)";
        database.execSQL(create_incometb);

        String create_expensetb = "CREATE TABLE "+EXPENSE_TB+"(Expense TEXT,Date TEXT,Purpose TEXT)";
        database.execSQL(create_expensetb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS "+INCOME_TB);
        onCreate(database);

        database.execSQL("DROP TABLE IF EXISTS "+EXPENSE_TB);
        onCreate(database);
    }

    void insertnamedata(String income, String date, String source){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values    = new ContentValues();
        values.put("Income",income);
        values.put("Date",date);
        values.put("Source",source);



        database.insert(INCOME_TB, null, values);
        database.close();
    }

    void expinsert(String expense, String date, String purpose){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values    = new ContentValues();
        values.put("Expense",expense);
        values.put("Date",date);
        values.put("Purpose",purpose);



        database.insert(EXPENSE_TB, null, values);
        database.close();
    }


    public ArrayList<ListClass> getListView() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from " + INCOME_TB, null);
        ArrayList<ListClass> olist = new ArrayList<ListClass>();

        cur.moveToFirst();
        int c = cur.getCount();
        for (int i = 0; i < c; i++) {

            ListClass out = new ListClass();

            String incomes = cur.getString(cur.getColumnIndex("Income"));
            String dates = cur.getString(cur.getColumnIndex("Date"));
            String sources = cur.getString(cur.getColumnIndex("Source"));


            out.setIncome(incomes);
            out.setDate(dates);
            out.setSource(sources);
            cur.moveToNext();
            olist.add(out);
        }
        return olist;
    }


/*creating a fn for expense*/

//    public ArrayList<ExpList> getListVie() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cur = db.rawQuery("select * from " + EXPENSE_TB, null);
//        ArrayList<ExpList> olist = new ArrayList<ExpList>();
//
//        cur.moveToFirst();
//        int c = cur.getCount();
//        for (int i = 0; i < c; i++) {
//
//            ExpList out = new ExpList();
//
//            String expenses = cur.getString(cur.getColumnIndex("Expense"));
//            String dates = cur.getString(cur.getColumnIndex("Date"));
//            String purposes = cur.getString(cur.getColumnIndex("Purpose"));
//
//
//            out.setExpense(expenses);
//            out.setDate(dates);
//            out.setExpense(purposes);
//            cur.moveToNext();
//            olist.add(out);
//        }
//        return olist;
//    }


}
