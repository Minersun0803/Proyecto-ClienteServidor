package Objects;

public class Medico extends Persona {

    private int medicoID;
    private String especialidad;

    //Contructor para insertar un medico nuevo

    public Medico(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion);
    }


    //Contructor para mostrar medico nuevo
    public Medico(int medicoID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Especialidad) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, "");
        this.medicoID = medicoID;
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

}
