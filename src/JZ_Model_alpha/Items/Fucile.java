package JZ_Model_alpha.Items;

import JZ_Model_alpha.Entities.Bullets.Bullet;
import JZ_Model_alpha.Entities.Bullets.PlayerShot;
import JZ_Model_alpha.Entities.Entity;

public class Fucile extends Item{
    Bullet bullet;
    Entity owner;


    public Fucile(String nomeItem, int danniArrecati, Entity owner) {
        super(nomeItem, danniArrecati);
        this.owner=owner;
    }

    public Bullet getBullet() {
        return bullet;
    }
    public void usa(){
        if(bullet==null){
            bullet=new PlayerShot(owner,5,this);
        }
        else{
            if(bullet.getDistanza()<=0){
                bullet=null;
                bullet=new PlayerShot(owner,5,this);
            }
        }

    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }
}
