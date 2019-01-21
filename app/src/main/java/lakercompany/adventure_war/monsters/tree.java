package lakercompany.adventure_war.monsters;

import lakercompany.adventure_war.R;

public class tree extends Enemy {

    public tree(int kol){
        mScore = 3;
        monsterId = R.drawable.tree;
        price = 6;

        damage.add(20);
        damage.add(25);
        damage.add(30);
        damage.add(40);
        damage.add(50);
        damage.add(60);

        hill_point.add(60);
        hill_point.add(80);
        hill_point.add(100);
        hill_point.add(120);
        hill_point.add(150);
        hill_point.add(200);

        lvl.add(0);
        lvl.add(10);
        lvl.add(25);
        lvl.add(50);
        lvl.add(75);
        lvl.add(100);

        int i=lvl.size()-1;
        while(kol<lvl.get(i)){
            i--;
        }
        Damage = damage.get(i);
        hp = hill_point.get(i);
    }
}
