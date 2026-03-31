package model;

public class Factura {

    // Atributos

    private int id;
    private int idReparacion;
    private double total;

    // Constructor vacío

    public Factura() {}

    // Constructor con parámetros

    public Factura(int id, int idReparacion, double total) {
        this.id = id;
        this.idReparacion = idReparacion;
        this.total = total;
    }

    // Getters y setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
