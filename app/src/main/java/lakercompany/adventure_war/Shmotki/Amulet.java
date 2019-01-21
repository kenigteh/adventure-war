package lakercompany.adventure_war.Shmotki;

public class Amulet {
    public int res;
    public int type;
    public int have=0;
    public int price=0;
    public String descrip;
    public String name;
    public Amulet(int res, int type, int price, String name, String descrip){
        this.res = res;
        this.type = type;
        this.price = price;
        this.name = name;
        this.descrip = descrip;
    }
}
