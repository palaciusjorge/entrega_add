package model;

public class PiezaReparacion {

    // Atributos

    private int idPieza;
    private int idReparacion;
    private int cantidad;

    // Constructor vacío

    public PiezaReparacion(int idPiezaReparacion, int idReparacion, int idPieza, int cantidad) {}

    // Constructor con parámetros

    public PiezaReparacion(int idPieza, int idReparacion, int cantidad) {
        this.idPieza = idPieza;
        this.idReparacion = idReparacion;
        this.cantidad = cantidad;
    }

    // Getters y setters


    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public int getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PiezaReparacion{" +
                "idPieza=" + idPieza +
                ", idReparacion=" + idReparacion +
                ", cantidad=" + cantidad +
                '}';
    }

}
