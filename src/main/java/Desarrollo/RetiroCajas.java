package Desarrollo;

import estructuras.Pila;
import model.Caja;


public class RetiroCajas {
    private RetiroCajas() {
    }
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila == null || pila.estaVacia()) {
            return false;
        }
      
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
    
        volcar(auxiliar, pila);
        if (retirada == null) {
            return false; 
        }

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
       
        if (retirada.getValor() % 2 == 0) {
            volcar(pares, pila); 
            volcar(impares, pila);
        } else {
            volcar(impares, pila);
            volcar(pares, pila);
        }
        return true;
    }

    private static void volcar(Pila<Caja> origen, Pila<Caja> destino) {
        while (!origen.estaVacia()) {
            destino.apilar(origen.desapilar());
        }
    }
}
