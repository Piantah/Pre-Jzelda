package JZ_Model.Entities.Proiettili;

import JZ_Model.Entities.Entita;

public class ProiettileNemico extends Proiettile {
    private final int danni;
    private final int id;

    //costruttori
    public ProiettileNemico(Entita owner, int distanza, int danni, int id) {
        super(owner, distanza);
        this.danni=danni;
        this.id=id;
    }
    public ProiettileNemico(Entita proprietario, int distanza, int danni, int id, int x, int y) {
        super(proprietario, distanza);
        this.danni=danni;
        this.id=id;
        setxCord(x);
        setyCord(y);
    }

    public int getDanni() {
        return danni;
    }

    public int getId() {
        return id;
    }
}
