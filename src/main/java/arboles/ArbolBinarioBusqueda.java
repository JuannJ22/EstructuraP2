package arboles;

import estructuras.Cola;

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

    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inorden(raiz, sb);
        return sb.toString().trim();
    }

    private void inorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            inorden(nodo.getIzquierdo(), sb);
            sb.append(nodo.getDato()).append(' ');
            inorden(nodo.getDerecho(), sb);
        }
    }
        
    public String preorden() {
        StringBuilder sb = new StringBuilder();
        preorden(raiz, sb);
        return sb.toString().trim();
    }
    
    private void preorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.getDato()).append(' ');
            preorden(nodo.getIzquierdo(), sb);
            preorden(nodo.getDerecho(), sb);
        }
    }

    public String postorden() {
        StringBuilder sb = new StringBuilder();
        postorden(raiz, sb);
        return sb.toString().trim();
    }

    private void postorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            postorden(nodo.getIzquierdo(), sb);
            postorden(nodo.getDerecho(), sb);
            sb.append(nodo.getDato()).append(' ');
        }
    }

     
    public String imprimirAmplitud() {
        StringBuilder sb = new StringBuilder();
        if (raiz == null) {
            return "";
        }
        Cola<Nodo> cola = new Cola<>();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            sb.append(actual.getDato()).append(' ');
            if (actual.getIzquierdo() != null) {
                cola.encolar(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.encolar(actual.getDerecho());
            }
        }
        return sb.toString().trim();
    }

    public boolean eliminarDato(int dato) {
        if (!existeDato(dato)) {
            return false;
        }
        raiz = eliminarDato(raiz, dato);
        peso--;
        return true;
    }

    private Nodo eliminarDato(Nodo nodo, int dato) {
        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(eliminarDato(nodo.getIzquierdo(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setDerecho(eliminarDato(nodo.getDerecho(), dato));
        } else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo(); 
            }
            Nodo menor = obtenerNodoMenor(nodo.getDerecho()); 
            nodo.setDato(menor.getDato());
            nodo.setDerecho(eliminarDato(nodo.getDerecho(), menor.getDato()));
        }
        return nodo;
    }
    
    private Nodo obtenerNodoMenor(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public void imprimirArbol() {
        imprimirArbol(raiz, 0);
    }

    private void imprimirArbol(Nodo nodo, int nivel) {
        if (nodo != null) {
            imprimirArbol(nodo.getDerecho(), nivel + 1);
            System.out.println(" ".repeat(nivel) + nodo.getDato());
            imprimirArbol(nodo.getIzquierdo(), nivel + 1);
        }
    }
}
