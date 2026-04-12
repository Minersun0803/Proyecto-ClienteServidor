package Interfaz;

import Objects.Cita;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
/*
En eta pantalla el paciente puede ver y gestionar sus citas
esta veien desde la interfaz _04PacienteMenu, al precionar el boton Citas
y esta lleva a la interfaz _07NuevaCitaMedicna cuando se presiona el boton nueva cita
y nuevamente al menu de _04PacienteMenu al presionar el boton atras

*/

public class _06Citas_Paciente extends javax.swing.JFrame {

    private int pacienteID; //Id del pacinte loqueado, este proviene desdel login a traves de toda la cadean y se filtras por las de manera que WHERE PacienteID = ?
    private int citaIDSeleccionada; // cuanda la citaID de la fila slecionada, esa vale 0 porque el SQL cuenta con un Auto_INCREMET, y se utiliza un jCancelarActionPerfomaed ara el delet
    

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_06Citas_Paciente.class.getName());

    //Se recibe el pacienteID, para saber que citas mostar, sin este no se podrian pasar por un filtro.
    public _06Citas_Paciente(int pacienteID) {
        this.pacienteID = pacienteID; //Guarad el id del paciente
        initComponents();
        setLocationRelativeTo(null); // centrar ventana

        // Configuración de la tabla
        tbCitas.setRowHeight(20);
        tbCitas.getTableHeader().setReorderingAllowed(false);
        tbCitas.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 14));
        tbCitas.getTableHeader().setForeground(new Color(0, 102, 204));

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbCitas.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Cargar datos desde la BD
        cargarCitas();

        // Dejar limpio el formulario
        limpiar();

    }

    public void limpiar() {
        txtdia.setText("");
        txthora.setText("");
        txtmedico.setText("");
        txtlugar.setText("");
        this.citaIDSeleccionada = 0; // Esto para que no haya ninguna cita seleccionada 

        //Poner un boton, si es true, si se puede activar, si esta false no se puede activar
        jNuevaCita.setEnabled(true);
        jCancelacion.setEnabled(false);
        jAtras.setEnabled(true);

    }

    public void cargarCitas() {
            //Carga especifiamete solo las citas del paciente loqueado, al llamr Cita.consultar(), al hacer un SELECT.. FromCita JOIN Medico JOIN Persona WHERE PacienteID = ?
        tbCitas.setModel(Cita.consultar(this, pacienteID));
        tbCitas.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbCitas.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbCitas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbCitas.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCitas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jNuevaCita = new javax.swing.JButton();
        jAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCancelacion = new javax.swing.JButton();
        txtdia = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        txtmedico = new javax.swing.JTextField();
        txtlugar = new javax.swing.JTextField();
        jLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Proximas Citas"));

        tbCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cita", "Dia", "Hora", "Lugar"
            }
        ));
        tbCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbCitasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCitas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        jNuevaCita.setText("Nueva Cita");
        jNuevaCita.addActionListener(this::jNuevaCitaActionPerformed);

        jAtras.setText("<Atras");
        jAtras.addActionListener(this::jAtrasActionPerformed);

        jLabel1.setText("Dia de la cita");

        jLabel2.setText("Hora de la cita");

        jLabel3.setText("Medico asiganado");

        jLabel4.setText("Lugar de atencion");

        jCancelacion.setText("Cancelar");
        jCancelacion.addActionListener(this::jCancelacionActionPerformed);

        jLimpiar.setText("Limpiar");
        jLimpiar.addActionListener(this::jLimpiarActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNuevaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCancelacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdia, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtlugar, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(txtmedico))))
                .addGap(57, 57, 57))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jNuevaCita)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtdia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCancelacion)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLimpiar))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jAtras))
                    .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    //Abre _07NuevaCitaMedicina pasandole el pacienteID, para que la nuevacita este asociaa a este paciente.
    private void jNuevaCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNuevaCitaActionPerformed

        _07NuevaCitaMedicina nuevaCitaMedicina = new _07NuevaCitaMedicina(pacienteID);
        nuevaCitaMedicina.setVisible(true);

        this.dispose();//Ciarra la venta
    }//GEN-LAST:event_jNuevaCitaActionPerformed

    private void jAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAtrasActionPerformed

        //Regresa al menu principal del paciente
        new _04PacienteMenu(pacienteID).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jAtrasActionPerformed

    
    /*Este se ejcuta cuando el paciente hace click en una fila de la tabla
    
    Guarda el CitaID en la columna O, en citaIDSeleionada, y llena los cambos de texto con los datos de la cita correspondiente.
    */
    private void tbCitasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCitasMousePressed

        int fila = tbCitas.getSelectedRow();

        this.citaIDSeleccionada = Integer.parseInt(tbCitas.getValueAt(fila, 0).toString());

        txtdia.setText(tbCitas.getValueAt(fila, 2).toString());    // FECHA
        txthora.setText(tbCitas.getValueAt(fila, 3).toString());   // HORA
        txtmedico.setText(tbCitas.getValueAt(fila, 1).toString()); // MÉDICO
        txtlugar.setText(tbCitas.getValueAt(fila, 4).toString());  //Direccion

        jNuevaCita.setEnabled(false);
        jCancelacion.setEnabled(true);
        jAtras.setEnabled(true);

    }//GEN-LAST:event_tbCitasMousePressed

    private void jLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLimpiarActionPerformed

        limpiar();
    }//GEN-LAST:event_jLimpiarActionPerformed

    //Cancela (elimina) la cita selecionada en la tabla
    //Utiliza citaIDSelecionada que se guardo en tbCitasMousePressed
    //
            
    private void jCancelacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelacionActionPerformed
        // TODO add your handling code here:
        //Verificacion de que halla una cita selecionada
        if (citaIDSeleccionada == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita para cancelar.");
            return;
        }

        //Confirmaco antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cancelar la cita del " + txtdia.getText() + " a las " + txthora.getText() + "?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        Conexiones.ConexionSQL conexionSQL = new Conexiones.ConexionSQL();
        try {
            java.sql.Connection con = conexionSQL.conectarSQL();
//DELETE con un doble filtro, CitaID y PacienteID, asi solo se borra la cita perteneciente a el paciente
            String sql = "DELETE FROM Cita WHERE CitaID = ? AND PacienteID = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, citaIDSeleccionada); //id de la cita eliminada
            ps.setInt(2, pacienteID); // seguridad: solo puede cancelar sus propias citas
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(this, "Cita cancelada exitosamente.");
            citaIDSeleccionada = 0; //Resetea, para que ya no haya citas selecionadas
            cargarCitas(); //Recarga la tabla sin la cita eliminada
            limpiar();

        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cancelar: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
        } finally {
            conexionSQL.desconectarSQL();
        }
    }//GEN-LAST:event_jCancelacionActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new _06Citas_Paciente(0).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAtras;
    private javax.swing.JButton jCancelacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jLimpiar;
    private javax.swing.JButton jNuevaCita;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCitas;
    private javax.swing.JTextField txtdia;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txtlugar;
    private javax.swing.JTextField txtmedico;
    // End of variables declaration//GEN-END:variables
}
