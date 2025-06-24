package model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConecction {

    private static DataBaseConecction instance;   // instancia única
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/bolsa_laboral_app";
    private static final String USER = "root";
    private static final String PASSWORD = "karate1_";

    private void  DataBaseConnection() {
        try {
            // Carga del driver (opcional en JDBC 4+, pero lo dejo explícito)
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    // ────────────────────────────────────────────────────────────────────────────
    // 2.  Método estático de acceso global
    // ────────────────────────────────────────────────────────────────────────────
    public static DataBaseConecction getInstance() {
        if (instance == null) {
            synchronized (DataBaseConecction.class) {
                if (instance == null) {          // doble verificación (thread-safe)
                    instance = new DataBaseConecction();
                }
            }
        }
        return instance;
    }

    // ────────────────────────────────────────────────────────────────────────────
    // 3.  Getter para la conexión JDBC
    // ────────────────────────────────────────────────────────────────────────────
    public Connection getConnection() {
        return connection;
    }
}
