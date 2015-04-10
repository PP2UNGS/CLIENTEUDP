package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class DataBaseManager{
	
	private  Connection c;
	private  Statement statemente;
	private  ResultSet resulsete;
	private  String base;
	private  String host;
	private  String cadena;
	private  String driver;
	private  String jdbc;
	private  String user;
	private  String password;



	public DataBaseManager(){

	}

	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getBase() {
		return base;
	}



	public void setBase(String base) {
		this.base = base;
	}



	public String getHost() {
		return host;
	}
	/**
	 * ej "localhost" o "192.168.0.2" una ip x;
	 * 
	 */
	 public void setHost(String host) {
		 this.host = host;
	 }


	 public String getJdbc() {
		 return jdbc;
	 }

	 /**
	  * Para mysql va jdbc:mysql
	  * 
	  */
	 public void setJdbc(String jdbc) {
		 this.jdbc = jdbc;
	 }

	 public String getCadena() {
		 return cadena;
	 }


	 public void setCadena() {
		 this.cadena = getJdbc()+"://"+getHost()+"/"+getBase();
	 }

	 private String getDriver() {
		 return driver;
	 }

	 /**
	  * "com.mysql.jdbc.Driver" para mysql
	  * @param driver
	  */
	 private void setDriver(String driver) {
		 this.driver = driver;
	 }

	 /**
	  * private int conectar(String driver,String cadena,String user,String pass)
	  * 
	  */
	 private int conectar(String driver,String cadena,String user,String pass){
		 int status=0;
		 try{

			 Class.forName(driver);
			 c=DriverManager.getConnection(cadena,user,pass);
			 statemente=c.createStatement();
			 c.setAutoCommit(false);

			 status=1;

		 }catch(ClassNotFoundException e1){
			 System.out.println("Error en los drivers");
			 status=0;

		 }
		 catch(SQLException e2){
			 System.out.println("Error en la conexion");

			 System.out.println(e2.getLocalizedMessage());
			 status=0;


		 }
		 return status;

	 }

	 public int insertarOmodif(String sentenciaSql) {
		 int status=0;
		 DataBaseManager con = new DataBaseManager();
		 System.out.println(sentenciaSql);

		 try {
			 con.conectar(getDriver(),getCadena(), getUser(), getPassword());
			 con.statemente.executeUpdate(sentenciaSql);
			 con.commit();


			 con.desconectar();
			 status=1;

		 } catch (SQLException e) {
			 System.out.println("Error en insertarOmodificar"+e.getMessage());
			 if(e.getMessage().contains("Duplicate entry")){
				 System.out.println("Entrada duplicada cambie la clave primaria e intente de nuevo");
				 JOptionPane.showMessageDialog(null, "Entrada duplicada cambie la clave primaria e intente de nuevo");
				 con.rollBack();
			 }

			 //e.printStackTrace();


			 con.desconectar();
			 status=-1;
		 }

		 catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			 status=-1;

		 }
		 return status;

	 }
     /**
      * Ejecuta una query y devuelve un string
      */
	 public String excecuteQuery(String SentenciaSql) {
		 ResultSet res =null;
		 ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();//creo una matriz
		 String aux=null;

		 DataBaseManager con = new DataBaseManager();


		 try {
			 con.conectar(getDriver(),getCadena(), getUser(), getPassword());
			 con.resulsete=con.statemente.executeQuery(SentenciaSql);
			 res = con.resulsete;
			 ResultSetMetaData rmd = res.getMetaData(); //guardo los datos referentes al resultset


			 while ( res.next()){
				 ArrayList<String> columnas = new ArrayList<String>();
				 for (int i=1; i<=rmd.getColumnCount(); i++) {
					 aux=res.getString(i);            

					 columnas.add(aux);
				 }
				 matriz.add(columnas);
			 }
			 con.desconectar();



		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error en metodosSql.consultar"+e.getMessage());
			 System.out.println("Error en metodosSql.consultar"+e.getMessage());
			 System.out.println(e.getLocalizedMessage());
			 return null;

		 }

		 return matriz.get(0).toString();


	 }




	 public String consultarNombresColumnas(String tabla) {
		 ResultSet res =null;
		 String nombreColumnas=null;


		 DataBaseManager con = new DataBaseManager();
		 String SentenciaSql = "Select * from "+tabla;

		 try {
			 con.conectar(getDriver(),getCadena(), getUser(), getPassword());
			 con.resulsete=con.statemente.executeQuery(SentenciaSql);
			 res = con.resulsete;
			 ResultSetMetaData rmd = res.getMetaData(); //guardo los datos referentes al resultset
			// nombreColumnas= new Object[rmd.getColumnCount()];

			 for(int i=0;i<rmd.getColumnCount();i++){
				 if(nombreColumnas==null){
					 nombreColumnas=rmd.getColumnName(i+1).toUpperCase();
				 }else{
				 nombreColumnas=nombreColumnas+","+rmd.getColumnName(i+1).toUpperCase();
				 }
			 }
			 con.desconectar();




		 } catch (Exception e) {
			 System.out.println("Error en metodosSql.consultar"+e.getMessage());
			 System.out.println(e.getLocalizedMessage());

		 }

		 return nombreColumnas;


	 }



	 /**
	  * String columna=la columna a consultar
	  * String tabla= la tabla en cuestión
	  * String whereCol= referencia del where ej where id = a algo
	  * String iguala= a algo
	  * ej: db.consultarUnaCelda("nombre","materia ","idmateria", "1"));
	  */
	 public String consultarUnaCelda(String columna,String tabla,String whereCol,String iguala) {
		 ResultSet res =null;
		 String resultado=null;
		 ArrayList<String> arreglo = new ArrayList<String>();//creo una matriz
		 String SentenciaSql="Select "+columna+" from "+tabla+" where "+whereCol+"= "+iguala;
		// DataBaseManager con = new DataBaseManager();
		 System.out.println(SentenciaSql);


		 try {
			 this.conectar(getDriver(),getCadena(), getUser(), getPassword());
			 this.resulsete=this.statemente.executeQuery(SentenciaSql);
			 res = this.resulsete;




			 while ( res.next()){

				 resultado=res.getString(1);
			 }
			 this.desconectar();



		 } catch (Exception e) {
			 System.out.println("Error en metodosSql.consultarUnaColumna"+e.getMessage());

		 }

		 return resultado;
	 }

	 public void commit(){
		 try {
			 c.commit();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
	 public Connection getConection() {
		 return c;
	 }



	 public void setConnection(Connection c) {
		 this.c = c;
	 }



	 private void rollBack(){
		 try {
			 c.rollback();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }

	 private void desconectar(){


		 try {
			 if (c != null){
				 c.close();		

			 }
			 else{
				 System.out.println("Ya estaba desconectado");

			 }





		 } catch (SQLException e) {

			 System.out.println("Desconectado incorrecto");
			 e.printStackTrace();
		 }


	 }






	
	 /**
	  * Ejemplo DataBaseManager db=new DataBaseManager();
		db.setearDatosConexion("localhost","pp2","root","root","jdbc:mysql","com.mysql.jdbc.Driver");
	  */
	 public void setearDatosConexion(String ip, String dbase, String user,
			 String pasw, String jdbc, String driver) {
		 setDriver(driver);
		 setHost(ip);
		 setBase(dbase);
		 setUser(user);
		 setPassword(pasw);
		 setJdbc(jdbc);
		 setCadena();
		 

	 }

	

}
