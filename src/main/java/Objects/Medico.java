package Objects;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Medico extends Persona implements Runnable{

    private int medicoID;
    private String especialidad;
    
    private JProgressBar barra;
    private JLabel label;

    //Contructor para mostrar medico nuevo
    public Medico(int medicoID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Especialidad) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, "");
        this.medicoID = medicoID;
        this.especialidad = Especialidad;
    }

    //crear medico
    public Medico(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Especialidad) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, "");
        this.especialidad = Especialidad;
    }
    
    public int getMedicoID() {
        return medicoID;
    }

    public void setMedicoID(int medicoID) {
        this.medicoID = medicoID;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            barra.setValue(i);
            label.setText(getNombre()+ " Atendiendo a un paciente......");
            
            try {
               Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
        }
        label.setText(getNombre() + " Cita finalizada con exito");
    }

}
