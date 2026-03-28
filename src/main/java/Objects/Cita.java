
package Objects;


public class Cita extends Paciente {
    
    private int mes;//random
    private String consulta;
    private int Año;//random
    private int dia;//random
    private String lugar;
    private String hora;
    private String String;
    private String Profesion;
    private String especialidad;

public Cita(String nombre, String apellidos, String cedula, String telefono, String correo, String ubicacion,
            int mes, int dia, int anio, String consulta, String lugar, String hora,
            String profesion, String especialidad) {
    super(nombre, apellidos, cedula, telefono, correo, ubicacion);
    this.mes = mes;
    this.dia = dia;
    this.Año = anio;
    this.consulta = consulta;
    this.lugar = lugar;
    this.hora = hora;
    this.Profesion = profesion;
    this.especialidad = especialidad;
}
    
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

    public String getProfesion() {
        return Profesion;
    }

    public void setProfesion(String Profesion) {
        this.Profesion = Profesion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }



}
