package Objects;

import Conexiones.ConexionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RecetaMedica {

    private int recetaID;
    private int pacienteID;
    private int medicoID;
    private String fecha;
    private String detalles;
    private String estado;

    public RecetaMedica() {
    }

    public RecetaMedica(int recetaID, int pacienteID, int medicoID, String fecha, String detalles, String estado) {
        this.recetaID = recetaID;
        this.pacienteID = pacienteID;
        this.medicoID = medicoID;
        this.fecha = fecha;
        this.detalles = detalles;
        this.estado = estado;
    }

    public static DefaultTableModel consultarPorPaciente(JFrame frame, int pacienteID) {
        ConexionSQL conexionSQL = new ConexionSQL();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID RECETA");
        modelo.addColumn("DIA");
        modelo.addColumn("ESTADO");
        modelo.addColumn("MEDICAMENTOS");
        modelo.addColumn("MEDICO");

        String[] datos = new String[5];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                "SELECT r.RecetaID, r.Fecha, r.Estado, r.Detalles, " +
                "CONCAT(p.FirstName, ' ', p.LastName) AS NombreMedico " +
                "FROM Receta r " +
                "JOIN Medico m ON r.MedicoID = m.MedicoID " +
                "JOIN Persona p ON m.PersonaID = p.PersonaID " +
                "WHERE r.PacienteID = ? " +
                "ORDER BY r.Fecha DESC"
            );

            ps.setInt(1, pacienteID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("RecetaID");
                datos[1] = rs.getString("Fecha");
                datos[2] = rs.getString("Estado");
                datos[3] = rs.getString("Detalles");
                datos[4] = rs.getString("NombreMedico");
                modelo.addRow(datos);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "No se pudieron cargar las recetas: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;
    }

    public static String activarReceta(JFrame frame, int recetaID) {
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                "UPDATE Receta SET Estado = 'Activa' WHERE RecetaID = ?"
            );
            ps.setInt(1, recetaID);

            int filas = ps.executeUpdate();
            ps.close();

            if (filas > 0) {
                return "Receta activada correctamente.";
            } else {
                return "No se encontró la receta seleccionada.";
            }

        } catch (SQLException ex) {
            return "Error SQL al activar la receta: " + ex.getMessage();
        } catch (Exception ex) {
            return "Error de conexión: " + ex.getMessage();
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    public int getRecetaID() {
        return recetaID;
    }

    public void setRecetaID(int recetaID) {
        this.recetaID = recetaID;
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