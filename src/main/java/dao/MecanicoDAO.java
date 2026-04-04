package dao;

import database.DatabaseConnection;
import model.Mecanico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDAO {

    //INSERTAR MECANICO

    public void insertarMecanico(Mecanico mecanico) {
        String sql = "INSERT INTO mecanicos (nombre, especialidad, telefono) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mecanico.getNombre());
            stmt.setString(2, mecanico.getEspecialidad());
            stmt.setString(3, mecanico.getTelefono());

            stmt.executeUpdate();
            System.out.println("Mecánico insertado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar mecánico: " + e.getMessage(), e);
        }
    }

    // LISTAR MECANICOS POR ESPECIALIDAD

    public List<Mecanico> listarMecanicosPorEspecialidad(String especialidad) {
        List<Mecanico> lista = new ArrayList<>();
        String sql = "SELECT * FROM mecanicos WHERE especialidad = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especialidad);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mecanico m = new Mecanico(
                        rs.getInt("id_mecanico"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar mecánicos por especialidad", e);
        }

        return lista;
    }

    // OBTENER MECANICO POR ID

    public Mecanico obtenerMecanicoPorId(int id) {
        String sql = "SELECT * FROM mecanicos WHERE id_mecanico = ?";
        Mecanico mecanico = null;

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mecanico = new Mecanico(
                        rs.getInt("id_mecanico"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener mecánico", e);
        }

        return mecanico;
    }

    // LISTAR TODOS LOS MECANICOS

    public List<Mecanico> listarMecanicos() {
        List<Mecanico> lista = new ArrayList<>();
        String sql = "SELECT * FROM mecanicos";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mecanico m = new Mecanico(
                        rs.getInt("id_mecanico"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar mecánicos", e);
        }

        return lista;
    }
    // ELIMINAR MECANICO POR ID

    public void eliminarMecanico(int idMecanico) {
        String sql = "DELETE FROM mecanicos WHERE id_mecanico = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMecanico);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mecánico eliminado correctamente");
            } else {
                System.out.println("No se encontró el mecánico con ID: " + idMecanico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar mecánico: " + e.getMessage(), e);
        }
    }

    // ACTUALIZAR MECANICO POR ID

    public void actualizarMecanico(int idMecanico, String nuevoNombre, String nuevaEspecialidad, String nuevoTelefono) {
        String sql = "UPDATE mecanicos SET nombre = ?, especialidad = ?, telefono = ? WHERE id_mecanico = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaEspecialidad);
            stmt.setString(3, nuevoTelefono);
            stmt.setInt(4, idMecanico);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mecánico actualizado correctamente");
            } else {
                System.out.println("No se encontró el mecánico con ID: " + idMecanico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar mecánico: " + e.getMessage(), e);
        }
    }
}
