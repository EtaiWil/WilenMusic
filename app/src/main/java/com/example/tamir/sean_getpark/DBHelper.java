package com.example.tamir.sean_getpark;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper{

    static String TAG = "DBHelper";
    static int DATABASE_VERSION = 40;

    static final String DATABASE_NAME = "music";
    static final String TABLE_USERS = "users";
    static final String TABLE_SONGS = "songs";
    static final String TABLE_SONGSPREF = "songs_pref";
    static final String TABLE_USEERARTISTS = "user_artists";


    static final String USER_USERNAME = "username";
    static final String USER_MAIL = "mail";
    static final String USER_PASSWORD = "password";


    static final String CREATE_USERS =
            "create table " + TABLE_USERS + "(" + USER_USERNAME + " text not null primary key, " +
                    USER_MAIL + " text  , " +
                    USER_PASSWORD + " text  ) ";



    static final String SONG_ID = "_id";
    static final String SONG_NAME = "songName";
    static final String SONG_ARTIST = "artist";
    static final String SONG_ERA = "era";
    static final String SONG_JANER = "janer";
    static final String SONG_MEDIA = "media";


    static final String CREATE_SONGS =
            "create table " + TABLE_SONGS + " (" + SONG_ID + " integer primary key autoincrement, "+
                     SONG_NAME + " text, " +
                    SONG_ARTIST + " text, " +
                    SONG_ERA + " text, " +
                    SONG_JANER + " text, " +
                    SONG_MEDIA + " text  ) ";


    static final String PREF_ID = "_id";
    static final String PREF_USERNAME = "username";
    static final String PREF_SONG = "song_id";
    static final String PREF_RANK = "rank";

    static final String CREATE_PREF=
            "create table " + TABLE_SONGSPREF + " (" + PREF_ID + " integer primary key autoincrement, "+
                    PREF_USERNAME + " text, " +
                    PREF_SONG + " integer , " +
                    PREF_RANK  + " integer) ";

    Context c;


    SQLiteDatabase db;

    public DBHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        c = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table IF EXISTS " + TABLE_USERS);
        db.execSQL("drop table IF EXISTS "+TABLE_SONGS);
        db.execSQL("drop table IF EXISTS " +TABLE_SONGSPREF);

        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_SONGS);
        db.execSQL(CREATE_PREF);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

       // db.execSQL("drop table IF EXISTS " + TABLE_USERS);
        //db.execSQL("drop table IF EXISTS "+TABLE_SONGS);
        db.execSQL("drop table IF EXISTS " +TABLE_SONGSPREF);

        //db.execSQL(CREATE_USERS);
        //db.execSQL(CREATE_SONGS);
        db.execSQL(CREATE_PREF);

    }




}

