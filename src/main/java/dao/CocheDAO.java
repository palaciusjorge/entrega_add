package dao;

import database.DatabaseConnection;
import model.Coche;

import java.sql.*;
import java.util.List;


public class CocheDAO {

    //INSERTAR COCHE


    public void insertarCoche (Coche coche) {
        String sql = "INSERT INTO coches (id_cliente, matricula, marca, modelo, anho) VALUES (?, ?, ?, ?, ?)";

        try (
        Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, coche.getIdCliente());
            stmt.setString(2, coche.getMatricula());
            stmt.setString(3, coche.getMarca());
            stmt.setString(4, coche.getModelo());
            stmt.setInt(5, coche.getAnho());

            stmt.executeUpdate();
            System.out.println("Coche insertado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar coche: " + e.getMessage(), e);
        }
    }

    //LISTAR COCHES POR ID DE CLIENTE

    public List<Coche> listarCochesPorCliente(int idCliente) {
        List<Coche> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM coches WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Coche coche = new Coche(
                        rs.getInt("id_coche"),
                        rs.getInt("id_cliente"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anho")
                );
                lista.add(coche);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar coches por cliente: " + e.getMessage(), e);
        }

        return lista;
    }

    // ELIMINAR COCHE POR ID

    public void eliminarCoche(int id) {
        String sql = "DELETE FROM coches WHERE id_coche = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Coche eliminado correctamente");
            } else {
                System.out.println("No se encontró el coche con ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar coche: " + e.getMessage(), e);
        }
    }

    //LISTAR TODOS LOS COCHES

    public List<Coche> listarTodosCoches() {
        List<Coche> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM coches";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Coche coche = new Coche(
                        rs.getInt("id_coche"),
                        rs.getInt("id_cliente"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anho")
                );
                lista.add(coche);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los coches: " + e.getMessage(), e);
        }

        return lista;
    }


}
