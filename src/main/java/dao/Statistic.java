package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Question_model;
/**
 * This class contains all methods used with statistic page
 * @author Jozef
 *
 *
 */
public class Statistic {

	public ArrayList<Question_model> getQuestion() throws SQLException {
		// Getting all questions from db
		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		String sql = "SELECT * FROM questions";
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<Question_model> questions = new ArrayList<Question_model>();
		while (resultSet.next()) {
			Question_model question_model = new Question_model();
			question_model.setQuestionID(resultSet.getInt("questionID"));
			question_model.setQuestionText(resultSet.getString("questionText"));
			questions.add(question_model);
		}
		statement.execute(sql);

		return questions;
	}

	public int getQuestionCount(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;

		// Count for selected questionID
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id;
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}


	public int getQuestionAvg(int id) throws SQLException {
		//not used
		// This method is returning avg of all answers with same id

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_avg = 0;
		/*
		 * // -> We are returning array of Ids ArrayList<Question_model> questions = new
		 * ArrayList<Question_model>();
		 * 
		 * for (Question_model question_model : questions) { int questionId =
		 * question_model.getQuestionID();
		 * 
		 * // Count for selected questionID String avg =
		 * "SELECT AVG(answer) FROM forStatistic WHERE questionId = " + questionId;
		 * ResultSet resultSet_avg = statement.executeQuery(avg); resultSet_avg.next();
		 * question_avg = resultSet_avg.getInt(1); }
		 */

		// Count for selected questionID
		String avg = "SELECT AVG(answer) FROM forStatistic WHERE questionId = " + id;
		ResultSet resultSet_avg = statement.executeQuery(avg);
		resultSet_avg.next();
		question_avg = resultSet_avg.getInt(1);

		return question_avg;
	}

	public int getQuestion1Count(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;
		// Count for selected questionID and selected answer
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id + " AND answer = 1";
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}

	public int getQuestion2Count(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;
		// Count for selected questionID and selected answer
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id + " AND answer = 2";
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}

	public int getQuestion3Count(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;
		// Count for selected questionID and selected answer
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id + " AND answer = 3";
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}

	public int getQuestion4Count(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;
		// Count for selected questionID and selected answer
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id + " AND answer = 4";
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}

	public int getQuestion5Count(int id) throws SQLException {
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;
		statement = con.createStatement();
		int question_count = 0;
		// Count for selected questionID and selected answer
		String count = "SELECT COUNT(questionId) FROM forStatistic WHERE questionId = " + id + " AND answer = 5";
		ResultSet resultSet_count = statement.executeQuery(count);
		resultSet_count.next();
		question_count = resultSet_count.getInt(1);
		return question_count;
	}

}