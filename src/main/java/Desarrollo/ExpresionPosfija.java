package Desarrollo;
import estructuras.Pila;

/** Punto 1 (segunda parte): pasar de infija a posfija y evaluar con una pila. */
public class ExpresionPosfija {
    private ExpresionPosfija() {
    }
    /** Convierte una expresión infija a posfija. Los símbolos van separados por espacios. */
    public static String aPosfija(String infija) {
        Pila<String> operadores = new Pila<>();
        StringBuilder salida = new StringBuilder();
        for (String simbolo : infija.trim().split("\\s+")) {
            if (simbolo.equals("(")) {
                operadores.apilar(simbolo);
            } else if (simbolo.equals(")")) {
                while (!operadores.verTope().equals("(")) {
                    salida.append(operadores.desapilar()).append(' ');
                }
                operadores.desapilar(); // se descarta el "("
            } else if (esOperador(simbolo)) {
                while (!operadores.estaVacia() && esOperador(operadores.verTope())
                        && prioridad(operadores.verTope()) >= prioridad(simbolo)) {
                    salida.append(operadores.desapilar()).append(' ');
                }
                operadores.apilar(simbolo);
            } else {
                salida.append(simbolo).append(' '); // operando: va directo a la salida
            }
        }
        while (!operadores.estaVacia()) {
            salida.append(operadores.desapilar()).append(' ');
        }
        return salida.toString().trim();
    }
    /** Cambia cada variable (A, B, C, D) de la expresión posfija por su valor. */
    public static String reemplazar(String posfija, double a, double b, double c, double d) {
        StringBuilder sb = new StringBuilder();
        for (String simbolo : posfija.split("\\s+")) {
            switch (simbolo) {
                case "A": sb.append(formatear(a)); break;
                case "B": sb.append(formatear(b)); break;
                case "C": sb.append(formatear(c)); break;
                case "D": sb.append(formatear(d)); break;
                default: sb.append(simbolo);
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }
    /** Evalúa una expresión posfija con una pila e imprime la tabla paso a paso. */
    public static double evaluar(String posfija) {
        Pila<Double> pila = new Pila<>();
        System.out.printf("%-4s %-8s %-28s %s%n", "Paso", "Elemento", "Operación",
                "Pila (tope -> fondo)");
        int paso = 1;
        for (String simbolo : posfija.split("\\s+")) {
            String operacion;
            if (esOperador(simbolo)) {
                double derecho = pila.desapilar(); // el primero que sale es el operando
                // derecho
                double izquierdo = pila.desapilar();
                double resultado = operar(izquierdo, derecho, simbolo);
                pila.apilar(resultado);
                operacion = formatear(izquierdo) + " " + simbolo + " " + formatear(derecho)
                        + " = " + formatear(resultado);
            } else {
                pila.apilar(Double.parseDouble(simbolo));
                operacion = "apilar " + simbolo;
            }
            System.out.printf("%-4d %-8s %-28s %s%n", paso++, simbolo, operacion, pila);
        }
        return pila.desapilar();
    }
    private static boolean esOperador(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    private static int prioridad(String operador) {
        return (operador.equals("*") || operador.equals("/")) ? 2 : 1;
    }
    private static double operar(double a, double b, String operador) {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            default: return a / b;
        }
    }
    /** 6.0 se muestra como 6 y 4.5 como 4.5 */
    public static String formatear(double numero) {
        return numero == Math.floor(numero) ? String.valueOf((long) numero)
                : String.valueOf(numero);
    }
}
