import Desarrollo.ExpresionPosfija;
import Desarrollo.HistorialEditor;
import Desarrollo.Mensajeria;
import Desarrollo.RetiroCajas;
import arboles.ArbolBinarioBusqueda;
import estructuras.Cola;
import estructuras.Pila;
import model.Caja;
import model.Paquete;
import model.TipoEntrega;
import arboles.ArbolAVL;
import arboles.ArbolRojoNegro;

public class Main {

    public static void main(String[] args) {
        punto1();
        punto2();
        punto3();
        punto4();
        punto5();
    }

    private static void titulo(String texto) {
        System.out.println("\n==================== " + texto + " ====================");
    }

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

    private static void punto2() {
        titulo ("PUNTO 2 - Segmento del historial");
        Pila<String> historial = new Pila<>();
        for (int i = 1; i <= 6; i++) {
            historial. apilar("A" + i);
            System.out.println("Original: " + historial);
            System.out.println("inicio=2, fin=4 -> "+ HistorialEditor.obtenerSegmento(historial, 2,4));
            System.out.println("inicio=3, fin=3 -> "+ HistorialEditor.obtenerSegmento(historial, 3,3));
            System.out.println("inicio=1, fin=6 -> " + HistorialEditor.obtenerSegmento(historial, 1,6));
            System.out.println("inicio=0, fin=3 -> "+ HistorialEditor.obtenerSegmento(historial, 0,3));
            System.out.println("inicio=4, fin=2 -> " + HistorialEditor.obtenerSegmento(historial, 4,2));
            System.out.println("inicio=2, fin=7 -> " + HistorialEditor.obtenerSegmento(historial, 2, 7 ));
            System.out.println("Original después de las llamadas: " + historial);
        }
    }

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

    private static void punto4() {
        titulo("PUNTO 4 - Árbol binario de búsqueda");
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] codigos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44};
        for (int codigo : codigos) {
            if (!arbol.agregarDato(codigo)) {
            System.out.println("El " + codigo + " ya existe: no se inserta (códigos únicos)");
            }
        }
        arbol.imprimirArbol();
        System.out.println("Peso: " + arbol.obtenerPeso());
        System.out.println("Inorden: " + arbol.inorden());
        System.out.println("Preorden: " + arbol.preorden());
        System.out.println("Postorden: " + arbol.postorden());
        System.out.println("Amplitud: " + arbol.imprimirAmplitud());
        System.out.println("\n4a. contarEquiposEnRango");
        System.out.println(" [15, 45] -> " + arbol.contarEquiposEnRango(arbol.getRaiz(), 15,45));
        System.out.println(" [40, 55] -> " + arbol.contarEquiposEnRango(arbol.getRaiz(), 40,55));
        System.out.println(" [1, 100] -> " + arbol.contarEquiposEnRango(arbol.getRaiz(), 1,100));
        System.out.println(" [30, 20] -> " + arbol.contarEquiposEnRango(arbol.getRaiz(), 30,20));
        System.out.println(" árbol vacío -> " + new ArbolBinarioBusqueda().contarEquiposEnRango(null, 1, 10));
        System.out.println("\n4b. Eliminar 41 (menor del subárbol derecho)");
        arbol.eliminarDato(41);
        arbol.imprimirArbol();
        System.out.println("Inorden después: " + arbol.inorden());
        System.out.println("Preorden después: " + arbol.preorden());
    }  
    
    private static void punto5() {
        titulo("PUNTO 5 - AVL y Rojo-Negro");
        String[] palabras = {"alma", "beso", "azul", "cine", "eco", "dedo", "bota", "hilo", "lago", "isla", "nube", "pato", "oro", "mapa", "gato"};

        ArbolAVL avl = new ArbolAVL();
        for (String palabra : palabras) {
            System.out.println("Insertar " + palabra);
            avl.insertar(palabra);
        }

        System.out.println("\nAVL final (acostado, FE entre paréntesis):");
        avl.imprimirArbol();
        System.out.println("Postorden AVL final: " + avl.postorden());
        System.out.println("\n5b. Eliminar cine");
        avl.eliminar("cine");
        avl.imprimirArbol();
        Cola<String> postorden = avl.postorden();
        System.out.println("\n5c. Postorden después de eliminar cine: " + postorden);
        ArbolRojoNegro rojoNegro = new ArbolRojoNegro();

        while (!postorden.estaVacia()) {
            String palabra = postorden.desencolar();
            System.out.println("Insertar " + palabra);
            rojoNegro.insertar(palabra);
        }   
        System.out.println("\nRojo-Negro final (acostado):");
        rojoNegro.imprimirArbol();  
    }


    
}   