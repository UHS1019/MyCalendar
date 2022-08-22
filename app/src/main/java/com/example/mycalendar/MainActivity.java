package com.example.mycalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;
    FragmentManager fmanager;
    FragmentTransaction ftransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        //프래그먼트 생성
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();

        fmanager = getSupportFragmentManager();
        ftransaction = fmanager.beginTransaction();
        ftransaction.add(R.id.main_fragment, f1);
        ftransaction.commit();
    }

    @Override
    public void onClick(View v) {
        ftransaction = fmanager.beginTransaction();
        switch (v.getId()){
            case R.id.btn1:
                Log.d("TEST", "버튼1");
                ftransaction.replace(R.id.main_fragment, f1);
                break;
            case R.id.btn2:
                Log.d("TEST", "버튼2");
                ftransaction.replace(R.id.main_fragment, f2);
                break;
            case R.id.btn3:
                Log.d("TEST", "버튼3");
                ftransaction.replace(R.id.main_fragment, f3);
                break;
        }
        ftransaction.commit();
    }
}