package JZ_Model.Levels;

import JZ_Model.Entities.Entita;

import java.util.ArrayList;
import java.util.Collection;

public  abstract class Tabellone {
    private int[][] mappa;
    private int lunghezza;
    private int altezza;
    protected Collection<Entita> entita;

    //Costruttori
    public Tabellone(){
        this.mappa=new int[16][16];
        this.lunghezza =16;
        this.altezza =16;
        entita= new ArrayList<>();

    }
    //per tabelloni di lunghezza variabile
    public Tabellone(int lunghezza, int altezza){
        this.mappa=new int[altezza][lunghezza];
        this.lunghezza = lunghezza;
        this.altezza = altezza;
        entita= new ArrayList<>();
    }

    //seter


    public void setEntita(Collection<Entita> entita) {
        this.entita = entita;
    }

    //getter
    public int getLunghezza() {
        return lunghezza;
    }
    public int getAltezza() {return altezza;}
    public int[][] getMappa() {
        return mappa;
    }

    public Collection<Entita> getEntita() {
        return entita;
    }
}
