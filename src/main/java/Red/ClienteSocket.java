/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Eduardo Corrales
 */
public class ClienteSocket {

    private String host;
    private int puerto;

    public ClienteSocket() {
        this("localhost", 5000);
    }

    public ClienteSocket(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public Respuesta enviarSolicitud(Solicitud solicitud) throws Exception {
        try (Socket socket = new Socket(host, puerto);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            salida.writeObject(solicitud);
            salida.flush();

            return (Respuesta) entrada.readObject();
        }
    }
}