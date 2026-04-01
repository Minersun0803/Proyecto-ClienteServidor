package Objects;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Medico extends Persona implements Runnable {

    private int medicoID;
    private String especialidad;
    private JProgressBar barra;
    private JLabel lbEstado;

    //Contructor para mostrar medico 
    public Medico(int medicoID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento, String Especialidad, String Genero) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento, Genero);
        this.medicoID = medicoID;
        this.especialidad = Especialidad;
    }

    //crear medico
    public Medico(String especialidad, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento, String Genero) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento, Genero);
        this.especialidad = especialidad;
    }
    
    // para listar
    public Medico(int medicoID, String nombre, String apellidos, String cedula,
              String telefono, String correo, String especialidad, String genero) {
    super(nombre, apellidos, cedula, telefono, correo, null, null, 0, genero);
    this.medicoID = medicoID;
    this.especialidad = especialidad;
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

    public void setBarra(JProgressBar barra) {
        this.barra=barra;
    }

    public void setLabel(JLabel label) {
        this.lbEstado=label;
    }
    
    private void actualizarlabel(String texto, Color color){
        if (lbEstado!=null) {
            lbEstado.setText(texto);
            lbEstado.setForeground(color);
        }
    }
    
    @Override
    public void run() {
        actualizarlabel(getNombre()+"Atendiendo al paciente.....", new Color(27,22,4));
        int tiempototal=5000;
        barra.setMinimum(0);
        barra.setMaximum(tiempototal);
        
        try {
            for (int i = 0; i < tiempototal; i+= 100) {
                Thread.sleep(100);
                barra.setValue(i);
            }
            barra.setValue(tiempototal);
            actualizarlabel(getNombre()+"ha terminado la consulta",new Color(27,157,15));
            
        }catch(InterruptedException ex) {
            System.out.println("Error: "+ ex.getMessage());
        } 
    }
    
    

}
