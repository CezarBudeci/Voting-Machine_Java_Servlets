import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.AllQuestions;

import java.sql.Statement;

/**
 * It is used for retrieving all questions from the database
 * 
 * @author Cezar
 *
 */

public class SqlConn {
	
	
	public SqlConn() {
		
	}
    

    //get all all questions from the db as arraylist of objects
	
	/**
	 * Retrieves all questions from the database and stores them into an arraylist of objects
	 * @param conn
	 * @return the arraylist or null in case of an error
	 */
    public ArrayList<AllQuestions> getQuestions(Connection conn){
    	ArrayList<AllQuestions> allQuestions = new ArrayList<>();
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM questions");
    		while (rs.next()){
				AllQuestions q = new AllQuestions();
				q.setId(rs.getInt("questionID"));
				q.setQuestion(rs.getString("questionText"));
				allQuestions.add(q);
			}
    		return allQuestions;
    		
    	} catch (Exception e) {
    		return null;
    	}
		
    }
    
    
    
    
 
}


