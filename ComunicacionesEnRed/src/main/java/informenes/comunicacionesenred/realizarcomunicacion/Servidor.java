/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informenes.comunicacionesenred.realizarcomunicacion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Servidor {

    ServerSocket skServidor;
    private static final int PUERTO = 2000;

    /**
     *
     * @throws IOException
     */
    public Servidor() throws IOException {
        this.skServidor = new ServerSocket(PUERTO);        
        System.out.println("Escucho por el puerto: " + PUERTO);
        for (int nCli = 0; nCli < 3; nCli++) {
            try (Socket sCliente = skServidor.accept()) {
                OutputStream aux = sCliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(aux);
                flujoSalida.writeUTF("Hola cliente"+nCli);
                sCliente.close();
            }
        }          
    }
    
    public static void main( String[] arg ) {
        try {
            new Servidor();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
