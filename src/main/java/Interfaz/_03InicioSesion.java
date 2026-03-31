package Interfaz;

import Conexiones.ConexionSQL;
import Conexiones.PersonaDAO;
import Red.AuthResultado;
import Red.ClienteSocket;
import Red.Respuesta;
import Red.Solicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class _03InicioSesion extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_03InicioSesion.class.getName());

    public _03InicioSesion() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jUsuarioLabel = new javax.swing.JLabel();
        jConstraseñaLabel = new javax.swing.JLabel();
        jAtras = new javax.swing.JButton();
        btnIniciarSesion = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtConstraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jUsuarioLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jUsuarioLabel.setText("Usuario");

        jConstraseñaLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jConstraseñaLabel.setText("Contraseña");

        jAtras.setText("<Atras");
        jAtras.addActionListener(this::jAtrasActionPerformed);

        btnIniciarSesion.setText("Iniciar sesion");
        btnIniciarSesion.addActionListener(this::btnIniciarSesionActionPerformed);

        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        txtConstraseña.addActionListener(this::txtConstraseñaActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jAtras))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(btnIniciarSesion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jUsuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jConstraseñaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtConstraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAtras)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUsuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jConstraseñaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConstraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnIniciarSesion)
                .addGap(101, 101, 101))
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


    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtConstraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConstraseñaActionPerformed
       
    }//GEN-LAST:event_txtConstraseñaActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        
        try {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtConstraseña.getPassword());

            Map<String, String> datos = new HashMap<>();
            datos.put("cedula", usuario);
            datos.put("contrasena", contrasena);

            ClienteSocket clienteSocket = new ClienteSocket("localhost", 5000);
            Respuesta respuesta = clienteSocket.enviarSolicitud(new Solicitud("LOGIN", datos));

            if (!respuesta.isExito()) {
                JOptionPane.showMessageDialog(this, respuesta.getMensaje());
                return;
            }

            AuthResultado resultado = (AuthResultado) respuesta.getDatos();

            if ("MEDICO".equalsIgnoreCase(resultado.getTipoUsuario())) {
                int medicoID = resultado.getMedicoID(); //Obteneos el id del medico 
                if ("Farmaceutico".equalsIgnoreCase(resultado.getEspecialidad())) {
                    new MenuFarmacia().setVisible(true);
                } else {
                    new _05MenuMedico(medicoID).setVisible(true); //se lo pasamos al siquiente menu
                }
                this.dispose();

            } else if ("PACIENTE".equalsIgnoreCase(resultado.getTipoUsuario())) {
                int pacienteID = resultado.getPacienteID();// obtenermos el id del paciente
                new _04PacienteMenu(pacienteID).setVisible(true); //le pasamos el id para que sepa quien es todo el tiempo
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo determinar el tipo de usuario.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de comunicación con el servidor: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void jAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAtrasActionPerformed
        
        _01PantallaInicio pantalla_Inicio = new _01PantallaInicio();
        pantalla_Inicio.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jAtrasActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new _03InicioSesion().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton jAtras;
    private javax.swing.JLabel jConstraseñaLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jUsuarioLabel;
    private javax.swing.JPasswordField txtConstraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
