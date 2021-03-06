package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Monitor.ComunHilos;

public class Servidor {

	private static int puerto = 5000;

	public static void main(String[] args) {

		while (true) {
			ServerSocket servidor;
			ComunHilos chilos= new ComunHilos();
			try {

				servidor = new ServerSocket(puerto);
				System.out.println("Servidor Iniciado");

				while (true) {
					//Aceptamos e iniciamos 
					Socket sc = servidor.accept();
					System.out.println("Cliente conectado");
					//Lanzamos hilo y corremos
					AtiendeCliente cliente = new AtiendeCliente(sc, chilos);
					cliente.start();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
