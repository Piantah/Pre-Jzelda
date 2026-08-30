package JZ_Model_alpha.Entities.OtherEntities;

import JZ_Model_alpha.Entities.Entity;

public class Drop extends Entity {
    private DropTypes tipo;
    private int valore;
    public Drop(int valore, DropTypes tipo,int x, int y){
        super(x,y);
        this.tipo=tipo;
        this.valore=valore;

    }

    public DropTypes getTipo() {
        return tipo;
    }

    public int getValore() {
        return valore;
    }
}
