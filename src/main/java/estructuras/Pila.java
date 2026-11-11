package estructuras;

public class Pila<T> {
    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;
        Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> tope;
    private int tamanio;
    public void apilar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T valor = tope.valor;
        tope = tope.siguiente;
        tamanio--;
        return valor;
    }

    public T verTope() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return tope.valor;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamanio() {
        return tamanio;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = tope;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }
        return sb.append("]").toString();
    }
}