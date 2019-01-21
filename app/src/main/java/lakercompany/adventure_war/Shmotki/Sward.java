package lakercompany.adventure_war.Shmotki;

public class Sward {
    public int res;
    public int dmg;
    public int have=0;
    public int price=0;
    public String descrip;
    public String name;

    public Sward(int res, int dmg, int price, String name, String descrip){
        this.res = res;
        this.dmg = dmg;
        this.price = price;
        this.name = name;
        this.descrip = descrip;
    }
}
