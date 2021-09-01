import java.io.IOException;
import java.sql.Connection;
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

import data.AllQuestions;
import data.CountOfQuestions;

/**
 * Adds a question to question list, redirects to questionlist
 * @author Samantha
 *
 */

@WebServlet(
    name = "AddQuestion",
    urlPatterns = {"/addquestion"}
)
public class AddQuestion extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    Connection conn =  DatabaseConnection.getConnection(); 
// Adding a question to the database
    String question = request.getParameter("question");
    if (question.length() > 0 ) {
    	
   
    try {
    	String sql = "INSERT INTO questions (questionText) VALUES (?)";
    	
		PreparedStatement pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, question);
    	pstmt.executeUpdate();
    	
    }catch (Exception e) {
    	e.printStackTrace();
    }
	 }else {
		 response.sendRedirect("questions_list");
	 }
	
	    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    response.sendRedirect("questions_list");

  }

}