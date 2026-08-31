package JZ_Model.Entities.OtherEntities;

import JZ_Model.Entities.Entita;

public class Ostacolo extends Entita {
    private final int id_tipo_ostacolo;
    public Ostacolo(String nome, int id, int x, int y){
        super(x,y);
        this.id_tipo_ostacolo=id;
        setNome(nome);
    }

    public int getId_tipo_ostacolo() {
        return id_tipo_ostacolo;
    }
}
