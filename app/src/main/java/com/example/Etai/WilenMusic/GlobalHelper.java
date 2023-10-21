package com.example.Etai.WilenMusic;

import android.content.Context;



public class GlobalHelper {
    public static DBAdapter MYDB;
    public static String UserConnected;

    public GlobalHelper(Context ctx)
    {
        if (MYDB==null)
        {
            MYDB = new DBAdapter(ctx);
            MYDB.addSongData();
        }
    }
}
