
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
    
    public List<Medico>ListaMedicos(){
        ArrayList<Medico>lista=new ArrayList<>();
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
    
    public static ArrayList<Medico> generarChefs(JComponent components[]) {
        ArrayList<Medico>medicos=new ArrayList<>();//trae de la base de datos
//        chefs.add(new Chef("Goedon Ramsay",(JProgressBar)components[0],(JLabel)components[3]));
//        chefs.add(new Chef("Julia Child",(JProgressBar)components[1],(JLabel)components[4]));
//        chefs.add(new Chef("Ricardo Muñoz",(JProgressBar)components[2],(JLabel)components[5]));
        for (int i = 0; i < medicos.size()&& i < 3 ; i++) {//agarra los primeros 3 medicos.
            medicos.get(i).setBarra((JProgressBar)components[i]);
            medicos.get(i).setLabel((JLabel)components[i+3]);
        }
        return medicos;
    }
        
}
