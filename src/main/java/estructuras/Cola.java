package estructuras;

 
public class Cola<T> {
    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> inicio; 
    private Nodo<T> fin;
    private int tamanio;

    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            inicio = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamanio++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        T valor = inicio.valor;
        inicio = inicio.siguiente;
        if (inicio == null) {
            fin = null;
        }
        tamanio--;
        return valor;
    }

    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        return inicio.valor;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int tamanio() {
        return tamanio;
    }

    
    @Override
    public String toString() {
        return "[" + recorrer(inicio) + "]";
    }

    private String recorrer(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        }
        if (nodo.siguiente == null) {
            return String.valueOf(nodo.valor);
        }
        return nodo.valor + " -> " + recorrer(nodo.siguiente);
    }
}