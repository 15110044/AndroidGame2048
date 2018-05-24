package com.example.vuongvanhau.a2048;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {
    private String[] linknhac = new String[100];
    private String[] tenbh = new String[100];
    private int bhdp = 0;
    private int sobh = 0;
    private ImageView[][] myarray = new ImageView[4][4];
    private int[][] game = new int[4][4];
    private int Diem = 0;
    private int Diemcao = 0;
    private Boolean check = true;
    private int n = 4;
    private Random rand = new Random();
    private TextView score, highscore;
    private ImageButton back, next, play, repeat, mute, shuffee, list, setting;
    private LinearLayout maingame;
    private int pla = 0, mu = 0, shuff = 0, rep = 0;
    private String ch;
    private MediaPlayer mediaPlayer;
    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private SeekBar seekBar;
    private Handler threadHandler = new Handler();
    private Handler threadHandlericon = new Handler();
    private ImageView v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16;
    private GestureDetector gesdet;
    private int VTA =100;
    private int VTB=100;
    private String ten = "tamsutuoi30";
    private String thumuc = "raw";
    private int backgame[][] = new int[4][4];
    private String namemrun;
    private ImageButton undo, reset;
    private Boolean dg = true;
    private int[][] truoc = new int[4][4];
    private int[][] sau = new int[4][4];
    private Boolean bac = false;
    private int icon = 0;
    private ImageView imageicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView) findViewById(R.id.score);
        highscore = (TextView) findViewById(R.id.highcsore);
        back = (ImageButton) findViewById(R.id.button_back);
        next = (ImageButton) findViewById(R.id.button_next);
        play = (ImageButton) findViewById(R.id.button_play);
        mute =(ImageButton) findViewById(R.id.button_mute);
        shuffee = (ImageButton) findViewById(R.id.button_shuffee);
        list = (ImageButton) findViewById(R.id.button_listmusic);
        repeat = (ImageButton) findViewById(R.id.button_repeat);
        setting = (ImageButton) findViewById(R.id.button_setting);
        maingame = (LinearLayout) findViewById(R.id.maingame);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        textCurrentPosition = (TextView)findViewById(R.id.textView_currentPosion);
        textMaxTime=(TextView) findViewById(R.id.textView_maxTime);
        undo = (ImageButton)findViewById(R.id.undo);
        reset = (ImageButton) findViewById(R.id.newgame);
        imageicon = (ImageView) findViewById(R.id.icon);
        v1 = (ImageView) findViewById(R.id.vt1);
        v2 = (ImageView) findViewById(R.id.vt2);
        v3 = (ImageView) findViewById(R.id.vt3);
        v4 = (ImageView) findViewById(R.id.vt4);
        v5 = (ImageView) findViewById(R.id.vt5);
        v6 = (ImageView) findViewById(R.id.vt6);
        v7 = (ImageView) findViewById(R.id.vt7);
        v8 = (ImageView) findViewById(R.id.vt8);
        v9 = (ImageView) findViewById(R.id.vt9);
        v10 =(ImageView) findViewById(R.id.vt10);
        v11 =(ImageView) findViewById(R.id.vt11);
        v12 =(ImageView) findViewById(R.id.vt12);
        v13 =(ImageView) findViewById(R.id.vt13);
        v14 =(ImageView) findViewById(R.id.vt14);
        v15 =(ImageView) findViewById(R.id.vt15);
        v16 =(ImageView) findViewById(R.id.vt16);
        myarray[0][0] = v1;
        myarray[0][1] = v2;
        myarray[0][2] = v3;
        myarray[0][3] = v4;
        myarray[1][0] = v5;
        myarray[1][1] = v6;
        myarray[1][2] = v7;
        myarray[1][3] = v8;
        myarray[2][0] = v9;
        myarray[2][1] = v10;
        myarray[2][2] = v11;
        myarray[2][3] = v12;
        myarray[3][0] = v13;
        myarray[3][1] = v14;
        myarray[3][2] = v15;
        myarray[3][3] = v16;
        loadgame();
        UpdateIcon2048 updateicon2048 = new UpdateIcon2048();
        threadHandlericon.postDelayed(updateicon2048, 500);
        gesdet = new GestureDetector(this,new mygesdet());

        maingame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesdet.onTouchEvent(event);
                return true;
            }
        });
        shuffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffee_click();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_click();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_click(ten,thumuc);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_click();
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat_click();
            }
        });
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mute_click();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_click();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_click();
            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo_click();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_click();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        try {
            ghigame();
            ghinhac();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Lỗi "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    class mygesdet extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            for(int i=0; i<4;i++)
            {
                for (int j=0;j<4;j++)
                {
                    truoc[i][j] = game[i][j];
                }
            }
            if(e1.getX()-e2.getX() > VTA && Math.abs(velocityX)>VTB)
            {
                PhimTrai();
            }
            if(e2.getX()-e1.getX() > VTA && Math.abs(velocityX)>VTB)
            {
                PhimPhai();
            }
            if (e2.getY()-e1.getY() > VTA && Math.abs(velocityY)>VTB)
            {
                PhimXuong();
            }
            if(e1.getY()-e2.getY() > VTA && Math.abs(velocityY)>VTB)
            {
                PhimLen();
            }
            if (check == true) checkwin();
            checkLose();

            for(int i=0; i<4;i++)
            {
                for (int j=0;j<4;j++)
                {
                    sau[i][j] = game[i][j];
                }
            }

            for(int i=0; i<4;i++)
            {
                for (int j=0;j<4;j++)
                {
                    if (truoc[i][j] != sau[i][j])
                    {
                        bac = true;
                        break;
                    }
                }
            }
            if (bac == true)
            {
                for(int i=0; i<4;i++)
                {
                    for (int j=0;j<4;j++)
                    {
                        backgame[i][j] = truoc[i][j];
                    }
                }
            }
            bac = false;
            return super.onFling(e1, e2, velocityX, velocityY);

        }
    }

    private void doclistnhac()
    {
        try
        {
            FileInputStream finlink = openFileInput("linkmusic.txt");
            FileInputStream finname = openFileInput("namemusic.txt");
            FileInputStream finrunning = openFileInput("namemusicrunning.txt");
            int c;
            int d;
            int e;
            int nlink=0;
            int nname =0;
            while( (c = finlink.read()) != -1) {
                if (Character.toString((char)c)!=" ")
                {
                    linknhac[nlink++] = Character.toString((char)c);
                }
            }
            while( (d = finname.read()) != -1){
                if (Character.toString((char)d)!=" ")
                {
                    tenbh[nname++] = Character.toString((char)d);
                }
            }
            String temp=null;
            while( (e = finrunning.read()) != -1){
                temp+=Character.toString((char)e);
            }
            namemrun = temp;
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Lỗi "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    protected void docgame()
    {
        try {
            int i=0,j=0;
            FileInputStream fin = openFileInput("data2048.txt");
            FileInputStream finscore = openFileInput("score2048.txt");
            BufferedReader brfin = new BufferedReader(new InputStreamReader(fin));
            BufferedReader brfinscore = new BufferedReader(new InputStreamReader(finscore));
            String cfin = null;
            while( (cfin = brfin.readLine()) != null){
                game[i][j]= Integer.parseInt(cfin.toString());
                    if (j<3) j++;
                    else {
                        j = 0;
                        i++;
                    }
            }
            String cfinscore = null;
            int nd =0;
            while((cfinscore = brfinscore.readLine()) != null){
                if ( nd ==0 )
                {
                    Diem =  Integer.parseInt(cfinscore.toString());
                    cfinscore = null;
                    nd++;
                }
                else
                {
                    Diemcao =  Integer.parseInt(cfinscore.toString());
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Lỗi "+e.getMessage(),Toast.LENGTH_LONG).show();
            dg =false;
        }

    }

    protected void loadgame()
    {
        try
        {
            if (bhdp == 0)
            {
                back.setEnabled(false);
                next.setEnabled(true);
                next.setBackgroundResource(R.drawable.next);
            }
            else if (bhdp==sobh-1)
            {
                next.setEnabled(false);
                back.setEnabled(true);
                back.setBackgroundResource(R.drawable.prev);
            }
            else if(bhdp > 0 && bhdp < sobh-1)
            {
                back.setEnabled(false);
                next.setEnabled(true);
                next.setBackgroundResource(R.drawable.next);
            }
            if(dg == true)
            {

                docgame();
                update();
                //doclistnhac();
            }
            else
            {
                 for (int i = 0; i < 4; i++) {
                     for (int j = 0; j < 4; j++) {
                         backgame[i][j] = game[i][j];
                     }
                 }
             }
             score.setText(String.valueOf(Diem));
             highscore.setText(String.valueOf(Diemcao));
             int ran =0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(game[i][j]!=0)
                    {
                        ran = 1;
                        break;
                    }
                }
                if(ran==1) break;
            }
            if (ran == 0)
            {

                RanDomViTriHien();
                RanDomViTriHien();
                update();
            }

        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Lỗi "+ ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    protected void set2048(int a, ImageView b)
    {
        switch (a)
        {
            case 0:
            {
                b.setImageResource(R.drawable.pic0);
                break;
            }
            case 2:
            {
                b.setImageResource(R.drawable.pic2);
                break;
            }
            case 4:
            {
                b.setImageResource(R.drawable.pic4);
                break;
            }
            case 8:
            {
                b.setImageResource(R.drawable.pic8);
                break;
            }
            case 16:
            {
                b.setImageResource(R.drawable.pic16);
                break;
            }
            case 32:
            {
                b.setImageResource(R.drawable.pic32);
                break;
            }
            case 64:
            {
                b.setImageResource(R.drawable.pic64);
                break;
            }
            case 128:
            {
                b.setImageResource(R.drawable.pic128);
                break;
            }
            case 256:
            {
                b.setImageResource(R.drawable.pic256);
                break;
            }
            case 512:
            {
                b.setImageResource(R.drawable.pic512);
                break;
            }
            case 1024:
            {
                b.setImageResource(R.drawable.pic1024);
                break;
            }
            case 2048:
            {
                b.setImageResource(R.drawable.pic2048);
                break;
            }
            case 4096:
            {
                b.setImageResource(R.drawable.pic4096);
                break;
            }
        }
    }

    protected Boolean RanDomViTriHien()
    {
        Boolean isDo = false;
        ArrayList<Integer> test = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++)
        {
            if (game[i / 4][ i % 4] == 0)
            {
                test.add(i);
                isDo = true;
            }
        }
        if (test.size() > 0)
        {
            int set = test.get(rand.nextInt(test.size()));
            while (game[set / 4][set % 4] != 0 && test.size() > 1)
            {
                test.remove(set);
                set = test.get(rand.nextInt(test.size()));
            }
            game[set / 4][ set % 4] = Math.random() < 0.9 ? 2 : 4;
            set2048(game[set / 4][ set % 4], myarray[set / 4][set % 4]);
        }
        return isDo;
    }

    protected Boolean PhimTrai()
    {
        Boolean isDo = false;
        for (int x = 0; x < n; x++)
        {
            for (int y = 0; y < n - 1; y++)
            {
                for (int y1 = y + 1; y1 < n; y1++)
                {
                    if (game[x][y1] > 0)
                    {
                        if (game[x][y] == 0)
                        {
                            game[x][y] = game[x][y1];
                            game[x][y1] = 0;
                            y--;
                            isDo = true;
                        }
                        else if (game[x][y] == game[x][y1])
                        {
                            game[x][y] *= 2;
                            game[x][y1] = 0;
                            Diem += 2;
                            isDo = true;
                        }
                        break;
                    }
                }
            }
        }
        update();
        if (isDo)
        {
            RanDomViTriHien();
        }
        return isDo;
    }

    protected Boolean PhimPhai()
    {
        Boolean isDo = false;
        for (int x = 0; x < n; x++)
        {
            for (int y = n - 1; y >= 1; y--)
            {
                for (int y1 = y - 1; y1 >= 0; y1--)
                {
                    if (game[x][y1] > 0)
                    {
                        if (game[x][y] == 0)
                        {
                            game[x][y] = game[x][y1];
                            game[x][y1] = 0;
                            y++;
                            isDo = true;
                        }
                        else if (game[x][y] == game[x][y1])
                        {
                            game[x][y] *= 2;
                            game[x][y1] = 0;
                            Diem += 2;
                            isDo = true;
                        }
                        break;
                    }
                }
            }
        }
        update();
        if (isDo)
        {
            RanDomViTriHien();
        }
        return isDo;
    }

    protected Boolean PhimXuong()
    {
        Boolean isDo = false;
        for (int y = 0; y < n; y++)
        {
            for (int x = n - 1; x >= 1; x--)
            {
                for (int x1 = x - 1; x1 >= 0; x1--)
                {
                    if (game[x1][y] > 0)
                    {
                        if (game[x][y] == 0)
                        {
                            game[x][y] = game[x1][y];
                            game[x1][y] = 0;
                            x++;
                            isDo = true;
                        }
                        else if (game[x][y] == game[x1][y])
                        {
                            game[x][y] *= 2;
                            game[x1][y] = 0;
                            Diem += 2;
                            isDo = true;
                        }
                        break;
                    }
                }
            }
        }
        update();
        if (isDo)
        {
            RanDomViTriHien();
        }
        return isDo;
    }

    protected Boolean PhimLen()
    {
        Boolean isDo = false;
        for (int y = 0; y < n; y++)
        {
            for (int x = 0; x < n - 1; x++)
            {
                for (int x1 = x + 1; x1 < n; x1++)
                {
                    if (game[x1][y] > 0)
                    {
                        if (game[x][y] == 0)
                        {
                            game[x][y] = game[x1][y];
                            game[x1][y] = 0;
                            x--;
                            isDo = true;
                        }
                        else if (game[x][y] == game[x1][y])
                        {
                            game[x][y] *= 2;
                            game[x1][y] = 0;
                            Diem += 2;
                            isDo = true;
                        }
                        break;
                    }
                }
            }
        }
        update();
        if (isDo)
        {
            RanDomViTriHien();
        }
        return isDo;
    }

    //Cập nhật lại hiển thị trên màn hình
    protected void update()
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                set2048(game[i][j], myarray[i][j]);
            }
        }
        score.setText(String.valueOf(Diem));
        if (Integer.parseInt(highscore.getText().toString()) < Diem) highscore.setText(String.valueOf(Diem));
    }

    //Kiểm tra xem còn chơi đc nữa không
    protected Boolean ThuaCuoc()
    {
        for (int x = 0; x < n; x++)
        {
            for (int y = 0; y < n; y++)
            {
                if (game[x][y] == 0 || (y < n - 1 && game[x][y] == game[x][y + 1]) || (x < n - 1 && game[x][y] == game[x + 1][y]))
                {
                    return false;
                }
            }
        }
        return true;
    }

    //Kiểm tra thắng cuộc hay chưa
    protected Boolean thangCuoc()
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (game[i][j] == 2048) return true;
            }
        }
        return false;
    }

    //Kiểm tra thắng
    protected void checkwin()
    {
        Boolean kt = thangCuoc();
        if (kt == true)
        {
            check = false;
            try {
                AlertDialog dialog = new AlertDialog.Builder(Main.this,R.style.Theme_AppCompat_Light_Dialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setTitle("Congratulations")
                        .setMessage("You win!")
                        .show();
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),"Loi roi " + ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void checkLose()
    {
        try
        {
            Boolean kt = ThuaCuoc();
            if (kt == true)
            {
                AlertDialog dialog = new AlertDialog.Builder(Main.this,R.style.Theme_AppCompat_Light_Dialog)
                        .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { newgame();
                                    }
                                })
                        .setNegativeButton(R.string.continue_game, null)
                        .setTitle(R.string.reset_dialog_title)
                        .setMessage(R.string.reset_dialog_message)
                        .show();

            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Loi roi " + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    protected void newgame()
    {
        try
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    game[i][j] = 0;
                }
            }
            RanDomViTriHien();
            RanDomViTriHien();
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    set2048(game[i][j], myarray[i][j]);
                }
            }
            score.setText("0");
            Diem = 0;
            ch = "0";
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    backgame[i][j] = game[i][j];
                }
            }
            ghigame();
        }
        catch (Exception ex)
        {

        }
    }

    protected Boolean conotrong()
    {
        Boolean kq = false;
        try
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (game[i][j] == 0)
                    {
                        kq = true;
                    }
                }
            }
        }
        catch (Exception ex)
        {

        }
        return kq;
    }

    protected void ghigame()
    {
        try
        {
            String datagame= new String();
            String datascore = new String();
            for(int i=0;i<4;i++)
            {
                for (int j=0;j<4;j++)
                {
                    datagame += game[i][j]+"\n";
                }
            }
            datascore+= score.getText().toString() +"\n"+highscore.getText().toString();
            FileOutputStream fOutgame = openFileOutput("data2048.txt", Context.MODE_PRIVATE);
            fOutgame.write(datagame.getBytes());
            fOutgame.close();
            FileOutputStream fOutscore = openFileOutput("score2048.txt", Context.MODE_PRIVATE);
            fOutscore.write(datascore.getBytes());
            fOutscore.close();

        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Lỗi "+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected void ghinhac()
    {
        try
        {
            String data= new String();
            for(int i=0;i<tenbh.length-1;i++)
            {
                data += tenbh[i]+"\n";
            }
            FileOutputStream fOut = openFileOutput("namemusic.txt",Context.MODE_PRIVATE);
            fOut.write(data.getBytes());
            fOut.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Lỗi "+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected void play_click(String name, String folder)
    {
        try
        {
            if(pla == 0) {
                mediaPlayer = MediaPlayer.create(this, this.getRawResIdByName(name,folder));
                int duration = this.mediaPlayer.getDuration();

                int currentPosition = this.mediaPlayer.getCurrentPosition();
                if (currentPosition == 0) {
                    this.seekBar.setMax(duration);
                    String maxTimeString = this.millisecondsToString(duration);
                    this.textMaxTime.setText(maxTimeString);
                } else if (currentPosition == duration) {
                    // Trở lại trạng thái ban đầu trước khi chơi.
                    this.mediaPlayer.reset();
                }
                mediaPlayer.start();
                // Tạo một thread để update trạng thái của SeekBar.
                UpdateSeekBarThread updateSeekBarThread = new UpdateSeekBarThread();
                threadHandler.postDelayed(updateSeekBarThread, 50);
                play.setBackgroundResource(R.drawable.play);
                pla = 1;
            }
            else if(pla == 1) {
                mediaPlayer.pause();
                play.setBackgroundResource(R.drawable.stop);
                pla = 2;
            }
            else if (pla == 2)
            {
                mediaPlayer.start();
                play.setBackgroundResource(R.drawable.play);
                pla = 1;
            }

        }
        catch(Exception ex)
        {

        }
    }
    class UpdateSeekBarThread implements Runnable {
        public void run()  {
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = millisecondsToString(currentPosition);
            textCurrentPosition.setText(currentPositionStr);
            seekBar.setProgress(currentPosition);
            // Ngừng thread 50 mili giây.
            threadHandler.postDelayed(this, 50);
        }
    }

    class UpdateIcon2048 implements Runnable {
        public void run()  {
            if(icon == 0)
            {
                icon = 1;
                imageicon.setBackgroundResource(R.drawable.game1);
            }
            else if (icon == 1)
            {
                icon = 2;
                imageicon.setBackgroundResource(R.drawable.game2);
            }
            else if (icon == 2)
            {
                icon = 3;
                imageicon.setBackgroundResource(R.drawable.game3);
            }
            else if (icon == 3)
            {
                icon = 0;
                imageicon.setBackgroundResource(R.drawable.game);
            }
            threadHandler.postDelayed(this, 500);
        }
    }

    private int getRawResIdByName(String resName, String folder) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        // Trả về 0 nếu không tìm thấy.
        int resID = this.getResources().getIdentifier(resName, folder, pkgName);
        return resID;
    }

    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((long) milliseconds) ;
        return minutes+":"+ (seconds-60*minutes);
    }

    protected void mute_click()
    {
        try
        {
            if (mu == 0 )
            {
                mute.setBackgroundResource(R.drawable.mute);
                mu = 1;
            }
            else {
                mute.setBackgroundResource(R.drawable.unmute);
                mu = 0;
            }
        }
        catch (Exception ex)
        {

        }
    }

    protected void next_click()
    {
        try
        {

        }
        catch (Exception ex)
        {

        }
    }

    protected void back_click()
    {
        try
        {

        }
        catch(Exception ex)
        {

        }
    }

    protected void list_click()
    {
        try
        {
            Intent intent = new Intent(Main.this, MusicClass.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Lỗi "+ ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    protected void setting_click()
    {
        try
        {
            Intent intent = new Intent(Main.this, SettingClass.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Lỗi "+ ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    protected void repeat_click()
    {
        try
        {
            if (rep == 0 )
            {
                repeat.setBackgroundResource(R.drawable.repeat);
                rep=1;
            }
            else if (rep==1){
                repeat.setBackgroundResource(R.drawable.repeatone);
                rep = 2;
            }
            else if (rep==2){
                repeat.setBackgroundResource(R.drawable.repeatoff);
                rep = 0;
            }
        }
        catch (Exception ex)
        {

        }
    }

    protected void shuffee_click()
    {
        try
        {
            if (shuff == 0 )
            {
                shuffee.setBackgroundResource(R.drawable.shuffee);
                shuff = 1;
            }
            else {
                shuffee.setBackgroundResource(R.drawable.shuffeeoff);
                shuff = 0;
            }
        }
        catch (Exception ex)
        {

        }
    }

    protected  void undo_click(){

        for(int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                game[i][j] = backgame[i][j];
            }
        }
        update();
    }

    protected  void reset_click(){
        AlertDialog dialog = new AlertDialog.Builder(Main.this,R.style.Theme_AppCompat_Light_Dialog)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newgame();
                    }
                })
                .setNegativeButton("NO", null)
                .setTitle("RESET")
                .setMessage("You want reset game!")
                .show();
    }
}
