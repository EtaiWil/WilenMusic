package com.example.tamir.sean_getpark;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reg;
    private EditText pass,username,email,phone,carnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_reg=(Button)findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);


        pass=(EditText) findViewById(R.id.pass);
        email=(EditText) findViewById(R.id.email);
        username=(EditText) findViewById(R.id.username);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        if(view.getId()==R.id.btn_reg)
        {
            if (pass.getText().toString().length()<4  )
                Toast.makeText(this,"YOU NEED TO INSERT more then4 NUMBERS IN your password", Toast.LENGTH_SHORT ).show();
            else
            if (username.getText().toString().equals("") )
                Toast.makeText(this,"YOU NEED TO INSERT your user name", Toast.LENGTH_SHORT ).show();
            else
            if (!email.getText().toString().contains("@")||email.getText().toString().equals("") )
                Toast.makeText(this,"YOU NEED TO INSERT your true email", Toast.LENGTH_SHORT ).show();

            else
            {
                GlobalHelper.MYDB.insertUser(username.getText().toString(), pass.getText().toString(), email.getText().toString());

                Toast.makeText(this,"user registered!",Toast.LENGTH_SHORT).show();
                i=new Intent(new Intent(Register.this , Login.class));

                startActivity(i);


            }

        }
    }
    }

