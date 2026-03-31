package model;

public class Coche {

    // Atributos

    private int id;
    private int idCliente;
    private String matricula;
    private String marca;
    private String modelo;
    private int anho;

    // Constructor vacío

    public Coche() {}

    // Constructor con parámetros

    public Coche(int id, int idCliente, String matricula, String marca, String modelo, int anho) {
        this.id = id;
        this.idCliente = idCliente;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anho = anho;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }
}

