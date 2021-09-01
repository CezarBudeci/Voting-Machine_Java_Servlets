

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Recieves information from Candidates_list.jsp, deletes the required data from the database and redirects back to the same jsp file
 * @author Cezar
 *
 */
@WebServlet(name = "DeleteCandidate",
urlPatterns = {"/deletecandidate"}
)
public class DeleteCandidate extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    Connection conn = DatabaseConnection.getConnection();
	    String candToDel = request.getParameter("candidateIdDel");
	    try {
	    	String sql = "DELETE FROM politicians WHERE politicID = ? LIMIT 1";
	    	PreparedStatement pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, candToDel);
	    	pstmt.executeUpdate();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
	    RequestDispatcher rd=request.getRequestDispatcher("./candidates_list");
		rd.forward(request, response);
	}

}
