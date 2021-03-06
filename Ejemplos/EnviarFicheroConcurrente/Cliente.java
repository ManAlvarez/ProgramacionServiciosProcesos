import java.io.*;
import java.net.*;
import java.util.Scanner;

class Cliente {
    static final String HOST = "localhost";
    static final int Puerto=1500;

    public Cliente( ) {
        try{
            Socket sCliente = new Socket( HOST , Puerto );
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujo_salida= new DataOutputStream(sCliente.getOutputStream());

            // TAREAS QUE REALIZA EL CLIENTE
            String datos=flujo_entrada.readUTF();           
             if (datos.equals("Se ha conectado el cliente de forma correcta")) {
				System.out.println("Conectado correctamente.");
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
            sCliente.close();
        } catch( Exception e ) {
     System.out.println( e.getMessage() );
        }
    }

    public static void main( String[] arg ) {
     new Cliente();
    }
}
