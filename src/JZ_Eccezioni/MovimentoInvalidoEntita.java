package JZ_Eccezioni;

public class MovimentoInvalidoEntita extends RuntimeException {
    public MovimentoInvalidoEntita() {
        super("Un entità sta tentando un movimento impossibile");
    }
}
