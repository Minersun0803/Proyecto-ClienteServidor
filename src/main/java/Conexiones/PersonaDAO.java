package Conexiones;

import Red.AuthResultado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//Este bjeto maneja todo los relacion con la autentificacion de los usuarios ( médicos y paciente)
public class PersonaDAO {

    //Instancia para la conexion
    ConexionSQL conexion = new ConexionSQL();

    //Un metodo que devuelve true/fase, este es usado para saber si el login fue valido
    public boolean IniciarSesion(String cedula, String contraseña) throws Exception {
        AuthResultado resultado = iniciarSesionClienteServidor(cedula, contraseña);
        return resultado.isValido();
    }

    //Este es el metodo prinicipal para la autentificacion
    /*
    Realiza un SELECT con un LEFT JOIN para buscar simultamete, en la tabla medico y paciente, en una sola query asi se sabe el tipo de usuario
    
    y devuelve un AuthResultado que vieja por el socket hasta el cliente
    
    */
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

        try (Connection con = new ConexionSQL().conectarSQL(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { //Si el medico no es null, es un medico y usa getObjet() en lugar de un get Int() para poder comparar con null, ya que el getInt devuelve 0 y si seria null
                    if (rs.getObject("MedicoID") != null) {
                        int medicoID = rs.getInt("MedicoID"); // obtenemos el id del medico para mas tarde
                        return new AuthResultado(true, "MEDICO", rs.getString("Especialidad"), 0 , medicoID);
                    }

                    if (rs.getObject("PacienteID") != null) {//Si pacienteID no es null, es un paciente
                        int pacienteID = rs.getInt("PacienteID"); // <-- obtenerlo del ResultSet
                        return new AuthResultado(true, "PACIENTE", null, pacienteID, 0); // <-- pasarlo
                    }
                }
            }

            //En dao caso de no encotrarningun resultado, las cerdenciales son incorrectas
            return new AuthResultado(false, "", null, 0, 0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + ex.getMessage());
            throw ex; //Relanza para que el servidor lo maneje
        }
    }
}
