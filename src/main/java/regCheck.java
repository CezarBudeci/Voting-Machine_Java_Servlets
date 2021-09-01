import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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


import java.sql.Connection;

/**
 * Servlet implementation class Statistics_servlet
 */
/**
 * Checks if email already exists, adds data to database, redirects to login 
 * @author Samantha
 *
 */

@WebServlet(
		name = "RegistrationProcessing", 
		urlPatterns = { "/registrationprocess" }
)

public class regCheck extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		Connection con = DatabaseConnection.getConnection();
	    
	    String code = request.getParameter("code");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String party = request.getParameter("party");
		String email = request.getParameter("email");
		String psswrd = request.getParameter("password");
		String hpsswrd = encryption.crypt(psswrd);
		
		List <String > result = new ArrayList <String>();
	    
// Checking if email exist	   
	    	try {
	    		   String sql = ("SELECT email FROM politicians WHERE email = '"+email+"'");
	    		   Statement st = con.createStatement();
	    		   ResultSet rst = st.executeQuery(sql);
	    		if (rst.next()) {
	    			// email already exist
	    			response.getWriter().write("The email is already registerd, please go back!");	    			
	    		}
	  
	    	 }catch (Exception e) {
	    		 System.out.println(e);
	    	 }
// Checking if the email registered is same as set for the invitation code and then adding values to database
	    	try {
	    		   String sql = ("SELECT usedByPolitician FROM invitationCodes WHERE inviteCode = '"+code+"'");
	    		   Statement st = con.createStatement();
	    		   ResultSet rst = st.executeQuery(sql);
	    		if (rst.next()) {
	    			String pemail = rst.getString(1);
	    			result.add(pemail);
					if (result.get(0).equals(email)) {
						try {
							String query = ("INSERT INTO politicians (firstName, lastName, party, email, pssword) VALUES (?,?,?,?,?)");							
							
							PreparedStatement pstmt = con.prepareStatement(query);
					    	pstmt.setString(1, fname);
					    	pstmt.setString(2, lname);
					    	pstmt.setString(3, party);
					    	pstmt.setString(4, email);
					    	pstmt.setString(5, hpsswrd);
							pstmt.executeUpdate();
							
							RequestDispatcher rd = request.getRequestDispatcher("./login");
							rd.forward(request, response);
						}catch (Exception e) {
							System.out.println("Something went wrong adding data to DB." + " " + e);
						} 
					}else {
						response.getWriter().write("Invalid email for this code \n");
					}
	    			
	    		} else {
	    			response.getWriter().write("The code does not exist \n");
	    		}
	  
	    	 }catch (Exception e) {
	    		 System.out.println(e);
	    	 }

			
		
		
		
		// response.getWriter().print("Hello App Engine - Servlet!\r\n");
		
	}

}
