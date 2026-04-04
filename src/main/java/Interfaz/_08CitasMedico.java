package Interfaz;

import Objects.Cita;
import Objects.Medico;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/*
Esta pantalla es donde el medico ve y atende sus citas asigandas

al abrir, carga las citas asigandas al medico loqueado
el medico tiene que selecionar una cita de la tabla
tiene que precionara tender y la brra se animara y eliminara la cita de la base de dtos
*/
public class _08CitasMedico extends javax.swing.JFrame {

    private int medicoID;
    private int pacienteID;
    private int citaIDSeleccionada = 0;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_08CitasMedico.class.getName());

    public _08CitasMedico(int medicoID) {
        //Recibe el medicoID para filtrar y solo mostrar las citas de medico loqueado
        this.medicoID = medicoID; //lo guarda para usarlo en la la clase
        initComponents();
        setLocationRelativeTo(null);

        // Configuración de la tabla
        tbCitas.setRowHeight(25);
        tbCitas.getTableHeader().setReorderingAllowed(false);
        tbCitas.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));
        tbCitas.getTableHeader().setForeground(new Color(102, 153, 0));

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbCitas.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        cargarCitas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAtender = new javax.swing.JButton();
        JAtenderBarra = new javax.swing.JProgressBar();
        btnAtras = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCitas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAtender.setText("Atender");
        btnAtender.addActionListener(this::btnAtenderActionPerformed);

        JAtenderBarra.setForeground(new java.awt.Color(102, 255, 102));

        btnAtras.setText("<Atras");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JAtenderBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtender))
                .addGap(0, 92, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnAtender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JAtenderBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(btnAtras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cita", "Hora"
            }
        ));
        tbCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbCitasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCitas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Logica para cargar las citas para el médico loqueado
    //LLama a las citas mediente Cita.consultarPorMedico que reliza lo siquiente
    //SELECT CitaID, nombrePaciente, Fecha, Hora, Direccion FROM Cita JOIN Paciente JOIN Persona
    //WHERE MedicoID = ?
    public void cargarCitas() {
        //Consulta de las citas
        tbCitas.setModel(Cita.consultarPorMedico(this, medicoID));
        // Ajustar columnas 
        tbCitas.getColumnModel().getColumn(1).setPreferredWidth(200); // PACIENTE
        tbCitas.getColumnModel().getColumn(2).setPreferredWidth(100); // FECHA
        tbCitas.getColumnModel().getColumn(3).setPreferredWidth(60);  // HORA
        tbCitas.getColumnModel().getColumn(4).setPreferredWidth(150); // DIRECCIÓN
    }
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

        new _05MenuMedico(medicoID).setVisible(true);

        this.dispose();

    }//GEN-LAST:event_btnAtrasActionPerformed

    //Atiende a la cita selecionada
    /*
    Verifica que haya una cita selecionada
    anima la barra de pogeso en un hilo separado, esto porque si se animara en el hilo principal la UI se congelaria
    Elimina la cita de las base de datos al terminar la animacion
    recarga la tabla sin la cita atentida
    
    se utilizan Thread para no bloqur la interfaz durante la animacion
    SwingUtilities.invokeLater garantiza que los cmbios visuales, se hagan en el hilo de Swing no el hilo secuentadio
    */
    
    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        if (citaIDSeleccionada == 0) {//verifiacion de la selecion de la cita
            JOptionPane.showMessageDialog(this, "Seleccione una cita para atender.");
            return;
        }

        //Desabilitamos el botn para uqe no halla un doble click
        btnAtender.setEnabled(false);
        JAtenderBarra.setValue(0);

        // Hilo para animar la barra de progreso
        Thread hilo = new Thread(() -> {
            try {
                //Anima la barra desde 0 a 100 con una pause de 30ms entre cada paso
                for (int i = 0; i <= 100; i++) {
                    final int valor = i;
                    //Actuliza la UI desde el hilo Swing
                    javax.swing.SwingUtilities.invokeLater(()
                            -> JAtenderBarra.setValue(valor));
                    Thread.sleep(30); //pausa para que la animacion n sea visible
                }
                
                // Eliminar la cita de la BD, al terminar la animacion
            Conexiones.ConexionSQL conexionSQL = new Conexiones.ConexionSQL();
            try {
                java.sql.Connection con = conexionSQL.conectarSQL();
                String sql = "DELETE FROM Cita WHERE CitaID = ? AND MedicoID = ?";
                java.sql.PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, citaIDSeleccionada);
                ps.setInt(2, medicoID); // seguridad: solo borra sus propias citas
                ps.executeUpdate();
                ps.close();
            } finally {
                conexionSQL.desconectarSQL();
            }
                javax.swing.SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Paciente atendido.");
                    btnAtender.setEnabled(true); //receta la barra
                    JAtenderBarra.setValue(0); //reseta la tabla
                    citaIDSeleccionada = 0; //resetea la seecion
                });
            } catch (InterruptedException ex) {
                System.out.println("Hilo interrumpido: " + ex.getMessage());
            }catch (Exception ex) {
            javax.swing.SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this, "Error al eliminar la cita: " + ex.getMessage()));
        }
        });
        hilo.start();
    }//GEN-LAST:event_btnAtenderActionPerformed

    //se ejcuta cuendo el medico hace click en una fila de la tabla
    private void tbCitasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCitasMousePressed
        // TODO add your handling code here:
        int fila = tbCitas.getSelectedRow();
        if (fila < 0) {
            return;
        } //ignora un click fuera de la tabla

        try {
            citaIDSeleccionada = Integer.parseInt(
                    tbCitas.getValueAt(fila, 0).toString());
            btnAtender.setEnabled(true);
        } catch (Exception ex) {
            System.out.println("Error al seleccionar cita: " + ex.getMessage());
        }
    }//GEN-LAST:event_tbCitasMousePressed

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
        java.awt.EventQueue.invokeLater(() -> new _08CitasMedico(0).setVisible(true));
    }
    ArrayList<Cita> citas;
    ArrayList<Medico> medicos;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar JAtenderBarra;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnAtras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCitas;
    // End of variables declaration//GEN-END:variables
}
