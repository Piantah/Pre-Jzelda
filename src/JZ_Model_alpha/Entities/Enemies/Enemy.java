package JZ_Model_alpha.Entities.Enemies;

import JZ_Model_alpha.Entities.Entity;

public abstract class Enemy extends Entity {
    private int id;
    private int value_drop;
    private int xpOnKill;

    public Enemy(String nome, int id){
        super();
        setNome(nome);
        this.id=id;
        setMove(1);
    }
    public Enemy(String nome, int id,int x, int y){
        super(x,y);
        setNome(nome);
        this.id=id;
        setMove(1);
    }

    public void setXpOnKill(int xpOnKill) {
        this.xpOnKill = xpOnKill;
    }

    public int getXpOnKill() {
        return xpOnKill;
    }

    public void resetMove() {
        setMove(1);
    }
    //setter
    public void setValue_drop(int value_drop) {this.value_drop = value_drop;}
    //getter
    public int getValue_drop() {return value_drop;}
    public int getId() {
        return id;
    }
}
