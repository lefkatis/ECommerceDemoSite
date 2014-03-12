import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.MessageDigest;

public class DBFetcher {
	
	Connection databaseConnection;

	/**
	*	The constructor method for this class. It initiates the connection to the database
	*	@param userInput a list with Strings as elements that serves as the credentials for connecting to the database
	*/
	public DBFetcher(List<String> userInput) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		databaseConnection = DriverManager.getConnection("jdbc:mysql://"+userInput.get(0)+":3306/"+userInput.get(3), userInput.get(1), userInput.get(2));
	}
		
	/**
	*	The method for returning the connection variable of this class, with no arguments
	*	@return The current connection object
	*/
	public Connection getCurrentConnection() {
		return databaseConnection;
	}

	/**
	*	The method for returning the tweets of a list of users stored in the database
	*	This method checks if a screen name exists in the database and if so, it fetches
	*	their tweets and displays them
	*	@param 	conn 			The connection to the database
	*	@param 	screenNames 	The list of screen names to search for 
	*	@return output 			The list of tweets to display
	*/
	public List<String> validateUser(Connection conn, String username, String password) throws Exception {
		String digest = null;
		List<String> output = new ArrayList<String>();
		Statement getUsers = conn.createStatement();
		String sql = "select * from users where username=\""+username+"\"";
		ResultSet userList = getUsers.executeQuery(sql);
		userList.beforeFirst();
		if (userList.next()) {
			String preHash = password + userList.getString("salt");
			digest = generateMD5(preHash);
	        if (digest.equals(userList.getString("password"))) {
				output.add("User: "+userList.getString("username"));
				System.out.println("login OK");
				return output;
			} else {
				output.add("Incorrect credentials");
				System.out.println("wrong pass");
				return output;
	        }
		} else {
			output.add("User not registered");
			System.out.println("no such user");
			return output;
		}
	}

	public List<String> register (Connection conn, String username, String password, String name, String surname) throws Exception{
		List<String> output = new ArrayList<String>();
		Statement registerUser = conn.createStatement();
		Random randomise = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!£$%^&*()";
		String salt = "";
		for (int i=0; i<15; i++) {
			salt = salt + alphabet.charAt(randomise.nextInt(alphabet.length()));
		}
		String sql = "insert into users (username, password, salt, name, surname) values (\""+username+"\", \""+password+"\", \""+salt+"\", \""+name+"\", \""+surname+"\")";
		registerUser.executeUpdate(sql);
		output.add("Welcome!");
		output.add("Your details are: ");
		output.add("Username: "+ username);
		output.add("Name: "+ name);
		output.add("Surname: "+ surname);
		return output;
	}

	private String generateMD5 (String preHash) {
		String digest =  null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(preHash.getBytes("UTF-8"));
	        //converting byte array to Hexadecimal String
	        StringBuilder sb = new StringBuilder(2*hash.length);
	        for(byte b : hash){
	           sb.append(String.format("%02x", b&0xff));
	        }
	        digest = sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return digest;
    
	}
	
	
	
	// The main method used for testing, it calls all the methods of this class
	public static void main(String[] args) throws Exception {
		List<String> credentials = new ArrayList<String>();
		credentials.add("stusql.dcs.shef.ac.uk");
		credentials.add("team106");
		credentials.add("4758a80b");
		credentials.add("team106");
		/*
		DBFetcher sampleFetcher = new DBFetcher(credentials);
		Connection sampleConnection = sampleFetcher.getCurrentConnection();
		if (sampleFetcher.validateUser(sampleConnection, "user78", "password")) {
			System.out.println("Validation OK");
		} else {
			System.out.println("Validation not OK");
		}
		*/
	}
}
