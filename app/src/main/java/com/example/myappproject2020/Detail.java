package com.example.myappproject2020;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {
    TextView txtname, etmenu1, etmenu2, etmenu3;
    TextView tvTel, tvURL, tvRegDate;
    Button btnback;

    ImageView imgno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        setTitle("Book Store");
        init();
    }

    void init(){
        int n;

        txtname = (TextView) findViewById(R.id.textname);
        etmenu1 = (TextView) findViewById(R.id.etmenu1);
        etmenu2 = (TextView) findViewById(R.id.etmenu2);
        etmenu3 = (TextView) findViewById(R.id.etmenu3);

        imgno = (ImageView) findViewById(R.id.imgno);


        tvTel = (TextView) findViewById(R.id.tvTel);
        tvURL = (TextView) findViewById(R.id.tvURL);
        tvRegDate = (TextView) findViewById(R.id.tvRegdate);

        Intent intent = getIntent();

        book r =intent.getParcelableExtra("book");

        txtname.setText(r.getName());

        String[] menu = r.getMenu();

        etmenu1.setText(menu[0]);
        etmenu2.setText(menu[1]);
        etmenu3.setText(menu[2]);

        n = r.getType();

        imgno.setImageResource(setImage(n));

        final String tel = r.getTell();
        final String h = r.getHomepage();


        tvTel.setText(tel);
        tvURL.setText(h);
        tvRegDate.setText(r.getDate());

    }

    int setImage(int n){
        if(n==1){
            return R.drawable.old;
        }else if(n==2){
            return R.drawable.good;
        }else{
            return R.drawable.veryne;
        }
    }

    public void onClick(View v){
        if(v.getId()==R.id.btnback){
            finish();
        }
    }


}
