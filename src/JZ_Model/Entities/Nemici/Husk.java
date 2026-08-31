package JZ_Model.Entities.Nemici;

import JZ_Model.Entities.Entita;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Husk extends Nemico {

    private Collection<Entita> attacchi;

    //costruttori
    public Husk(int id) {
        super("Husk", id);
        setVita(4);
        setValoreDrop(10);
        setXpDati(10);
    }
    public Husk(int id,int x,int y) {
        super("Husk", id,x,y);
        setVita(4);
        setValoreDrop(10);
        setXpDati(10);
    }


    public void attacca(){
        Random r = new Random();
        attacchi = new ArrayList<>();
        int valore=r.nextInt(6);
        if(valore<=1){
            return;
        }
        if(valore<=4){
            HuskAttacco attacco = null;
            switch (getDirezione()){
                case DESTRA ->  attacco=new HuskAttacco(getxCord()+1,getyCord());
                case SINISTRA ->  attacco=new HuskAttacco(getxCord()-1,getyCord());
                case GIU ->  attacco=new HuskAttacco(getxCord(),getyCord()+1);
                case SU ->  attacco=new HuskAttacco(getxCord(),getyCord()-1);
            }
            attacchi.add(attacco);
            return;
        }
        //always true, ma lo lascio per leggibilita del codice
        if(valore==5){
            HuskAttacco attacco;

            attacco=new HuskAttacco(getxCord()+1,getyCord());
            attacchi.add(attacco);

            attacco=new HuskAttacco(getxCord()-1,getyCord());
            attacchi.add(attacco);

            attacco=new HuskAttacco(getxCord(),getyCord()-1);
            attacchi.add(attacco);

            attacco=new HuskAttacco(getxCord(),getyCord()+1);
            attacchi.add(attacco);
        }

    }


    public Collection<Entita> getAttacchi() {
        return attacchi;
    }
}
