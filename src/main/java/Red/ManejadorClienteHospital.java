/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;

import Conexiones.PacienteDAO;
import Conexiones.PersonaDAO;
import Objects.Paciente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Eduardo Corrales
 */
public class ManejadorClienteHospital extends Thread {

    private Socket socket;

    public ManejadorClienteHospital(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            Solicitud solicitud = (Solicitud) entrada.readObject();
            Respuesta respuesta = procesarSolicitud(solicitud);

            salida.writeObject(respuesta);
            salida.flush();

        } catch (Exception ex) {
            System.out.println("Error atendiendo cliente: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
                System.out.println("No se pudo cerrar el socket.");
            }
        }
    }

    private Respuesta procesarSolicitud(Solicitud solicitud) {
        try {
            if (solicitud == null || solicitud.getAccion() == null) {
                return new Respuesta(false, "Solicitud invalida.", null);
            }

            switch (solicitud.getAccion()) {
                case "LOGIN":
                    return login((Map<String, String>) solicitud.getDatos());

                case "REGISTRAR_PACIENTE":
                    return registrarPaciente((Paciente) solicitud.getDatos());

                default:
                    return new Respuesta(false, "Accion no soportada por el servidor.", null);
            }

        } catch (Exception ex) {
            return new Respuesta(false, "Error procesando solicitud: " + ex.getMessage(), null);
        }
    }

    private Respuesta login(Map<String, String> datos) {
        try {
            String cedula = datos.get("cedula");
            String contrasena = datos.get("contrasena");

            PersonaDAO personaDAO = new PersonaDAO();
            AuthResultado resultado = personaDAO.iniciarSesionClienteServidor(cedula, contrasena);

            if (resultado.isValido()) {
                return new Respuesta(true, "Inicio de sesion correcto.", resultado);
            }

            return new Respuesta(false, "Usuario o contraseña incorrectos.", resultado);

        } catch (Exception ex) {
            return new Respuesta(false, "No se pudo iniciar sesion: " + ex.getMessage(), null);
        }
    }

    private Respuesta registrarPaciente(Paciente paciente) {
        try {
            PacienteDAO dao = new PacienteDAO();
            boolean agregado = dao.AgregarPaciente(paciente);

            if (agregado) {
                return new Respuesta(true, "Paciente registrado con exito.", null);
            }

            return new Respuesta(false, "No se pudo registrar el paciente.", null);

        } catch (Exception ex) {
            return new Respuesta(false, "Error al registrar paciente: " + ex.getMessage(), null);
        }
    }
}