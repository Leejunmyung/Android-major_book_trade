package com.example.myappproject2020;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Join {
    EditText edtId, edtPw;
    Button btnJoin, btnLogin;

    int idFlag=0;//아이디가 일치하는 회원 화면입력과 데이터베이스 테이블에 ID 일치할때 1 불일치 0
    int pwFlag=0;//패스워드도 일치 1, 불일치 0


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = (EditText) findViewById(R.id.edtId);
        edtPw = (EditText) findViewById(R.id.edtPw);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        //회원가입버튼을 누르면 회원가입화면으로 이동
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        Join.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cursor 저장 읽기전용으로 DB열어서 읽어옴
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM JoinInfo;", null);
                String edt1 = null;//임시변수 화면상에 ID임시저장, PW임시저장
                String pass1 = null;
                String edt2 = null;
                String pass2 = null;//DB에서 가져온 ID, PW를 임시저장할 변수 선언

                while (cursor.moveToNext()) {
                    edt2 = cursor.getString(0);//첫번째 레코드 아이디
                    pass2 = cursor.getString(0);//첫번째 레코드 비번번
                   edt1 = edtId.getText().toString();
                    pass1 = edtPw.getText().toString();
                    if (edt2.equals(edt1)) {
                        idFlag = 1;
                        if (pass2.equals(pass1)) {
                            pwFlag = 1;
                            Toast.makeText(getApplicationContext(),
                                    "로그인 되었습니다.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),
                                    Activity2.class);
                            startActivity(intent);

                        } else {//아이디는 일치 비번은 불일치
                            Toast.makeText(getApplicationContext(),
                                    "비밀번호가 일치하지 않습니다.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }

                if (idFlag == 0 && pwFlag == 0) {//아이디 비번 둘다 불일치
                    Toast.makeText(getApplicationContext(),
                            "일치하는 정보가 없습니다. 회원가입을 해주세요",
                            Toast.LENGTH_LONG).show();
                }
            }

        });


    }
}