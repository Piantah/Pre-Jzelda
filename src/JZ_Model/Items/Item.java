package JZ_Model.Items;

import JZ_Model.Entities.Entita;

public abstract class Item {
    private String nomeItem;
    private int danniArrecati;
    protected Entita propietario;
    Item(String nomeItem, int danniArrecati){

        this.nomeItem=nomeItem;
        this.danniArrecati=danniArrecati;
    }
    public abstract void usa();

    public int getDanniArrecati() {
        return danniArrecati;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setPropietario(Entita propietario) {
        this.propietario = propietario;
    }


}
