package Objects;

public class Paciente extends Persona {

    private int pacienteID;

    //Constructor para insertar un paciente nuevo
     public Paciente(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento);
    }

    //constructor para mostrar un paciente nuevo
    public Paciente(int pacienteID, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        super(Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion, Contraseña, AñoNacimiento);
        this.pacienteID = pacienteID;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

}
