package client;

import java.util.ArrayList;
import java.util.List;

public class Encolador {
	
private long seconds;
	
	private int intentos=1;
	private List<Comunicator> tasks = new ArrayList<Comunicator>();


	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public long getSeconds() {
		return seconds;
	}
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

	public Encolador(){
		
	}
	
	
	public int encolarMensajes(Comunicator com){
		int flag=0;
		try{
			tasks.add(com);
			flag=1;
		}catch(Exception e){
			flag=-1;
		}
		return flag;
	}
	
	/**
	 * Si intentos es igual a -1 es infinito por default es 1
	 */
	public int ejecutarEnvios(){
		int messagesSent=0;
		for(int j=0;j<getIntentos()||getIntentos()==-1;j++){
		for(int i=0;i<tasks.size();i++){
			
			try {
				Thread.sleep(getSeconds()*1000);
			} catch (InterruptedException e) {
				
				System.out.println("No pude esperar "+e.getMessage());
			}
			
			tasks.get(i).enviarMensaje();
			messagesSent++;
			if(i==tasks.size() && getIntentos()==-1){
				ejecutarEnvios();
			}
			
		}
		}
		return messagesSent;
	}
	
	public List<Comunicator> getTasks() {
		return tasks;
	}


	public void setTasks(List<Comunicator> tasks) {
		this.tasks = tasks;
	}


	
}
