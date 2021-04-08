package com.example.myappproject2020;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Join extends Activity {
    EditText jId, jPw, jName, jPhone, jEmail;
    Button btnJoin2, btnMain;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        jId = (EditText) findViewById(R.id.jId);
        jPw = (EditText) findViewById(R.id.jPw);
        jName = (EditText) findViewById(R.id.jName);
        jPhone = (EditText) findViewById(R.id.jPhone);
        jEmail = (EditText) findViewById(R.id.jEmail);
        btnMain = (Button)findViewById(R.id.btnMain);
        btnJoin2 = (Button)findViewById(R.id.btnJoin2);

        myHelper = new myDBHelper(this);

        btnJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO JoinInfo VALUES('" +
                        jId.getText().toString() + "','" + jPw.getText().toString()
                         + "');");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "가입되었습니다.",
                        Toast.LENGTH_LONG).show();

            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메인화면으로 이동하는 버튼
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context) {
            super(context, "LoginDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE JoinInfo(uId TEXT PRIMARY KEY,uPassword TEXT);");
        }



        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS JoinInfo");
            onCreate(sqLiteDatabase);
        }
    }
}
