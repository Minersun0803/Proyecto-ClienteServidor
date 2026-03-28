package Objects;

public class Medico extends Persona {

    private int medicoID;
    private String especialidad;

    //Contructor para mostrar medico 
    public Medico(int medicoID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Especialidad) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo);
        this.medicoID = medicoID;
        this.especialidad = Especialidad;
    }

    //crear medico
    public Medico(String especialidad, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento);
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

}
