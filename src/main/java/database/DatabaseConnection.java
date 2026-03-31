package database;

import java.sql.Connection;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/taller_entrega";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection conectar(){
        try {
            return java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error de conexion: " + e.getMessage(), e);
        }
    }
}
