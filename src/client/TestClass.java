package client;

import java.net.DatagramSocket;

import junit.framework.Assert;

import org.junit.Test;


public class TestClass {

	Encolador  enco=new Encolador();
	Comunicator com=new Comunicator("Hola");
	Comunicator com2=new Comunicator("Chau");
	Comunicator com3=new Comunicator("Pruebo autoconexion");
	Comunicator comEmpty=new Comunicator("AULA VACIA");

	@Test
	public  void autoconnection(){
		Assert.assertEquals(1,com3.autoConnectionSetter("src/ConnectionParameters"));
		Assert.assertEquals("127.0.0.1",com3.getIp());
		Assert.assertEquals(5000,com3.getPuerto());
		Assert.assertEquals(-1,com3.autoConnectionSetter("src/ConnectionPara"));
	}
	@Test //Encolador
	public void encoladorEncolarMensajes(){		
		Assert.assertEquals(1,enco.encolarMensajes(com));
	}
	@Test //Encolador
	public void ejecutarEnvios(){
		int messageCountExpected=2;
		enco.encolarMensajes(com);
		enco.encolarMensajes(com2);		
		Assert.assertEquals(messageCountExpected,enco.ejecutarEnvios());
	}
	//testing cambio en git	
	@Test //Comunicator
	public void readConnectionSettings(){
		Assert.assertEquals("127.0.0.1", com.getIp());
		Assert.assertEquals(5000, com.getPuerto());
	}
	@Test //Comunicator // abarca DatagramSocket socketCliente = crearSocket();
						//		  crearPaqueteyEnviar(socketCliente);
	public void enviarMensaje(){
		Assert.assertEquals(com.getMensaje(), com.enviarMensaje());
	}
	@Test //Comunicator
	public void aulaVacia(){					
			Assert.assertEquals("AULA VACIA",comEmpty.enviarMensaje());		
	}
	//@Test //Encolador
	public void ejecutarEnviosInfinitos(){
		enco.setIntentos(-1);
		enco.setSeconds(2);
		enco.encolarMensajes(com);
		enco.encolarMensajes(com2);		
		Assert.assertEquals(enco.ejecutarEnvios()>1,true);
	}
}
