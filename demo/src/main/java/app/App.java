package app;

import java.util.List;
import java.util.Scanner;

import app.helpers.ContentHelper;
import app.helpers.LoginHelper;
import app.helpers.PasswordHelper;
import app.sqliteConnect.DbConnector;


public class App {
	private DbConnector dbConnector;
	private LoginHelper loginHelper;
	private PasswordHelper passwordHelper;
	private ContentHelper contentHelper;
	private User user;
	

	public App(){
		String path = "WhiteBoard.db";
		this.dbConnector = new DbConnector(path);
		this.loginHelper = new LoginHelper();
		this.passwordHelper = new PasswordHelper();
		this.contentHelper = new ContentHelper();
	}

	public User getCurrentUser(){
		return this.user;
	}

	public static void main(String[] args) {
		boolean isRunning = true;
		App app = new App();
		while (isRunning){
			if (app.getCurrentUser() == null) {
				System.out.println("Welcome to the Whiteboard! Type Help to get an overview over commands. Type Login to start the log in process.");
			}
			else {
				app.DisplayWhiteboard();
			}
			// Using Scanner for Getting Input from User
			Scanner userInput = new Scanner(System.in);
			while(!userInput.hasNext());
			String inputString = "";
			if (userInput.hasNext()) inputString = userInput.nextLine();
			switch(inputString.toLowerCase()){
				case "login":
				app.initiateLogin(userInput);
				break;
				case "help":
				app.initiateHelp();
				break;
				case "change password":
				app.initiatePasswordChange(userInput);
				break;
				case "write a note":
				app.initiateNewContent(userInput);
				break;
				case "delete a note":
				app.initiateDeleteContent(userInput);
				break;
				case "resetdb":
				app.initiateResetDb();
				System.out.println("database reset");
				break;
				case "log out":
				app.logOut();
				break;
				case "quit":
				app.quit();
				isRunning = false;
				System.out.println("The program can be closed now.");
				break;
			}

		}
	}

	private void initiateResetDb() {
		dbConnector.connect();
		dbConnector.resetDb();
		dbConnector.disconnect();
	}

	private void initiateDeleteContent(Scanner userInput) {
		dbConnector.connect();
		contentHelper.deleteContent(userInput, dbConnector, user);
		dbConnector.disconnect();
	}

	private void initiateNewContent(Scanner userInput) {
		dbConnector.connect();
		contentHelper.addContent(userInput, dbConnector, user.getAlias());
		dbConnector.disconnect();
	}

	private void initiatePasswordChange(Scanner userInput) {
		if (this.user.getUserLevel() == 0){
			System.out.println("The standard team account can not have its password changed");
		} else {
			passwordHelper.changePassword(userInput, dbConnector, user.getUsername());
		}
	}

	private void logOut() {
		this.user = null;
	}

	private void quit() {
		dbConnector.connect();
		dbConnector.disconnect();
	}

	private void DisplayWhiteboard() {
		dbConnector.connect();
		List<Content> contentList = dbConnector.getAllContent();
		for (Content content : contentList){
			System.out.println(content.toStringNoId());
		}
		dbConnector.disconnect();
	}

	private void initiateHelp() {
		System.out.println("Login: initiates the log in process. Once logged in, the content of the whiteboard is displayed in the terminal.");

		System.out.println("Help: Gives information over all available commands.");
		
		System.out.println("change Password: allows a logged in user to change password.");
		
		System.out.println("write a note: Allows you to write a new note on the whiteboard.");
		
		System.out.println("delete a note: Depending on account level, lets you delete either none, your own or all posts.");
		
		System.out.println("add user: Allows the mods and admins to add more private accounts."); 
		
		System.out.println("reset db: Populates db with standard users.");
		
		System.out.println("log out: logs you out of the account");
		
		System.out.println("quit: closes the program");
	}

	private void initiateLogin(Scanner input) {
		if (user != null){
			System.out.println("You are already logged in");
			return;
		}
		List<User> userList = loginHelper.logIn(input, dbConnector);
		dbConnector.disconnect();
		if(userList.size() == 1){
			this.user = userList.get(0);
			System.out.println(String.format("Login Successful! Welcome %s", user.getAlias()));
			return;
		}
		else {
			System.out.println("Login failed try again.");
			return;
		}
	}

}