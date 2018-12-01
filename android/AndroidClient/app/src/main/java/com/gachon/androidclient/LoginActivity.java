package com.gachon.androidclient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_LOGIN = 109;
    public static final int RESULT_FAIL = 100;
    public static final String ID_DATA = "id";
    public static final String NAME_DATA = "name";

    EditText idInput;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idInput = (EditText)findViewById(R.id.idInput);
        nameInput = (EditText)findViewById(R.id.nameInput);

        CardView loginButton = (CardView)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    int id = Integer.parseInt(idInput.getText().toString());
                    String name = nameInput.getText().toString();
                    if(name.contentEquals("")){
                        throw new Exception();
                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    intent.putExtra(ID_DATA, id);
                    intent.putExtra(NAME_DATA, name);

                    startActivityForResult(intent, REQUEST_CODE_LOGIN);
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(),"학번과 이름을 입력해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_LOGIN){
            if(resultCode==RESULT_FAIL){
                Toast.makeText(getApplicationContext(),"Login 실패",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreState();
    }

    protected void saveState(){
        SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id", idInput.getText().toString());
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }

    protected void restoreState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref!=null)){
            if(pref.contains("id")){
                String id = pref.getString("id","");
                idInput.setText(id);
            }
            if(pref.contains("name")){
                String name = pref.getString("name","");
                nameInput.setText(name);
            }
        }
    }
}
