package dao;

import database.DatabaseConnection;
import model.Reparacion;

import java.sql.*;
import java.util.List;

public class ReparacionDAO {

    //INSERTAR REPARACION

    public void insertarReparacion (Reparacion reparacion) {
        String sql = "INSERT INTO reparacion (id_coche, id_mecanico) VALUES (?, ?)";

        try (
        Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reparacion.getIdCoche());
            stmt.setInt(2, reparacion.getIdMecanico());

            stmt.executeUpdate();
            System.out.println("Reparación insertada correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar reparación: " + e.getMessage(), e);
        }
    }

    //LISTAR TODAS LAS REPARACIONES

    public List<Reparacion> listarReparaciones() {
        List<Reparacion> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM reparacion";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reparacion reparacion = new Reparacion(
                        rs.getInt("id_reparacion"),
                        rs.getInt("id_coche"),
                        rs.getInt("id_mecanico")
                );
                lista.add(reparacion);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar reparaciones: " + e.getMessage(), e);
        }

        return lista;
    }

    //LISTAR REPARACIONES POR ID DE COCHE

    public List<Reparacion> listarReparacionesPorCoche(int idCoche) {
        List<Reparacion> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM reparacion WHERE id_coche = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCoche);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reparacion reparacion = new Reparacion(
                        rs.getInt("id_reparacion"),
                        rs.getInt("id_coche"),
                        rs.getInt("id_mecanico")
                );
                lista.add(reparacion);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar reparaciones por coche: " + e.getMessage(), e);
        }

        return lista;
    }

    // ACTUALIZAR REPARACION

    public void actualizarReparacion(Reparacion reparacion) {
        String sql = "UPDATE reparacion SET id_coche = ?, id_mecanico = ? WHERE id_reparacion = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reparacion.getIdCoche());
            stmt.setInt(2, reparacion.getIdMecanico());
            stmt.setInt(3, reparacion.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reparación actualizada correctamente");
            } else {
                System.out.println("No se encontró la reparación con ID: " + reparacion.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar reparación: " + e.getMessage(), e);
        }
    }

    // OBTENER REPARACION POR ID

    public Reparacion obtenerReparacionPorId(int id) {
        String sql = "SELECT * FROM reparacion WHERE id_reparacion = ?";
        Reparacion reparacion = null;

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reparacion = new Reparacion(
                        rs.getInt("id_reparacion"),
                        rs.getInt("id_coche"),
                        rs.getInt("id_mecanico")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener reparación: " + e.getMessage(), e);
        }

        return reparacion;
    }

    // ELIMINAR REPARACION POR ID

    public void eliminarReparacion(int id) {
        String sql = "DELETE FROM reparacion WHERE id_reparacion = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reparación eliminada correctamente");
            } else {
                System.out.println("No se encontró la reparación con ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar reparación: " + e.getMessage(), e);
        }
    }
}
