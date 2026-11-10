package arboles;
import estructuras.Cola;

public class ArbolAVL {

    private static class NodoAVL {

        String dato;
        NodoAVL izquierdo;
        NodoAVL derecho;
        int altura = 1;

        NodoAVL(String dato) {
            this.dato = dato;
        }
    }
    
    private NodoAVL raiz;

    public void insertar(String dato) {
        raiz = insertar(raiz, dato);
    }
    
    private NodoAVL insertar(NodoAVL nodo, String dato) {
        if (nodo == null) {
            return new NodoAVL(dato); 
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = insertar(nodo.derecho, dato);
        } else {
            return nodo; 
        }
        return balancear(nodo); 
    }

    public void eliminar(String dato) {
        raiz = eliminar(raiz, dato);
    }
        
    private NodoAVL eliminar(NodoAVL nodo, String dato) {
        if (nodo == null) {
            return null;
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = eliminar(nodo.derecho, dato);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            NodoAVL mayor = nodo.izquierdo;
            while (mayor.derecho != null) {
                mayor = mayor.derecho;
            }
            System.out.println(" Se reemplaza " + nodo.dato + " por " + mayor.dato
                                + " (mayor del subárbol izquierdo)");
            nodo.dato = mayor.dato;
            nodo.izquierdo = eliminar(nodo.izquierdo, mayor.dato);
        }
        return balancear(nodo);
    }

    private NodoAVL balancear (NodoAVL nodo) {
        actualizarAltura (nodo);
        int fe = fe(nodo);

        if (fe == -2) { 
            if (fe(nodo.izquierdo) <= 0) {
                System.out.println(" FE(" + nodo.dato + ") = -2 > rotación simple a la derecha");
                return rotarDerecha (nodo);
            }
            System. out.println(" FE(" + nodo.dato +") = -2 y FE(" + nodo.izquierdo.dato + ") = +1 > rotación doble a la izquierda");
            nodo.izquierdo = rotarIzquierda (nodo.izquierdo);
            return rotarDerecha (nodo) ;
        }
        if (fe == 2) {
            if (fe(nodo.derecho) >= 0) {
                System. out.println(" FE(" + nodo.dato + ") = +2 > rotación simple a la izquierda");
            return rotarIzquierda (nodo);
            }
            System. out. println(" FE(" + nodo.dato + ") = +2 y FE(" + nodo.derecho.dato + ") = -1 -> rotación doble a la derecha")
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }
    private NodoAVL rotarDerecha(NodoAVL a) {
        NodoAVL b = a.izquierdo; 
        a.izquierdo = b.derecho;
        b.derecho = a;
        actualizarAltura(a);
        actualizarAltura(b);
        return b;
    }

    private NodoAVL rotarIzquierda(NodoAVL a) {
        NodoAVL b = a.derecho; 
        a.derecho = b.izquierdo;
        b.izquierdo = a;
        actualizarAltura(a);
        actualizarAltura(b);
        return b;
    }

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private void actualizarAltura(NodoAVL nodo) {
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    private int fe(NodoAVL nodo) {
        return altura(nodo.derecho) - altura(nodo.izquierdo);
    }   

    public Cola<String> postorden() {
        Cola<String> cola = new Cola<>();
        postorden(raiz, cola);
        return cola;
    }

    private void postorden(NodoAVL nodo, Cola<String> cola) {
        if (nodo != null) {
            postorden(nodo.izquierdo, cola);
            postorden(nodo.derecho, cola);
            cola.encolar(nodo.dato);
        }
    }
    
    public void imprimirArbol() {
        imprimirArbol(raiz, 0);
    }
        
    private void imprimirArbol(NodoAVL nodo, int nivel) {
        if (nodo != null) {
            imprimirArbol(nodo.derecho, nivel + 1);
            int fe = fe(nodo);
            System.out.println("  ".repeat(nivel)+ nodo.dato + "(" + (fe > 0? "+" :"") + fe + ")");
            imprimirArbol(nodo.izquierdo, nivel + 1);
        }
    }
          
}
    
