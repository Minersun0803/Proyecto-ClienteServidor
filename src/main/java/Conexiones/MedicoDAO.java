package Conexiones;

import Objects.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    
       //  MÉTODO: obtiene todos los médicos de la base de datos
    public List<Medico> listMedicos() {
        List<Medico> lista = new ArrayList<>();
        // Consulta SQL: une Persona + Medico para obtener todos los datos
        String sql = """
                SELECT m.MedicoID, p.FirstName, p.LastName, p.Cedula,
                       p.Telefono, p.Correo, m.Especialidad,p.Genero
                FROM Persona p
                INNER JOIN Medico m ON p.PersonaID = m.PersonaID
                """;
        
        
         // Ejecuta la consulta y obtiene los resultados
        try (Connection con = new ConexionSQL().conectarSQL(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            
            // Recorre cada fila del resultado
            while (rs.next()) {
                
                
                // Convierte cada fila en un objeto Medico
                lista.add(new Medico(
                        rs.getInt("MedicoID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Cedula"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Especialidad"),
                rs.getString("Genero")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //  Retorna la lista de médicos
        return lista;
    }

    public boolean AgregarMedico(Medico medico) {
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            // Inserta primero en Persona datos generales.
            String sqlPerson = "INSERT INTO Person (FirstName, LastName, Cedula, Telefono, Correo, Direccion, Contraseña, AñoNacimiento)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            //Luego inserta en medico datos especificos.
            String sqlMedico = "INSERT INTO Medico(PersonaID, Especialidad)" + "VALUES(?,?)";

            PreparedStatement psPerson = conexionSQL.conectarSQL().prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);

            //Insertar datos del medico
            psPerson.setString(1, medico.getNombre());
            psPerson.setString(2, medico.getApellidos());
            psPerson.setString(3, medico.getCedula());
            psPerson.setString(4, medico.getTelefono());
            psPerson.setString(5, medico.getCorreo());
            psPerson.setString(6, medico.getUbicacion());

            psMedico.executeUpdate();

            ResultSet rs = psPerson.getGeneratedKeys();
            if (rs.next()) {
                int personID = rs.getInt(1);
                // Insertar en Paciente
                psMedico.setInt(1, personID);
                psMedico.executeUpdate();
            }

            return true;

        } catch (Exception ex) {
            System.out.println("Exception Listar Medicos: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }
        return false;
    }

    public boolean EditarMedico(Medico medico) {
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            String sqlPersona = """
                         UPDATE Persona SET
                         FirstName  = ?,
                         LastName  = ?,
                         Telefono   = ?,
                         Correo   = ?,
                         Especialidad = ?
                         WHERE codigo = ?
                         """;
            String sqlMedico = """
                              UPDATE Medico SET
                              Especialidad = ?
                              WHERE MedicoID= ?
                              """;

            PreparedStatement psPersona = conexionSQL.conectarSQL().prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);

            psPersona.setString(1, medico.getNombre());
            psPersona.setString(1, medico.getNombre());
            psPersona.setString(2, medico.getApellidos());
            psPersona.setString(3, medico.getCedula());
            psPersona.setString(4, medico.getTelefono());
            psPersona.setString(5, medico.getCorreo());
            psPersona.setString(6, medico.getUbicacion());
            psPersona.setInt(7, medico.getMedicoID());

            psPersona.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out.println("Exception Listar Medicos: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }
        return false;

    }

    public boolean EliminarMedico(int codigo) {
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            String sqlPerson = "DELETE FROM Persona WHERE PersonID = ?";
            String sqlMedico = "DELETE FROM Medico WHERE MedicoID = ?";

            PreparedStatement psPerson = conexionSQL.conectarSQL().prepareStatement(sqlPerson);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);
            psPerson.setInt(1, codigo);
            psMedico.setInt(1, codigo);

            psPerson.executeUpdate();
            psMedico.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out.println("Exception Listar Medicos: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }
        return false;
    }
}
