package JZ_Model_alpha.Entities.OtherEntities;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Items.Item;

import java.util.ArrayList;
import java.util.Collection;

public class Player extends Entity {
    private int soldi;
    private Collection<Item> items;
    private int tenatativi;
    private int xp;
    private int morti;
    private int skin;

    public Player(String nome){
        super();
        setVita(6);
        skin=0;
        this.soldi=0;
        this.items=new ArrayList<>();
        setNome(nome);
        morti=0;
        xp=0;
        tenatativi=1;

    }


    //metodi item e attacco

    public Item usa( String nome){
        for(Item i: items){
            if(i.getNomeItem().equals(nome)){
                return i;
            }
        }
        return null;
    }
    public void resetItems(){
        items.clear();
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
    public void aggiungiItem(Item i){items.add(i);}

    public void gainXp(int val){
        this.xp+=val;
    }
    public void ritenta(){
        tenatativi+=1;
    }
    public void muori(){
        morti+=1;
    }

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

    public Collection<Item> getItems() {
        return items;
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
