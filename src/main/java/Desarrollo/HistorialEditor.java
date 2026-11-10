package Desarrollo;

import estructuras.Pila;

public class HistorialEditor {

    private HistorialEditor() {
    }

    public static <T> Pila<T> obtenerSegmento(Pila<T> original, int inicio, int fin) {
        Pila<T> resultado = new Pila<>();
        if (original == null || inicio < 1 || inicio > fin || fin > original.tamanio()) {
            return resultado; 
        }
        Pila<T> auxiliar = new Pila<>(); 
        Pila<T> segmentoInvertido = new Pila<>(); int posicion = 1;
        while (posicion <= fin) {
            T accion = original.desapilar();
            auxiliar.apilar(accion);
            if (posicion >= inicio) {
                segmentoInvertido.apilar(accion);
            }
            posicion++;
        }
            
        while (!auxiliar.estaVacia()) {
            original.apilar(auxiliar.desapilar());
        }
      
        while (!segmentoInvertido.estaVacia()) {
            resultado.apilar(segmentoInvertido.desapilar());
        }
        return resultado;
    }
}
               
