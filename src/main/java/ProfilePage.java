
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompareResults;
import dao.Dao;
import dao.DbConnection;
import data.QuestionAnswerPoliticObj;
import data.QuestionAnswersObj;

/**
 * Servlet implementation class ProfilePage
 * This servlet is used to pass data from Dao class into profile-page.jsp.
 * @author Samu Uunonen
 */

@WebServlet(
		name = "ProfilePage",
		urlPatterns = { "/ProfilePage"} )

public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		// database connection
		dao = new Dao("jdbc:mysql://135.181.156.254:3306/voting_db", "dogetomoon", "D4jmd6r7h7MF2aUK");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String id = "";
		
		if(session.getAttribute("politicID") == null) {
			id = request.getParameter("id");
		} else {
			 id = String.valueOf(session.getAttribute("politicID"));

		}
		
		 
		HashMap<String, String> candidateProfile = null;
		HashMap<String, String> answers = null;
		ArrayList<String> questions = null;
		

		// Read data from database
		if (dao.getConnection()) {
			candidateProfile = dao.readCandidate(id);
			questions = dao.readQuestions();
			answers = dao.readAnswers(id);
		} else {
			System.out.println("No connection to database");
		}
		// Set profile information
		request.setAttribute("fullName", candidateProfile.get("fullName"));
		request.setAttribute("nation", candidateProfile.get("nation"));
		request.setAttribute("city", candidateProfile.get("city"));
		request.setAttribute("age", candidateProfile.get("age"));
		request.setAttribute("sex", candidateProfile.get("sex"));
		request.setAttribute("party", candidateProfile.get("party"));
		request.setAttribute("politicNumber", candidateProfile.get("politicNumber"));
		request.setAttribute("description", candidateProfile.get("description"));
		request.setAttribute("email", candidateProfile.get("email"));
		request.setAttribute("pssword", candidateProfile.get("pssword"));
		request.setAttribute("picture", candidateProfile.get("picture"));

		// Show questions
		int index = -1;
		for (int i = 1; i < questions.size() + 1; i++) {
			index++;
			request.setAttribute("q" + i, questions.get(index));
		}

		// Show answers ( Set checked attribute to radio button )
		for (int i = 1; i < answers.size() + 1; i++) {
			String answer = answers.get("a" + i);
			switch (answer) {
			case "1":
				request.setAttribute("checked1" + i, "checked");
				break;
			case "2":
				request.setAttribute("checked2" + i, "checked");
				break;
			case "3":
				request.setAttribute("checked3" + i, "checked");
				break;
			case "4":
				request.setAttribute("checked4" + i, "checked");
				break;
			case "5":
				request.setAttribute("checked5" + i, "checked");
				break;
			default:
				// do nothing
			}
		}

		//request.setAttribute("answers", questionUserPoliticAnswer);
		// Forward into profile page
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile-page.jsp");
		rd.forward(request, response);

		// Reset HashMaps.
		candidateProfile.clear();
		questions.clear();
		answers.clear();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
