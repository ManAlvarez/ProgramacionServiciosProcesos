/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinanumeroconcurrente;

import java.io.*;
import java.net.*;
import java.util.Random;

class Servidor extends Thread {

    Socket skCliente;
    static final int Puerto = 2000;
    private Random random = new Random();

    public Servidor(Socket sCliente) {
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
        } catch (Exception e) {;
        }
    }

    public void run() {
        try {
            int numero = random.nextInt(100);
            int numeroCliente = -1;
            String cadena = "";

            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICIÓN DEL CLIENTE
            flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");
            while (numeroCliente != numero) {
                flujo_salida.writeUTF("Introduce un número del 0 al 100: ");
                //Recibo el mensaje del cliente.
                cadena = flujo_entrada.readUTF();
                //Compruebo si es un número de una o tres cifras.
                if (cadena.matches("^[0-9]{1,3}$")) {
                    numeroCliente = Integer.parseInt(cadena);
                    if (numeroCliente == numero) {
                        flujo_salida.writeUTF("As acertado el número el número era " + numero);
                        System.out.println("El cliente a acertado");
                        break;
                    } else if (numeroCliente < numero) {
                        flujo_salida.writeUTF("El número del servidor es mayor, intentalo de nuevo.");
                        System.out.println("El cliente introdujo un número menor.");
                    } else {
                        flujo_salida.writeUTF("El número del servidor es menor, intentalo de nuevo.");
                        System.out.println("El cliente introdujo un número mayor.");
                    }
                } else {
                    flujo_salida.writeUTF("Valor incorrecto, vuelva a intentarlo.");
                    System.out.println("El cliente introdujo un valor incorrecto.");
                }
            }
            flujo_salida.writeUTF("Enhorabuena!!!");

            // Se cierra la conexión
            skCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
