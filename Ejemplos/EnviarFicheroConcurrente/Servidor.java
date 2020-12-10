import java.io.*;
import java.net.*;

class Servidor extends Thread {

    Socket skCliente;
    static final int Puerto = 1500;

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
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICIÓN DEL CLIENTE
            flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");
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

            // Se cierra la conexión
            skCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
