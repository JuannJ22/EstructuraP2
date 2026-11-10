package model;

public class Paquete {
    private final String codigo;
    private final TipoEntrega tipo;
    private final int peso; // kg
    public Paquete(String codigo, TipoEntrega tipo, int peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
    }
    public String getCodigo() {
        return codigo;
    }
    public TipoEntrega getTipo() {
        return tipo;
    }
    public int getPeso() {
        return peso;
    }
    @Override
    public String toString() {
        return codigo + "(" + tipo + "," + peso + "kg)";
    }
}