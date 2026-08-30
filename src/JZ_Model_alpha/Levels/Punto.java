package JZ_Model_alpha.Levels;

public class Punto {
    public int x;
    public int y;
    public Punto(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Punto){
            if(((Punto) obj).x==this.x &&((Punto) obj).y==this.y) return true;
        }
        return false;
    }
}
