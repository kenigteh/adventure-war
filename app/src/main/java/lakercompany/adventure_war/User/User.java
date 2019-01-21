package lakercompany.adventure_war.User;


import android.location.Location;
import android.util.Log;

import java.util.ArrayList;

public class User {
    public static int ID;
    public static String name = "user";
    public static int age=14;
    public static int pers=1;
    public static int hp=100;
    public static int damage=10;
    public static int gold = 5;
    public static int lvl = 1;
    public static int score = 1;

    public static boolean chek = true;
    private static ArrayList<Integer> lvlList = new ArrayList<>();
    private static ArrayList<Integer> dmgList = new ArrayList<>();
    private static ArrayList<Integer> hpList = new ArrayList<>();
    public User(){
        if(chek){
            lvlList.add(1);
            lvlList.add(5);
            lvlList.add(15);
            lvlList.add(30);
            lvlList.add(50);
            lvlList.add(80);
            lvlList.add(150);
            lvlList.add(10000000);

            dmgList.add(10);
            dmgList.add(12);
            dmgList.add(15);
            dmgList.add(19);
            dmgList.add(24);
            dmgList.add(30);
            dmgList.add(37);
            dmgList.add(1000000);

            hpList.add(50);
            hpList.add(60);
            hpList.add(70);
            hpList.add(80);
            hpList.add(90);
            hpList.add(100);
            hpList.add(110);
            hpList.add(110000000);

            chek=false;
        }
    }

    public void lvlChek(){
        int i=0;
        while(score >= lvlList.get(i)){
            if(lvlList.size()-1!=i)
                i++;
            else
                break;
        }
        Log.i("LvlLog ", String.valueOf(i));
        lvl=i;
        damage = dmgList.get(i-1);
        hp = hpList.get(i-1);

    }
}