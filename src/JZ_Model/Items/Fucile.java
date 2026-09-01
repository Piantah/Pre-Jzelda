package JZ_Model.Items;

import JZ_Model.Entities.Proiettili.Proiettile;
import JZ_Model.Entities.Proiettili.ProiettileGiocatore;
import JZ_Model.Entities.Entita;

public class Fucile extends Item{
   private Proiettile proiettile;



    public Fucile(String nomeItem, int danniArrecati, Entita propietario) {
        super(nomeItem, danniArrecati);
        this.propietario = propietario;
    }

    public Proiettile getProiettile() {
        return proiettile;
    }

    //viene creato Un proiettile soltanto. questo proiettile esiste finchè non raggiunge la sua distanza massima
    public void usa(){
        if(proiettile ==null){
            proiettile =new ProiettileGiocatore(propietario,5,this);
        }
        else{
            if(proiettile.getDistanza()<=0){
                proiettile =null;
                proiettile =new ProiettileGiocatore(propietario,5,this);
            }
        }

    }

}
