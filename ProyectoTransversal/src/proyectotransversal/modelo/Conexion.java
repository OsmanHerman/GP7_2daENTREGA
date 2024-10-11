package proyectotransversal.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Osman Herman
 * @author Ulises Perez
 * @author Nahuel Alegre
 * @author Nicolas Dominguez
 */
public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String DB = "universidadulp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection getConexion(){
        if (connection == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                
                connection = DriverManager.getConnection(URL+DB, USUARIO, PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar el Driver " + "'" + ex.getMessage() + "'");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión " + "'" + ex.getMessage() + "'");
            }
        }
        return connection;
    }
}
