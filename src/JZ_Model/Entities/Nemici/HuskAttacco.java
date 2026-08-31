package JZ_Model.Entities.Nemici;

import JZ_Model.Entities.Entita;

public class HuskAttacco extends Entita {
    public HuskAttacco(int x, int y){
        super(x,y);
    }

    public int getDannoArrecato() {
        int dannoArrecato = 3;
        return dannoArrecato;
    }
}
