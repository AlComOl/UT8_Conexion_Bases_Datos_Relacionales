import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Cuenta {

	public static void main(String[] args) {
		try {
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jugadores", "root", "Al2com");
	                    System.out.println("Connection succed OK");
	                    
	                    
//	                    creaTablaCuenta(con);
	                    
//	                    cargaCuenta(con);
	                    
	                    transaccion(con,"jugadores",1,2,500);

	                    
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
	
	public static void creaTablaCuenta(Connection con) throws SQLException {
        String createString= "CREATE TABLE Cuenta (numeroCuenta INT(4) PRIMARY KEY, nombre VARCHAR(40) NOT NULL, saldo INT(10) NOT NULL)";        

        Statement stmt= null;

        try {
            stmt= con.createStatement();
            stmt.executeUpdate(createString);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
	
	public static void cargaCuenta(Connection con) throws SQLException{
        String insert=" INSERT INTO cuenta (numeroCuenta, nombre, saldo) VALUES " + 
        "('1', 'A', '150')," + 
        "('2','B','150')";
        Statement stmt = null;
        try {
            stmt= con.createStatement();
            stmt.executeUpdate(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }
	
	
	
	
	
	public static void transaccion(Connection con, String BDNombre,int cuentaA, int cuentaB, int cantidad) throws SQLException{
		
		String compruebaCuentaA="SELECT saldo FROM " + BDNombre + ".Cuenta WHERE numeroCuenta = " +cuentaA;
				
		String actualizaA= "UPDATE "+BDNombre+ ".Cuenta "+
                "SET SALDO = SALDO - "+cantidad+" where numeroCuenta = "+cuentaA;
		String actualizaB="UPDATE "+BDNombre+ ".Cuenta "+
               "SET SALDO = SALDO + "+cantidad+" where numeroCuenta = "+cuentaB;
		Statement stmt =con.createStatement();
		
		try{
			
			
			ResultSet rs=stmt.executeQuery(compruebaCuentaA);//Resulset tiene el metodo para ejecutar la consulta
			
			rs.next();//el next lo que hace es pasar consulta por consulta 
			
			int saldo=rs.getInt("saldo");//mete la consulta de la etiqueta saldo
			
			if(saldo<cantidad) {
				throw new IllegalArgumentException("Saldo insuficiente en la cuenta "+cuentaA);
			}
			
		con.setAutoCommit(false);
		stmt=con.createStatement();
//		if(stmt.executeUpdate(actualizaA)==0)throw new SQLException();
//		if(stmt.executeUpdate(actualizaB)==0)throw new SQLException();
		stmt.executeUpdate (actualizaA);
		stmt.executeUpdate (actualizaB);
	
		}
		catch (SQLException e){
		e.printStackTrace();
		
		if(con != null){
		try{
		System.err.print("Roll back de la Transacción");
		con.rollback();
		}catch(SQLException ex){
		printSQLException(ex);
		}
		}
		}
		finally{
		stmt.close();
		con.setAutoCommit(true);
		}
		}


	


	
}
