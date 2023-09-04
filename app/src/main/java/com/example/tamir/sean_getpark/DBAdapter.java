package com.example.tamir.sean_getpark;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBAdapter {
    static Context c;
    static String TAG = "DBHelper";
    final Context context;
    static DBHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        if (DBHelper== null) {
            DBHelper = new DBHelper(context);

        }
        open();
        if(countSongs()==0)
             addSongData();
    }

    //open the database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //closes the database
    public void close() {
        DBHelper.close();

    }

//if the passorod and user that was enterd fits the usertable
    public Cursor isPasswordTrue (String username, String pass) throws SQLException
    {
        Cursor mCursor = db.query(true, DBHelper.TABLE_USERS, new String[] { DBHelper.USER_USERNAME,
                        DBHelper.USER_PASSWORD}, DBHelper.USER_USERNAME + "='" +username +"' and "+
                        DBHelper.USER_PASSWORD + "='" +pass+"'",
                null, null, null, null, null);
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }

//insert a new user into the usertable
    public long insertUser(String username, String password, String mail)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DBHelper.USER_USERNAME,username);
        initialValues.put(DBHelper.USER_PASSWORD, password);
        initialValues.put(DBHelper.USER_MAIL,mail);

        return db.insert(DBHelper.TABLE_USERS, null, initialValues);
    }
//
    public long insertSong(String sname,String artist,String era,String janer, String media)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DBHelper.SONG_NAME,sname);
        initialValues.put(DBHelper.SONG_ARTIST,artist);
        initialValues.put(DBHelper.SONG_ERA,era);
        initialValues.put(DBHelper.SONG_JANER,janer);
        initialValues.put(DBHelper.SONG_MEDIA,media);

        long i= db.insert(DBHelper.TABLE_SONGS, null, initialValues);
        return i;
    }
//count
    public Cursor favArtists(String username)
    {
        Cursor c=null;
       String sql = "select sp." + DBHelper.PREF_ID + " ,count(*) as total, "  +DBHelper.SONG_ARTIST + " from " +
               DBHelper.TABLE_SONGS + " s inner join " + DBHelper.TABLE_SONGSPREF + " sp " +
               " on s." + DBHelper.SONG_ID + "=sp." + DBHelper.PREF_SONG +
               " where " + DBHelper.PREF_RANK + "=" + 1 +
               " and " + DBHelper.USER_USERNAME + "='" + username + "'" +
               " group by " + DBHelper.SONG_ARTIST +
               " order by count(*) desc";
      // Toast.makeText(context , sql ,Toast.LENGTH_LONG).show();
       c=db.rawQuery(sql , null);

      if(c!=null)
          c.moveToFirst();
      return c;

    }
    //choose in random
    public Cursor findFavSongArtist(String username,String artist)
    {
        Cursor c=null;
        String sql = "select s." + DBHelper.SONG_ID +
                " from " +
                DBHelper.TABLE_SONGS + " s inner join " + DBHelper.TABLE_SONGSPREF + " sp " +
                " on s." + DBHelper.SONG_ID + "=sp." + DBHelper.PREF_SONG +
                " where " + DBHelper.PREF_RANK + "=" + 1 +
                " and " + DBHelper.USER_USERNAME + "='" + username + "'" +
                " and " + DBHelper.SONG_ARTIST + "='" + artist + "'" +
                " order by random() limit 1";


        c=db.rawQuery(sql , null);

      if(c.moveToFirst())
          return c;
      else
          return null;

    }
//insert song to the database
    public void addSongData()
    {
        insertSong("Hotel California","Eagles","70","rock","song1");
        insertSong("Bohemian Rhapsody","Queen","70","rock","song2");
        insertSong("Enjoy the Silence","Depeche Mode","90","pop","song3");
        insertSong("Lose Yourself","Eminem","00","hip-hop","song4");
        insertSong("Billy Jean","Michael Jackson","80","pop","song5");
        insertSong("havana","camila cabello", "10","pop","song6");
        insertSong("cant stop" ,"Red Hot Chili Peppers","00", "rock","song7");
        insertSong("the next episode", "Dr.dre","00","rap","song8");
        insertSong("Layla","Eric Clapton","70","rock","song 9");
        insertSong("not afried","Eminem","10","rap","song10");
        insertSong("Yesterday","Beatles","60","pop","song11");
        insertSong("Mi Gente","Willy William","10","pop","song12");
        insertSong("Toy","netta barzilai","10","pop","song13");
        insertSong("once in life","omer adam","10","oriental",  "song14");
        insertSong("Love Me Do","Beatles","60","pop","song15");
        insertSong("come as you are","Nirvana","90","rock","song16  ");
        insertSong("lithium","Nirvana","90","rock","song17");
        insertSong("smells like teen spirit","Nirvana","90","rock","song18");
        insertSong("Back in Black","Amy Winehouse","90","pop","song19");
        insertSong("Set Fire To The Rain","Adele","10","pop","song20");
        insertSong("Imagine","John Lennon","90","rock","song21");
        insertSong("rolling in the deep","Adele","10","pop","song22");
        insertSong("Hello","Adele","10","pop","song23");

    }
//count songs in the database
    public int countSongs()
    {
        int tot=0;
        String sql = "select count(*) from " + DBHelper.TABLE_SONGS;
        Cursor c  = db.rawQuery(sql,null);
        if(c==null)
            return 0;
        if(c.getCount()>0)
        {
            c.moveToFirst();
            tot=c.getInt(0);
        }
        return tot;
    }
//return favorite artists
    public Cursor getAllpref(String user) throws SQLException {
        return db.query(DBHelper.TABLE_SONGSPREF, new String[]{DBHelper.PREF_USERNAME, DBHelper.PREF_SONG }
                , DBHelper.PREF_USERNAME + "='" + user + "'", null, null, null, null);

    }



//find  all song detalissong
    public Cursor findSong(int songId) throws SQLException {
        return db.query(DBHelper.TABLE_SONGS, new String[]{DBHelper.SONG_ID, DBHelper.SONG_ARTIST,DBHelper.SONG_ERA, DBHelper.SONG_NAME , DBHelper.SONG_MEDIA }
                , DBHelper.SONG_ID + "=" + songId , null, null, null, null);

    }


    public long insertRank (int rank, int songId, String user)
    {


        ContentValues initialValues = new ContentValues();
        Cursor c = db.query(DBHelper.TABLE_SONGSPREF, new String[]{DBHelper.PREF_SONG, DBHelper.PREF_USERNAME }
                , DBHelper.PREF_SONG + "=" + songId + " and " + DBHelper.PREF_USERNAME + "='" + user + "'"
                , null, null, null, null);


        if (c.getCount()==0) {

            initialValues.put(DBHelper.PREF_RANK, rank);
            initialValues.put(DBHelper.PREF_SONG, songId);
            initialValues.put(DBHelper.PREF_USERNAME, user);
            return db.insert(DBHelper.TABLE_SONGSPREF, null, initialValues);
        }

        return -1;
    }
//if the song that came in the dislike
    public boolean dislike(String user, int songId) {

        String sql;
        sql = "select " + DBHelper.PREF_SONG + " from " +
                DBHelper.TABLE_SONGSPREF +
                " where " + DBHelper.PREF_USERNAME + "='" + user +"'"
                + " and " + DBHelper.PREF_RANK + "=0 and " +
                 DBHelper.PREF_SONG + "=" + songId;
        //Toast.makeText(context,sql,Toast.LENGTH_LONG).show();
        Cursor c=db.rawQuery(sql,null);
        if(c.moveToFirst())
             return true;
        else
            return false;

    }

//if the song that came is marked by the user.
    public boolean favExist(int song_num, String username)
    {

        Cursor c;
        String sql="select " + DBHelper.PREF_SONG + " from " + DBHelper.TABLE_SONGSPREF
                 + " where " + DBHelper.PREF_USERNAME + "='" + username + "'"
                + " and " + DBHelper.PREF_SONG + "=" + song_num;
        c=db.rawQuery(sql,null);
        if(c.moveToFirst())
           return true;
        else
        return false;


    }


    public int countDislikes(String username)

    {
        Cursor c;
        String sql="select " + DBHelper.PREF_ID +" from " + DBHelper.TABLE_SONGSPREF
                + " where " + DBHelper.PREF_USERNAME + "='" + username + "'";

        c=db.rawQuery(sql,null);
        if(c!=null)
            c.moveToFirst();
        return c.getCount();
    }


    public  void updateFav(int song_num, String username, int rank)
    {
        String sql;
         sql="update " + DBHelper.TABLE_SONGSPREF + " set " + DBHelper.PREF_RANK + "=" + rank
                + " where " + DBHelper.PREF_USERNAME + "='" + username + "'"
                + " and " + DBHelper.PREF_SONG + "=" + song_num;
         db.execSQL(sql);
    }

}

