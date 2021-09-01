package data;

import java.io.Serializable;


/**
 * Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes that includes the object's
 * data as well as information about the object's type and the types of data stored in the object.
 * After a serialized object has been written into a file, it can be read from the file and deserialized that is,
 * the type information and bytes that represent the object and its data can be used to recreate the object in memory.
 * 
 * Class to store all questions and answers provided by users
 * 
 * @author Cezar
 *
 */

public class QuestionAnswersObj implements Serializable {

	private int questionId;
	private String question;
	private int questionAnswer;
	
	
	public QuestionAnswersObj() {
		
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
}