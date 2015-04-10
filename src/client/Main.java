package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import junit.framework.Assert;


public class Main {
/**
 * Conecta con pantalla y envía datos
 *//*
	public static void probarEnviosProgramadosInfinitos(){
		ParametroConexionSocket para=new ParametroConexionSocket();
		para.setIp("255.255.255.255");
		para.setPort(5000);
		Scheduler sh = new Scheduler();
		sh.setSeconds(3);		
		sh.setIntentos(-1);
		String mensaje = "Mensaje1";			
		String mensaje2 = "Mensaje2";
		sh.encolarMensajes(para,mensaje);
		sh.encolarMensajes(para,mensaje2);
		sh.ejecutarEnvios();
	}
	*//**
	 * Conecta con BD local o remota (y el diseño)
	 *//*
	public static void probarComunicacionBase(){
		DataBaseManager db = new DataBaseManager();
		db.setearDatosConexion("localhost","pp2","root","root","jdbc:mysql","com.mysql.jdbc.Driver");
		System.out.println(db.consultarUnaCelda("nombre","materia ","idmateria", "1"));
		System.out.println(db.consultarNombresColumnas("materia"));
	}
	*//**
	 * Reenvía hasta 3 veces con timer adaptativo
	 * @param args
	 *//*
	public static void probarEnviosProgramados3Intentos(){
		ParametroConexionSocket para=new ParametroConexionSocket();
		para.setIp("192.168.0.2");
		para.setPort(8888);
		Scheduler sh = new Scheduler();
		sh.setSeconds(1);		
		sh.setIntentos(1);
		String mensaje = "AULA 7010";			
		//String mensaje2 = "Mensaje2";
		sh.encolarMensajes(para,mensaje);
		//sh.encolarMensajes(para,mensaje2);
		sh.ejecutarEnvios();
		
	}
	
	*/
	public static void main(String[] args) {
		
	
	
		
		
		
		
	}

}
