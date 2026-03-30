package Conexiones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PersonaDAO {

    ConexionSQL conexion = new ConexionSQL();
    

    public boolean IniciarSesion(String cedula, String contraseña) throws Exception {
        boolean acceso = false;
        PreparedStatement ps = null;
        
        try {
            ps = conexion.conectarSQL()
                    .prepareStatement("SELECT * FROM Persona WHERE Cedula = ? AND Contraseña = ?");

            ps.setString(1, cedula);       // primer parámetro
            ps.setString(2, contraseña);   // segundo parámetro

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Si hay resultado, significa que el usuario existe con esa contraseña
                acceso = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + ex.getMessage());
        } finally {
            conexion.desconectarSQL();
        }
        return acceso;
    }
}
