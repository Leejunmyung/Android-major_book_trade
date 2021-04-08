package com.example.myappproject2020;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView listview;

    ArrayAdapter<String> adapter;

    TextView tv;

    int Addition = 1;

    ArrayList<book> storage = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Button listbtn = (Button) findViewById(R.id.listbtn);
        setTitle("Book Store");

        init();

        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        Activity2.class);
                startActivity(intent);
            }
        });

    }//onCreate()

    void init() {
        listview = (ListView) findViewById(R.id.listview);
        tv = (TextView) findViewById(R.id.tv);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);

        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                androidx.appcompat.app.AlertDialog.Builder dlg = new AlertDialog.Builder(List.this);
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까 ?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                title.remove(position);
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                                tv.setText("Book Recycle(" + title.size() + ")");
                            }
                        }).show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List.this, Detail.class);

                intent.putExtra("book", storage.get(i));

                startActivity(intent);
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Sign.class);

        startActivityForResult(intent, Addition);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Addition) {
            if (resultCode == RESULT_OK) {
                book r = data.getParcelableExtra("book");
                title.add(r.getName());
                storage.add(r);
                adapter.notifyDataSetChanged();
                tv.setText("Book Recycle(" + title.size() + ")");
            }
        }
    }
}
