package lakercompany.adventure_war.User;

import android.util.Log;

public class UserStatistic {
    public static int id=0;
    public static int tm=0;
    //Типичный монстр
    public static int m1=0;
    //Гантер
    public static int m2=0;
    //Снежный Король
    public static int m3=0;
    //Дерево
    public static int m4=0;
    //Вампир
    public static int m5=0;
    //Лич
    public static int m6=0;

    public static void add(int type){
        switch (type){
            case 1:
                m1++;
                break;
            case 2:
                m2++;
                break;
            case 3:
                m3++;
                break;
            case 4:
                m4++;
                break;
            case 5:
                m5++;
                Log.i("VampirLog","score = " + String.valueOf(m5));
                break;
            case 6:
                m6++;
                break;
        }
    }
}
