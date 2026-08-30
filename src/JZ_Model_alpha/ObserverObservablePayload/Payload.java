package JZ_Model_alpha.ObserverObservablePayload;

public class Payload {
    private PayloadTypes tipo;
    private Object o;
    public Payload(PayloadTypes tipo, Object o){
        this.tipo=tipo;
        this.o=o;
    }


    //getter
    public Object getO() {
        return o;
    }
    public PayloadTypes getTipo() {
        return tipo;
    }
}
