/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import Conexiones.ConexionSQL;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Corrales
 */
public class Receta {

    int recetaID;
    private int pacienteID;
    private int medicoID;
    private String fecha;
    private String detalles;
    private String estado;

    public Receta(int recetaID, int pacienteID, int medicoID, String fecha, String detalles, String estado) {
        this.recetaID = recetaID;
        this.pacienteID = pacienteID;
        this.medicoID = medicoID;
        this.fecha = fecha;
        this.detalles = detalles;
        this.estado = estado;
    }

    public Receta(int pacienteID, int medicoID, String fecha, String detalles, String estado) {
        this.pacienteID = pacienteID;
        this.medicoID = medicoID;
        this.fecha = fecha;
        this.detalles = detalles;
        this.estado = estado;
    }

    public static DefaultTableModel consultarTodos(JFrame frame) {
        ConexionSQL conexionSQL = new ConexionSQL();
        DefaultTableModel modelo = new DefaultTableModel();

        //La columna 0 se oculatada mientra que la 1 y 2 seran visibles
        modelo.addColumn("RECETA ID");  // oculta
        modelo.addColumn("PACIENTE");   // nombre completo
        modelo.addColumn("FECHA");      // fecha de la receta
        modelo.addColumn("DETALLES");   // descripción del medicamento
        modelo.addColumn("ESTADO");

        String[] datos = new String[5];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                    "SELECT r.RecetaID, "
                    + "CONCAT(p.FirstName, ' ', p.LastName) AS NombrePaciente, "
                    + "r.Fecha, r.Detalles, r.Estado "
                    + "FROM Receta r "
                    + "JOIN Paciente pa ON r.PacienteID = pa.PacienteID "
                    + "JOIN Persona p ON pa.PersonaID = p.PersonaID"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("RecetaID");
                datos[1] = rs.getString("NombrePaciente");
                datos[2] = rs.getString("Fecha");
                datos[3] = rs.getString("Detalles");
                datos[4] = rs.getString("Estado");
                modelo.addRow(datos);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Error al cargar recetas: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;

    }

    public static boolean eliminar(JFrame frame, int recetaID) {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Receta WHERE RecetaID = ?"
            );
            ps.setInt(1, recetaID);
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "Error al eliminar receta: " + ex.getMessage());
            return false;
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

    public int getMedicoID() {
        return medicoID;
    }

    public void setMedicoID(int medicoID) {
        this.medicoID = medicoID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

