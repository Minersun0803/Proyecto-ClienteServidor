/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Eduardo Corrales
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket servidor = null; //este es el socket del servior
        Socket cliente = null; // Socket del cliente

        DataInputStream in; // Ingreso de mensajes
        DataOutputStream out; // Para salida de mensajes

        try {
            System.out.println("Servidor iniciado...");

            servidor = new ServerSocket(5200);
            // Puertos: [0 - 65535]
            // No usar: [0 - 1023]
            // Recomendado: [5000 y 9000]

            while (true) {
                cliente = servidor.accept();

                System.out.println("Cliente conectado...");

                in = new DataInputStream(cliente.getInputStream());
                out = new DataOutputStream(cliente.getOutputStream());

                Random random = new Random();

                int numeroTicket = random.nextInt(1000, 10000);

                out.writeUTF("Hola desde el servidor, su numero de ticket es: " + numeroTicket);

                String mensaje = in.readUTF(); //Recibimos una respuesta del cliente
                System.out.println("La respuesta del clinte es: " + mensaje);

                out.writeUTF("Fin de la conexion"); //enviamos un mensaje de cierre

                cliente.close();
                System.out.println("Cliente desconectado");
            }

        } catch (Exception ex) {
            System.out.println("Exception:  " + ex.getMessage());
        }

    }
//    public static void broadcast() {
//        for (ManejadorCliente cliente : clientes) {
//            try {
//                cliente.enviar("REFRESCAR");
//            } catch (Exception ex) {
//                System.out.println("Error en broadcast");
//            }
//        }
//    }
}
