package Objects;

public class Cita extends Paciente {

    private int mes;//random
    private String consulta;
    private int año;//random
    private int dia;//random
    private String lugar;
    private String hora;
    private String String;
    private String profesion;
    private String especialidad;

    public Cita(String nombre, String apellidos, String cedula, String telefono, String correo, String ubicacion,
            String contraseña, int añoNacimiento,
            int mes, int dia, int anio, String consulta, String lugar, String hora,
            String profesion, String especialidad) {
        super(nombre, apellidos, cedula, telefono, correo, ubicacion, contraseña, añoNacimiento);
        this.mes = mes;
        this.dia = dia;
        this.año = año;
        this.consulta = consulta;
        this.lugar = lugar;
        this.hora = hora;
        this.profesion = profesion;
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

    public int getaño() {
        return año;
    }

    public void setaño(int año) {
        this.año = año;
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

    public String getprofesion() {
        return profesion;
    }

    public void setprofesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
