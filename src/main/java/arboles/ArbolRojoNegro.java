package arboles;

public class ArbolRojoNegro {
    private static final boolean ROJO = true;
    private static final boolean NEGRO = false;

    private static class NodoRN {
        String dato;
        boolean color = ROJO; NodoRN izquierdo;
        NodoRN derecho;
        NodoRN padre;

        NodoRN(String dato) {
            this.dato = dato;
        }
    }

    private NodoRN raiz;

    public void insertar(String dato) {

        NodoRN padre = null;
        NodoRN actual = raiz;

        while (actual != null) {
            padre = actual;
            int comparacion = dato.compareTo(actual.dato);
            if (comparacion == 0) {
                return; 
            }
            actual = comparacion < 0 ? actual.izquierdo : actual.derecho;
        }

        NodoRN x = new NodoRN(dato);
        x.padre = padre;
        if (padre == null) {
            raiz = x;
        } else if (dato.compareTo(padre.dato) < 0) {
            padre.izquierdo = x;
        } else {
            padre.derecho = x;
        }

        reajustar(x)
    }

    private void reajustar(NodoRN x) {
        while (x != raiz && x.padre.color == ROJO) { 
            NodoRN padre = x.padre;
            NodoRN abuelo = padre.padre;
            boolean padreEsIzquierdo = (padre == abuelo.izquierdo);
            NodoRN tio = padreEsIzquierdo ? abuelo.derecho : abuelo.izquierdo;
            if (tio != null && tio.color == ROJO) {
                System.out.println(" " + x.dato + ": subcaso 1 (tío " + tio.dato + " rojo) -> recoloreo");
                padre.color = NEGRO;
                tio.color = NEGRO;
                abuelo.color = ROJO;
                x = abuelo;
            } else {
                boolean xEsIzquierdo = (x == padre.izquierdo);
                if (padreEsIzquierdo != xEsIzquierdo) {
                    System.out.println(" " + x.dato + ": subcaso 3 -> rotación en " + padre.dato + " para llevarlo al subcaso 2");
                    if (padreEsIzquierdo) {
                        rotarIzquierda(padre);
                    } else {
                        rotarDerecha(padre);
                    }
                    x = padre;
                    padre = x.padre;
                }
                System.out.println(" " + x.dato + ": subcaso 2 -> rotación simple en "+ abuelo.dato + ", " + padre.dato + " negro y " + abuelo.dato + " rojo");
                padre.color = NEGRO;
                abuelo.color = ROJO;
                if (padreEsIzquierdo) {
                    rotarDerecha(abuelo);
                } else {
                    rotarIzquierda(abuelo);
                }
            }
        }
        if (raiz.color == ROJO) {
            System.out.println(" Caso 2: la raíz " + raiz.dato + " quedó roja -> se pinta de negro");
        }

        raiz.color = NEGRO;
    }

    private void rotarIzquierda(NodoRN a) {
        NodoRN b = a.derecho;
        a.derecho = b.izquierdo;
        if (b.izquierdo != null) {
            b.izquierdo.padre = a;
        }
        cambiarHijo(a, b);
        b.izquierdo = a;
        a.padre = b;
    }
           
    private void rotarDerecha(NodoRN a) {
        NodoRN b = a.izquierdo;
        a.izquierdo = b.derecho;
        if (b.derecho != null) {
            b.derecho.padre = a;
        }
        cambiarHijo(a, b);
        b.derecho = a;
        a.padre = b;
    }
           
    private void cambiarHijo(NodoRN a, NodoRN b) {
        b.padre = a.padre;
        if (a.padre == null) {
            raiz = b;
        } else if (a == a.padre.izquierdo) {
            a.padre.izquierdo = b;
        } else {
            a.padre.derecho = b;
        }
    }

    public void imprimirArbol() {
        imprimirArbol(raiz, 0);
    }

    private void imprimirArbol(NodoRN nodo, int nivel) {
        if (nodo != null) {
            imprimirArbol(nodo.derecho, nivel + 1);
            System.out.println("     ".repeat (nivel)+ nodo.dato + (nodo.color == ROJO ?"(R)" : "(N")); 
            imprimirArbol(nodo.izquierdo, nivel + 1);
        }
    }
    

}
