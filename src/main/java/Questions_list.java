import java.io.IOException;
import java.sql.Connection;
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

import data.AllQuestions;
import data.CountOfQuestions;
/**
 * Shows all the questions 
 * @author Samantha
 *
 */

@WebServlet(
    name = "Questions_list",
    urlPatterns = {"/questions_list"}
)
public class Questions_list extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    Connection conn =  DatabaseConnection.getConnection(); 
    List<AllQuestions> questionList =  new ArrayList<AllQuestions>(); 
    List<CountOfQuestions> numOfQuestion =  new ArrayList<CountOfQuestions>();
    
	    try {
	    	String sql = "SELECT COUNT(questionID) AS totalQue FROM questions";
	    	Statement st = conn.createStatement();
	    	ResultSet results = st.executeQuery(sql);
	    	
	    	while(results.next()) {
	    		CountOfQuestions countQue = new CountOfQuestions();
	    		countQue.setQcoun(results.getInt("totalQue"));
	    		numOfQuestion.add(countQue);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    
	    try {
	    	String sql = "SELECT * FROM questions";
	    	Statement st = conn.createStatement();
	    	ResultSet results = st.executeQuery(sql);
	    	
	    	while(results.next()) {
	    		AllQuestions question = new AllQuestions();
	    		question.setId(results.getInt("questionID"));
	    		question.setQuestion(results.getString("questionText"));
	    		questionList.add(question);
	    		
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    request.setAttribute("numberofquestions", numOfQuestion);
	    request.setAttribute("allQuestions", questionList);
	
	
	    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
	    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Questions_list.jsp");
		rd.forward(request, response);

  }
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    Connection conn =  DatabaseConnection.getConnection(); 
    List<AllQuestions> questionList =  new ArrayList<AllQuestions>(); 
    List<CountOfQuestions> numOfQuestion =  new ArrayList<CountOfQuestions>();
    
	    try {
	    	String sql = "SELECT COUNT(questionID) AS totalQue FROM questions";
	    	Statement st = conn.createStatement();
	    	ResultSet results = st.executeQuery(sql);
	    	
	    	while(results.next()) {
	    		CountOfQuestions countQue = new CountOfQuestions();
	    		countQue.setQcoun(results.getInt("totalQue"));
	    		numOfQuestion.add(countQue);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    
	    try {
	    	String sql = "SELECT * FROM questions";
	    	Statement st = conn.createStatement();
	    	ResultSet results = st.executeQuery(sql);
	    	
	    	while(results.next()) {
	    		AllQuestions question = new AllQuestions();
	    		question.setId(results.getInt("questionID"));
	    		question.setQuestion(results.getString("questionText"));
	    		questionList.add(question);
	    		
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    request.setAttribute("numberofquestions", numOfQuestion);
	    request.setAttribute("allQuestions", questionList);

    //response.getWriter().print("Hello App Engine - Servlet!\r\n");
    RequestDispatcher rd=request.getRequestDispatcher("./jsp/Questions_list.jsp");
	rd.forward(request, response);

  }
}