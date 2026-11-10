import Desarrollo.ExpresionPosfija;
import Desarrollo.Mensajeria;
import Desarrollo.RetiroCajas;
import estructuras.Cola;
import estructuras.Pila;
import model.Caja;
import model.Paquete;
import model.TipoEntrega;

public class Main {

    public static void main(String[] args) {
        punto1();
        punto3();
    }

    private static void titulo(String texto) {
        System.out.println("\n==================== " + texto + " ====================");
    }

    // -------------------------------------------------------------PUNTO 1
    private static void punto1() {
        titulo("PUNTO 1 - Retiro de cajas");
        Pila<Caja> cajas = new Pila<>();
        cajas.apilar(new Caja("C1", 2));
        cajas.apilar(new Caja("C2", 4));
        cajas.apilar(new Caja("C3", 6));
        cajas.apilar(new Caja("C4", 8));
        cajas.apilar(new Caja("C5", 3));
        cajas.apilar(new Caja("C6", 9));
        cajas.apilar(new Caja("C7", 5));
        System.out.println("Pila inicial (tope -> fondo): " + cajas);

        for (String codigo : new String[]{"C3", "C4", "C7"}) {
            boolean retorno = RetiroCajas.retirarCaja(cajas, codigo);
            System.out.println("retirarCaja(" + codigo + ") = " + retorno + " -> " + cajas);
        }
        System.out.println("Prueba código inexistente C9 = "
                + RetiroCajas.retirarCaja(cajas, "C9") + " -> " + cajas);
        System.out.println("Pila final (tope -> fondo): " + cajas);

        // Evaluación de la expresión con las cuatro cajas finales (de la cima al fondo)
        Caja primera = cajas.desapilar();
        Caja segunda = cajas.desapilar();
        Caja tercera = cajas.desapilar();
        Caja cuarta = cajas.desapilar();
        double a = primera.getValor();
        double b = segunda.getValor() / a;
        double c = tercera.getValor() + a;
        double d = (cuarta.getValor() + c) * a;
        System.out.println("\nA = " + ExpresionPosfija.formatear(a) + ", B = "
                + ExpresionPosfija.formatear(b)
                + ", C = " + ExpresionPosfija.formatear(c) + ", D = "
                + ExpresionPosfija.formatear(d));

        String infija = "( A + B ) * C - ( A - B ) * D";
        String posfija = ExpresionPosfija.aPosfija(infija);
        String conValores = ExpresionPosfija.reemplazar(posfija, a, b, c, d);
        System.out.println("Infija: " + infija);
        System.out.println("Posfija: " + posfija);
        System.out.println("Con valores: " + conValores + "\n");
        double resultado = ExpresionPosfija.evaluar(conValores);
        System.out.println("Resultado final = " + ExpresionPosfija.formatear(resultado));
    }

    // ------------------------------------------------------------PUNTO 3
    private static void punto3() {
        titulo("PUNTO 3 - Selección de paquetes");
        Cola<Paquete> pendientes = new Cola<>();
        pendientes.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        pendientes.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        pendientes.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        pendientes.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        pendientes.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        pendientes.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        pendientes.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        System.out.println("Cola inicial (frente -> final): " + pendientes);

        System.out.println("Paquetes evaluados (capacidad 14 kg):");
        Cola<Paquete> seleccionados = Mensajeria.seleccionarPaquetes(pendientes, 14);
        System.out.println("Cola retornada:   " + seleccionados);
        System.out.println("Cola pendientes: " + pendientes);

        System.out.println("Prueba capacidad 0 -> " + Mensajeria.seleccionarPaquetes(pendientes,
                0)
                + ", pendientes: " + pendientes);
    }
}