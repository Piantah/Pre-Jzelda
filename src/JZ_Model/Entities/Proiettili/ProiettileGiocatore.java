package JZ_Model.Entities.Proiettili;

import JZ_Model.Entities.Entita;
import JZ_Model.Items.Item;

public class ProiettileGiocatore extends Proiettile {
    private final Item oggetto;

    public ProiettileGiocatore(Entita owner, int distanza, Item oggetto) {
        super(owner, distanza);
        this.oggetto=oggetto;
    }

    public Item getOggetto() {
        return oggetto;
    }



}
