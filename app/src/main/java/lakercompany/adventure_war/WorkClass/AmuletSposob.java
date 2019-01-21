package lakercompany.adventure_war.WorkClass;

public class AmuletSposob {

    public String getSposobnost(int type){
        switch (type){
            case 1:
                return "Здоровье";
            case 2:
                return "Опыт";
            case 3:
                return "Блокирование";
            case 4:
                return "Золото";
            default:
                return "Крит. урон";
        }
    }
}
