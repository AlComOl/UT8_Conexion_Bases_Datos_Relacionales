package EjerciciosGitHub;
/************************************************
 * @author Álvaro Comenge Oliver
 * 
 * @fecha 03/05/2024
 * 
 *esta mal tengo que crear un fichero con los datos y meterlos en la base de datos
 * 
 *************************************************/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Prg_1_bis {

	public static void main(String[] args) throws FileNotFoundException{
		String m[] =new String[6];
		
		try {
//			se instancia un obgeto de la clase DriverManager pasandole credenciales para establecer conexión.
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "Al2com");
	                    System.out.println("Connection succed OK");
	                    
	                    crearTablaCoches(con);
	                    leerFichero(con,"E1.txt");
	                    
	                    InsertdatosTabla(con,m);
	                   
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
				/**
				 * Inserta los datos en la tabla 
				 * @throws SQLException *******************************
				 * 
				 * 
				 **********************************/
				public static void InsertdatosTabla(Connection con, String m[]) throws SQLException {
					
					
					String m1 = m[0];
					String ma = m[1];
					String mo = m[2];
					String co = m[3];

					int any = 0; // Valor predeterminado en caso de que m[4] sea nulo o esté vacío
					int p = 0;   // Valor predeterminado en caso de que m[5] sea nulo o esté vacío

					if (m[4] != null && !m[4].isEmpty()) {//garantiza que m[4] no sea nulo y que tenga al menos un caracter antes de pasarlo a entero.
						any = Integer.parseInt(m[4]);
					}

					if (m[5] != null && !m[5].isEmpty()) {
						 p = Integer.parseInt(m[5]);
					}
//					comando SQL introducir los valores del fichero	
					 String insert=" INSERT INTO coches (Matricula, Marca,Modelo,Color,Anyo,Precio) VALUES "
					 		+ "('" + m1 + "', '" + ma + "', '" + mo + "', '" + co + "', '" + any + "', '" + p + "')";

					 				Statement stmt=null;
						       
						        try {
						        	 stmt = con.createStatement();
						        	 stmt.executeUpdate(insert);
						       
						        } catch (Exception e) {
						            e.printStackTrace();
						        }
					}
					
					
					
				
				
				/**
				 * Lee el fichero de texto que pasamos en la funcion
				 * 
				 * @throws FileNotFoundException 
				 * 
	      		 */
				public static void leerFichero(Connection con,String fichero)throws SQLException, FileNotFoundException{
					
					FileReader file = new FileReader(fichero);
					BufferedReader  br= new BufferedReader (file);
					
					String line;
					int lng;
					
					try {
						 while ((line = br.readLine()) != null) {
							 lng=line.split(" ").length;//numero de palabras.
							 
							 if(lng==6) {
								 
								 	String[] m =new String[6];
//								 dividimos los datos por los espacios
								 	 m[0]=line.split(" ")[0];
									 m[1]=line.split(" ")[1];
									 m[2]=line.split(" ")[2];
									 m[3]=line.split(" ")[3];
									 m[4]=line.split(" ")[4];
									 m[5]=line.split(" ")[5];
								
									// Llamar a la función para insertar los datos en la tabla
						                InsertdatosTabla(con, m);			
							 }
							 
						 }
					
						
					} catch (Exception e) {
						
					}
				}
				
				}


