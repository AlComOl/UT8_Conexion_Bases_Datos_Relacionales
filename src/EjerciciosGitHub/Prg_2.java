package EjerciciosGitHub;

/******************************************
 * @autor Álvaro Comenge 
 * 
 * @fecha 24/05/24
 * 
 * @descripcion Entrega 2 
 * 
 * 
 ******************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prg_2 {

	public static void main(String[] args) {

		try {
//			se instancia un obgeto de la clase DriverManager pasandole credenciales para establecer conexión.
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/numeros", "root", "Al2com");
	                    System.out.println("Connection succed OK");
//	                   crear la tabla 
//	                    crearTablaPrimos(con);
//	              		Mostrar cuántos números primos existen
//	                    inprimo(1000,con);
	                    
//	                    Mostrar cuántos números primos existen entre dos numeros que pasamos por valor.
//	                    primosbetween(con,500,1000);
	                    
//	                    Mostrar cuál es el número primo más alto
//	                    primoMayor(con);
	                    
//	                    Mostrar cuántos números primos existen.
//	                    primoCount(con);
	                     
//	                    Mostrar la media de los números primos. 
	                    primoAVG(con);
	                   
		}catch (SQLException e){
	                 printSQLException(e);
	            }
	}
	
	
	
	/**
     * @descripcion Esta función printSQLException en Java
     * imprime información detallada sobre una excepción
     *  SQLException que se ha producido.
     * @param ex
     * 
     */
	public static void printSQLException(SQLException ex){
	    
	    ex.printStackTrace(System.err);
	    System.err.println("SQLState: "+ex.getSQLState());
	    System.err.println("Error Code: "+ex.getErrorCode());
	    System.err.println("Message: "+ex.getMessage());
	    Throwable t= ex.getCause();
	    while (t!=null){
	        System.out.println("Cause: "+t);
	        t=t.getCause();
	    }        
	}
	
	/**
	 * @author acome
	 * @descripcion saca los numeros primos hasta n
	 * @param n
	 */
	public static void inprimo(int n, Connection con)throws SQLException  {
		
		Statement stmt= null;
		
	  try {
		  stmt=con.createStatement();
		  
		for(int i=2;i<=n;i++) {//i menos igual que el numero incrementa i
			int cont=0;//para cada numero iniciamos el contador
			for(int j=1;j<=i;j++) {//j menor igual que i incrementa j
				if(i%j==0) {//si es divisible cuenta
					cont++;
				}
			}
				if(cont==2) {
						
					 String insert = "INSERT INTO primos(num) VALUES(" + i + ")";
				        	stmt.executeUpdate(insert);
				} 
		}
		
	 }catch (SQLException s) {
		            s.printStackTrace();
	 } finally {
         if (stmt != null) {
             try {
                 stmt.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
 }
			
			
			
	
	/**
	 * @descripcion crea la estructuta de la tabla coches 
	 * @param con
	 * @throws SQLException
	 */
	public static void crearTablaPrimos(Connection con)throws SQLException{
	    
	    String createString="CREATE TABLE IF NOT EXISTS primos (num INT PRIMARY KEY)";
	            
	         
	            Statement stmt=null;
	            
	            try{
	                stmt=con.createStatement();//crear Statement
	                stmt.executeUpdate(createString);//crear la tabla
	                System.out.println("La tabla ha sido creada");
	                
	            }catch(SQLException e){
	                printSQLException(e);
	            }finally{
	            	 if (stmt != null) {
	                     try {
	                    	 stmt.close();
	                     }catch (SQLException e) {
	                    	 e.printStackTrace();
	                     }
	
	            	 }
	            }	
			}
	
		public static void primosbetween(Connection con,int min,int max) {
			Statement stmt=null;
			
			try {
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query= "Select * From primos WHERE num BETWEEN "+ min +" AND " +max;
				
				ResultSet r = stmt.executeQuery(query);
				
				while(r.next()) {
					int n=r.getInt("num");
					
					System.out.println(n);
				}
			} catch (Exception e) {
				System.out.println("Error");
				
			}
		}
		public static void primoMayor(Connection con) {
			Statement stmt=null;
			
			try {
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query= "Select MAX(num) FROM primos ";
				
				ResultSet r = stmt.executeQuery(query);
				
				if(r.next()) {
					int n=r.getInt(1);
					
					System.out.println(n);
				}
			} catch (Exception e) {
				System.out.println("Error");
				
			}
		}
		
		public static void primoCount(Connection con) {
			Statement stmt=null;
			
			try {
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query= "Select COUNT(num) FROM primos ";
				
				
				ResultSet r = stmt.executeQuery(query);
				
				if(r.next()) {
					int n=r.getInt(1);
					
					System.out.println(n);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}
		
		public static void primoAVG(Connection con) {
			Statement stmt=null;
			
			try {
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query= "Select AVG(num) AS MEDIA FROM primos";
				
				
				ResultSet r = stmt.executeQuery(query);
				
				if(r.next()) {
					float n=r.getFloat(1);
					
					System.out.println(n);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}
	}
