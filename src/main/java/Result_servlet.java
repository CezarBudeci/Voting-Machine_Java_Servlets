import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompareResults;
import dao.DbConnection;
import data.QuestionAnswersObj;
import model.Politic_model;

/**
 * Servlet used for display best matched three politicians
 * @author Jozef
 *
 *
 */
@WebServlet(name = "Results", urlPatterns = { "/result_servlet" })
public class Result_servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		// Connection create
		try {
			Connection con = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement statement = null;
		//call of Compare results object
		CompareResults compareResults = new CompareResults();
		//Getting array of user aQuestionAnswers from user as an attribute from StoreResults servlet
		List<QuestionAnswersObj> resultsArrayList = (ArrayList<QuestionAnswersObj>) request.getAttribute("results");
		//Inside the answers we will have stored our user questions
		ArrayList<Integer> answers = new ArrayList<Integer>();



		// Return the existing session if there is one. Create a new session otherwise.
		HttpSession session = request.getSession();
		session.setAttribute("listOfAnswers", resultsArrayList); //session will be use inside the Result detail servlet
		//System.out.println("Answers-retrived" + String.valueOf(session.getAttribute("listOfAnswers")));

		for (QuestionAnswersObj questionAnswersObj : resultsArrayList) {
			answers.add(questionAnswersObj.getQuestionAnswer());

		}

		System.out.println("Answers:" + String.valueOf(answers));

		//Calculate score from selected array of answers
		int userScore = compareResults.scoreOfArrays(answers);
		System.out.println("My score is:" + String.valueOf(userScore));

		//Create of politician objects
		Politic_model politician1 = new Politic_model();
		Politic_model politician2 = new Politic_model();
		Politic_model politician3 = new Politic_model();
		try {
			politician1 = compareResults.politician11(con, statement, userScore);
			politician2 = compareResults.politician22(con, statement, userScore);
			politician3 = compareResults.politician33(con, statement, userScore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// sending data into the result.jsp
		request.setAttribute("politic1", politician1);
		request.setAttribute("politic2", politician2);
		request.setAttribute("politic3", politician3);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/resultPage.jsp");
		rd.forward(request, response);

	}

}