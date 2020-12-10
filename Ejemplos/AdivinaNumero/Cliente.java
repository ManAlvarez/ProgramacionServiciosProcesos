import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    static final String HOST = "localhost";
    static final int Puerto = 2000;

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
                System.out.println(cadenaEntrada);
                while (!cadenaEntrada.contains("acertado")) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println(flujo_entrada.readUTF());
                    String cadena = sc.nextLine();
                    flujo_salida.writeUTF(cadena);
                    System.out.println(flujo_entrada.readUTF());
                }
            }
            // Cierro el socket
            sCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Cliente();
    }
}
