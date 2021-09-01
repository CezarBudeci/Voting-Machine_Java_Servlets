package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class UpdateProfile
 * This servlet is used to pass data into Dao class from editprofile.jsp file.
 * @author Samu Uunonen
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		// database connection
		dao = new Dao("jdbc:mysql:", "", "");
	} 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = String.valueOf(session.getAttribute("politicID"));
		
		HashMap<String, String> candidateProfile = new HashMap<String, String>();
		HashMap<String, String> candidate = new HashMap<String, String>();
		HashMap<String, String> answers = new HashMap<String, String>();
		
		// Update candidate's information
		candidate.put("nation", request.getParameter("nationTextField"));
		candidate.put("city", request.getParameter("cityTextField"));
		candidate.put("age", request.getParameter("ageTextField"));
		candidate.put("sex", request.getParameter("sexTextField"));
		candidate.put("party", request.getParameter("maritalTextField"));
		candidate.put("description", request.getParameter("profile-text"));
		candidate.put("politicNumber", request.getParameter("politicNumber"));
		candidate.put("picture", request.getParameter("file-textField"));
		candidate.put("politicNumber", request.getParameter("candidateNumber"));
		candidate.put("email", request.getParameter("email-textField"));
		candidate.put("pssword", request.getParameter("password-textField"));
		
		// Update candidate's answers
		for (int i = 1; i < 11; i++) {
			answers.put("a"+i, request.getParameter("answer-radio-"+i));
		}
		
		if (dao.getConnection()) {
			dao.updateCandidate(candidate, id);
			dao.submitAnswers(answers, id);
		} else {
			System.out.println("No connection to database");
		}

		// Redirect into profile page
		response.sendRedirect("/ProfilePage");
		
		// Reset HashMaps
		answers.clear();
		candidateProfile.clear();
		candidate.clear();
		
	}

}
