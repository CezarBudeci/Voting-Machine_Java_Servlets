import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Leads to loginpage
 * @author Samantha
 *
 */


@WebServlet(
    name = "Login",
    urlPatterns = {"/login"}
)
public class loginpage extends HttpServlet {
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
	    RequestDispatcher rd=request.getRequestDispatcher("./jsp/loginpage.jsp");
		rd.forward(request, response);

	  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
	  

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/loginpage.jsp");
	rd.forward(request, response);

  }
}