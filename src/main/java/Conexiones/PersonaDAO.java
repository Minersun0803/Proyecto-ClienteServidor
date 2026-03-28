/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Corrales
 */
public class PersonaDAO {

    public boolean IniciarSesion(String cedula, String contraseña) {
        boolean acceso = false;
        try {
            PreparedStatement ps = ConexionSQL.conectarSQL()
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
            ConexionSQL.desconectarSQL();
        }
        return acceso;
    }



}
