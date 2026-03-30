/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import Red.AuthResultado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Corrales
 */
public class PersonaDAO {

    ConexionSQL conexion = new ConexionSQL();
    

    public boolean IniciarSesion(String cedula, String contraseña) throws Exception {
        AuthResultado resultado = iniciarSesionClienteServidor(cedula, contraseña);
        return resultado.isValido();
    }
    
        public AuthResultado iniciarSesionClienteServidor(String cedula, String contraseña) throws Exception {
        String sql = """
                SELECT p.PersonaID,
                       m.MedicoID,
                       pa.PacienteID,
                       m.Especialidad
                FROM Persona p
                LEFT JOIN Medico m ON p.PersonaID = m.PersonaID
                LEFT JOIN Paciente pa ON p.PersonaID = pa.PersonaID
                WHERE p.Cedula = ? AND p.Contraseña = ?
                """;

        try (Connection con = new ConexionSQL().conectarSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if (rs.getObject("MedicoID") != null) {
                        return new AuthResultado(true, "MEDICO", rs.getString("Especialidad"));
                    }

                    if (rs.getObject("PacienteID") != null) {
                        int pacienteID = rs.getInt("PacienteID"); // <-- obtenerlo del ResultSet
    return new AuthResultado(true, "PACIENTE", null, pacienteID); // <-- pasarlo
                    }
                }
            }

            return new AuthResultado(false, "", null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + ex.getMessage());
            throw ex;
        }
    }
}

    
    


