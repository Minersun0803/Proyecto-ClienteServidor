package Objects;

import Conexiones.ConexionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Cita {

    private int citaID;
    private int pacienteID;
    private int medicoID;
    private String fecha; // año-mes-dia
    private String hora;// hora:minutos
    private String direccion;

    public Cita(int citaID, int pacienteID, int medicoID, String fecha, String hora, String lugar) {
        this.citaID = citaID;
        this.pacienteID = pacienteID;
        this.medicoID = medicoID;
        this.fecha = fecha;
        this.hora = hora;
        this.direccion = direccion;
    }

    //Constructor para base de datos
    public Cita(int pacienteID, int medicoID,
            String fecha, String hora, String direccion) {
        this.pacienteID = pacienteID;
        this.medicoID = medicoID;
        this.fecha = fecha;
        this.hora = hora;
        this.direccion = direccion;
    }

    public static DefaultTableModel consultar(JFrame frame, int pacienteID) {
        ConexionSQL conexionSQL = new ConexionSQL();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID CITA");
        modelo.addColumn("ID MÉDICO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("DIRECCIÓN");

String datos[] = new String[5];

    try {
        PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
            "SELECT c.CitaID, " +
            "CONCAT(pm.FirstName, ' ', pm.LastName) AS NombreMedico, " +
            "c.Fecha, c.Hora, c.Direccion " +
            "FROM Cita c " +
            "JOIN Medico m ON c.MedicoID = m.MedicoID " +
            "JOIN Persona pm ON m.PersonaID = pm.PersonaID " +
            "WHERE c.PacienteID = ?" // <-- filtro por paciente
        );

        ps.setInt(1, pacienteID); // <-- asignar el ID

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            datos[0] = rs.getString("CitaID");
            datos[1] = rs.getString("NombreMedico");
            datos[2] = rs.getString("Fecha");
            datos[3] = rs.getString("Hora");
            datos[4] = rs.getString("Direccion");
            modelo.addRow(datos);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(frame, "No se pudieron cargar las citas.");
        System.out.println("Exception: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "No se pudo establecer conexión con la base de datos.");
    } finally {
        conexionSQL.desconectarSQL();
    }

    return modelo;
}



    public int getCitaID() {
        return citaID;
    }

    public void setCitaID(int citaID) {
        this.citaID = citaID;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
