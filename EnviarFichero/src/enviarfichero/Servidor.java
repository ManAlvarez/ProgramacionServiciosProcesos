/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviarfichero;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static final int Puerto = 1500;

    public Servidor() {
        try {
            // Inicio la escucha del servidor en un determinado puerto.
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);
            // Espero a que se conecte un cliente y creo un nuevo socket para el cliente.
            Socket sCliente = skServidor.accept();
            // ATENDER PETICIÓN DEL CLIENTE
            //Creo un stream de entrada.
            InputStream in = sCliente.getInputStream();
            //Creo un flujo de entrada.
            DataInputStream flujo_entrada = new DataInputStream(in);
            //Creo un stream de salida.
            OutputStream out = sCliente.getOutputStream();
            //Creo un flujo de salida.
            DataOutputStream flujo_salida = new DataOutputStream(out);
            //Envio un mensaje al cliente.
            flujo_salida.writeUTF("Conectado");
            //Declaro el fichero
            File fichero = new File(flujo_entrada.readUTF());
            //Compruebo que exista el archivo.
            if (fichero.exists()) {
                //Creo un flujo de entrada para el fichero.
                InputStream ficheroIn = new FileInputStream(fichero);
                //Creo el buffer de entrada
                BufferedReader reader = new BufferedReader(new InputStreamReader(ficheroIn));
                //voy leyendo linea a linea
                String linea;
                while ((linea = reader.readLine()) != null) {
                    flujo_salida.writeUTF(linea);
                }
                System.out.println("Fichero enviado con éxito!!!");
            } else {
                flujo_salida.writeUTF("El fichero no existe!!!");
            }
            // Cierro el socket
            sCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
