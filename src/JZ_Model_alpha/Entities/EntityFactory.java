package JZ_Model_alpha.Entities;

import JZ_Model_alpha.Entities.Enemies.Husk;
import JZ_Model_alpha.Entities.Enemies.Mago;
import JZ_Model_alpha.Entities.OtherEntities.Mercante;
import JZ_Model_alpha.Entities.OtherEntities.Ostacolo;

public class EntityFactory {
    public static Entity createEntity(char valore, int id, int x, int y) {
        switch (valore) {
            case 'H': return new Husk(id, x, y);
            case 'M': return new Mago("mago", id, x, y);
            case 'D': return Mercante.getInstance(x,y);
            case 'C': return new Ostacolo("cespuglio", 1, x, y);
            case 'R': return new Ostacolo("roccia", 2, x, y);
            case 'A': return new Ostacolo("acqua", 3, x, y);
            case 'W': return new Ostacolo("Muro", 4, x, y);
            case 'E': return new Ostacolo("Tall grass", 5, x, y);
            case 'T': return new Ostacolo("Albero", 6, x, y);
            case 'V': return new Ostacolo("Vulcanic_Rock", 7, x, y);
            case 'L': return new Ostacolo("Lava", 8, x, y);
            case 'B': return new Ostacolo("Tavolo", 9, x, y);
            case 'Y': return new Ostacolo("Roccia_ALt", 10, x, y);
            default: return null;
        }
    }
    public static int getId(char valore) {
        switch (valore) {
            case 'C': return 1;
            case 'R': return 2;
            case 'A': return 3;
            case 'W': return 4;
            case 'E': return 5;
            case 'T': return 6;
            case 'V': return 7;
            case 'L': return 8;
            case 'B': return 9;
            case 'Y': return 10;
            case '.': return -1;
            default: return 0;
        }
    }
}
