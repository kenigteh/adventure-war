package lakercompany.adventure_war.User;

import java.util.ArrayList;
import java.util.List;

import lakercompany.adventure_war.R;
import lakercompany.adventure_war.Shmotki.Amulet;
import lakercompany.adventure_war.Shmotki.Sward;
import lakercompany.adventure_war.Shmotki.Weapon;

public class UserShmot {
    public static int id;

    public static int ts=1;
    public static int tnum;

    public static int tsward=0;
    public static int tweapon=0;
    public static int tamulet=0;

    public static List<Sward> swards = new ArrayList<>();
    public static List<Weapon> weapon = new ArrayList<>();
    public static List<Amulet> amulet = new ArrayList<>();

    private static boolean chek = true;
    public UserShmot(){
        if(chek){
            swards.add(new Sward(R.drawable.m3, 11, 0,"Меч из коры","Самый слабый, и в тоже время эффективный меч. Когда герой блуждал по миру, то увидел старое дерево. Именно из его легендарной коры был сотворён этот меч."));
            swards.add(new Sward(R.drawable.m2, 32, 10,"Кровавый меч","Жил раньше в этих землях монстер. Рождён он был в самом пекле. Ни один герой не мог побороть его. Но однажды пришел маленький мальчик и бросил ему вызов. Пока монстр смеялся, мальчик подкрался сзади и нанес удар. Когда монстр уирал, его кровь закипела, и превратилась в этот кровавый меч."));
            swards.add(new Sward(R.drawable.m5, 8, 17,"Травяной меч","Однажды случилась великая битва. Герой срадажся доблестно, но в коце сражения потерял руку. Через некоторое время пришел волшебник и сделал травяной меч взамен руки, и теперь вы можете купить его"));
            swards.add(new Sward(R.drawable.m4, 15, 20,"Финомеч","Древнее позмелье, охраняемое кучей монстров, смертельные ловушки, всё это преодолел наш герой, чтобы добыть Легендарный меч души, теперь вы можете преобрести его в нашем магазине."));
            swards.add(new Sward(R.drawable.m1, 10, 5,"Скарлет","Главный меч во вселенной, хоть и не является лучшим. Он выполнен из чистого золота"));

            weapon.add((new Weapon(R.drawable.w1, 10, 0, "Шапка Фина","Легендарная шапка фина! Даёт немного жизней, но в ней вы всегда выглядите курто!")));
            weapon.add((new Weapon(R.drawable.w2, 30, 30, "Ледяная броня","Сам ледяной король создал эту броню! Она отлично защитит тебя от любых атак!")));
            weapon.add((new Weapon(R.drawable.w3, 20, 20, "Корона Короля","Когда Грибная война ещё не началось, ученые создали корону, спосбную подчинять стихию льда. Прошло время, и вот теперь её владеет сам Ледяной Король! Но за хорошую цену, он готов продать её тебе.")));
            weapon.add((new Weapon(R.drawable.w4, 15, 10, "Рюкзак Фина","Большой рюкзак, в котором вы смодете хранить свои вещи.")));
            weapon.add((new Weapon(R.drawable.w5, 50, 50, "Броня Цильдерона","Самая сильная броня в игре! Сами боги выковали её в своей поднебесной кузнице. И вот она лежит на прелавке, ожидая достойного героя, за хорошую плату.")));

            amulet.add(new Amulet(R.drawable.a1, 1, 0, "Мужицкая книга","Самая брутальная книга во всей вселенной. Познав её секреты, вам откроются пути ко всем сердцам + вы получите дополнительные 10% здоровья."));
            amulet.add(new Amulet(R.drawable.a2, 2, 30, "Пульт Багов","Открывает вам путь во все дыры вселенной, и вы получаете нечто!"));
            amulet.add(new Amulet(R.drawable.a3, 3, 30, "Браслет защиты","Даёт шанс заблокировать аттаку врага"));
            amulet.add(new Amulet(R.drawable.a4, 4, 30, "Золотая медаль","Приносит успех и дополнительные деньги за убийство монстров"));
            amulet.add(new Amulet(R.drawable.a5, 5, 30, "Огненный Амулет","Даёт возможность критического урона по противнику."));

            chek = false;
        }

    }
}
