/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviarfichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    static final String HOST = "localhost";
    static final int Puerto = 1500;

    public Cliente() {
        try {
            // Me conecto al servidor en un detrminado puerto.
            Socket sCliente = new Socket(HOST, Puerto);
            //Creo un stream de entrada.
            InputStream in = sCliente.getInputStream();
            //Creo un flujo de entrada.
            DataInputStream flujo_entrada = new DataInputStream(in);
            //Creo un stream de salida.
            OutputStream out = sCliente.getOutputStream();
            //Creo un flujo de salida.
            DataOutputStream flujo_salida = new DataOutputStream(out);
            //Recivo mensajes del servidor
            String cadenaEntrada = flujo_entrada.readUTF();
            if (cadenaEntrada.equals("Conectado")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Introduce la ruta del fichero: ");
                String cadena = sc.nextLine();
                flujo_salida.writeUTF(cadena);
                while (true) {
                    try {
                        System.out.println(flujo_entrada.readUTF());
                    } catch (IOException e) {
                        break;
                    }
                }
            }
            // Cierro el socket
            sCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
