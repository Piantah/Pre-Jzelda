package JZ_Model.Levels;

import JZ_Model.Entities.Direzione;

public class Gate {
    private final int x;
    private final int y;
    private final Direzione direzioneIngresso;
    private final int id_Link;
    private final int nLink;

    public Gate(int x, int y, Direzione direzioneIngresso, int id_Link, int nLink){
        this.x=x;
        this.y=y;
        this.direzioneIngresso=direzioneIngresso;
        this.id_Link=id_Link;
        this.nLink=nLink;
    }

    public boolean enter(int x, int y, Direzione d){
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
