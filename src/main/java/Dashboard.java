import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session checks the users role and redirect it to dashboard or profilepage
 * @author Samantha
 *
 */

@WebServlet(
    name = "Dashboard",
    urlPatterns = {"/dashboard"}
)
public class Dashboard extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    HttpSession session = request.getSession();
    if (session.getAttribute("role").equals("interviewer")) {
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Dashboard.jsp");
	rd.forward(request, response);
    }
    else {
    	RequestDispatcher rd=request.getRequestDispatcher("./jsp/profile-page.jsp");
    	rd.forward(request, response);
    }
  }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {

      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");

      //response.getWriter().print("Hello App Engine - Servlet!\r\n");
      HttpSession session = request.getSession();
      if (session.getAttribute("role").equals("interviewer")) {
      RequestDispatcher rd=request.getRequestDispatcher("./jsp/Dashboard.jsp");
  	rd.forward(request, response);
      }
      else {
      	RequestDispatcher rd=request.getRequestDispatcher("./jsp/profile-page.jsp");
      	rd.forward(request, response);
      }

  }
}