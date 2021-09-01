import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Leads to intro.jsp
 * @author Samantha
 *
 */

@WebServlet(
    name = "Intro",
    urlPatterns = {"/intro"}
)

public class intro extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {


    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/intro.jsp");
	rd.forward(request, response);

  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
	  //HttpSession session = request.getSession();


    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./index.jsp");
	rd.forward(request, response);

  }
}