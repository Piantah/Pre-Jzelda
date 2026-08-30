package JZ_Model_alpha.Levels;

import JZ_Model_alpha.Entities.Facing;

public class Gate {
    private int x;
    private int y;
    private Facing direzioneIngresso;
    private int id_Link;
    private int nLink;

    public Gate(int x, int y, Facing direzioneIngresso,int id_Link,int nLink){
        this.x=x;
        this.y=y;
        this.direzioneIngresso=direzioneIngresso;
        this.id_Link=id_Link;
        this.nLink=nLink;
    }

    public boolean enter(int x, int y, Facing d){
        if(x==this.x && y==this.y && d==direzioneIngresso)return true;
        return false;
    }


    public int getId_Link() {
        return id_Link;
    }

    public int getnLink() {
        return nLink;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
