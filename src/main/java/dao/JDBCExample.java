package dao;

/**
 * This class is used to create a tables for our project/ we are using different connection technique in here
 * @author Jozef
 *
 *
 */
//STEP 1. Import required packages
import java.sql.*;
public class JDBCExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql:";
   //  Database credentials
   static final String USER = "";
   static final String PASS = "";
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      //STEP 4: Execute a query
      System.out.println("Creating table in given database...");
      stmt = conn.createStatement();

      //Table creations

//      String sql = "CREATE TABLE questionsAnswers("
//      		+ "questionAnswerID INT NOT NULL AUTO_INCREMENT,"
//      		+ "answer INT,"
//      		+ "userID INT,"
//      		+ "questionID INT)";  

//		  String sql = "CREATE TABLE questions(" +
//		  "questionID INT NOT NULL AUTO_INCREMENT," + "questionText VARCHAR(250)";

//      String sql = "CREATE TABLE politicians("
//        		+ "politicID INT NOT NULL AUTO_INCREMENT,"
//        		+ "politicNumber INT,"
//        		+ "firstName VARCHAR(50),"
//        		+ "lastName VARCHAR(51),"
//        		+ "nation VARCHAR(50),"
//        		+ "party VARCHAR(50),"
//        		+ "age INT,"
//        		+ "sex VARCHAR(10),"
//        		+ "description VARCHAR(250),"
//        		+ "city VARCHAR(50),"
//        		+ "email VARCHAR(100),"
//        		+ "pssword VARCHAR(50),"
//        		+ "picture VARCHAR(250),"
//        		+ "role VARCHAR(50) DEFAULT candidate)";  
//      String sql = "CREATE TABLE invitationCodes("
//      		+ "invitationID INT NOT NULL AUTO_INCREMENT,"
//      		+ "inviteCode VARCHAR(50),"
//      		+ "usedByPolitician VARCHAR(50))"; 
		/*
		 * String sql = "CREATE TABLE forStatistic(" + "questionId INT," +
		 * "answer INT)";
		 */
      String sql = "INSERT INTO questionsAnswers(answer,userID,questionID"
        		+ ") VALUES "
        		+ "(5,9,1),"
        		+ "(5,9,2),"
        		+ "(1,9,3),"
        		+ "(3,9,4),"
        		+ "(4,9,5),"
        		+ "(4,9,6),"
        		+ "(2,9,7),"
        		+ "(3,9,8),"
        		+ "(2,9,9),"
        		+ "(2,9,10),"
        		+ "(5,9,11)"; 

      stmt.executeUpdate(sql);
      System.out.println("Created table in given database...");
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample