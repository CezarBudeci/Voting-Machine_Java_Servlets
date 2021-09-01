

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

import data.QuestionAnswersObj;

/**
 * This servlet stores anonymously answers of a basic user into a database
 * It redirects to result_servlet
 * 
 * @author Cezar
 *
 */


@WebServlet(name = "StoreResults",
urlPatterns = {"/storeresults"}
)
public class StoreResults extends HttpServlet {


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	  //get questionID, question, questionAnswer of site visitor as a string
	    String userAnswers = request.getParameter("values");//split it to get each question with its id and answer separately
	    String[] userAnswersDivided1 = userAnswers.split("-");
	    
	    List<String[]> dividedAnswers = new ArrayList<String[]>();//list of arrays of string for questionID, question, questionAnswer
	    List<QuestionAnswersObj> dividedAnswers1 = new ArrayList<QuestionAnswersObj>();

	    for (String s : userAnswersDivided1) {
	    	String[] answerDivision = s.split("_");
	    	dividedAnswers.add(answerDivision);
	    	QuestionAnswersObj questionAnswer = new QuestionAnswersObj();
	    	questionAnswer.setQuestionId(Integer.parseInt(dividedAnswers.get(dividedAnswers.size()-1)[0]));
	    	questionAnswer.setQuestion(dividedAnswers.get(dividedAnswers.size()-1)[1]);
	    	questionAnswer.setQuestionAnswer(Integer.parseInt(dividedAnswers.get(dividedAnswers.size()-1)[2]));
	    	dividedAnswers1.add(questionAnswer);
	    	
	    }//spliting again to separate id and answer from question. saving information into objects and adding each object to list
	    
	    //db connection
	    Connection conn = DatabaseConnection.getConnection();
	    try {
	    	
	    	String sql = "INSERT INTO forStatistic (`questionID`, `answer`) VALUES (?, ?)";
	    	PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    	for (int i = 0; i<dividedAnswers.size(); i++) {
	        	pstmt.setString(1, dividedAnswers.get(i)[0]);
	        	pstmt.setString(2, dividedAnswers.get(i)[2]);
	        	pstmt.executeUpdate();
	    	}//inserting anonymous answers in the db
	    	
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    //System.out.println("Results" + String.valueOf(dividedAnswers1));
	    request.setAttribute("results", dividedAnswers1);
	    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
	    RequestDispatcher rd=request.getRequestDispatcher("./result_servlet");
		rd.forward(request, response);
	}

}
