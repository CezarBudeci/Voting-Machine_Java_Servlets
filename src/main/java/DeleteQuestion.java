import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Deleting questions from question list, redirects to questionlist
 * @author Samantha
 *
 */

@WebServlet(
    name = "DeleteQuestion",
    urlPatterns = {"/deletequestion"}
)
public class DeleteQuestion extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    String questionid = request.getParameter("inputquestion");    
    
    
    Connection conn =  DatabaseConnection.getConnection(); 
 // Deleting a question from the database   
    try {
    	String sql = "DELETE FROM questions WHERE questionID = ? ";
    	
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, questionid);
    	pstmt.executeUpdate();
    	
    	
    }catch (Exception e) {
    	e.printStackTrace();
    }

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    response.sendRedirect("questions_list");

  }
}