/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    private String url;
    private String db;
    private String user;
    private String password;
    private String driver;
    private Connection connection;

    public ConexionSQL() {
        this.url = "jdbc:mysql://localhost:3306/";
        this.db = "Hospital";
        this.user = "root";
        this.password = "Max@2005";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Connection conectarSQL() throws Exception {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + db, user, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception Class: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exception SQL: " + ex.getMessage());
        }
        throw new Exception();
    }

    public void desconectarSQL() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException ex) {
                System.out.println("Exception SQL: " + ex.getMessage());
            }
        }
    }

}
