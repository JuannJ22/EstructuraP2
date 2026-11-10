package arboles;

public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int peso; 

    public boolean estaVacio() {    
        return raiz == null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public int obtenerPeso() {
        return peso;
    }

    public boolean agregarDato(int dato) {
        if (existeDato(dato)) {
            return false; 
        }
        raiz = agregarDato(raiz, dato);
        peso++;
        return true;
    }

    private Nodo agregarDato(Nodo nodo, int dato) {
        if (nodo == null) {
            return new Nodo(dato); 
        }
        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(agregarDato(nodo.getIzquierdo(), dato));
        } else {
            nodo.setDerecho(agregarDato(nodo.getDerecho(), dato));
        }
        return nodo;
    }

    public boolean existeDato(int dato) {
        Nodo actual = raiz;
        while (actual != null) {
            if (dato == actual.getDato()) {
                return true;
            }
            actual = dato < actual.getDato() ? actual.getIzquierdo() : actual.getDerecho();
        }
        return false;
    }

    public int contarEquiposEnRango(Nodo nodo, int inferior, int superior) {
        if (nodo == null || inferior > superior) {
            return 0;
        }
        if (nodo.getDato() < inferior) {
            return contarEquiposEnRango(nodo.getDerecho(), inferior, superior);
        }
        if (nodo.getDato() > superior) {
            return contarEquiposEnRango(nodo.getIzquierdo(), inferior, superior);
        }
        return 1 + contarEquiposEnRango(nodo.getIzquierdo(), inferior, superior)
            + contarEquiposEnRango(nodo.getDerecho(), inferior, superior);
    }


}
