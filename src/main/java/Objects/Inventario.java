
package Objects;

import Conexiones.ConexionSQL;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public  class Inventario  {
    private int productoID;
    
    private String nombre;
    
    private String descripcion;
    
    private int cantidad;

    public Inventario(int productoID, String nombre, String descripcion, int cantidad) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Inventario(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public static DefaultTableModel consultarTodos(JFrame frame) {
        ConexionSQL conexionSQL = new ConexionSQL();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("PRODUCTO ID"); // oculta — para UPDATE interno
        modelo.addColumn("NOMBRE");      // nombre del medicamento
        modelo.addColumn("CANTIDAD");    // stock disponible
        modelo.addColumn("DESCRIPCIÓN"); // descripción del producto

        String[] datos = new String[4];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                "SELECT ProductoID, Nombre, Cantidad, Descripcion FROM Inventario"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("ProductoID");
                datos[1] = rs.getString("Nombre");
                datos[2] = rs.getString("Cantidad");
                datos[3] = rs.getString("Descripcion");
                modelo.addRow(datos);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,
                "Error al cargar inventario: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;
    }
   
     public String agregar() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Inventario (Nombre, Descripcion, Cantidad) " +
                "VALUES (?, ?, ?)"
            );
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            ps.close();

            return "Producto agregado exitosamente.";

        } catch (Exception ex) {
            return "Error al agregar producto: " + ex.getMessage();
        } finally {
            conexionSQL.desconectarSQL();
        }
     }
     
      public String recargarCantidad() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            // UPDATE solo la cantidad — el nombre y descripción no cambian
            PreparedStatement ps = con.prepareStatement(
                "UPDATE Inventario SET Cantidad = ? WHERE ProductoID = ?"
            );
            ps.setInt(1, cantidad);    // nueva cantidad ingresada
            ps.setInt(2, productoID);  // producto a actualizar
            ps.executeUpdate();
            ps.close();

            return "Cantidad actualizada exitosamente.";

        } catch (Exception ex) {
            return "Error al recargar cantidad: " + ex.getMessage();
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
