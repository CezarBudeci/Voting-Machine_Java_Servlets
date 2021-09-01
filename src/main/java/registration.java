import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Leads to registration
 * @author Samantha
 *
 */

@WebServlet(
    name = "signup",
    urlPatterns = {"/signup"}
)
public class registration extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/registration.jsp");
	rd.forward(request, response);
	
	
    

  }
}