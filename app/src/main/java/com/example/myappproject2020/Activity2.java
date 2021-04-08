package com.example.myappproject2020;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Activity2 extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Button mainbtn1=(Button)findViewById(R.id.mainbtn1);
        Button mainbtn2=(Button)findViewById(R.id.mainbtn2);

        mainbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        List.class);
                startActivity(intent);
            }
        });
        mainbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        Sign.class);
                startActivity(intent);
            }
        });
    }
}
