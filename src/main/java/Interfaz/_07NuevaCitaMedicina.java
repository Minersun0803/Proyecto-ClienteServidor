package Interfaz;

import Conexiones.ConexionSQL;
import java.util.Random;
import java.sql.*;
import javax.swing.JOptionPane;

public class _07NuevaCitaMedicina extends javax.swing.JFrame {

    private int pacienteID;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_07NuevaCitaMedicina.class.getName());

    //Lista de medico cargados desde la base de datos
    private java.util.List<Object[]> medicos = new java.util.ArrayList<>();

    public _07NuevaCitaMedicina(int pacienteID) {
        this.pacienteID = pacienteID;
        initComponents();
        setLocationRelativeTo(null);
        cargarMedicos(); // carga médicos desde la BD
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jAgendar = new javax.swing.JButton();
        jAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Dia", "Hora"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jAgendar.setText("Agendar");
        jAgendar.addActionListener(this::jAgendarActionPerformed);

        jAtras.setText("< Atras");
        jAtras.addActionListener(this::jAtrasActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jAgendar))
                    .addComponent(jAtras))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAgendar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarMedicos() {
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();
            String sql
                    = "SELECT m.MedicoID, CONCAT(p.FirstName, ' ', p.LastName) AS Nombre "
                    + "FROM Medico m JOIN Persona p ON m.PersonaID = p.PersonaID "
                    + "WHERE m.Especialidad != 'Farmaceutico'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                medicos.add(new Object[]{
                    rs.getInt("MedicoID"),
                    rs.getString("Nombre")
                });
            }

            rs.close();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar médicos: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    private void jAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAtrasActionPerformed

        _06Citas_Paciente citas_Paciente = new _06Citas_Paciente(pacienteID);
        citas_Paciente.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jAtrasActionPerformed

    private void jAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgendarActionPerformed

        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay médicos disponibles.");
            return;
        }

        int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un día y hora en la tabla.");
            return;
        }

        // Obtener valores seleccionados de la tabla
        String dia = (String) jTable1.getValueAt(fila, 0);
        String hora = (String) jTable1.getValueAt(fila, 1);

        // Generar año y mes aleatorio
        Random rand = new Random();
        int mes = rand.nextInt(12); // 1-12
        String[] años = {"2026", "2027", "2028", "2029", "2030"};
        String año = años[rand.nextInt(años.length)];

        String fecha = String.format("%s-%02d-%s", año, mes, dia);

        // Seleccionar médico aleatorio
        int indice = rand.nextInt(medicos.size());
        int medicoID = (int) medicos.get(indice)[0];

        String direccionFija = "Hospital Calderon Guardia";

        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            // Verificar disponibilidad
            String sqlVerificar
                    = "SELECT COUNT(*) FROM Cita WHERE MedicoID = ? AND Fecha = ? AND Hora = ?";
            PreparedStatement psVerificar = con.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, medicoID);
            psVerificar.setString(2, fecha);
            psVerificar.setString(3, hora);
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this,
                        "Ese horario ya está ocupado. Seleccione otro.");
                rs.close();
                psVerificar.close();
                return;
            }
            rs.close();
            psVerificar.close();

            // Insertar cita
            String sqlInsert
                    = "INSERT INTO Cita (PacienteID, MedicoID, Fecha, Hora, Direccion) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(sqlInsert);
            psInsert.setInt(1, pacienteID);
            psInsert.setInt(2, medicoID);
            psInsert.setString(3, fecha);
            psInsert.setString(4, hora);
            psInsert.setString(5, direccionFija);
            psInsert.executeUpdate();
            psInsert.close();

            JOptionPane.showMessageDialog(this, "¡Cita agendada para el " + fecha + " a las " + hora + "!");
            new _06Citas_Paciente(pacienteID).setVisible(true);
            this.dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agendar: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }

    }//GEN-LAST:event_jAgendarActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
        if (fila >= 0) {
            String dia = (String) jTable1.getValueAt(fila, 0);
            String hora = (String) jTable1.getValueAt(fila, 1);
            System.out.println("Seleccionado: Día " + dia + " - Hora " + hora);
        }
    }//GEN-LAST:event_jTable1MousePressed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new _07NuevaCitaMedicina(0).setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAgendar;
    private javax.swing.JButton jAtras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
