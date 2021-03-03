package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import Monitor.ComunHilos;

public class AtiendeCliente extends Thread {
	private Socket socket;
	private String user;
	private ComunHilos chilos;

	public AtiendeCliente(Socket sc, ComunHilos ch) {
		this.socket = sc;
		this.chilos = ch;
		this.user="";
	}

	@Override
	public void run() {
		Scanner kb = new Scanner(System.in);
		DataInputStream entrada;
		DataOutputStream salida;
		try {
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
			
			chilos.anadirCliente(socket);//Cliente añadido a la lista de sockets
			
			chilos.mostrarHistorialMensajes();//Le mostramos el historial de mensajes
			
			user = entrada.readUTF();
			
			String mns = user+":"+entrada.readUTF();
			
			while (!mns.equals(user+":"+"*")) {
				
				System.out.println(mns);
				chilos.anadirMensaje(mns);
				mns= user+":"+entrada.readUTF();
			}
		chilos.eliminarClient(socket);
		System.out.println(user+" se ha desconectado");
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
