package Objects;


public abstract class Persona {
        

    protected int IDPersona;
    protected String Nombre;
    protected String Apellidos;
    protected String Cedula;
    protected String Telefono;
    protected String Correo;
    protected String Ubicacion;
    protected String Contraseña;
    protected int AñoNacimiento;

    //mostar persona
    public Persona(int IDPersona, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        this.IDPersona = IDPersona;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Cedula = Cedula;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Ubicacion = Ubicacion;
        this.Contraseña = Contraseña;
        this.AñoNacimiento = AñoNacimiento;
    }
    
    //Crear persona
    public Persona(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion, String Contraseña, int AñoNacimiento) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Cedula = Cedula;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Ubicacion = Ubicacion;
        this.Contraseña = Contraseña;
        this.AñoNacimiento = AñoNacimiento;
    }
    
    //Mostar persona medico
    public Persona(String Nombre, String Apellidos, String Cedula, String Telefono, String Correo) {
    this.Nombre = Nombre;
    this.Apellidos = Apellidos;
    this.Cedula = Cedula;
    this.Telefono = Telefono;
    this.Correo = Correo;
}



    

    @Override
    public String toString() {//mostrar
        return Nombre + " " + Apellidos;
    }

    public int getIDPersona() {
        return IDPersona;
    }

    public void setIDPersona(int IDPersona) {
        this.IDPersona = IDPersona;
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

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public int getAñoNacimiento() {
        return AñoNacimiento;
    }

    public void setAñoNacimiento(int AñoNacimiento) {
        this.AñoNacimiento = AñoNacimiento;
    }

    
    
}