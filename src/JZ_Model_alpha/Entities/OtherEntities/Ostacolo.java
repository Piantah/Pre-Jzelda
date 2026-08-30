package JZ_Model_alpha.Entities.OtherEntities;

import JZ_Model_alpha.Entities.Entity;

public class Ostacolo extends Entity {
    private int id_tipo_ostacolo;
    public Ostacolo(String nome, int id, int x, int y){
        super(x,y);
        this.id_tipo_ostacolo=id;
        setNome(nome);
    }

    public int getId_tipo_ostacolo() {
        return id_tipo_ostacolo;
    }
}
