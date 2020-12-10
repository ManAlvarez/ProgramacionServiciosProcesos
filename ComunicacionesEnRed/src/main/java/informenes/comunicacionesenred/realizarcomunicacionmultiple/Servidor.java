package informenes.comunicacionesenred.realizarcomunicacionmultiple;

import java.io.*;
import java.net.*;
import java.util.Date;

class Servidor extends Thread {

    Socket skCliente;
    static final int Puerto = 2000;

    public Servidor(Socket sCliente) {
        // Inicialización de la hebra
        skCliente = sCliente;
    }

    public static void main(String[] arg) {
        try {
            // Inicio el servidor en el puerto
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);

            while (true) {
                // Se conecta un cliente
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado");
                // Atiendo al cliente mediante un thread
                new Servidor(skCliente).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        //tareas que realiza la hebra
        try {
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICIÓN DEL CLIENTE
            //flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");
            
            // CUERPO DEL ALGORITMO
            long tiempo1=(new Date()).getTime();
            flujo_salida.writeUTF(Long.toString(tiempo1));     

            // Se cierra la conexión
            skCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
