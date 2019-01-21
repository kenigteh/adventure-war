package lakercompany.adventure_war.monsters;

import java.util.ArrayList;

public class Enemy {
    public int mScore;
    public int monsterId;
    public int Damage;
    public int hp;
    public int price;

    public ArrayList<Integer> damage = new ArrayList<>();
    public ArrayList<Integer> hill_point = new ArrayList<>();
    public ArrayList<Integer> lvl = new ArrayList<>();
}

/*

Виды монстров
1 - типичный монстр
2 - гантер
3 - снежный король
4 - дерево
5 - вампир
6 -лич

 */
