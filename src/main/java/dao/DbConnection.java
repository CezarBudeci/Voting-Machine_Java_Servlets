package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to make a connection with our DB. We are using singleton pattern for better work with resources (no duplicate connections)
 * @author Jozef
 *
 *
 */
public class DbConnection {
	  private static DbConnection instance;
	  private Connection connection;
	  private String url = "jdbc:mysql:";
	  private String username = "";
	  private String password = "";
	  private DbConnection() throws SQLException {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      this.connection = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException ex) {
	      System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
	    }
	  }
	  public Connection getConnection() {
	    return connection;
	  }
	  public static DbConnection getInstance() throws SQLException {
	    if (instance == null) {
	      instance = new DbConnection();
	    } else if (instance.getConnection().isClosed()) {
	      instance = new DbConnection();
	    }
	    return instance;
	  }

	}