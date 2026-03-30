/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;

import java.io.Serializable;

/**
 *
 * @author Eduardo Corrales
 */
public class AuthResultado implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean valido;
    private String tipoUsuario;
    private String especialidad;
    private int pacienteID;

    public AuthResultado() {
    }

    public AuthResultado(boolean valido, String tipoUsuario, String especialidad) {
        this.valido = valido;
        this.tipoUsuario = tipoUsuario;
        this.especialidad = especialidad;
    }
    
        public AuthResultado(boolean valido, String tipoUsuario, String especialidad, int pacienteID) {
        this.valido = valido;
        this.tipoUsuario = tipoUsuario;
        this.especialidad = especialidad;
        this.pacienteID = pacienteID;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }
    
    
}

