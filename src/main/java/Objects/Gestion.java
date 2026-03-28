package Objects;

import Conexiones.ConexionSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Gestion {

    public List<Medico> ListaMedicos() {
        ArrayList<Medico> lista = new ArrayList<>();
        String sql = """
                SELECT m.MedicoID, p.FirstName, p.LastName, p.Cedula,
                       p.Telefono, p.Correo, m.Especialidad
                FROM Persona p
                INNER JOIN Medico m ON p.PersonaID = m.PersonaID
                """;

        try (Connection con = new ConexionSQL().conectarSQL(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

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

    public static ArrayList<Medico> generarMedicos(JComponent components[]) {
        Gestion gestion = new Gestion();
        ArrayList<Medico> medicos = new ArrayList<>(gestion.ListaMedicos());
        for (int i = 0; i < medicos.size() && i < 3; i++) {
            medicos.get(i).setBarra((JProgressBar) components[i]);
            medicos.get(i).setLabel((JLabel) components[i + 3]);
        }
        return medicos;

    }
}
