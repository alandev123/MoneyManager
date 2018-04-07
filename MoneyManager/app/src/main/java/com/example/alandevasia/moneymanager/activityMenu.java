package com.example.alandevasia.moneymanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activityMenu extends AppCompatActivity {
        ImageButton imgbtn;
        Button income,expense,incexp,calendar,showCalculator;
//    public static final String CALCULATOR_PACKAGE ="com.android.calculator2";
   // public static final String CALCULATOR_CLASS ="com.android.calculator2.Calculator";
    public static final String CALCULATOR_PACKAGE ="com.miui.calculator";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        imgbtn=(ImageButton)findViewById(R.id.imageButton);
        income=(Button)findViewById(R.id.incomebtn);
        expense=(Button)findViewById(R.id.expensebtn);
        incexp=(Button)findViewById(R.id.btnincexp);
        calendar=(Button)findViewById(R.id.calendarbtn);
        showCalculator = (Button) findViewById(R.id.showCalculatorButton);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activityMenu.this,activityIncome.class));
            }
        });
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activityMenu.this, activityExpence.class));
            }
        });
        incexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activityMenu.this, IncExpView.class));
            }
        });

        showCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.setComponent(new ComponentName(
//                        CALCULATOR_PACKAGE,
//                        CALCULATOR_CLASS));
////                CALCULATOR_CLASS.
//                Calculator.this.startActivity(intent);
//                startActivity(intent);
//                miui

                ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();

                final PackageManager pm = getPackageManager();
                List<PackageInfo> packs = pm.getInstalledPackages(0);
                for (PackageInfo pi : packs) {
                    if( pi.packageName.toString().toLowerCase().contains("calcul")){
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("appName", pi.applicationInfo.loadLabel(pm));
                        map.put("packageName", pi.packageName);
                        items.add(map);
                    }
                }

                if(items.size()>=1){
                    String packageName = (String) items.get(0).get("packageName");
                    Intent i = pm.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivity(i);
                }
                else{
                    // Application not found
                }

            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(activityMenu.this,Calendar.class);
                startActivity(in);
            }
        });
    }
}
