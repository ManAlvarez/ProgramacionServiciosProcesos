/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informenes.comunicacionesenred.realizarcomunicacion;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Cliente {

    private static final int PUERTO = 2000;
    private static final String HOST = "localhost";

    public Cliente() throws IOException {
        Socket sCliente = new Socket(HOST, PUERTO);
        InputStream aux = sCliente.getInputStream();
        DataInputStream flujo_entrada = new DataInputStream(aux);
        System.out.println(flujo_entrada.readUTF());
        sCliente.close();
    }
    
    public static void main( String[] arg ) {
        try {
            new Cliente();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
