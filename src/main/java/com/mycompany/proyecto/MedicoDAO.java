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

public class MedicoDAO {
    //Mostrar Medicos

    public void listMedicos() {
        String sql = """
SELECT p.FirstName, p.LastName, p.Cedula, p.Telefono, p.Correo, p.Direccion, m.Especialidad
                                 FROM Person p
                                 INNER JOIN Medico m ON p.PersonID = m.PersonID
                """;

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medico medico = new Medico(
                        rs.getString("FirtName"),
                        rs.getString("LastName"),
                        rs.getString("Cedula"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Direccion"));

                //Ver especilidad
                System.out.println("Médico: " + medico.getNombre()
                        + " " + medico.getApellidos()
                        + "| Especilidad: " + rs.getString("Especialidad"));

                lista.add(medico);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}
