import java.io.IOException;
import java.sql.Connection;
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

import dao.CompareResults;
import dao.DbConnection;
import dao.Statistic;
import data.QuestionAnswerPoliticObj;
import data.QuestionAnswersObj;
import model.Politic_model;
import model.Question_model;

@WebServlet(name = "Result_detail_servlet",

		urlPatterns = { "/result_detail_servlet" })

/**
 * Servlet used for detail view of politician and comparison with user
 * @author Jozef
 *
 *
 */
public class Result_detail_servlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		//Getting session from resault servlet // my questions objects
		HttpSession session =request.getSession();  //return pre exististing session
		//Saving session atributes into the list  //Session will be getted from result servlet
	    List<QuestionAnswersObj> resultsArrayList = (ArrayList<QuestionAnswersObj>) session.getAttribute("listOfAnswers");
		System.out.println("SessioTest:" + String.valueOf(resultsArrayList));

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
		// getting id of politician from resulpage.jsp
		String id = request.getParameter("id");
		int politicID = Integer.valueOf(id);
		
		

		//Object need for getting politician detail
		CompareResults compareResults = new CompareResults();
		// Create of object of politician
		Politic_model politician_model_detail = new Politic_model();
		try {

			politician_model_detail = compareResults.politicianDetail(con, statement, politicID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//getting all questions from the DB
		ArrayList<Question_model> questions = new ArrayList<Question_model>();
		//Statistic object needed bcs we want all questions from db
		Statistic statistic = new Statistic();
		try {
			questions = statistic.getQuestion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Getting array of answers for selected politician
		ArrayList<QuestionAnswersObj> politicianAnswers = new ArrayList<QuestionAnswersObj>();
		try {
			 politicianAnswers = compareResults.getPoliticianAnswers(con, statement, politicID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Connecting values from user array and politician array into one, so we can use it in our resultpage detail.
		ArrayList<QuestionAnswerPoliticObj> questionUserPoliticAnswer = new ArrayList<QuestionAnswerPoliticObj>();
		for(int x = 0; x < politicianAnswers.size();x++){
			QuestionAnswerPoliticObj answerPoliticObj = new QuestionAnswerPoliticObj();
			answerPoliticObj.setPoliticianAnswer(politicianAnswers.get(x).getQuestionAnswer()); //This is from our politician answers
			answerPoliticObj.setQuestion(resultsArrayList.get(x).getQuestion()); //Those are values from our session (my answers)
			answerPoliticObj.setQuestionAnswer(resultsArrayList.get(x).getQuestionAnswer());
			answerPoliticObj.setQuestionId(resultsArrayList.get(x).getQuestionId());
			questionUserPoliticAnswer.add(answerPoliticObj);

		}



		// sending data
		request.setAttribute("politicDetail", politician_model_detail);
		request.setAttribute("question_list", questions);
		//request.setAttribute("answersList", resultsArrayList);
		//request.setAttribute("politicianAnswers", politicianAnswers);
		request.setAttribute("questionsAnswersUserPoliticList", questionUserPoliticAnswer);
		// response.getWriter().print("Hello App Engine - Servlet!\r\n");
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/resultPage_detail.jsp");
		rd.forward(request, response);
	}
}