package Desarrollo;
import estructuras.Cola;
import model.Paquete;
import model.TipoEntrega;


/** Punto 3: seleccionar paquetes para la jornada, solo con recursividad. */
public class Mensajeria {
    private Mensajeria() {
    }
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (pendientes == null || capacidad <= 0) {
            return seleccionados; // cola vacía y pendientes sin cambios
        }
        // Primer recorrido: urgentes. Segundo recorrido: normales.
        int restante = recorrer(pendientes, seleccionados, TipoEntrega.URGENTE, capacidad,
                pendientes.tamanio());
        restante = recorrer(pendientes, seleccionados, TipoEntrega.NORMAL, restante,
                pendientes.tamanio());
                System.out.println("Capacidad que quedó disponible: " + restante + " kg");
        return seleccionados;
    }
    /**
     * Revisa "faltan" paquetes de la cola. Cada paquete que no se selecciona se
     * vuelve a encolar al final, así los pendientes conservan su orden de registro.
     * Retorna la capacidad que sobra.
     */
    private static int recorrer(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
                                TipoEntrega tipo, int capacidad, int faltan) {
        if (faltan == 0) {
            return capacidad; // caso base: ya se dio la vuelta completa
        }
        Paquete actual = pendientes.desencolar();
        if (actual.getTipo() == tipo && actual.getPeso() <= capacidad) {
            seleccionados.encolar(actual);
            capacidad -= actual.getPeso();
            System.out.println(" " + actual + " -> SELECCIONADO (quedan " + capacidad + " kg)");
        } else {
            if (actual.getTipo() == tipo) {
                System.out.println(" " + actual + " -> PENDIENTE (no cabe, quedan " + capacidad
                        + " kg)");
            }
            pendientes.encolar(actual);
        }
        return recorrer(pendientes, seleccionados, tipo, capacidad, faltan - 1);
    }
}