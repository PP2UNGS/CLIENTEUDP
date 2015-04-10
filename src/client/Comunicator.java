package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import junit.framework.Assert;

import org.junit.Test;
public class Comunicator
{
	private String ip;
	private String message;
	private int port;
	private String routeToSettings="src/ConnectionParameters";

	public Comunicator(String message) {

		this.message = message;
		autoConnectionSetter(routeToSettings);
	}
	public Comunicator(){}
	private DatagramSocket crearSocket(){
		DatagramSocket socketCliente = null;
		try {
			socketCliente = new DatagramSocket();
		} catch (IOException e)
		{
			System.out.println("Error al crear el objeto socket client");
			System.exit ( 0 );
		}
		return socketCliente;

	}
	/**
	 * Traduce un String a dirección ip del tipo InetAddress
	 * 
	 */
	private InetAddress entenderIp(){
		InetAddress DireccionIP = null;
		try {

			DireccionIP = InetAddress.getByName(ip);
		} catch (IOException e)
		{
			System.out.println("Error al recuperar la IP del proceso");
			System.exit ( 0 );
		}
		return DireccionIP;
	}
	/**
	 * Método encagado de enviar el message del constructor una vez que está seteado ip,mensaje y puerto
	 */
	public String enviarMensaje(){
		String msg=getMensaje();
		try{
			DatagramSocket socketCliente = crearSocket();
			crearPaqueteyEnviar(socketCliente);
			
		}catch(Exception e){
			
		}
		return msg;

	}

	private void crearPaqueteyEnviar(DatagramSocket socketCliente) {
		byte [] datos = new byte[message.getBytes().length];


		datos = message.getBytes();


		DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, entenderIp(), port);
		try {
			socketCliente.send(enviarPaquete);
		} catch (IOException e) {

			e.printStackTrace();
		}
		socketCliente.close();


	}
	
	public int autoConnectionSetter(String urlConnectionSettings){
		int flagOk=0;

		try{
			ArrayList<String>dataConnection=readConnectionSettings(urlConnectionSettings);
			this.ip=dataConnection.get(0);
			this.port=Integer.parseInt(dataConnection.get(1));
			flagOk=1;
		}catch(Exception e){
			flagOk=-1;
		}
		Assert.assertEquals(true, true);
		return flagOk;
		
	}

	private  ArrayList<String> readConnectionSettings(String rutaAlArchivo){
		ArrayList<String> lista=new ArrayList<String>();

		try{

			// Abrimos el archivo
			FileInputStream fstream = new FileInputStream(rutaAlArchivo);//("D:\\Desktop\\Ford.txt");
			// Creamos el objeto de entrada
			DataInputStream entrada = new DataInputStream(fstream);
			// Creamos el Buffer de Lectura
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String strLinea;            
			// Leer el archivo linea por linea
			while ((strLinea = buffer.readLine()) != null)   {
				lista.add(strLinea);
			}
			// Cerramos el archivo
			entrada.close();

		}catch (Exception e){ //Catch de excepciones

		}
		return lista;
	}

	public String getIp() {
		return ip;
	}


	public String getMensaje() {

		return message;
	}

	public int getPuerto() {
		return port;
	}


}

