package model;
/**
 * Model for question, used in Result_detail_servlet
 * @author Jozef
 *
 *
 */
public class Question_model {
	int questionID;
	String questionText;
	
	public Question_model() {
		
	}
	
	public Question_model(int questionID, String questionText) {
		super();
		this.questionID = questionID;
		this.questionText = questionText;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	
	

}
