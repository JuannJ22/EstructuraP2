package Desarrollo;

import estructuras.Pila;
import model.Caja;

/** Punto 1: retirar una caja de la pila y reorganizar las restantes. */
public class RetiroCajas {
    private RetiroCajas() {
    }
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila == null || pila.estaVacia()) {
            return false;
        }
        // 1. Buscar la caja. Las que están encima se guardan en una pila auxiliar.
        Pila<Caja> auxiliar = new Pila<>();
        Caja retirada = null;
        while (!pila.estaVacia() && retirada == null) {
            Caja actual = pila.desapilar();
            if (actual.getCodigo().equals(codigo)) {
                retirada = actual;
            } else {
                        auxiliar.apilar(actual);
            }
        }
        // 2. Devolver las cajas de encima a su lugar (quedan en el mismo orden).
        volcar(auxiliar, pila);
        if (retirada == null) {
            return false; // no existía: la pila quedó igual que al inicio
        }
        // 3. Separar por posición contando desde la cima (posición 1).
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;
        while (!pila.estaVacia()) {
            if (posicion % 2 != 0) {
                impares.apilar(pila.desapilar());
            } else {
                pares.apilar(pila.desapilar());
            }
            posicion++;
        }
        // 4. Reconstruir. El grupo que debe quedar arriba se apila de último.
        if (retirada.getValor() % 2 == 0) {
            volcar(pares, pila); // valor par: impares arriba, pares abajo
            volcar(impares, pila);
        } else {
            volcar(impares, pila); // valor impar: pares arriba, impares abajo
            volcar(pares, pila);
        }
        return true;
    }
    /** Pasa todos los elementos de una pila a otra (se invierte el orden). */
    private static void volcar(Pila<Caja> origen, Pila<Caja> destino) {
        while (!origen.estaVacia()) {
            destino.apilar(origen.desapilar());
        }
    }
}
