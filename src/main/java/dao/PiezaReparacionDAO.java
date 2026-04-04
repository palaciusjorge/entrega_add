package dao;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiezaReparacionDAO {

    // AÑADIR PIEZA A UNA REPARACION
    public void anadirPiezaAReparacion(int idReparacion, int idPieza, int cantidad) {
        String sql = "INSERT INTO pieza_reparacion (id_reparacion, id_pieza, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReparacion);
            stmt.setInt(2, idPieza);
            stmt.setInt(3, cantidad);

            stmt.executeUpdate();
            System.out.println("Pieza añadida a la reparación");

        } catch (SQLException e) {
            throw new RuntimeException("Error al añadir pieza a reparación: " + e.getMessage(), e);
        }
    }

    // LISTAR PIEZAS DE UNA REPARACION
    public List<String> listarPiezasDeReparacion(int idReparacion) {
        List<String> lista = new ArrayList<>();

        String sql = """
            SELECT p.nombre, pr.cantidad
            FROM pieza_reparacion pr
            JOIN piezas p ON pr.id_pieza = p.id_pieza
            WHERE pr.id_reparacion = ?
        """;

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReparacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String info = "Pieza: " + rs.getString("nombre") +
                        " | Cantidad: " + rs.getInt("cantidad");
                lista.add(info);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar piezas de la reparación: " + e.getMessage(), e);
        }

        return lista;
    }

    // ACTUALIZAR CANTIDAD DE PIEZA EN UNA REPARACION
    public void actualizarCantidad(int idReparacion, int idPieza, int nuevaCantidad) {
        String sql = "UPDATE pieza_reparacion SET cantidad = ? WHERE id_reparacion = ? AND id_pieza = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, idReparacion);
            stmt.setInt(3, idPieza);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Cantidad actualizada");
            } else {
                System.out.println("No existe esa pieza en la reparación");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cantidad: " + e.getMessage(), e);
        }
    }

    // ELIMINAR PIEZA DE UNA REPARACION
    public void eliminarPiezaDeReparacion(int idReparacion, int idPieza) {
        String sql = "DELETE FROM pieza_reparacion WHERE id_reparacion = ? AND id_pieza = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReparacion);
            stmt.setInt(2, idPieza);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Pieza eliminada de la reparación");
            } else {
                System.out.println("No se encontró la pieza en esa reparación");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pieza de la reparación: " + e.getMessage(), e);
        }
    }
}
