package JZ_Model.Entities.OtherEntities;

import JZ_Model.Entities.Entita;
import JZ_Model.Items.Item;

import java.util.ArrayList;
import java.util.Collection;

public class Player extends Entita {
    private int soldi;
    private final Collection<Item> oggetti;
    private int tenatativi;
    private int xp;
    private int morti;
    private int skin;

    //il player è singleTon
    private static Player instance;


    public static Player getInstance(){
        if(instance==null)instance= new Player();
        return instance;
    }

    private Player(){
        super();
        setVita(6);
        skin=0;
        this.soldi=0;
        this.oggetti =new ArrayList<>();
        morti=0;
        xp=0;
        tenatativi=1;
    }


    //metodi oggetti

    public Item usa( String nome){
        for(Item i: oggetti){
            if(i.getNomeItem().equals(nome)){
                return i;
            }
        }
        return null;
    }
    public void resetOggetti(){
        oggetti.clear();
    }

    //metodi aggiornamento valori player
    public void perdiVita(int coef){
        setVita(getVita()-coef);
        if(getVita()<0)setVita(0);
    }
    public void guadagnaVita(int coef){
        setVita(getVita()+coef);
        if(getVita()>6){
            setVita(6);
        }
    }
    public void paga(int coef){
        this.soldi-=coef;
    }
    public void guadagna(int coef){
        this.soldi+=coef;
    }
    public void aggiungiOggetti(Item i){
        oggetti.add(i);}

    public void guadagnaXp(int val){
        this.xp+=val;
    }
    public void ritenta(){
        tenatativi+=1;
    }
    public void muori(){
        morti+=1;
    }

    //setter

    public void setSoldi(int soldi) {
        this.soldi = soldi;
    }

    public void setMorti(int morti) {
        this.morti = morti;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setTenatativi(int tenatativi) {
        this.tenatativi = tenatativi;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    //metodi getter.
    public int getSoldi() {
        return soldi;
    }

    public Collection<Item> getOggetti() {
        return oggetti;
    }

    public int getTenatativi() {
        return tenatativi;
    }

    public int getMorti() {
        return morti;
    }

    public int getXp() {
        return xp;
    }

    public int getSkin() {
        return skin;
    }
}
