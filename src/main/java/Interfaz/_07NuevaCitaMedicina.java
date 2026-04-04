package Interfaz;

import Conexiones.ConexionSQL;
import java.util.Random;
import java.sql.*;
import javax.swing.JOptionPane;

/*
_07NuevaCitaMedicina - esta pantalla es donde el paciente puede agendar una cita nueva

en esta pantalla, all abrir y cargar a los medicos desde la base de datos y genera 4 horarios de manera aletoria
el paciente seleciona un horario de la tabla
y preciona agendar para que esta sea agendada en la tabla de citas en la base de datos
*/

public class _07NuevaCitaMedicina extends javax.swing.JFrame {

    private int pacienteID;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(_07NuevaCitaMedicina.class.getName());

    //Lista de medico cargados desde la base de datos
    private java.util.List<Object[]> medicos = new java.util.ArrayList<>();

    //Recibie el paciente id para asocialr al cita neuva ak pacinte correspondiente, pero 
    // primero carga medicos y luego genera los horarios disponibles
    
    public _07NuevaCitaMedicina(int pacienteID) {
        this.pacienteID = pacienteID;
        initComponents();
        setLocationRelativeTo(null);
        cargarMedicos();// carga médicos desde la BD
        cargarArreglos(); //Generacion de los 4 horarios aletorios
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

    //Carga a todos los medicos extuando a los farmaceuticos, desde la base de datos y lis guarda e una lista interna llamda medicos
    //No se muestra la tabla solo se una de manera interma para la asignacion de un medico de manera aletoria a la cita
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

            //Giarda a cada medico como Object[] en la lista
            //[0] es ek medico id (int) para hacer INSERTS de la cita
            //[1] es el nombre del medico (String) por si es necesario llegar a mostrarlo
            while (rs.next()) {
                medicos.add(new Object[]{//tabla medicos
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

    /*
    Genera 4 combinaciones aletoias de día y hora y las muestas en la tabla jtable1
    
    los arreglos definen los valores posibles
    - dias: del 01 al 30
    - horas: de las 09:00 a 17:30 cada hora tiene un intervalo de 30 minutos
    - meses: estos van desde el 01 al 12 (Estos son usado sla contruit la fecha)
    - años: van desde el 2026 al 2030 (usado al construit la fecha)
    
    se utiliza un Set 'usadas' para evitar que aparezan dos filas con el mismo dia y hora.
    */
    
    public void cargarArreglos() {
        //areglos de los dias
            String[] dias = {"01", "02", "03", "04",
                     "05", "06", "07", "08",
                     "09", "10", "11", "12",
                     "13", "14", "15", "16",
                     "17", "18", "19", "20",
                     "21", "22", "23", "24",
                     "25", "26", "27", "28",
                     "29", "30"};

            //Arreglos de las horas
    String[] horas = {
        "09:00", "09:30", "10:00", "10:30",
        "11:00", "11:30", "12:00", "12:30",
        "13:00", "13:30", "14:00", "14:30",
        "15:00", "15:30", "16:00", "16:30",
        "17:00", "17:30"
    };

    //arreglos de los meses
    String[] meses = {"01","02","03","04","05","06",
                      "07","08","09","10","11","12"};
    
    //areglos de los años
    String[] años  = {"2026","2027","2028","2029","2030"};

    //El modelo de la tabla
    javax.swing.table.DefaultTableModel modelo = 
        new javax.swing.table.DefaultTableModel();
    modelo.addColumn("Dia"); //Solo muestara el dia
    modelo.addColumn("Hora"); //Solo muestra la hora

    // Solo 4 combinaciones aleatorias
    Random rand = new Random();
    java.util.Set<String> usadas = new java.util.HashSet<>(); // el HashSet crea una interfaz set y no permite elemtos dublicados u ingnora los elemtos dublicadas
    int count = 0; //contador de veces

    while (count < 4) { //genracion extacta de 4 citas
        String dia  = dias[rand.nextInt(dias.length)];
        String hora = horas[rand.nextInt(horas.length)];
        String clave = dia + hora; // evitar duplicados

        //Solo agregara si la combinacion no a haparecio antes
        if (!usadas.contains(clave)) {
            usadas.add(clave);
            modelo.addRow(new Object[]{dia, hora});
            count++;
        }
    }

    jTable1.setModel(modelo);
}

    private void jAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAtrasActionPerformed

        _06Citas_Paciente citas_Paciente = new _06Citas_Paciente(pacienteID);
        citas_Paciente.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jAtrasActionPerformed

    /*
    Agenda la cita selcionda en la tabla
    
    primero verifiaca que haya medicos dispobnibles
    de segundo verifica que el paciente haya selecionado una fila
    obtiene dia y hora de la fila selecionada
    genera mes y año aletaorimete para completar la fecha
    seleciona un medico aletorimete de la lista 'medicos'
    verifica que el horario no est ay selcionado en la base de datos
    intrta la cita en la tabla cita
    y finalmete regrasa a _06Citas_Paciente donde ya tiene que aparecer la nueva cita
    */
    private void jAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgendarActionPerformed

        //Verifica que se hallan cargado medicos en la base de datos
        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay médicos disponibles.");
            return;
        }

        //Verificacion de que el paciente selciono una fila
        int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un día y hora en la tabla.");
            return;
        }

        // Obtener valores seleccionados de la tabla
        String dia = (String) jTable1.getValueAt(fila, 0);
        String hora = (String) jTable1.getValueAt(fila, 1);

        // Generar año y mes aleatorio
        //contruye la fecha requeriada ne la base de datos
        Random rand = new Random();
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] años = {"2026", "2027", "2028", "2029", "2030"};
        String mes = meses[rand.nextInt(meses.length)];
        String año = años[rand.nextInt(años.length)];
        String fecha = año + "-" + mes + "-" + dia;

        // Seleccionar médico aleatorio
        int indice = rand.nextInt(medicos.size());
        int medicoID = (int) medicos.get(indice)[0];

        //Direcion fija para todas las citas
        String direccionFija = "Hospital Calderon Guardia";

        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            Connection con = conexionSQL.conectarSQL();

            // Verificar disponibilidad del medico a esa hora y dia, asi se evitan conflictos
            //
            String sqlVerificar
                    = "SELECT COUNT(*) FROM Cita WHERE MedicoID = ? AND Fecha = ? AND Hora = ?";
            PreparedStatement psVerificar = con.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, medicoID);
            psVerificar.setString(2, fecha);
            psVerificar.setString(3, hora);
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                //El horario ya esta ocupada, por ende el paciente debe de selecionar otra cita
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
            psInsert.setInt(1, pacienteID); //paciente loqueado
            psInsert.setInt(2, medicoID); //medico asigando aletorimente
            psInsert.setString(3, fecha); // fecha contruida
            psInsert.setString(4, hora); //hora selecionada
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

    //Mustras en consla el dia y la hora selecionados
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
