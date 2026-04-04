package dao;

import database.DatabaseConnection;
import model.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    // INSERTAR FACTURA
    public void insertarFactura(int idReparacion, double total) {
        String sql = "INSERT INTO facturas (id_reparacion, total) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReparacion);
            stmt.setDouble(2, total);

            stmt.executeUpdate();
            System.out.println("Factura insertada correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar factura: " + e.getMessage(), e);
        }
    }

    // LISTAR FACTURAS POR REPARACION
    public List<Factura> listarFacturasPorReparacion(int idReparacion) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas WHERE id_reparacion = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReparacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id_factura"),
                        rs.getInt("id_reparacion"),
                        rs.getDouble("total")
                );
                lista.add(factura);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar facturas: " + e.getMessage(), e);
        }

        return lista;
    }

    // LISTAR FACTURAS POR CLIENTE (JOIN BIEN HECHO)
    public List<Factura> listarFacturasPorCliente(int idCliente) {
        List<Factura> lista = new ArrayList<>();

        String sql = """
            SELECT f.*
            FROM facturas f
            JOIN reparacion r ON f.id_reparacion = r.id_reparacion
            JOIN coches c ON r.id_coche = c.id_coche
            WHERE c.id_cliente = ?
        """;

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id_factura"),
                        rs.getInt("id_reparacion"),
                        rs.getDouble("total")
                );
                lista.add(factura);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar facturas por cliente: " + e.getMessage(), e);
        }

        return lista;
    }

    // ELIMINAR FACTURA
    public void eliminarFactura(int id) {
        String sql = "DELETE FROM facturas WHERE id_factura = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Factura eliminada");
            } else {
                System.out.println("⚠No existe factura con ese ID");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar factura: " + e.getMessage(), e);
        }
    }
}
