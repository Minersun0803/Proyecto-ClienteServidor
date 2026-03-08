package com.mycompany.proyecto;
/**
 *
 * Persona
 */
public abstract class Persona {
    protected int id;
    protected String Nombre;
    protected String Apellidos;
    protected String Cedula;
    protected String Telefono;
    protected String Correo;
    protected String Ubicacion;

    public Persona(int id, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
