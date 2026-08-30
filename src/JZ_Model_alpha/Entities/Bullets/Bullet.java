package JZ_Model_alpha.Entities.Bullets;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Items.Item;

public class Bullet extends Entity {
    private int mxdistanza;
    private int distanza;


    public Bullet(Entity owner ,int distanza){
        super(owner.getxCord(), owner.getyCord());
        setDirezione(owner.getDirezione());
        this.distanza=distanza;
        mxdistanza=distanza;
    }

    public void fire(){
        if (distanza>0){

            switch (getDirezione()){
                case LEFT -> moveLeft(1);
                case RIGHT -> moveRight(1);
                case UP -> moveUp(1);
                case DOWN -> moveDown(1);
            }
            distanza-=1;
        }

    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }


    public int getMxdistanza() {
        return mxdistanza;
    }
}
