package lakercompany.adventure_war.Activnosty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.User.UserStatistic;
import lakercompany.adventure_war.Zaprosi.ChangeInfo;
import lakercompany.adventure_war.monsters.ice_king;
import lakercompany.adventure_war.R;
import lakercompany.adventure_war.WorkClass.SwipeDetector;
import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.monsters.Enemy;
import lakercompany.adventure_war.monsters.gunter;
import lakercompany.adventure_war.monsters.lich;
import lakercompany.adventure_war.monsters.tmon;
import lakercompany.adventure_war.monsters.tree;
import lakercompany.adventure_war.monsters.vamper;

public class Fighting extends AppCompatActivity{
    private GestureDetector gestureDetector;
    UserStatistic us = new UserStatistic();
    User user = new User();
    UserShmot userShmot= new UserShmot();
    Enemy monster;
    ArrayList<Integer> movearr = new ArrayList<>();

    ImageView moveimg;
    ImageView mon;
    ImageView me;

    TextView herohp;
    TextView herodmg;
    TextView monsterhp;
    TextView monsterdmg;
    TextView time;
    TextView swipe;

    Thread tr;

    int tuh = user.hp + userShmot.weapon.get(userShmot.tweapon).hp;
    int tdmg = user.damage + userShmot.swards.get(userShmot.tsward).dmg;
    int t=6;
    int m=2;
    int point = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighting);

        switch (us.tm){
            case 1:
                monster=new tmon(us.m1);
                break;
            case 2:
                monster=new gunter(us.m2);
                break;
            case 3:
                monster=new ice_king(us.m3);
                break;
            case 4:
                monster=new tree(us.m4);
                break;
            case 5:
                monster=new vamper(us.m5);
                break;
            case 6:
                monster=new lich(us.m6);
                break;
        }

        moveimg = (ImageView) findViewById(R.id.move);
        mon = (ImageView) findViewById(R.id.mon);
        me = (ImageView) findViewById(R.id.my_pers);

        herohp  = (TextView) findViewById(R.id.herohp);
        herodmg  = (TextView) findViewById(R.id.herodmg);
        monsterhp  = (TextView) findViewById(R.id.monsterhp);
        monsterdmg  = (TextView) findViewById(R.id.monsterdmg);
        time  = (TextView) findViewById(R.id.time);
        swipe = (TextView) findViewById(R.id.swips);

        mon.setImageResource(monster.monsterId);

        switch (user.pers){
            case 1:me.setImageResource(R.drawable.finn);
                break;
            case 2:me.setImageResource(R.drawable.jake);
                break;
            default:me.setImageResource(R.drawable.bublegum);
        }

        changeText();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Fighting.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Fighting.this,"Старт",Toast.LENGTH_LONG).show();
                        gestureDetector = initGestureDetector();
                        View view = findViewById(R.id.ActivityFighting);
                        view.setOnTouchListener(new View.OnTouchListener() {
                            public boolean onTouch(View v, MotionEvent event) {
                                return gestureDetector.onTouchEvent(event);
                            }
                        });
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View arg0) {
                            }
                        });
                        startTh();

                    }
                });

            }
        });
        thread.setDaemon(true);
        thread.start();

        tr = new Thread(new Runnable() {
            @Override
            public void run() {
                while(monster.hp>0){
                    if(tuh<1){
                        Fighting.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               lose();
                            }
                        });
                        break;
                    }
                    else {
                        t = 6;
                        point++;
                        m = point;
                        Fighting.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                generateStack();
                                changeText();
                                swipe.setText(String.valueOf(m));
                            }
                        });
                        while (t != 0) {
                            Fighting.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t--;
                                    time.setText(String.valueOf(t));
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (m == 0)
                            monster.hp -= tdmg;
                        else
                            tuh -= monster.Damage;
                    }

                }
                if(tuh>0) {
                    //мы победили
                    user.score += monster.mScore;
                    user.lvlChek();
                    user.gold+=monster.price;

                    us.add(us.tm);

                    try {
                        new ChangeInfo(Fighting.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Fighting.this.finish();
                }

            }
        });
        tr.setDaemon(true);


    }
    void lose(){
        Toast.makeText(Fighting.this,"Ты проиграл",Toast.LENGTH_LONG).show();
        Fighting.this.finish();
    }

    void startTh(){
        tr.start();
    }

    public void generateStack(){
        movearr.clear();
        for(int i=0;i<point;i++)
            movearr.add(1 + (int)(Math.random() * ((4- 1) + 1)));
        changeIMG();
    }

    private void changeIMG(){
        if(movearr.isEmpty()){
            moveimg.setImageResource(R.drawable.galka);
        }
        else if(movearr.get(0)==1){
            moveimg.setImageResource(R.drawable.up_arrow);
        }
        else if(movearr.get(0)==2){
            moveimg.setImageResource(R.drawable.right_arrow);
        }
        else if(movearr.get(0)==3){
            moveimg.setImageResource(R.drawable.down_arrow);
        }
        else if(movearr.get(0)==4){
            moveimg.setImageResource(R.drawable.left_arrow);
        }
    }

    private void changeText(){
        herohp.setText(String.valueOf(tuh));
        herodmg.setText(String.valueOf(tdmg));
        monsterhp.setText(String.valueOf(monster.hp));
        monsterdmg.setText(String.valueOf(monster.Damage));
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        if(movearr.get(0)==3 && !movearr.isEmpty()){
                            action();
                        }
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        if(movearr.get(0)==1 && !movearr.isEmpty()){
                            action();
                        }
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        if(movearr.get(0)==4 && !movearr.isEmpty()){
                            action();
                        }
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        if(movearr.get(0)==2 && !movearr.isEmpty()){
                           action();
                        }
                    }
                } catch (Exception e) {} //for now, ignore
                return false;
            }

            private void action(){
                movearr.remove(0);
                m--;
                swipe.setText(String.valueOf(m));
                changeIMG();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
