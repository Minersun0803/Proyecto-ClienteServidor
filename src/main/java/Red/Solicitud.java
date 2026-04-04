/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;

import java.io.Serializable;

public class Solicitud implements Serializable {
    //Permite que este objeto se pueda enviar por red cliente-servidor
    private static final long serialVersionUID = 1L;

    private String accion;
    private Object datos;

    public Solicitud() {
    }
    //Constructor para crear una solicitud cona ccion y datos.
    public Solicitud(String accion, Object datos) {
        this.accion = accion;
        this.datos = datos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}