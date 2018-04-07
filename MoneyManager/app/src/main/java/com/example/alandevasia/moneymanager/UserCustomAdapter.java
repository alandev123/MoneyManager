package com.example.alandevasia.moneymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alan Devasia on 02-04-2018.
 */

public class UserCustomAdapter extends ArrayAdapter<ListClass> {

    SQLiteDatabase db;
    int resource;
    String response;
    Integer aid;
    Context context;
    private List<ListClass> items;
    private UserCustomAdapter adapter;

    public UserCustomAdapter(Context context, int resource, List<ListClass> items) {
        super(context, resource, items);
        this.resource = resource;
        this.context = context;
        this.items = items;
        this.adapter = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LinearLayout contactsView;

        final ListClass contact = getItem(position);
        if (convertView == null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        final TextView tincome = (TextView) contactsView.findViewById(R.id.inctv);
        final TextView tdate = (TextView) contactsView.findViewById(R.id.datetv);
        final TextView tsource = (TextView) contactsView.findViewById(R.id.sourcetv);


        if (tincome != null) {
            tincome.setText((contact.getIncome()));

        }
        if (tdate != null) {
            tdate.setText((contact.getDate()));

        }
        if (tsource != null) {
            tsource.setText(contact.getSource());
        }



       /* final Button approve = (Button)contactsView.findViewById(R.id.delete);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtid = (TextView) v.findViewById(R.id.txtid);

//                Toast.makeText(v.getContext(),
//                        "List View Clicked:" + txtid.getText(), Toast.LENGTH_LONG)
//                        .show();
                int position=Integer.parseInt(txtid.getText().toString());
                Cursor c = db.rawQuery("SELECT * FROM " + DoContract.DoEntry.TABLE_NAME + " WHERE " + DoContract.DoEntry._ID + " = 'position' ", null);
                c.moveToFirst();
                if (c != null) {
                    db.execSQL("DELETE FROM " + DoContract.DoEntry.TABLE_NAME + " WHERE " + DoContract.DoEntry._ID + " = 'position' ");
                    //
                } else {
                    Toast.makeText(getContext(), "Invalid Employee id", Toast.LENGTH_SHORT).show();
                }
            }

        });*/
        return contactsView;
    }


}