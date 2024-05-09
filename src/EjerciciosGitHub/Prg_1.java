package EjerciciosGitHub;
import java.sql.*;
/************************************************
 * @author Álvaro Comenge Oliver
 * 
 * @fecha 03/05/2024
 * 
 *esta mal tengo que crear un fichero con los datos y meterlos en la base de datos
 * 
 *************************************************/
public class Prg_1 {

	public static void main(String[] args) {
		
		try {
//			se instancia un obgeto de la clase DriverManager pasandole credenciales para establecer conexión.
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "Al2com");
	                    System.out.println("Connection succed OK");
	                    
	                   crearTablaCoches(con);
	                   
	                   incorporarDatosCoches(con);
	                   
	                   
		}catch (SQLException e){
//	                 printSQLException(e);
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
		 * @descripcion crea la estructuta de la tabla coches 
		 * @param con
		 * @throws SQLException
		 */
		public static void crearTablaCoches(Connection con)throws SQLException{
		    
		    String createString="CREATE TABLE coches(Matricula VARCHAR(8) NOT NULL,"
		            + "Marca VARCHAR(40) NOT NULL,"
		            + "Modelo VARCHAR(40) NOT NULL,"
		            + "Color VARCHAR(40) NOT NULL,"
		            + "Anyo INT NOT NULL,"
		            + "Precio INT NOT NULL"+")";
		            
		         
		            Statement stmt=null;
		            
		            try{
		                stmt=con.createStatement();
		                stmt.executeUpdate(createString);
		                
		            }catch(SQLException e){
		                printSQLException(e);
		            }finally{
		                stmt.close();
		            }
		            
		            }
		
		
		/***************
		 * @descripcion introduce los valores en la tabla coche ,metiendolos en un string
		 * con el executeUpdate actualiza el valor de marca modelo color y anyo campo
		 * 
		 * 
		 ***************/
		public static void incorporarDatosCoches(Connection con) throws SQLException {
		    String insertString1 = "INSERT INTO coches (Matricula, Marca, Modelo, Color, Anyo, Precio) VALUES ('8012-CLY', 'RENAULT', 'MEGANE', 'NEGRO', 2003, 2350)";
		    String insertString2 = "INSERT INTO coches (Matricula, Marca, Modelo, Color, Anyo, Precio) VALUES ('5068-GDB', 'VOLKSWAGEN', 'PASSAT', 'GzuS', 2008, 13500)";
		    String insertString3 = "INSERT INTO coches (Matricula, Marca, Modelo, Color, Anyo, Precio) VALUES ('3268-BTN', 'OPEL', 'ASTRA', 'NEGRO', 2002, 2000)";
		    
		    Statement stmt = null;
		    
		    try {
		        stmt = con.createStatement();
		        stmt.executeUpdate(insertString1);
		        stmt.executeUpdate(insertString2);
		        stmt.executeUpdate(insertString3);
		    } catch (SQLException e) {
		        printSQLException(e);
		    } finally {
		        if (stmt != null) {
		            stmt.close();
		        }
		    }
		}
	}
