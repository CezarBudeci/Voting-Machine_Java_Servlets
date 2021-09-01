package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is creating a connection into database and contains methods for download and upload data.
 * @author Samu Uunonen
 *
 */
public class Dao {

	private String url;
	private String user;
	private String pass;
	private Connection conn;

	public Dao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	/**
	 *  Create connection to database
	 * @return
	 */
	public boolean getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new SQLException(e);
				}
				conn = DriverManager.getConnection(url, user, pass);
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Delete candidate from database. Used in profile-page. 
	 * @author Samu Uunonen
	 * @param userId
	 */
	public void deleteCandidate(String userId) {
		try {
			String sql = "DELETE FROM politicians WHERE politicID=? LIMIT 1";
			String sql2 = "DELETE FROM questionsanswers WHERE userID=? LIMIT 10";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			pstmt2.setString(1, userId);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deleting candidate failed");

		}
	}

	/**
	 * Read answers. Returns answers in HashMap.
	 * @author Samu Uunonen
	 * @param userId
	 * @return
	 */
	public HashMap<String, String> readAnswers(String userId) {

		HashMap<String, String> answers = new HashMap<String, String>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM questionsAnswers WHERE userID=" + userId);
			while (rs.next()) {
				answers.put("a" + rs.getString("questionID"), rs.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to read answers");
			return null;
		}
		return answers;
	}

	/**
	 * Submit answers into database.
	 * @author Samu Uunonen
	 * @param answers
	 * @param userId
	 */
	public void submitAnswers(HashMap<String, String> answers, String userId) {
		PreparedStatement pstmt;
		String sql;
		HashMap<String, String> answerList = readAnswers(userId);
		if (answers.size() > 0) { // If answer map contains answers, insert or update it into database.
			if (answerList.size() > 0) { // if database already contains answers from user, update data.
				try {
					for (int i = 1; i < answers.size() + 1; i++) {
						sql = "UPDATE questionsAnswers SET answer=? WHERE userID=" + userId + " AND questionID=" + i;
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, answers.get("a" + i));
						pstmt.executeUpdate();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Answer submission failed");
				}
			} else { // if database doesn't have values from this user, insert new values.
				try {
					for (int i = 1; i < answers.size() + 1; i++) {
						sql = "INSERT INTO questionsAnswers (questionID, answer, userID) VALUES (?, ?, ?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, Integer.toString(i));
						pstmt.setString(2, answers.get("a" + i));
						pstmt.setString(3, userId);
						pstmt.executeUpdate();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Answer insertion failed");
				}
			}
		} else {
			// If answers map is empty, do nothing.
		}

	}

	/**
	 * Reads questions. Returns ArrayList of questions
	 * @author Samu Uunonen
	 * @return
	 */
	public ArrayList<String> readQuestions() {

		ArrayList<String> questions = new ArrayList<String>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT questionText FROM questions");
			while (rs.next()) {
				questions.add(rs.getString("questionText"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to read data");
			return null;
		}
		return questions;
	}

	/**
	 * Update candidate information.  returns ReadCandidate() method HashMap.
	 * @author Samu Uunonen
	 * @param candidate
	 * @param userId
	 * @return
	 */
	public HashMap<String, String> updateCandidate(HashMap<String, String> candidate, String userId) {

		HashMap<String, String> candidateList = readCandidate(userId);

		if (candidateList.size() > 0) {
			try {
				String sql = "UPDATE politicians SET nation=?, city=?, age=?, sex=?, party=?, politicNumber=?, description=?, email=?, pssword=?, picture=? "
						+ "WHERE politicID =" + userId;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, candidate.get("nation"));
				pstmt.setString(2, candidate.get("city"));
				pstmt.setString(3, candidate.get("age"));
				pstmt.setString(4, candidate.get("sex"));
				pstmt.setString(5, candidate.get("party"));
				pstmt.setString(6, candidate.get("politicNumber"));
				pstmt.setString(7, candidate.get("description"));
				pstmt.setString(8, candidate.get("email"));
				pstmt.setString(9, candidate.get("pssword"));
				// if image is not set, upload default "no-image" path
				if (candidate.get("picture").equalsIgnoreCase("")) {
					pstmt.setString(10, "../images/no-image-icon.png");
				} else {
					pstmt.setString(10, "../images/" + candidate.get("picture"));
				}
				pstmt.executeUpdate();
				return readCandidate(userId);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to update data" + e);
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Reads candidate information. Returns candidate's information in HashMap.
	 * @author Samu Uunonen
	 * @param userId
	 * @return
	 */
	public HashMap<String, String> readCandidate(String userId) {
		HashMap<String, String> candidateProfile = new HashMap<String, String>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM politicians WHERE politicID=" + userId);
			while (rs.next()) {
				candidateProfile.put("fullName", rs.getString("firstName") + " " + rs.getString("lastName"));
				candidateProfile.put("nation", rs.getString("nation"));
				candidateProfile.put("city", rs.getString("city"));
				candidateProfile.put("age", rs.getString("age"));
				candidateProfile.put("sex", rs.getString("sex"));
				candidateProfile.put("party", rs.getString("party"));
				candidateProfile.put("politicNumber", String.valueOf((rs.getInt("politicNumber"))));
				candidateProfile.put("description", rs.getString("description"));
				candidateProfile.put("email", rs.getString("email"));
				candidateProfile.put("pssword", rs.getString("pssword"));
				candidateProfile.put("picture", rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to read data");
			return null;
		}
		return candidateProfile;
	}

}
