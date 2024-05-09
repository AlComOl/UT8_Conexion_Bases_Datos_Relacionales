import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Jugador_Fran {

    public static void main(String[] args) {

        String user = "root";
        String password="admin";
        Connection conexion1 = null; 
        String url1= "jdbc:mysql://localhost:3306/jugadores?user= " + user + "&password=" + password;

        try{
            conexion1= DriverManager.getConnection(url1);
            if(conexion1 != null){
                System.out.println("Conexion 1 satisfactoria");
            }
            //creaTablaEquipo(conexion1);
            //creaTablaJugador(conexion1);
            //cargaEquipo(conexion1);
            //cargaJugador(conexion1);
            //verEquipo(conexion1);
            //verJugador(conexion1);
            //modificarEquipo(conexion1);
            //modificarJugador(conexion1, 1);
            //insertaJugador(conexion1,10 , 2, "PEPE CASTRO", 4, 33 );
            //insertaEquipo(conexion1, 0, "paco's team", "maracana", "Villabajo", "Villarriba", 0505);
            getJugadores(conexion1);
            //getJugadoresProc(conexion1);
        }catch (Exception e) {
                System.out.println("Error de conexión, verifique si el user o el password son incorrectos");
                e.printStackTrace();
        }
    }   

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

    public static void modificarJugador(Connection con, int cuantoMas) throws SQLException{

        Statement stmt= null;

        try {
            //stmt= con.createStatement();
            stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query="SELECT * FROM Jugador";
            ResultSet rs= stmt.executeQuery(query);

        while(rs.next()){
            int i = rs.getInt("edad");
            rs.updateInt("edad", (i+cuantoMas));
            rs.updateRow();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    public static void insertaJugador(Connection con, int player_id, int team_id, String nombre,  int dorsal, int edad) throws SQLException {
        
        Statement stmt= null;
        try {
            stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query= "Select * From Jugador";
            ResultSet rs = stmt.executeQuery(query);

            rs.moveToInsertRow();
            rs.updateInt("player_id", player_id);
            rs.updateInt("team_id", team_id);
            rs.updateString("nombre", nombre);
            rs.updateInt("dorsal", dorsal);
            rs.updateInt("edad", edad);
            rs.insertRow();//se insertan datos en el reultset y en la BD
            rs.beforeFirst();//coloca el cursor antes de la primera fila 
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }
    
    public static void insertaEquipo(Connection con, int team_id, String eq_nombre, String estadio, String población, String provincia, int cod_postal) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM Equipo";
            ResultSet rs = stmt.executeQuery(query);
    
            rs.moveToInsertRow();
            rs.updateInt("team_id", team_id);
            rs.updateString("eq_nombre", eq_nombre);
            rs.updateString("estadio", estadio);
            rs.updateString("población", población);
            rs.updateString("provincia", provincia);
            rs.updateInt("cod_postal", cod_postal);
            rs.insertRow();
            rs.beforeFirst(); // colocar el cursor antes de la primera fila
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public static void getJugadores(Connection con) throws SQLException {
        Statement stmt = null;
        String query ="select dimeCuantos() from jugador";
        
        try{
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String cuantos;
        rs.next();
        cuantos = rs.getString(1);
        System.out.println("Existen " + cuantos + " jugadores en nuestra BD");
        System.out.println("**************************************");
        
        }
        catch (SQLException e){
        e.printStackTrace();
        } finally {
        stmt.close();
        }
    } 
    
    public static void getJugadoresProc(Connection con) throws SQLException {
        CallableStatement cs = null;
        try{
        cs = con.prepareCall("{call cuantosJugadores (?)}"); 
            //CallableStatement para llamar a la BD
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        int cuantos = cs.getInt(1);
        System.out.println("Existen " + cuantos + " jugadores en nuestra BD");
        System.out.println("**************************************");
        
        }
        catch (SQLException e){
            e.printStackTrace();
        } finally {
        cs.close();
        }
    }
}
