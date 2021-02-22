package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AtiendeCliente extends Thread {
	private Socket socket;
	private String user;

	public AtiendeCliente(Socket sc) {
		this.socket = sc;
	}

	@Override
	public void run() {
		Scanner kb = new Scanner(System.in);
		DataInputStream entrada;
		DataOutputStream salida;
		try {
			//Flujo de datos
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
			//leemos primero cual sera el nombre del usuario
			user = entrada.readUTF();
			//ahora realizamos un bucle infinito para que este escuacho todo el rato(entrada de datos)
			// y pueda imprimir en consola los mensajes(salida de datos)
			while (true) {
				String mns = entrada.readUTF();
				System.out.println(user + ": " + mns);
				salida.writeUTF(user + ": " + mns);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
