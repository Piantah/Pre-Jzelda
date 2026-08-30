package JZ_Model_alpha.Entities.Enemies;

import JZ_Model_alpha.Entities.Entity;

public class HuskAttack extends Entity {
    private int dannoArrecato;
    public HuskAttack(int x, int y){
        super(x,y);
        dannoArrecato = 3;
    }

    public int getDannoArrecato() {
        return dannoArrecato;
    }
}
