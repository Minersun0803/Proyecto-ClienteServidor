/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Eduardo Corrales
 */
public class ManejadorCliente extends Thread{
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {

            System.out.println("Cliente conectado.");

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

            Random random = new Random();
            int numTicket = random.nextInt(1000, 10000);

            out.writeUTF("¡Hola desde el servidor!\n"
                    + "Su numero de ticket es: " + numTicket); // Enviamos un mensaje al cliente.

            String mensaje = in.readUTF(); // Recibimos una respuesta del cliente.
            System.out.println("La respuesta del cliente es: " + mensaje);

            out.writeUTF("Fin de la conexion, gracias."); // Enviamos un mensaje de cierre al cliente.

            cliente.close();
            System.out.println("Cliente desconectado.");

        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }

}
