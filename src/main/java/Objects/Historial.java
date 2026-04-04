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
/*
esta clase, es el historial medico de un paciente

esta clase es usada en _09Historial_Paciente_Doctor
*/
public class Historial {
    //Atributos para mapear las columnas de a tabla historial
    private int PacienteID;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Genero;
    private String Antecedentes;
    private String Tratamiento;
    private String Habitos;
    private String Notas;

    //Constructor para la consulta o para editarla
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
    /*
    Esto solo mostrar el nobre y cedula en la tabla , el medico podras bucar el historial compelto con al opcion de revisar
    
    
    */
       public static DefaultTableModel consultarTodos(JFrame frame) {
        ConexionSQL conexionSQL = new ConexionSQL();
        DefaultTableModel modelo = new DefaultTableModel();

        //La columna 0 se oculatada mientra que la 1 y 2 seran visibles
        modelo.addColumn("PACIENTE ID"); // oculta
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CÉDULA");

        String[] datos = new String[3];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                    //Se realiza un JOIN triple, de las tablas Historial paciente y persona
                    //esto para obeneter el nombre y cdula que no estan prsentes en la tabla historial
                "SELECT h.PacienteID, " +
                "CONCAT(p.FirstName, ' ', p.LastName) AS Nombre, " + //CONCAT funciona para 
                "p.Cedula " +
                "FROM Historial h " +
                "JOIN Paciente pa ON h.PacienteID = pa.PacienteID " +
                "JOIN Persona p ON pa.PersonaID = p.PersonaID"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("PacienteID");//se oculta
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
       /*
       busca el historial completo de un paciente mediante la cedula
       
       Devulve un objeto Historial con todos los campos llenos o nul si no encuntra historial para esa cedula
       
       
       */
        
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
                "WHERE p.Cedula = ?" //Filtra por cedula ingresada
            );
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Conturlle y devulevo el objeto Historial con todos los datos
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
                //En caso que no exita historial para esa cedula 
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
                /*
                Crea un histrial nuevo oara alqun paciente que no tenga uno
                
                primero verifica que el paciente esta buscandolo por la cpedula y devolvera un string con el mensaje de éxito o error
                
                */
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

            //Inserta el historial con el pacienteID real
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Historial (PacienteID, Nombre, Apellido, Edad, Genero, " +
                "Antecedentes, Tratamiento, Habitos, Notas) VALUES (?,?,?,?,?,?,?,?,?)"
            );
            ps.setInt(1, pid); //ID real del paciente
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
            /*
            Edita el historial exitente de algun paciente
            
            utiliza this.pacienteID para identificar que registro actulizar, este id proviene desde pacienteIDSelecionado en _09Historial, el cual es cudado al hacer click
            en la tabla o al buscarlo por cedula
            */
           public String editar() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            PreparedStatement ps = con.prepareStatement(
                    //UPDATE - actuliza todos los campos del historial
                    //WHERE pacienteID identifa cual historial modificar
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
            ps.setInt(9, PacienteID); //WHERE del historial a actulizar
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


