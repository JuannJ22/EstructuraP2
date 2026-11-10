package arboles;

public class Nodo {
    private int dato;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }
    
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

}
