package JZ_Model_alpha.Entities.Enemies;

import JZ_Model_alpha.Entities.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Husk extends Enemy {

    private Collection<Entity> attacchi;
    public Husk(int id) {
        super("Husk", id);
        setVita(4);
        setValue_drop(10);
        setXpOnKill(10);
    }
    public Husk(int id,int x,int y) {
        super("Husk", id,x,y);
        setVita(4);
        setValue_drop(10);
        setXpOnKill(10);
    }


    public void attacca(){
        Random r = new Random();
        attacchi = new ArrayList<>();
        int valore=r.nextInt(6);
        if(valore<=1){
            return;
        }
        if(valore<=4){
            HuskAttack attacco = null;
            switch (getDirezione()){
                case RIGHT ->  attacco=new HuskAttack(getxCord()+1,getyCord());
                case LEFT ->  attacco=new HuskAttack(getxCord()-1,getyCord());
                case DOWN ->  attacco=new HuskAttack(getxCord(),getyCord()+1);
                case UP ->  attacco=new HuskAttack(getxCord(),getyCord()-1);
            }
            attacchi.add(attacco);
            return;
        }
        //always true, ma lo lascio per leggibilita del codice
        if(valore==5){
            HuskAttack attacco = null;

            attacco=new HuskAttack(getxCord()+1,getyCord());
            attacchi.add(attacco);

            attacco=new HuskAttack(getxCord()-1,getyCord());
            attacchi.add(attacco);

            attacco=new HuskAttack(getxCord(),getyCord()-1);
            attacchi.add(attacco);

            attacco=new HuskAttack(getxCord(),getyCord()+1);
            attacchi.add(attacco);
        }

    }


    public Collection<Entity> getAttacchi() {
        return attacchi;
    }
}
