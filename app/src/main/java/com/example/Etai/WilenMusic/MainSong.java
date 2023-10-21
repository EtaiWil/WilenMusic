package com.example.Etai.WilenMusic;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Etai.WilenMusic.R;

import java.util.Random;

public class MainSong extends AppCompatActivity implements View.OnClickListener {

    ImageView song_pic,thumb_up, thumb_down,btn_play;
    TextView desc;
    int snum,id;
    int count=1;
    Cursor s,c , topArtistCur , findFavCur;
    String media , topAsrtist;
    Button fav_artists;
    boolean dislike,check2;
    private String state= "play";
    MediaPlayer med;
   MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_song);
         med = new MediaPlayer();


        song_pic = (ImageView)findViewById(R.id.song_pic);
        thumb_up = (ImageView)findViewById(R.id.thumb_up);
        thumb_down = (ImageView)findViewById(R.id.thumb_down);
        btn_play = (ImageView)findViewById(R.id.btn_play);
        fav_artists= (Button) findViewById(R.id.fav_artists);
        btn_play.setOnClickListener(this);
        thumb_up.setOnClickListener(this);
        thumb_down.setOnClickListener(this);
        fav_artists.setOnClickListener(this);
        desc=(TextView)findViewById(R.id.desc);
        topArtistCur = GlobalHelper.MYDB.favArtists(GlobalHelper.UserConnected);

        choose();



    }
//chose song in random to show
  public void choose()
  {
      //after 10 dislikes disable the button
      //if (GlobalHelper.MYDB.countDislikes(GlobalHelper.UserConnected)==10)
      {
      //    thumb_down.setEnabled(false);
      }
      Random r = new Random();
      //every 5th song will be randomli from the favorite artist
      if(count%5==0)
      {

          topArtistCur = GlobalHelper.MYDB.favArtists(GlobalHelper.UserConnected);

          //prefar the cursor and the adapter for showing the list of top artists only if the user have chosen alrady song
          if(topArtistCur.getCount()!=0)

          {
              topAsrtist = topArtistCur.getString(2);
              findFavCur = GlobalHelper.MYDB.findFavSongArtist(GlobalHelper.UserConnected,topAsrtist);

              snum=Integer.parseInt(findFavCur.getString(0));
          }
      }
      else {
          //choose randomly while dislike choose is choosen songs that are not dislikes songs
          snum = r.nextInt(GlobalHelper.MYDB.countSongs()) + 1;
          dislike = GlobalHelper.MYDB.dislike(GlobalHelper.UserConnected, snum);

          while (dislike) {
              snum = r.nextInt(GlobalHelper.MYDB.countSongs()) + 1;
              dislike = GlobalHelper.MYDB.dislike(GlobalHelper.UserConnected, snum);


          }
      }

          display_song();

  }
//
    public void display_song()
    {


         c = GlobalHelper.MYDB.getAllpref(GlobalHelper.UserConnected);


        c.moveToFirst();



            s=GlobalHelper.MYDB.findSong(snum);
            s.moveToFirst();

            desc.setText("Song: "+ s.getString(3)+ "    Artist: " + s.getString(1));
            media = "song"+snum;
            id=getResources().getIdentifier(media,"drawable",getPackageName());
            song_pic.setImageResource(id);


    }

    @Override
//changeActivty
    public void onClick(View view) {
        Cursor c1,cv;
        String song;

        if(view.getId() == R.id.fav_artists) {
            cv=GlobalHelper.MYDB.favArtists(GlobalHelper.UserConnected);
            Intent i =new Intent(this , ListFavorite.class);
            startActivity(i);

        }
//sound the music and change the buuton from start to pause
       if(view.getId()==R.id.btn_play)
       {
           if (state.equals("play")) {

               int id=getResources().getIdentifier(media,"raw",getPackageName());
               med = MediaPlayer.create(MainSong.this, id);
               if (!med.isPlaying())
                   med.start();
                   btn_play.setImageResource(R.drawable.pause);
                   state = "pause";

           }
           else{

                   med.pause();
                   btn_play.setImageResource(R.drawable.play);
                   state="play";

           }

       }

        if(view.getId()==R.id.thumb_down)
        {

            count++;
            if(GlobalHelper.MYDB.favExist(snum , GlobalHelper.UserConnected))
                GlobalHelper.MYDB.updateFav(snum,GlobalHelper.UserConnected,0);
            else
               GlobalHelper.MYDB.insertRank(0,snum,GlobalHelper.UserConnected);

            med.stop();
            btn_play.setImageResource(R.drawable.play);
            state="play";
            Toast.makeText(getBaseContext(),"this song wont apper again",Toast.LENGTH_LONG).show();
            choose();






        }


           if(view.getId()==R.id.thumb_up)
           {
               count++;
               if(GlobalHelper.MYDB.favExist(snum , GlobalHelper.UserConnected))
              GlobalHelper.MYDB.updateFav(snum,GlobalHelper.UserConnected,1);
               else
               GlobalHelper.MYDB.insertRank(1,snum,GlobalHelper.UserConnected);

               med.stop();
               btn_play.setImageResource(R.drawable.play);
               state="play";


               choose();


           }




    }
}
