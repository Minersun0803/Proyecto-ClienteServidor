package Conexiones;

// Hola

import Objects.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public List<Medico> listMedicos() {
        List<Medico> lista = new ArrayList<>();
        String sql = """
                SELECT m.MedicoID, p.FirstName, p.LastName, p.Cedula,
                       p.Telefono, p.Correo, m.Especialidad
                FROM Person p
                INNER JOIN Medico m ON p.PersonID = m.PersonID
                """;

        try (Connection con = new ConexionSQL().conectarSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Medico(
                        rs.getInt("MedicoID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Cedula"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Especialidad")));
            }

        } catch (Exception ex) { 
            ex.printStackTrace();
        }

        return lista;
    }
    
    public boolean AgregarMedico(Medico medico){
        ConexionSQL conexionSQL = new ConexionSQL();
        
        try{    
            String sqlPerson = "INSERT INTO person (FirstName, LastName, Cedula, Telefono, Correo,Especialidad) "
                    + "VALUES(?,?,?,?,?,?)";
            
            String sqlMedico ="INSERT INTO Medico(MedicoID,Especialidad)"+"VALUES(?,?)";
            
            PreparedStatement psPerson = conexionSQL.conectarSQL().prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);
            
            //Instertar person
            
            
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
        }finally{
            conexionSQL.desconectarSQL();
        }
        return false;
    }
    
    public boolean EditarMedico(Medico medico){
        ConexionSQL conexionSQL = new ConexionSQL();
        
        try{    
            String sqlPerson = """
                         UPDATE Medico SET
                         FirstName  = ?,
                         LastName  = ?,
                         Telefono   = ?,
                         Correo   = ?,
                         Especialidad = ?
                         WHERE codigo = ?
                         """;
            String sqlMedico ="""
                              UPDATE Medico SET
                              Especialidad = ?,
                              WHERE MedicoID= ?
                              """;
            
            PreparedStatement psPerson = conexionSQL.conectarSQL().prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);
            
            psPerson.setString(1, medico.getNombre());
            psPerson.setString(1, medico.getNombre());
            psPerson.setString(2, medico.getApellidos());
            psPerson.setString(3, medico.getCedula());
            psPerson.setString(4, medico.getTelefono());
            psPerson.setString(5, medico.getCorreo());
            psPerson.setString(6, medico.getUbicacion());
            psPerson.setInt(7, medico.getMedicoID());
            
            psPerson.executeUpdate();
            return true;
            
        } catch (Exception ex) {
            System.out.println("Exception Listar Medicos: " + ex.getMessage());
        }finally{
            conexionSQL.desconectarSQL();
        }
        return false;
    
    
    }
    public boolean EliminarMedico(int codigo){
        ConexionSQL conexionSQL = new ConexionSQL();
        
        try{    
            String sqlPerson = "DELETE FROM Person WHERE PersonID=1";
            String sqlMedico = "DELETE FROM Medico WHERE MedicoID=1";
            
            PreparedStatement psPerson = conexionSQL.conectarSQL().prepareStatement(sqlPerson);
            PreparedStatement psMedico = conexionSQL.conectarSQL().prepareStatement(sqlMedico);
            psPerson.setInt(1,codigo);
            psMedico.setInt(1,codigo);
            
            psPerson.executeUpdate();
            psMedico.executeUpdate();
            return true;
            
            
        } catch (Exception ex) {
            System.out.println("Exception Listar Productos: " + ex.getMessage());
        }finally{
            conexionSQL.desconectarSQL();
        }
        return false;
    }
}
