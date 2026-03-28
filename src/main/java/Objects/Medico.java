package Objects;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Medico extends Persona {

    private int medicoID;
    private String especialidad;

    //Contructor para mostrar medico 
    public Medico(int medicoID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento, String Especialidad) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento);
        this.medicoID = medicoID;
        this.especialidad = Especialidad;
    }

    //crear medico
    public Medico(String especialidad, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento);
        this.especialidad = especialidad;
    }
    
    // para listar
    public Medico(int medicoID, String nombre, String apellidos, String cedula,
              String telefono, String correo, String especialidad) {
    super(nombre, apellidos, cedula, telefono, correo, null, null, 0);
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

    void setBarra(JProgressBar jProgressBar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setLabel(JLabel jLabel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
