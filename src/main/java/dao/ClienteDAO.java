package dao;

import database.DatabaseConnection;
import model.Cliente;

import java.sql.*;
import java.util.List;

public class ClienteDAO {

    // INSERTAR CLIENTE
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, apellido, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getDireccion());

            stmt.executeUpdate();
            System.out.println("Cliente insertado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cliente: " + e.getMessage(), e);
        }
    }

    // LISTAR TODOS LOS CLIENTES
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar clientes: " + e.getMessage(), e);
        }

        return lista;
    }

    // OBTENER CLIENTE POR ID
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener cliente: " + e.getMessage(), e);
        }

        return cliente;
    }

    // ACTUALIZAR CLIENTE
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, email = ?, direccion = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getDireccion());
            stmt.setInt(6, cliente.getId());

            stmt.executeUpdate();
            System.out.println("Cliente actualizado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage(), e);
        }
    }

    // ELIMINAR CLIENTE
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente: " + e.getMessage(), e);
        }
    }
}
