package Interfaz;

public class _01PantallaInicio extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_01PantallaInicio.class.getName());

    public _01PantallaInicio() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jIniciarSesion = new javax.swing.JButton();
        jRegistro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("Inicio de Sesion Medico");

        jIniciarSesion.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jIniciarSesion.setText("Iniciar sesion");
        jIniciarSesion.addActionListener(this::jIniciarSesionActionPerformed);

        jRegistro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jRegistro.setText("Registrarse");
        jRegistro.addActionListener(this::jRegistroActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(jIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIniciarSesionActionPerformed

    _03InicioSesion inicioSesion = new _03InicioSesion();
    inicioSesion.setVisible(true);

    // Cerrar la ventana actual (_01PantallaInicio)
    this.dispose();
    }//GEN-LAST:event_jIniciarSesionActionPerformed

    private void jRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRegistroActionPerformed

            // Abrir la ventana de _02Registro
    _02Registro registro = new _02Registro();
    registro.setVisible(true);

    // Cerrar la ventana actual (_01PantallaInicio)
    this.dispose();
    }//GEN-LAST:event_jRegistroActionPerformed


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

        java.awt.EventQueue.invokeLater(() -> new _01PantallaInicio().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jRegistro;
    // End of variables declaration//GEN-END:variables
}
