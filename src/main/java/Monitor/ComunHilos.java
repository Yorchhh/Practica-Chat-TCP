package Monitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ComunHilos {
	private ArrayList<String> historial = new ArrayList<String>();
    private ArrayList<Socket> sockets = new ArrayList<Socket>();

    public void anadirMensaje(String msg) throws IOException {
        historial.add(msg);

        for (Socket sc : sockets) {
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(msg);
        }
    }

    public void mostrarHistorialMensajes() throws IOException {
        for (Socket sc : sockets) {
            for (String m : historial) {
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF(m);
            }
        }
    }

    public synchronized void anadirCliente(Socket cliente) throws IOException {

        DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
        sockets.add(cliente);
        salida.writeUTF("*Cliente añadido al chat*");   
    }

    public synchronized void eliminarClient(Socket cliente) throws IOException {
        sockets.remove(cliente);
    }
}
