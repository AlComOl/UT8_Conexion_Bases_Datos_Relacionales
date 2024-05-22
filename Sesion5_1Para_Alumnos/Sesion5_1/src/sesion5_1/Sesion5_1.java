/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sesion5_1;
import java.sql.Connection;
import sesion5_1.DAO.Conexion_DB;
/**
 *
 * @author Emiliano
 */
public class Sesion5_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            Conexion_DB _conexion_DB = new Conexion_DB();  
            System.out.println("Abrir conexion");
        Connection _con = _conexion_DB.AbrirConexion();// Abrimos la conexión
            System.out.println("Conexion abierta");
            //programa
            System.out.println("Cerrar conexion");
            _conexion_DB.CerrarConexion(_con);// Abrimos la conexión
            System.out.println("Conexion cerrada");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
