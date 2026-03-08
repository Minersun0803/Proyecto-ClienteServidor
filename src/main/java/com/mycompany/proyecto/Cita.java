
package com.mycompany.proyecto;


public class Cita extends Paciente {
    
    private int mes;//random
    private String consulta;
    private int Año;//random
    private int dia;//random
    private String lugar;
    private String hora;
    private String String;
    private String Profecion;
    private String especialidad;
    private Estado_Cuarto EstadoC;

    public Cita(int id, String Nombre, String Apellidos, String Cedula, String Telefono, String Correo, String Ubicacion) {
        super(id, Nombre, Apellidos, Cedula, Telefono, Correo, Ubicacion);
    }//Prueba de git
    
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getString() {
        return String;
    }

    public void setString(String String) {
        this.String = String;
    }

    public String getProfecion() {
        return Profecion;
    }

    public void setProfecion(String Profecion) {
        this.Profecion = Profecion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Estado_Cuarto getEstadoC() {
        return EstadoC;
    }

    public void setEstadoC(Estado_Cuarto EstadoC) {
        this.EstadoC = EstadoC;
    }

}
