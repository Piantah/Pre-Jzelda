package JZ_Model.Entities.Proiettili;

import JZ_Model.Entities.Entita;

public abstract class Proiettile extends Entita {
    private final int mxdistanza;
    private int distanza;


    public Proiettile(Entita owner , int distanza){
        super(owner.getxCord(), owner.getyCord());
        setDirezione(owner.getDirezione());
        this.distanza=distanza;
        mxdistanza=distanza;
    }

    public void fuoco(){
        if (distanza>0){

            switch (getDirezione()){
                case SINISTRA -> movimentoSinistra(1);
                case DESTRA -> movimentoDestra(1);
                case SU -> movimentoSu(1);
                case GIU -> movimentoGiu(1);
            }
            distanza-=1;
        }

    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }


    public int getMxdistanza() {
        return mxdistanza;
    }
}
