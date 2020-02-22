package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* @see <a href="http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html">Javadoc</a>
* @see <a href="https://docs.oracle.com/javase/8/docs/api/">Javadoc JDK</a>
* @author miguel
* @version 1.0
*
*/

public class Conexion {
    private static String url = "jdbc:hsqldb:hsql://localhost/testdb";    
    private static String driverName = "org.hsqldb.jdbcDriver";
    private static String username = "sa";   
    private static String password = "";
    private static Connection con;
    
    /**
     * Genera una conexion con la base de datos
     * @return devuelve un Objeto de la clase Connection
     */

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
