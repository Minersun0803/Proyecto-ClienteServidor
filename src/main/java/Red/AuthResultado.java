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
    private int medicoID;

    public AuthResultado() {
    }

    public AuthResultado(boolean valido, String tipoUsuario, String especialidad, int id) {
        this.valido = valido;
        this.tipoUsuario = tipoUsuario;
        this.especialidad = especialidad;
        // Asignar según tipo
        if ("MEDICO".equalsIgnoreCase(tipoUsuario)) {
            this.medicoID = id;
        } else {
            this.pacienteID = id;
        }
    }
    
    public AuthResultado(boolean valido, String tipoUsuario, String especialidad, int pacienteID, int medicoID) {
    this.valido = valido;
    this.tipoUsuario = tipoUsuario;
    this.especialidad = especialidad;
    this.pacienteID = pacienteID;
    this.medicoID = medicoID;
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

    public int getMedicoID() {
        return medicoID;
    }

    public void setMedicoID(int medicoID) {
        this.medicoID = medicoID;
    }

}
