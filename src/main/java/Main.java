import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //test de conexion a la base de datos
        try {
            database.DatabaseConnection.conectar();
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (RuntimeException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        //mostrar datos de la bse de datos (aun sin dao)
        try (java.sql.Connection conn = database.DatabaseConnection.conectar();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_cliente") + ", Nombre: " + rs.getString("nombre") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
