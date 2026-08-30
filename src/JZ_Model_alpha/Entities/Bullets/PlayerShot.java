package JZ_Model_alpha.Entities.Bullets;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Items.Item;

public class PlayerShot extends Bullet{
    private Item s;

    public PlayerShot(Entity owner, int distanza, Item sp) {
        super(owner, distanza);
        this.s=sp;
    }

    public Item getS() {
        return s;
    }



}
