package lakercompany.adventure_war.Shmotki;

public class Weapon {
    public int res;
    public int hp;
    public int have=0;
    public int price=0;
    public String descrip;
    public String name;

    public Weapon(int res, int hp, int price, String name, String descrip){
        this.res = res;
        this.hp = hp;
        this.price = price;
        this.name = name;
        this.descrip = descrip;
    }
}
