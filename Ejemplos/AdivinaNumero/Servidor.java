import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor {

    static final int Puerto = 2000;
    private Random random = new Random();

    public Servidor() {
        int numero = random.nextInt(100);
        int numeroCliente = -1;
        String cadena = "";
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
            // Cierro el socket
            sCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
