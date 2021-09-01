package model;
/**
 * Model contains all info about the politician - used in CompareResults
 * @author Jozef
 *
 *
 */
public class Politic_model {
	int politicID, age,politicNumber;
	String firstName,lastName,party,description,city,pssword,picture,sex,role,nation;

	public Politic_model() {

	}

	public Politic_model(int politicID, int age, int politicNumber, String firstName, String lastName, String party,
			String description, String city, String pssword, String picture, String sex, String role, String nation) {
		super();
		this.politicID = politicID;
		this.age = age;
		this.politicNumber = politicNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.party = party;
		this.description = description;
		this.city = city;
		this.pssword = pssword;
		this.picture = picture;
		this.sex = sex;
		this.role = role;
		this.nation = nation;
	}

	public int getPoliticID() {
		return politicID;
	}

	public void setPoliticID(int politicID) {
		this.politicID = politicID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoliticNumber() {
		return politicNumber;
	}

	public void setPoliticNumber(int politicNumber) {
		this.politicNumber = politicNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPssword() {
		return pssword;
	}

	public void setPssword(String pssword) {
		this.pssword = pssword;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	

	}