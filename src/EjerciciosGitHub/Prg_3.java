package EjerciciosGitHub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author: Álvaro Comenge 
 * @version: 8/02/24
 * @description: Necesitamos crear una pequeña aplicación que gestione los animales de un pequeño zoológico. Cada animal tendrá las siguientes características: Nombre. Edad. Color. TipoComida.
 * CantidadComida (cantidad de comida en Kgs que toma diariamente). Cuidador. Características especiales (en este campo se anotarán características del propio animal como por ejemplo si está enfermo, si está a dieta de algún tipo, etc.).
 * Ubicación. Número interno (codigo identificativos del animal, parecido al DNI).
 * El programa deberá poder Insertar, Modificar y Eliminar animales en la base de datos. Cuando se inserta un animal el sistema deberá de dotarle de un código interno. Se deberán poder realizar consultas como:
 * Listado de animales de una ubicación determinada.
 * Cuántos animales hay por ubicación.
 * Tipo de comida y cantidad de comida que comen los animales.
 * Listado de animales responsabilidad de un cuidador determinado.
 * Algunos cuidadores tienen el título de veterinario. Se necesitaría también saber el número de veterinarios por ubicación.
 */
public class Prg_3 {

	public static void main(String[] args) {
	

			try {
//				se instancia un obgeto de la clase DriverManager pasandole credenciales para establecer conexión.
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Entrega3", "root", "Al2com");
		                    System.out.println("Connection succed OK");

		                   
//		                    crearTablaAnimales(con);
		                    
//		                    cargarAnimalesTabla(con);
		                    
//		                    modificarAnimales(con);
//		                    
//		                    listadoPorUbicacion(con,"Zona Norte");
//		                    
//		                    mostrarCantidadAnimalesEnUbicacion(con);
		                    
		                    mostrarTipoComidaYCantidad(con);
		                    
		                    
		                    
		                    
		                    
		                    
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
	
	
	public static void crearTablaAnimales(Connection con)throws SQLException{
		    
		String createString = "CREATE TABLE IF NOT EXISTS Animales (" +
			    "Nombre VARCHAR(50), " +
			    "Edad INT, " +
			    "Color VARCHAR(20), " +
			    "TipoComida VARCHAR(50), " +
			    "CantidadComida VARCHAR(50), " +
			    "Cuidador VARCHAR(50), " +
			    "CaracteristicasEspeciales TEXT, " +
			    "Ubicacion VARCHAR(50), " +
			    "NumeroInterno INT PRIMARY KEY)";
		            
		         
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

	public static void cargarAnimalesTabla(Connection con)throws SQLException {
		
		String insert = "INSERT INTO Animales (Nombre, Edad, Color, TipoComida, CantidadComida, Cuidador, CaracteristicasEspeciales, Ubicacion, NumeroInterno) VALUES " +
			    "('León', 5, 'Marrón', 'Carnívoro', 15, 'Juan Pérez', 'Grande y majestuoso', 'Zona Norte', 1001), " +
			    "('Tigre', 4, 'Anaranjado', 'Carnívoro', 20, 'Ana Gómez', 'Rápido y ágil', 'Zona Sur', 1002), " +
			    "('Elefante', 10, 'Gris', 'Herbívoro', 30, 'Carlos Díaz', 'Orejas grandes', 'Zona Este', 1003), " +
			    "('Cebra', 3, 'Blanco y negro', 'Herbívoro', 25, 'Laura Fernández', 'Rápida y social', 'Zona Oeste', 1004), " +
			    "('Jirafa', 6, 'Amarillo', 'Herbívoro', 28, 'Miguel Ángel', 'Cuello largo', 'Zona Central', 1005);";
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
	
	
	public static void modificarAnimales(Connection con) throws SQLException {
		
		String update="UPDATE Animales SET Edad =5 WHERE Nombre ='Leon'";
		  Statement stmt = null;
	        try {
	            stmt= con.createStatement();
	            stmt.executeUpdate(update);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            stmt.close();
	        }
	}
	
	
	
	public static void EliminarAnimales(Connection con) throws SQLException {
			
			String update="DELETE FROM Animales WHERE Nombre ='Leon'";
			  Statement stmt = null;
		        try {
		            stmt= con.createStatement();
		            stmt.executeUpdate(update);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }finally{
		            stmt.close();
		        }
		}
	
	
	public static void listadoPorUbicacion(Connection con, String ubicacion) throws SQLException {
		
		
		try {
			 String select = "SELECT Nombre, Edad, Color, TipoComida, CantidadComida, Cuidador, CaracteristicasEspeciales, Ubicacion, NumeroInterno FROM Animales WHERE Ubicacion ='" + ubicacion + "'";
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(select);
	         
	         while (rs.next()) {
	             System.out.println("Nombre: " + rs.getString("Nombre")
	                     + ", Edad: " + rs.getInt("Edad")
	                     + ", Color: " + rs.getString("Color") + ", Tipo de Comida: " + rs.getString("TipoComida")
	                     + ", Cantidad de Comida: " + rs.getDouble("CantidadComida")
	                     + ", Cuidador: " + rs.getString("Cuidador")
	                     + ", Caracteristicas Especiales: " + rs.getString("CaracteristicasEspeciales")
	                     + ", Ubicacion: " + rs.getString("Ubicacion")
	                     + ", Número de Interno: " + rs.getInt("NumeroInterno"));
	         }
		} catch (SQLException e) {
         e.printStackTrace();
     }
		
	}
	
	 public static void mostrarCantidadAnimalesEnUbicacion(Connection con){
	        try {
	            String select="SELECT Ubicacion, COUNT(*) FROM Animales GROUP BY Ubicacion";
	            Statement stmt= con.createStatement();
	            ResultSet rs =stmt.executeQuery(select);
	            while (rs.next()) {
	                System.out.println("Ubicación: " + rs.getString(1) +
	                ", Cantidad: " + rs.getInt(2));
	               
	            }
	        }catch(SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
		 public static void mostrarTipoComidaYCantidad(Connection con){
		        try {
		            String select="Select NumeroInterno, nombre, TipoComida,CantidadComida AS 'Cantidad' FROM animales";
		            Statement stmt= con.createStatement();
		            ResultSet rs =stmt.executeQuery(select);
		            while (rs.next()) {
		                System.out.println("Numero Interno:" + rs.getInt("NumeroInterno") +
		                ", Nombre: " + rs.getString("nombre") +
		                ", Tipo de Comida: " + rs.getString("TipoComida") +
		                ", Cantidad de Comida: " + rs.getDouble(4));
		               
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
		    }
		
	}


