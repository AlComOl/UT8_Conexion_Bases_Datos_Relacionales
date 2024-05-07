import java.sql.*;


public class ConexionBaseDeDatos {
	
	
	 public static void main(String[] args) {
		 
		 
	try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jugadores", "root", "Al2com");
                    System.out.println("Connection succed OK");
                    
//                      creaTablaEquipo(con);
                    
//                    	creaTablaJugador(con);
//                    
//                    	cargaEquipo(con);
//                    
//                    	cargaJugador(con);
                    
                    	verEquipo(con);
                    
//                    	verJugador(con);
                    
	}catch (SQLException e){
                 printSQLException(e);
            }


	 }
	 	/**
	 	 * 
	 	 * 
	 	 * @descripcion esta funcion sirve para tratar los errores
	 	 * @param ex
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
		   * Creamos una tabla llamada equipo con la clave primaria y las columnas
		   * 
		   * 
		   * @param con
		   * @throws SQLException
		   */
		  public static void creaTablaEquipo(Connection con) throws SQLException {
		        String createString= "CREATE TABLE Equipo (team_id INT(4) PRIMARY KEY, eq_nombre VARCHAR(40) NOT NULL, estadio VARCHAR(40) NOT NULL, población VARCHAR(20) NOT NULL, provincia VARCHAR(20) NOT NULL, cod_postal CHAR(5) NOT NULL)";        

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
		  /**
		   * 
		   * Creamos la tabla jugador con la clave primaria y las columnas
		   * 
		   * @param con
		   * @throws SQLException
		   */
		  public static void creaTablaJugador(Connection con) throws SQLException{
		        String createString = "CREATE TABLE Jugador(player_id INT(4) PRIMARY KEY, team_id INT(4) NOT NULL REFERENCES Equipo(team_id), nombre VARCHAR(40) NOT NULL, dorsal INT(2) NOT NULL, edad INT(2) NOT NULL)";

		        Statement stmt= null;

		        try {
		            stmt= con.createStatement();
		            stmt.executeUpdate(createString);
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally {
		            stmt.close();
		        }
		    }
		
		  public static void cargaEquipo(Connection con) throws SQLException{
		        String insert=" INSERT INTO Equipo (team_id, eq_nombre, estadio, " +
		        "población, provincia, cod_postal ) VALUES " + 
		        "(1, 'ESTEPONIA', 'MONTERROSO', 'ESTEPONIA', 'MALAGA', '29680')," + 
		        "(2,'ALCORCON', 'SANTO DOMINGO','ALCORCON', 'MADRID', '28924')," + 
		        "(3,'PORCUNA', 'SAN CRISTOBAL', 'FORCUNA', 'JAEN', '23790')";
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
		  /*
		   * cargamos la tabla jugador con 3 jugadores
		   * 
		   * 
		   */
		  public static void cargaJugador(Connection con) throws SQLException{ 
		        Statement stmt = null;
		        try{
		            stmt = con.createStatement();
		            String insert = "INSERT INTO Jugador (player_id, team_id, nombre, dorsal, edad) VALUES "+
		                "(1,1, 'JOSE ANTONIO', 1, 42),"+
		                "(2,1, 'IGNACIO', 2, 62),"+
		                "(3,1, 'DIEGO', 3, 20)";
		            stmt.executeUpdate(insert);
		                insert = "INSERT INTO Jugador (player_id, team_id, nombre, dorsal, edad) VALUES "+
		                    "(4,2, 'TURRION', 1, 37),"+
		                    "(5,2, 'LUIS ABEL', 2, 37),"+
		                        "(6,2, 'ISAAC', 3, 40)";
		            stmt.executeUpdate(insert);
		                insert = "INSERT INTO Jugador (player_id, team_id, nombre, dorsal, edad) VALUES "+
		                    "(7,3, 'JUAN FRANCISCO', 1, 33),"+
		                    "(8,3, 'PARRA', 2, 37),"+
		                    "(9,3, 'RAUL', 3, 19)";
		            stmt.executeUpdate(insert);
		        }catch (SQLException e){
		            e.printStackTrace();
		        } finally{
		            stmt.close();
		        }
		    }
		
		  /***
		   * 
		   * La funcion es para ver por consola la tabla equipo 
		   * 
		   * @param con
		   * @throws SQLException
		   */
		  public static void verEquipo(Connection con) throws SQLException{
		        Statement stmt= null;
		        String query= "select EQ_NOMBRE, ESTADIO, POBLACIÓN, PROVINCIA, cod_postal FROM EQUIPO";
		        
		        try {
		            stmt= con.createStatement();
		            ResultSet rs= stmt.executeQuery(query);

		            while(rs.next()){
		                String equipo = rs.getString("EQ_NOMBRE");
		                System.out.println("Equipo: " + equipo);
		                
		                String estadio = rs.getString("ESTADIO");
		                System.out.println("Estadio: " + estadio);

		                String poblacion = rs.getString("POBLACIÓN");
		                System.out.println("Poblacion: " + poblacion);

		                String provincia = rs.getString("PROVINCIA");
		                System.out.println("Provincia: " + provincia);

		                String codigoPostal= rs.getString("cod_postal");
		                System.out.println("Codigo postal:" + codigoPostal);
		                System.out.println("*******************************************************");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }finally{
		            stmt.close();
		        }
		    }
				/**
				 * 
				 * La funcion es para ver por consola la tabla jugador
				 * 
				 * @param con
				 * @throws SQLException
				 */
			 public static void verJugador(Connection con) throws SQLException{
			        Statement stmt= null;
			        String query= "select PLAYER_ID,TEAM_ID ,NOMBRE , DORSAL, EDAD FROM JUGADOR";
			        
			        try {
			            stmt= con.createStatement();
			            ResultSet rs= stmt.executeQuery(query);

			            while(rs.next()){
			                String idJugador = rs.getString("PLAYER_ID");
			                System.out.println("ID JUGADOR: " + idJugador);
			                
			                String idTeam = rs.getString("TEAM_ID");
			                System.out.println("Estadio: " + idTeam);

			                String nombre = rs.getString("NOMBRE");
			                System.out.println("Nombre: " + nombre);

			                String dorsal = rs.getString("DORSAL");
			                System.out.println("Dorsal: " + dorsal);

			                String edad= rs.getString("EDAD");
			                System.out.println("Edad " + edad);
			                System.out.println("*******************************************************");
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }finally{
			            stmt.close();
			        }
			    }

			    public static void modificarEquipo(Connection con) throws SQLException{
			        Statement stmt= null;

			        try {
			            stmt= con.createStatement();
			            String update="UPDATE Equipo SET ESTADIO= 'ALBORAN' WHERE team_id=1";
			            stmt.executeUpdate(update);
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }finally{
			            stmt.close();
			        }
			    }
			
			public static  void modificaEquipo(Connection con) throws SQLException {
				Statement stmt=null;
				try {
					stmt=con.createStatement();
					String update="UPDATE Equipo SET ESTADIO='ALBORAN'WHERE team_id=1";
					stmt.executeUpdate(update);
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					stmt.close();
				}
			}
			
			
			
			
			public static void modificarEdadJugador(Connection con,int cuantoMas) throws SQLException {
				
				Statement stmt=null;
				try {
					stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					String query="SELECT FROM Jugador";
					ResultSet rs=stmt.executeQuery(query);
					while(rs.next()) {
						int i=rs.getInt("edad");
						rs.updateInt("edad", (i+cuantoMas));
						rs.updateRow();
					
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					stmt.close();
				}
		
		}
}