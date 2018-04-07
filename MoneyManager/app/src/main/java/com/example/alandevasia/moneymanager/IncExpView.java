package com.example.alandevasia.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IncExpView extends AppCompatActivity {
    Button inco,expe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_exp_view);

        inco=(Button) findViewById(R.id.singleinc);
        expe=(Button) findViewById(R.id.singleexp);
        inco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IncExpView.this, IncoView.class));

            }
        });
        expe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IncExpView.this, ExpeView.class));

            }
        });

    }
}
