package com.example.tamir.sean_getpark;

import android.content.Context;

/**
 * Created by Tamir on 21/05/2018.
 */

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
