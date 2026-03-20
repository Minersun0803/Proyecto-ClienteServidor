package Objects;
/**
 *
 * Persona
 */
public abstract class Persona {
    protected String Nombre;
    protected String Apellidos;
    protected String Cedula;
    protected String Telefono;
    protected String Correo;
    protected String Ubicacion;

    public Persona(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Cedula = Cedula;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Ubicacion = Ubicacion;
    }

    

    @Override
    public String toString() {//mostrar
        return super.toString(); 
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
    
    
    
    
    
}
