package JZ_Model.Items;

import JZ_Model.Entities.Direzione;
import JZ_Model.Entities.Entita;
import JZ_Model.Levels.Punto;

public class Spada extends Item{
    private Punto attacco;
    public Spada(String nomeItem, int danniArrecati, Entita proprietario) {
        super(nomeItem, danniArrecati);
        this.propietario=proprietario;
    }

    @Override
    //crea un punto davanti a se
    public void usa() {
        attacco=null;
        int x_tmp = propietario.getxCord();
        int y_tmp = propietario.getyCord();
        switch (propietario.getDirezione()) {
            case SU -> y_tmp -= 1;
            case GIU -> y_tmp += 1;
            case SINISTRA -> x_tmp -= 1;
            case DESTRA -> x_tmp += 1;
        }
        attacco=new Punto(x_tmp,y_tmp);
    }

    public Punto getAttacco() {
        return attacco;
    }
}
