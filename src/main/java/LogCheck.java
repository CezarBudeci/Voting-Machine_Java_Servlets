import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
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
import javax.servlet.http.HttpSession;

import java.sql.Connection;

/**
 * Servlet implementation class Statistics_servlet
 */
/**
 * Checks the email and password for the user log in
 * @author Samantha
 *
 */

@WebServlet(
		name = "LoginProcessing", 
		urlPatterns = { "/loginprocess" }
)

public class LogCheck extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		Connection con = DatabaseConnection.getConnection();
		
// Loging in 
		
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String psswrd = request.getParameter("password");
		String hpsswrd = encryption.crypt(psswrd);
		
		
		try {
 		   String sql = ("SELECT politicID, role FROM politicians WHERE email = '"+email+"' AND pssword = '"+hpsswrd+"'");
 		   Statement st = con.createStatement();
 		   ResultSet rst = st.executeQuery(sql);
 // If the values match, creating sessions for the logged in user 
	 		if (rst.next()) {
	 			HttpSession session = request.getSession();
	 			
	 			
	 			session.setAttribute("role", rst.getString("role"));
	 			session.setAttribute("politicID", rst.getInt("politicID"));
	 			System.out.println(session.getAttribute("role"));
	 			
	 			
	 			
	 			if (session.getAttribute("role").equals("candidate")) {
	 				//RequestDispatcher rd=request.getRequestDispatcher("./ProfilePage");
	 				//rd.forward(request, response);
	 				response.sendRedirect("./ProfilePage");
	 				
	 			} else {
	 				RequestDispatcher rd=request.getRequestDispatcher("./dashboard");
	 				rd.forward(request, response);
	 			}
	 			
	 		} else {
//	 			response.getWriter().write("Invalid log in details");
//	 			out.print("Invalid log in details");
	 			response.sendRedirect("login");
	 		}

	 	 }catch (Exception e) {
	 		 System.out.println(e);
	 	 }
 		 out.close();
	}
}

