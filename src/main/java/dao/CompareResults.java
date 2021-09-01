package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.QuestionAnswersObj;
import model.AnsQue_model;
import model.PoliticScore_model;
import model.Politic_model;
/**
 * Class contains all methods used with politicians and users
 * @author Jozef
 *
 *
 */

public class CompareResults {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DbConnection.getInstance().getConnection();
		Statement statement = null;

	}

	private static ArrayList<Integer> getPoliticians(Connection con, Statement statement) throws SQLException {
		// This method is returning all politicians ids from politicians table....we are
		// saving it inside the array
		// -> We are returning array of Ids
		statement = con.createStatement();
		String sql = "SELECT politicID FROM politicians";
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<Integer> politicians = new ArrayList<Integer>();
		while (resultSet.next()) {
			politicians.add(resultSet.getInt("politicID"));
		}
		// here will be code sql
		// System.out.println("Politic Ids: " + String.valueOf(politicians));
		statement.execute(sql);
		return politicians;
	}

	public ArrayList<QuestionAnswersObj> getPoliticianAnswers(Connection con, Statement statement,int id) throws SQLException {
		// This method is returning object of answers for selected politician id
		statement = con.createStatement();
		String sql = "SELECT answer FROM questionsAnswers WHERE userID = " + id;
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<QuestionAnswersObj> politicianAnswers = new ArrayList<QuestionAnswersObj>();

		while (resultSet.next()) {
			QuestionAnswersObj answersObj = new QuestionAnswersObj();
			//answersObj.setQuestionId(resultSet.getInt("questionID"));
			answersObj.setQuestionAnswer(resultSet.getInt("answer"));
			politicianAnswers.add(answersObj);
		}

		
		// System.out.println("Politic Ids: " + String.valueOf(politicians));
		statement.execute(sql);

		return politicianAnswers;
	}


	private static Set<Integer> getAnswers(Connection con, Statement statement, int userScore) throws SQLException {
		// Getting all answers for selected politician ID
		statement = con.createStatement();
		// We are saving our politicians id list inside new variable politicians
		ArrayList<Integer> politicians = getPoliticians(con, statement);
		//System.out.println("Politicians:" + String.valueOf(getPoliticians(con, statement)));

		//Here will be stored our answers objects
		ArrayList<AnsQue_model> answers = new ArrayList<AnsQue_model>();
		//Set score contains hashSet (list) of selected users
		Set<Integer> score = new HashSet<Integer>();

		// Using set does not allow to add duplicates into them
		Set<Integer> matchID = new HashSet<>();

		//for thrue all ids
		for (int i = 0; i < politicians.size(); i++) {
			// Getting polititcian ID
			int politicianID = politicians.get(i);
			String sql = "SELECT * FROM questionsAnswers WHERE userID = " + politicianID;
			// System.out.println("Cislo" + String.valueOf(i));
			// Score for selected politician
			String sum = "SELECT SUM(answer) FROM questionsAnswers WHERE userID = " + politicianID;
			ResultSet resultSet_sum = statement.executeQuery(sum);
			resultSet_sum.next();
			int politic_score = resultSet_sum.getInt(1);

			//getting info about selected politician
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				// Saving score for selected user into the array list
				// score.add(politic_score);
				AnsQue_model answer_question = new AnsQue_model();
				// answer_question = new AnsQue_model();
				// Getting answers from selected id and saving it inside answer_question model
				// answers.add(resultSet.getInt("answer"));
				answer_question.setUserID(resultSet.getInt("userID"));
				answer_question.setQuestionID(resultSet.getInt("questionID"));
				answer_question.setAnswer(resultSet.getInt("answer"));
				// resultsOfTheArray.add(scoreOfArrays(answers));
				answer_question.setScore(politic_score);
				// checking for 3 best candidates
				score.add(politic_score);
				answers.add(answer_question);
			}
			statement.execute(sql);

		}

		// Casting from set of scores to arrayList
		ArrayList<Integer> score_ArrayList = new ArrayList<Integer>(score);
		//this array will contains 3 closes scores compare to user score
		ArrayList<Integer> three_score_ArrayList = new ArrayList<Integer>();
		// System.out.println("All politic score:" + String.valueOf(score_ArrayList));
		for (int x = 0; x < 3; x++) {
			int number = closest(userScore, score_ArrayList); // Closes method is checking closes value from selected array compared to our
			// number
			three_score_ArrayList.add(number);

			//if the score is once selected, it will be removed from array, so close function can check array again and find second closes number..
			for (int k = 0; k < score_ArrayList.size(); k++) {
				if (score_ArrayList.get(k) == number) {
					score_ArrayList.remove(k);
				}
			}
		}
		System.out.println("3 best match politics:" + String.valueOf(three_score_ArrayList));
		// scoreOfArrays(answers);
		// Testing:

		//for loop going thrue all politicIds answers
		for (AnsQue_model answer : answers) {
			System.out.println("Politics ID:" + String.valueOf(answer.getUserID()));
			System.out.println("Politics answer:" + String.valueOf(answer.getAnswer()));
			System.out.println("Politics questionID:" + String.valueOf(answer.getQuestionID()));
			System.out.println("Politics Score is:" + String.valueOf(answer.getScore()));

			//if object of answer contains same score as one of the three closes scores, the user 
			//id for selected score will be added into the match array
			for(Integer scoreArrayList : three_score_ArrayList) {
				if (answer.getScore() == scoreArrayList) {
					System.out.println("Politic answers:" + String.valueOf(answer.getScore()));
					// matchID contains 3 best matched politician id inside the array
					matchID.add(answer.getUserID());
					//System.out.println("Odpovede:" + String.valueOf(matchID));
				}
			}
			

		}

		//System.out.println("Odpovede2:" + String.valueOf(matchID));


		return matchID;
	}

	public int scoreOfArrays(ArrayList<Integer> array) {
		// Sum of all values inside the array
		int result = 0;
		int score = 0;
		for (int i = 0; i < array.size(); i++) {
			result = array.get(i);
			// result = result + result;
			score = score + result;
		}
		return score;
	}



	public Politic_model politician11(Connection con, Statement statement, int userScore) throws SQLException {
		/*
		 * statement = con.createStatement(); ArrayList<Integer> politicianIds =
		 * (ArrayList<Integer>) getAnswers(con, statement, userScore); //this shouldnt
		 * be hardcoded String sql = "SELECT * FROM politicians WHERE userID = " +
		 * politicianIds.indexOf(0);// must be 0 ResultSet resultSet =
		 * statement.executeQuery(sql); Politic_model politic = null;
		 * 
		 * while (resultSet.next()) { politic = new Politic_model();
		 * politic.setPoliticID(resultSet.getInt("politicID"));
		 * politic.setFirstName(resultSet.getString("firstName"));
		 * politic.setLastName(resultSet.getString("lastName"));
		 * politic.setDescription(resultSet.getString("description"));
		 * 
		 * }
		 * 
		 * return politic;
		 */
		statement = con.createStatement();
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); // this shouldnt be hardcoded

		//Saving 3 best matched user ids into the ArrayList<Integer> from Set
		for (Integer answerIntegers : getAnswers(con, statement, userScore)) {
			politicianIds.add(answerIntegers);
		}
		// System.out.println("Politik ID:" + String.valueOf(politicianIds.get(0)));

		String sql = "SELECT * FROM politicians WHERE politicID = " + politicianIds.get(0);// must be 0 - our first candidate best match
		ResultSet resultSet = statement.executeQuery(sql);
		Politic_model politic = new Politic_model();
		while (resultSet.next()) {
			politic.setPoliticID(resultSet.getInt("politicID"));
			politic.setFirstName(resultSet.getString("firstName"));
			politic.setLastName(resultSet.getString("lastName"));
			politic.setDescription(resultSet.getString("description"));
			politic.setNation(resultSet.getString("nation"));
			politic.setPoliticNumber(resultSet.getInt("politicNumber"));
			politic.setParty(resultSet.getString("politicNumber"));
			politic.setCity(resultSet.getString("city"));
			politic.setAge(resultSet.getInt("age"));


		}

		return politic;
	}

	public Politic_model politician22(Connection con, Statement statement, int userScore) throws SQLException {
		statement = con.createStatement();
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); // this shouldnt be hardcoded

		for (Integer answerIntegers : getAnswers(con, statement, userScore)) {
			politicianIds.add(answerIntegers);
		}
		// System.out.println("Politik ID:" + String.valueOf(politicianIds.get(0)));
		String sql = "SELECT * FROM politicians WHERE politicID = " + politicianIds.get(1);// must be 2
		ResultSet resultSet = statement.executeQuery(sql);
		Politic_model politic = new Politic_model();
		while (resultSet.next()) {
			politic.setPoliticID(resultSet.getInt("politicID"));
			politic.setFirstName(resultSet.getString("firstName"));
			politic.setLastName(resultSet.getString("lastName"));
			politic.setDescription(resultSet.getString("description"));
			politic.setNation(resultSet.getString("nation"));
			politic.setPoliticNumber(resultSet.getInt("politicNumber"));

		}

		return politic;
	}


	public Politic_model politician33(Connection con, Statement statement, int userScore) throws SQLException {
		statement = con.createStatement();
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); // this shouldnt be hardcoded

		for (Integer answerIntegers : getAnswers(con, statement, userScore)) {
			politicianIds.add(answerIntegers);
		}
		// System.out.println("Politik ID:" + String.valueOf(politicianIds.get(0)));
		String sql = "SELECT * FROM politicians WHERE politicID = " + politicianIds.get(2);// must be 2
		ResultSet resultSet = statement.executeQuery(sql);
		Politic_model politic = new Politic_model();
		while (resultSet.next()) {
			politic.setPoliticID(resultSet.getInt("politicID"));
			politic.setFirstName(resultSet.getString("firstName"));
			politic.setLastName(resultSet.getString("lastName"));
			politic.setDescription(resultSet.getString("description"));
			politic.setPoliticNumber(resultSet.getInt("politicNumber"));

		}

		return politic;
	}

	public Politic_model politicianDetail(Connection con, Statement statement, int userID) throws SQLException {

		//Detailed politician info

		statement = con.createStatement();
		String sql = "SELECT * FROM politicians WHERE politicID = " + userID;
		ResultSet resultSet = statement.executeQuery(sql);
		Politic_model politic = new Politic_model();
		while (resultSet.next()) {
			politic.setPoliticID(resultSet.getInt("politicID"));
			politic.setFirstName(resultSet.getString("firstName"));
			politic.setLastName(resultSet.getString("lastName"));
			politic.setDescription(resultSet.getString("description"));
			politic.setNation(resultSet.getString("nation"));
			politic.setPicture(resultSet.getString("picture"));
			politic.setPoliticNumber(resultSet.getInt("politicNumber"));
		}
		return politic;
	}
	private static int closest(int of, ArrayList<Integer> in) {
		// This method is checking closes value from selected array compared to our
		// number
		int min = Integer.MAX_VALUE;
		int closest = of;
		for (int v : in) {
			final int diff = Math.abs(v - of);
			if (diff < min) {
				min = diff;
				closest = v;
			}
		}
		return closest;
	}

	private static ArrayList<Integer> matchedPoliticianIds(ArrayList<Integer> match) {
		//not used
		return match;

	}


}