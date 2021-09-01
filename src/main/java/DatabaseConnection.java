import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used for connecting to the database
 * We used the singleton model
 * 
 * @author Cezar
 *
 */
  
public class DatabaseConnection {
  
    private static Connection con = null;
  
    static
    {
    	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    	final String DB_URL = "jdbc:mysql:";



    	 //  Database credentials
    	final String USER = "";
    	final String PASS = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }

}


