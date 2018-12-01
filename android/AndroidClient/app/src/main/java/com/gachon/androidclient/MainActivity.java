package com.gachon.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.gachon.androidclient.LoginActivity.ID_DATA;
import static com.gachon.androidclient.LoginActivity.NAME_DATA;
import static com.gachon.androidclient.LoginActivity.RESULT_FAIL;

public class MainActivity extends AppCompatActivity {
    Student myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            int id = intent.getIntExtra(ID_DATA,-1);
            String name = intent.getStringExtra(NAME_DATA);
            myInfo = new Student(id, name);

            if(myInfo.getId()==-1){
                setResult(RESULT_FAIL);
                finish();
            }
        }
    }
}
