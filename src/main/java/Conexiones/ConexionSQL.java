package Conexiones;


import java.sql.Connection;        // Representa la conexión con la base de datos
import java.sql.DriverManager;     // Se usa para obtener la conexión
import java.sql.SQLException;      // Manejo de errores SQL

// Clase encargada de manejar la conexión con MySQL
public class ConexionSQL {


    private String url;       
    private String db;         
    private String user;       
    private String password; 
    private String driver;     // Driver de MySQL (necesario para conectarse)
    private Connection connection; // Objeto que representa la conexión activa

    // Constructor: inicializa los datos de conexión
    public ConexionSQL() {
        this.url = "jdbc:mysql://localhost:3306/";  
        this.db = "Hospital";                      
        this.user = "root";                       
        this.password = "Max@2005";                 
        this.driver = "com.mysql.cj.jdbc.Driver";   
    }

    // Método para CONECTARSE a la base de datos
    public Connection conectarSQL() throws Exception {
        try {
            // Carga el driver de MySQL en memoria
            Class.forName(driver);

            // Establece la conexión usando URL + base de datos + credenciales
            connection = DriverManager.getConnection(url + db, user, password);

            // Retorna la conexión para poder usarla en otras clases (DAO)
            return connection;

        } catch (ClassNotFoundException ex) {
            // Error si no se encuentra el driver
            System.out.println("Exception Class: " + ex.getMessage());

        } catch (SQLException ex) {
            // Error si falla la conexión (Datos incorrectos, DB apagada, etc.)
            System.out.println("Exception SQL: " + ex.getMessage());
        }

     
        throw new Exception();
    }

    // Método para DESCONECTARSE de la base de datos
    public void desconectarSQL() {

        // 🔸 Verifica que sí exista una conexión activa
        if (connection != null) {
            try {
                // Cierra la conexión
                connection.close();

            
                System.out.println("Conexion cerrada.");

            } catch (SQLException ex) {
             
                System.out.println("Exception SQL: " + ex.getMessage());
            }
        }
    }

}
