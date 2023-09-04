package com.example.tamir.sean_getpark;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Tamir on 17/06/2018.
 */

public class Favadapter extends CursorAdapter {

    public Favadapter (Context context, Cursor c, int flags)
    {
        super(context,c,0);

    }

    public void bindView (View view , Context context , Cursor c)
    {
        TextView fav_artist = (TextView)view.findViewById(R.id.fav_artist);
        TextView fav_total = (TextView)view.findViewById(R.id.fav_total);
        String   val_artist = c.getString(c.getColumnIndex("artist"));
        String   val_total = c.getString(c.getColumnIndex("total"));
        fav_artist.setText(val_artist);
        fav_total.setText(val_total);
    }

    public View newView(Context context , Cursor c, ViewGroup parent)
    {
             return LayoutInflater.from(context).inflate(R.layout.layout_item_fav,parent,false);
    }
}
