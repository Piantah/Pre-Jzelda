package JZ_Model.Items;

import JZ_Model.Entities.Proiettili.Proiettile;
import JZ_Model.Entities.Proiettili.ProiettileGiocatore;
import JZ_Model.Entities.Entita;
import JZ_Model.Entities.OtherEntities.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Staffa extends Item{
    Entita proprietario;
    Collection<Proiettile> proiettili;
    public Staffa(String nomeItem, Entita proprietario) {
        super(nomeItem, 10);
        proiettili =new ArrayList<>();
        this.proprietario =proprietario;
    }


    public Collection<Proiettile> getBullet() {
        return proiettili;
    }
    public void usa(){
        boolean canShot=true;
        for(Proiettile b: proiettili){
            if((b.getMxdistanza()-b.getDistanza())<2){
                canShot=false;
            }
        }
        if(canShot){
            proiettili.add(new ProiettileGiocatore(proprietario,10,this));
            if(proprietario instanceof Player)((Player) proprietario).guadagnaVita(1);
        }
        aggiornaProiettili();
    }

    private void aggiornaProiettili(){
        Collection<Proiettile> tmp = new ArrayList<>();
        for(Proiettile b: proiettili) if(b.getDistanza()<=0)tmp.add(b);
        proiettili.removeAll(tmp);
    }

    public void setProprietario(Entita proprietario) {
        this.proprietario = proprietario;
    }
}
