package com.example.tamir.sean_getpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity  implements View.OnClickListener{


    private Button btn_enter;
    private EditText pass,username;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);
        btn_enter=(Button)findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(this);


        pass=(EditText) findViewById(R.id.pass);
        username=(EditText) findViewById(R.id.username);

    }

    @Override
    public void onClick(View view) {
       if(view.getId()==R.id.btn_enter)
       {

           Cursor c = GlobalHelper.MYDB.isPasswordTrue(username.getText().toString(),pass.getText().toString());
           if(c.moveToFirst()){
               //TODO ok
               Intent i =new Intent(this , MainSong.class);

               GlobalHelper.UserConnected = username.getText().toString();
               startActivity(i);

           }
           else {
               count++;

               Toast.makeText(this, "INCORRECT EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
               if(count==3)
               {
                   Intent i =new Intent(this , Register.class);
                   startActivity(i);
               }


           }
       }


       }

    }

