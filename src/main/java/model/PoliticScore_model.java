package model;
/**
 * Model class contains politicID, politic score and user score 
 * This model is for testing purposes
 * @author Jozef
 *
 *
 */

public class PoliticScore_model {
	int politicID,score,myScore;

	public PoliticScore_model() {

	}

	public PoliticScore_model(int politicID, int score, int myScore) {
		super();
		this.politicID = politicID;
		this.score = score;
		this.myScore = myScore;
	}

	public int getPoliticID() {
		return politicID;
	}
	public void setPoliticID(int politicID) {
		this.politicID = politicID;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getMyScore() {
		return myScore;
	}

	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}




}