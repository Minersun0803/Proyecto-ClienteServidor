package Conexiones;

import Objects.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // Metodo para listar los pacientes
    public List<Paciente> listPacientes() {
        List<Paciente> lista = new ArrayList<>();

        String sql = """
                SELECT pa.PacienteID, p.FirstName, p.LastName, p.Cedula,
                       p.Telefono, p.Correo, p.Ubicacion
                FROM Person p
                INNER JOIN Paciente pa ON p.PersonID = pa.PersonID
                """;

        try (Connection con = new ConexionSQL().conectarSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Paciente(
                        rs.getInt("PacienteID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Cedula"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Ubicacion")
                ));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    // Metodo Agregar pacientes
    public boolean AgregarPaciente(Paciente paciente) {

        String sqlPerson = """
                INSERT INTO Person (FirstName, LastName, Cedula, Telefono, Correo, Ubicacion)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        String sqlPaciente = """
                INSERT INTO Paciente (PersonID)
                VALUES (?)
                """;

        try (Connection con = new ConexionSQL().conectarSQL();
             PreparedStatement psPerson = con.prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psPaciente = con.prepareStatement(sqlPaciente)) {

            // Insertar en Person
            psPerson.setString(1, paciente.getNombre());
            psPerson.setString(2, paciente.getApellidos());
            psPerson.setString(3, paciente.getCedula());
            psPerson.setString(4, paciente.getTelefono());
            psPerson.setString(5, paciente.getCorreo());
            psPerson.setString(6, paciente.getUbicacion());

            psPerson.executeUpdate();

            ResultSet rs = psPerson.getGeneratedKeys();
            if (rs.next()) {
                int personID = rs.getInt(1);

                // Insertar en Paciente
                psPaciente.setInt(1, personID);
                psPaciente.executeUpdate();
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Metodo Eliominar pacientes
    public boolean EliminarPaciente(int pacienteID) {

        String sql = "DELETE FROM Paciente WHERE PacienteID = ?";

        try (Connection con = new ConexionSQL().conectarSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pacienteID);
            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Actualizar pacientes
    
    public boolean EditarPaciente(Paciente paciente) {

        String sql = """
                UPDATE Person p
                INNER JOIN Paciente pa ON p.PersonID = pa.PersonID
                SET p.FirstName = ?, p.LastName = ?, p.Cedula = ?, 
                    p.Telefono = ?, p.Correo = ?, p.Ubicacion = ?
                WHERE pa.PacienteID = ?
                """;

        try (Connection con = new ConexionSQL().conectarSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidos());
            ps.setString(3, paciente.getCedula());
            ps.setString(4, paciente.getTelefono());
            ps.setString(5, paciente.getCorreo());
            ps.setString(6, paciente.getUbicacion());
            ps.setInt(7, paciente.getPacienteID());

            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}