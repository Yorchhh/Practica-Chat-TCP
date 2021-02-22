package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	/* La clase cliente lee de la consola y escribe en el socket */

	private static int puerto = 5000;

	public static void main(String[] args) {

		final String host = "localhost";
		try {
			Scanner kb = new Scanner(System.in);
			//Declaramos el socket
			Socket sc = new Socket(host, puerto);
			//establecemos flujo de infomacion
			DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
			//preguntamos cual es el nombre de usuario y lo mostramos
			System.out.println("** ¿USUARIO? **");
			String user = kb.next();
			salida.writeUTF(user);
			//Lanzamos el hilo
			AtiendeServidor servidor = new AtiendeServidor(sc);
			servidor.start();

			while (true) {
				salida.writeUTF(kb.nextLine());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
