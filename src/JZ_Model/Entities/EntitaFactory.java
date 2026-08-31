package JZ_Model.Entities;

import JZ_Model.Entities.Nemici.Husk;
import JZ_Model.Entities.Nemici.Mago;
import JZ_Model.Entities.OtherEntities.Mercante;
import JZ_Model.Entities.OtherEntities.Ostacolo;

public class EntitaFactory {
    public static Entita creaEntita(char valore, int id, int x, int y) {
        return switch (valore) {
            case 'H' -> new Husk(id, x, y);
            case 'M' -> new Mago("mago", id, x, y);
            case 'D' -> Mercante.getInstance(x, y);
            case 'C' -> new Ostacolo("cespuglio", 1, x, y);
            case 'R' -> new Ostacolo("roccia", 2, x, y);
            case 'A' -> new Ostacolo("acqua", 3, x, y);
            case 'W' -> new Ostacolo("Muro", 4, x, y);
            case 'E' -> new Ostacolo("Tall grass", 5, x, y);
            case 'T' -> new Ostacolo("Albero", 6, x, y);
            case 'V' -> new Ostacolo("Vulcanic_Rock", 7, x, y);
            case 'L' -> new Ostacolo("Lava", 8, x, y);
            case 'B' -> new Ostacolo("Tavolo", 9, x, y);
            case 'Y' -> new Ostacolo("Roccia_ALt", 10, x, y);
            default -> null;
        };
    }
    public static int getId(char valore) {
        return switch (valore) {
            case 'C' -> 1;
            case 'R' -> 2;
            case 'A' -> 3;
            case 'W' -> 4;
            case 'E' -> 5;
            case 'T' -> 6;
            case 'V' -> 7;
            case 'L' -> 8;
            case 'B' -> 9;
            case 'Y' -> 10;
            case '.' -> -1;
            default -> 0;
        };
    }
}
