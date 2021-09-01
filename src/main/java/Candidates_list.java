import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.CandidateObj;
import data.CountOfCand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * This servlet retrieves the cound of candidates and stores it in a list.
 * It also retrieves id, firstname and lastname of the candidates and stores them in a list
 * It sends these lists of objects as attributes to the according jsp file
 * 
 * 
 * @author Cezar
 *
 */

@WebServlet(
    name = "CandidatesList",
    urlPatterns = {"/candidates_list"}
)


public class Candidates_list extends HttpServlet {

	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {

		 response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    //connects to db
		    Connection conn =  DatabaseConnection.getConnection(); 
		    List<CandidateObj> candidateList =  new ArrayList<CandidateObj>();//list of candidate objects
		    List<CountOfCand> candCount =  new ArrayList<CountOfCand>();//list of nr of candidates objects
		    
		    try {
		    	String sql = "SELECT COUNT(politicID) AS totalCan FROM politicians WHERE role = 'candidate'";
		    	Statement st = conn.createStatement();
		    	ResultSet results = st.executeQuery(sql);
		    	
		    	while(results.next()) {
		    		CountOfCand candCount1 = new CountOfCand();
		    		candCount1.setCountNr(results.getInt("totalCan"));
		    		candCount.add(candCount1);
		    	}//retrieves the number of candidates, stores it in an object, the object in a list
		    } catch(SQLException e) {
		    	e.printStackTrace();
		    }
		    
		    
		    try {
		    	String sql = "SELECT politicID, firstname, lastname FROM politicians WHERE role = 'candidate'";
		    	Statement st = conn.createStatement();
		    	ResultSet results = st.executeQuery(sql);
		    	
		    	while(results.next()) {
		    		CandidateObj candidate = new CandidateObj();
		    		candidate.setId(results.getInt("politicId"));
		    		candidate.setFname(results.getString("firstname"));
		    		candidate.setLname(results.getString("lastname"));
		    		candidateList.add(candidate);
		    		
		    	}//retrieves id, fname, lname of candidates, stores it in an object, the object in a list
		    } catch(SQLException e) {
		    	e.printStackTrace();
		    }
		    
		    request.setAttribute("nrOfCands", candCount);
		    request.setAttribute("allCands", candidateList);

		    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
		    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Candidates_list.jsp");
			rd.forward(request, response);
	 }
	 
	 @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {

		 response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  //connects to db
		    Connection conn =  DatabaseConnection.getConnection(); 
		    List<CandidateObj> candidateList =  new ArrayList<CandidateObj>();//list of candidate objects 
		    List<CountOfCand> candCount =  new ArrayList<CountOfCand>();//list of nr of candidates objects
		    
		    try {
		    	String sql = "SELECT COUNT(politicID) AS totalCan FROM politicians WHERE role = 'candidate'";
		    	Statement st = conn.createStatement();
		    	ResultSet results = st.executeQuery(sql);
		    	
		    	while(results.next()) {
		    		CountOfCand candCount1 = new CountOfCand();
		    		candCount1.setCountNr(results.getInt("totalCan"));
		    		candCount.add(candCount1);
		    	}
		    } catch(SQLException e) {
		    	e.printStackTrace();
		    }
		    
		    
		    try {
		    	String sql = "SELECT politicID, firstname, lastname FROM politicians WHERE role = 'candidate'";
		    	Statement st = conn.createStatement();
		    	ResultSet results = st.executeQuery(sql);
		    	
		    	while(results.next()) {
		    		CandidateObj candidate = new CandidateObj();
		    		candidate.setId(results.getInt("politicId"));
		    		candidate.setFname(results.getString("firstname"));
		    		candidate.setLname(results.getString("lastname"));
		    		candidateList.add(candidate);
		    		
		    	}//retrieves id, fname, lname of candidates, stores it in an object, the object in a list
		    } catch(SQLException e) {
		    	e.printStackTrace();
		    }
		    
		    request.setAttribute("nrOfCands", candCount);
		    request.setAttribute("allCands", candidateList);

		    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
		    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Candidates_list.jsp");
			rd.forward(request, response);
	 }

}