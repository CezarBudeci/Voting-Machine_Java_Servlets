import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.AllQuestions;

/**
 * This servlet retrieves all questions from the database using an object of SqlConn class and sends it to questions.jsp
 * 
 * @author Cezar
 *
 */

@WebServlet(
    name = "Questions",
    urlPatterns = {"/questions"}
)
public class Questions extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    SqlConn connObj = new SqlConn();//select all questions from the db and put then into and arraylistt of objects
    Connection conn = (Connection) DatabaseConnection.getConnection();
    ArrayList<AllQuestions> allQuestions = connObj.getQuestions(conn);
    request.setAttribute("questions", allQuestions);//setting the question object to and attribute

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Questions.jsp");
	rd.forward(request, response);
	
	
    
    
  }
}