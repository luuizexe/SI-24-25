package es.udc.sistemasinteligentes;


public class Nodo implements Comparable<Nodo> {
    private final Estado e;
    private final Nodo p;
    private final Accion a;

    //Costes de A*
    private float g;
    private float h;
    private float f;

    public static Nodo crearNodo(Estado e, Nodo p, Accion a, float g, float h) {
        return new Nodo(e, p, a, g, h);
    }

    public static Nodo crearNodo(Estado e, Nodo p, Accion a) {
        return new Nodo(e, p, a, 0, 0);
    }


    private Nodo(Estado e, Nodo p, Accion a, float g, float h) {
        this.e = e;
        this.p = p;
        this.a = a;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    public Estado getE() {
        return e;
    }

    public Nodo getP() {
        return p;
    }

    public float getH() {
        return h;
    }

    public float getG() {
        return g;
    }

    public float getF() {
        return g + h;
    }

    @Override
    public String toString() {
        return "Nodo " + e +
                " {a=" + a +
                " | p=" + (p != null ? p.e : "null") +
                "}\n";
    }

    @Override
    public int compareTo(Nodo otro) {
        return Float.compare(this.f, otro.f);
    }


}