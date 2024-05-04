import java.sql.*;


public class ConexionBaseDeDatos {
	
	
	 public static void main(String[] args) {
	try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/programacion", "root", "Al2com");
                    System.out.println("Connection succed OK");
                    
                    crearTablaEquipo(con);
	}catch (SQLException e){
                 printSQLException(e);
            }


	 }

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
		public static void crearTablaEquipo(Connection con)throws SQLException{
		    
		    String createString="CREATE TABLE Programacion(alumno_id INT(4) PRIMARY KEY,"
		            + "al_nombre VARCHAR(40) NOT NULL,"
		            + "instituto VARCHAR(40) NOT NULL,"
		            + "poblacion VARCHAR(20) NOT NULL,"
		            + "provincia VARCHAR(20) NOT NULL,"
		            + "nota VARCHAR(20) NOT NULL,"
		            +"COD_POSTAL CHAR(5) NOT NULL)";
		            
		         
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
		}
