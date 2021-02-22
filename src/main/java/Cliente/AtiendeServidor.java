package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/*Esta clase lee los mensajes d el servidor y los metra por pantalla, porun hilo*/

public class AtiendeServidor extends Thread {
	private Socket socket;

	// Solo escucha y muestra por pabtalla
	public AtiendeServidor(Socket sc) {
		this.socket = sc;

	}

	@Override
	public void run() {
		try {
			//bulbe infinitu para que este escuchando todo el rato y pueda escribir en pantalla los datos recibidos
			while (true) {
				DataInputStream entrada = new DataInputStream(socket.getInputStream());
				System.out.println(entrada.readUTF());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
