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
    //conecta al servidor local en el puerto 5000
    public ClienteSocket() {
        this("localhost", 5000);
    }

    public ClienteSocket(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }
    //Este es el método más importante: envía una solicitud y recibe una respuesta
    public Respuesta enviarSolicitud(Solicitud solicitud) throws Exception {
        
        
        //  Aquí se crea la conexión con el servidor
        //  También se preparan los canales para enviar y recibir información
        try (Socket socket = new Socket(host, puerto);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {
            // Se envia la solicitud al servidor
            salida.writeObject(solicitud);
            salida.flush();
            // Se espera y recibe respuesta del servidor
            return (Respuesta) entrada.readObject();
        }
    }
}