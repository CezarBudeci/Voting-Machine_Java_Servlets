import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Statistic;
import model.Question_model;
/**
 * Servlet used to handle the statistic
 * @author Jozef
 *
 *
 */
@WebServlet(
		name = "Statistics", 
		urlPatterns = { "/statistics_servlet" }
)
public class Statistics_servlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		//Getting questions for statistic page
		ArrayList<Question_model> questions = new ArrayList<Question_model>();
		Statistic statistic = new Statistic();
		try {
			questions = statistic.getQuestion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//sending data into the satistic page jsp
		request.setAttribute("question_list", questions);

		// response.getWriter().print("Hello App Engine - Servlet!\r\n");
		//we cannot have more then one redirect
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/statisticsPage.jsp");
		rd.forward(request, response);
	}
}