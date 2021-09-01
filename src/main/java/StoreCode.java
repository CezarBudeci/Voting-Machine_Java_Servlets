import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * This servlet does the valitation for invitation codes and emails, before adding them in the database
 * It redirects to candidates_list servlet
 * 
 * @author Cezar
 *
 */

@WebServlet(
    name = "Storecode",
    urlPatterns = {"/storecode"}
)
public class StoreCode extends HttpServlet {

  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    String inviteCode = request.getParameter("invitationCode");
    String toEmail = request.getParameter("emailAddress");
    String sql;

    Connection conn = DatabaseConnection.getConnection();
    if (inviteCode.length() != 0) {
	    try {
	    	sql = "SELECT * FROM invitationCodes WHERE usedByPolitician = '" + toEmail + "'";
	    	Statement st = conn.createStatement();
	    	ResultSet rs = st.executeQuery(sql);
	    	if (rs.next()) {
	    		response.getWriter().print("This email was invite already. Please go back and select another email!");
	    	} else {
	    		try {
	    			sql = "SELECT * FROM invitationCodes WHERE inviteCode = '" + inviteCode + "'";
	    	    	Statement st1 = conn.createStatement();
	    	    	ResultSet rs1 = st1.executeQuery(sql);
	    	    	if (rs1.next()) {
	    	    		response.getWriter().print("This invite code was already used. Please go back and generate a new invite code!");
	    	    	} else {
	    	    		try {
	    	    	    	sql = "INSERT INTO invitationCodes (inviteCode, usedByPolitician) VALUES (?, ?)";
	    	    	    	PreparedStatement stmt = conn.prepareStatement(sql);
	    	    	    	stmt.setString(1, inviteCode);
	    	    	    	stmt.setString(2, toEmail);
	    	    	    	stmt.executeUpdate();
	    	    	    	RequestDispatcher rd=request.getRequestDispatcher("./candidates_list");
	    	    			rd.forward(request, response);
	    	    	    } catch (Exception e) {
	    	    	    	e.printStackTrace();
	    	    	    }
	    	    	}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    } else {
    	response.getWriter().print("Please generate an invitation code!");
    }
    
    
    
   
    
   
   

  }
}