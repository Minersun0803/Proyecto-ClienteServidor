
package Red;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorHospital {

    public static void main(String[] args) {
        //puerto donde se van a escuchar las conexiones
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor Hospital iniciado en el puerto " + puerto);

            //El servidor queda esperando clientes
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());
                //Se crea un hilo nuevo para atender al cliente
                new ManejadorClienteHospital(cliente).start();
            }

        } catch (Exception ex) {
            System.out.println("Error en el servidor: " + ex.getMessage());
        }
    }
}