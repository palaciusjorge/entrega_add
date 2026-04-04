package dao;

import database.DatabaseConnection;
import model.Pieza;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PiezaDAO {

    // INSERTAR PIEZA

    public void insertarPieza(Pieza pieza) {
        String sql = "INSERT INTO piezas (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pieza.getNombre());
            stmt.setDouble(2, pieza.getPrecio());
            stmt.setInt(3, pieza.getStock());

            stmt.executeUpdate();
            System.out.println("Pieza insertada correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar pieza: " + e.getMessage(), e);
        }
    }

    // LISTAR TODAS LAS PIEZAS

    public List<Pieza> listarPiezas() {
        List<Pieza> lista = new ArrayList<>();
        String sql = "SELECT * FROM piezas";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pieza pieza = new Pieza(
                        rs.getInt("id_pieza"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(pieza);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar piezas: " + e.getMessage(), e);
        }

        return lista;
    }

    // OBTENER PIEZA POR ID

    public Pieza obtenerPiezaPorId(int idPieza) {
        String sql = "SELECT * FROM piezas WHERE id_pieza = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPieza);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pieza(
                        rs.getInt("id_pieza"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            } else {
                return null; // No se encontró la pieza
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pieza por ID: " + e.getMessage(), e);
        }
    }

    // ACTUALIZAR STOCK DE PIEZA

    public void actualizarStockPieza(int idPieza, int nuevoStock) {
        String sql = "UPDATE piezas SET stock = ? WHERE id_pieza = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idPieza);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Stock de pieza actualizado correctamente");
            } else {
                System.out.println("No se encontró la pieza para actualizar el stock");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar stock de pieza: " + e.getMessage(), e);
        }
    }

    // ELIMINAR PIEZA POR ID

    public void eliminarPieza(int idPieza) {
        String sql = "DELETE FROM piezas WHERE id_pieza = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPieza);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Pieza eliminada correctamente");
            } else {
                System.out.println("No se encontró la pieza para eliminar");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pieza: " + e.getMessage(), e);
        }
    }
}
