package model;

public class Reparacion {
    // Atributos
    private int id;
    private int idCoche;
    private int idMecanico;

    // Constructor vacío

    public Reparacion() {}

    // Constructor con parámetros

    public Reparacion(int id, int idCoche, int idMecanico) {
        this.id = id;
        this.idCoche = idCoche;
        this.idMecanico = idMecanico;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
}

