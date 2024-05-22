package EjerciciosGitHub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Prg_2 {

	public static void main(String[] args) {
//		inprimo(1000);
		try {
//			se instancia un obgeto de la clase DriverManager pasandole credenciales para establecer conexión.
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/numeros", "root", "Al2com");
	                    System.out.println("Connection succed OK");
	                    
	                    crearTablaPrimos(con);
//	                    
	                    inprimo(1000,con);
	                   
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
						
					 String insert = "INSERT INTO primos VALUES(" + i + ")";
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
	    
	    String createString="CREATE TABLE IF NOT EXISTS num ("+"num INT PRIMARY KEY)";
	            
	         
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
	}
