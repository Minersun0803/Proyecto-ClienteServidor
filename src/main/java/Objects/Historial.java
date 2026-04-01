/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import Conexiones.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Corrales
 */
public class Historial {
    private int PacienteID;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Genero;
    private String Antecedentes;
    private String Tratamiento;
    private String Habitos;
    private String Notas;

    //Constructor para la consulta
    public Historial(int PacienteID, String Nombre, String Apellido, int Edad, String Genero, String Antecedentes, String Tratamiento, String Habitos, String Notas) {
        this.PacienteID = PacienteID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Edad = Edad;
        this.Genero = Genero;
        this.Antecedentes = Antecedentes;
        this.Tratamiento = Tratamiento;
        this.Habitos = Habitos;
        this.Notas = Notas;
    }
    
    //Cargar los historiales, en la tabla
       public static DefaultTableModel consultarTodos(JFrame frame) {
        ConexionSQL conexionSQL = new ConexionSQL();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("PACIENTE ID"); // oculta
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CÉDULA");

        String[] datos = new String[3];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                "SELECT h.PacienteID, " +
                "CONCAT(p.FirstName, ' ', p.LastName) AS Nombre, " +
                "p.Cedula " +
                "FROM Historial h " +
                "JOIN Paciente pa ON h.PacienteID = pa.PacienteID " +
                "JOIN Persona p ON pa.PersonaID = p.PersonaID"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("PacienteID");
                datos[1] = rs.getString("Nombre");
                datos[2] = rs.getString("Cedula");
                modelo.addRow(datos);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al cargar historiales: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;
    }
        
        //Buscar u historial por la cedula del paciente
            public static Historial buscarPorCedula(JFrame frame, String cedula) {
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                "SELECT h.PacienteID, h.Nombre, h.Apellido, h.Edad, h.Genero, " +
                "h.Antecedentes, h.Tratamiento, h.Habitos, h.Notas " +
                "FROM Historial h " +
                "JOIN Paciente pa ON h.PacienteID = pa.PacienteID " +
                "JOIN Persona p ON pa.PersonaID = p.PersonaID " +
                "WHERE p.Cedula = ?"
            );
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Historial(
                    rs.getInt("PacienteID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Edad"),
                    rs.getString("Genero"),
                    rs.getString("Antecedentes"),
                    rs.getString("Tratamiento"),
                    rs.getString("Habitos"),
                    rs.getString("Notas")
                );
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró historial para esa cédula.");
                return null;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al buscar: " + ex.getMessage());
            return null;
        } finally {
            conexionSQL.desconectarSQL();
        }
    }
            
            //Crear un historial nuevo
            public String crear() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            // Verificar que el paciente existe por cédula
            PreparedStatement psBuscar = con.prepareStatement(
                "SELECT pa.PacienteID FROM Paciente pa " +
                "JOIN Persona p ON pa.PersonaID = p.PersonaID " +
                "WHERE p.Cedula = ?"
            );
            psBuscar.setInt(1, this.PacienteID); // aquí pacienteID es temporal como cédula
            ResultSet rs = psBuscar.executeQuery();

            if (!rs.next()) return "No existe un paciente con esa cédula.";
            int pid = rs.getInt("PacienteID"); //Captura el id del paciente
            rs.close();
            psBuscar.close();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Historial (PacienteID, Nombre, Apellido, Edad, Genero, " +
                "Antecedentes, Tratamiento, Habitos, Notas) VALUES (?,?,?,?,?,?,?,?,?)"
            );
            ps.setInt(1, pid);
            ps.setString(2, Nombre);
            ps.setString(3, Apellido);
            ps.setInt(4, Edad);
            ps.setString(5, Genero);
            ps.setString(6, Antecedentes);
            ps.setString(7, Tratamiento);
            ps.setString(8, Habitos);
            ps.setString(9, Notas);
            ps.executeUpdate();
            ps.close();

            return "Historial creado exitosamente.";

        } catch (Exception ex) {
            return "Error al crear historial: " + ex.getMessage();
        } finally {
            conexionSQL.desconectarSQL();
        }
    }
            
            //Editar un historial
           public String editar() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE Historial SET Nombre=?, Apellido=?, Edad=?, Genero=?, " +
                "Antecedentes=?, Tratamiento=?, Habitos=?, Notas=? " +
                "WHERE PacienteID=?"
            );
            ps.setString(1, Nombre);
            ps.setString(2, Apellido);
            ps.setInt(3, Edad);
            ps.setString(4, Genero);
            ps.setString(5, Antecedentes);
            ps.setString(6, Tratamiento);
            ps.setString(7, Habitos);
            ps.setString(8, Notas);
            ps.setInt(9, PacienteID);
            ps.executeUpdate();
            ps.close();

            return "Historial actualizado exitosamente.";

        } catch (Exception ex) {
            return "Error al editar historial: " + ex.getMessage();
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    
    
    

    public int getPacienteID() {
        return PacienteID;
    }

    public void setPacienteID(int PacienteID) {
        this.PacienteID = PacienteID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getAntecedentes() {
        return Antecedentes;
    }

    public void setAntecedentes(String Antecedentes) {
        this.Antecedentes = Antecedentes;
    }

    public String getTratamiento() {
        return Tratamiento;
    }

    public void setTratamiento(String Tratamiento) {
        this.Tratamiento = Tratamiento;
    }

    public String getHabitos() {
        return Habitos;
    }

    public void setHabitos(String Habitos) {
        this.Habitos = Habitos;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String Notas) {
        this.Notas = Notas;
    }
    
    
}


