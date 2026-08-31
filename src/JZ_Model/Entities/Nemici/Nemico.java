package JZ_Model.Entities.Nemici;

import JZ_Model.Entities.Entita;

public abstract class Nemico extends Entita {
    private int id;
    private int valoreDrop;
    private int xpDati;


    public abstract void attacca();
    public Nemico(String nome, int id){
        super();
        setNome(nome);
        this.id=id;
        setMossa(1);
    }
    public Nemico(String nome, int id, int x, int y){
        super(x,y);
        setNome(nome);
        this.id=id;
        setMossa(1);
    }
    //tipo movimento nemico
    public void resetMossa() {
        setMossa(1);
    }
    //setter
    public void setValoreDrop(int valoreDrop) {this.valoreDrop = valoreDrop;}
    public void setXpDati(int xpDati) {
        this.xpDati = xpDati;
    }
    //getter
    public int getXpDati() {
        return xpDati;
    }
    public int getValoreDrop() {return valoreDrop;}
    public int getId() {
        return id;
    }
}
