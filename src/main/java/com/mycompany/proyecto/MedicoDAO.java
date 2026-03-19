/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Eduardo Corrales
 */
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

        } catch (Exception ex) { // ← este es el único cambio
            ex.printStackTrace();
        }

        return lista;
    }
}
