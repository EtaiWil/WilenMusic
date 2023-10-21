package com.example.Etai.WilenMusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_reg, btn_login;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalHelper.MYDB = new DBAdapter(this);


        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reg.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        total = (TextView)findViewById(R.id.total);
        total.setText("enjoy an invantar of " + GlobalHelper.MYDB.countSongs() +" songs. join us now! its FREE");

    }


    @Override
    public void onClick(View view) {
        Intent i;
        if (view.getId() == R.id.btn_login) {

            i = new Intent(new Intent(MainActivity.this, Login.class));
            startActivity(i);
        } else if (view.getId() == R.id.btn_reg) {
            i = new Intent(new Intent(MainActivity.this, Register.class));
            startActivity(i);
        }
    }
}
