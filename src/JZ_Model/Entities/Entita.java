package JZ_Model.Entities;

public abstract class Entita {
    private int xCord;
    private int yCord;
    private String nome;
    private int vita;
    private Direzione direzione;


    //indicano il tipo di movimento
    private int mossa;
    private int mossa_precedente;

    //costruttori
    public Entita(){
        this.xCord=0;
        this.yCord=0;
        direzione= Direzione.NESSUNA;
        mossa =0;
        mossa_precedente =2;
    }
    public Entita(int x, int y){
        this.xCord=x;
        this.yCord=y;
        direzione= Direzione.NESSUNA;
        mossa =0;
        mossa_precedente =2;
    }

    //metodi di movimento
    public void movimentoSinistra(int coef){
        this.xCord-=coef;
        direzione= Direzione.SINISTRA;
        cambiaMossa();
    }
    public void movimentoDestra(int coef){
        this.xCord+=coef;
        direzione= Direzione.DESTRA;
        cambiaMossa();

    }
    public void movimentoSu(int coef){
        this.yCord-=coef;
        direzione= Direzione.SU;
        cambiaMossa();

    }
    public void movimentoGiu(int coef){
        this.yCord+=coef;
        direzione= Direzione.GIU;
        cambiaMossa();

    }

    //metodi per la gestione del tipo di movimento
    public void resetMossa() {
        this.mossa = 0;
    }

    private void cambiaMossa() {
        if(mossa ==0){
            if(mossa_precedente ==1){
                mossa = mossa_precedente;
                mossa_precedente =2;
            }
            else{
                mossa = mossa_precedente;
                mossa_precedente =1;
            }
        }

        else if (mossa ==2){
            mossa_precedente = mossa;
            mossa =1;
        }
        else if (mossa ==1){
            mossa_precedente = mossa;
            mossa =2;
        }
        else resetMossa();
    }

    //setter
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setVita(int vita) {
        this.vita = vita;
    }
    public void setxCord(int xCord) {
        this.xCord = xCord;
    }
    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public void setDirezione(Direzione direzione) {
        this.direzione = direzione;
    }

    public void setMossa(int mossa) {
        this.mossa = mossa;
    }

    //metodi getter
    public int getxCord() {
        return xCord;
    }
    public int getyCord() {
        return yCord;
    }
    public int getVita() {
        return vita;
    }
    public String getNome() {
        return nome;
    }

    public Direzione getDirezione() {
        return direzione;
    }

    public int getMossa() {
        return mossa;
    }

}
