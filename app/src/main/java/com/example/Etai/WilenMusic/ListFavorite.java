package com.example.Etai.WilenMusic;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListFavorite extends AppCompatActivity {
ListView list_fav;
String []fields;
int []views;
Favadapter fadapter;
Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorite);

        c = GlobalHelper.MYDB.favArtists(GlobalHelper.UserConnected);
        list_fav = (ListView)findViewById(R.id.list_fav);
        if(c!=null)
        {
            c.moveToFirst();
            fadapter = new Favadapter(getApplicationContext(),c,0);
            list_fav.setAdapter(fadapter);

        }

    }
}
