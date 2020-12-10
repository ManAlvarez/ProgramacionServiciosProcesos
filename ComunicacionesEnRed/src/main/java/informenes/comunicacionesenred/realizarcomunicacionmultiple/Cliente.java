package informenes.comunicacionesenred.realizarcomunicacionmultiple;

import java.io.*;
import java.net.*;
import java.util.Date;

class Cliente {

    static final String HOST = "localhost";
    static final int Puerto = 2000;

    public Cliente() {
        try {
            Socket skCliente = new Socket(HOST, Puerto);
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // TAREAS QUE REALIZA EL CLIENTE
            String datos = flujo_entrada.readUTF();
            long tiempo1 = Long.valueOf(datos);
            Thread.sleep(1000);
            long tiempo2 = (new Date()).getTime();
            System.out.println("\n El tiempo es:" + (tiempo2 - tiempo1));
            //System.out.println(datos);

            skCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Cliente();
    }
}
