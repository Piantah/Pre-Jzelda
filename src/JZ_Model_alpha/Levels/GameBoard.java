package JZ_Model_alpha.Levels;

import JZ_Model_alpha.Entities.Entity;

import java.util.ArrayList;
import java.util.Collection;

public  abstract class  GameBoard {
    private int[][] mappa;
    private int lenght;
    private int width;
    protected Collection<Entity> entita;

    public GameBoard(){
        this.mappa=new int[16][16];
        this.lenght=16;
        this.width=16;
        entita= new ArrayList<>();

    }
    public GameBoard(int lenght, int width){
        this.mappa=new int[width][lenght];
        this.lenght=lenght;
        this.width=width;
        entita= new ArrayList<>();
    }

//    protected abstract void createEntities();

    //seter


    public void setEntita(Collection<Entity> entita) {
        this.entita = entita;
    }

    //getter
    public int getLength() {
        return lenght;
    }
    public int getWidth() {return width;}
    public int[][] getMappa() {
        return mappa;
    }

    public Collection<Entity> getEntita() {
        return entita;
    }
}
