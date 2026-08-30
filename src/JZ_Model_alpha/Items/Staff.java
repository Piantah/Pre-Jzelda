package JZ_Model_alpha.Items;

import JZ_Model_alpha.Entities.Bullets.Bullet;
import JZ_Model_alpha.Entities.Bullets.PlayerShot;
import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Entities.OtherEntities.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Staff extends Item{
    Entity owner;
    Collection<Bullet> bullets;
    public Staff(String nomeItem, Entity o) {
        super(nomeItem, 10);
        bullets=new ArrayList<>();
        this.owner=o;
    }


    public Collection<Bullet> getBullet() {
        return bullets;
    }
    public void usa(){
        boolean canShot=true;
        for(Bullet b: bullets){
            if((b.getMxdistanza()-b.getDistanza())<2){
                canShot=false;
            }
        }
        if(canShot){
            bullets.add(new PlayerShot(owner,10,this));
            if(owner instanceof Player)((Player) owner).guadagnaVita(1);
        }
        updateBullets();
    }

    private void updateBullets(){
        Collection<Bullet> tmp = new ArrayList<>();
        for(Bullet b: bullets) if(b.getDistanza()<=0)tmp.add(b);
        bullets.removeAll(tmp);
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }
}
