package JZ_Model_alpha.Entities.Bullets;

import JZ_Model_alpha.Entities.Entity;

public class EnemyShot extends Bullet{
    private int danni;
    private int id;
    public EnemyShot(Entity owner, int distanza,int danni,int id) {
        super(owner, distanza);
        this.danni=danni;
        this.id=id;
    }
    public EnemyShot(Entity owner, int distanza,int danni,int id,int x, int y) {
        super(owner, distanza);
        this.danni=danni;
        this.id=id;
        setxCord(x);
        setyCord(y);
    }

    public int getDanni() {
        return danni;
    }

    public int getId() {
        return id;
    }
}
