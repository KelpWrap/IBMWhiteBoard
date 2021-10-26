package app;

import java.util.List;
import java.util.Scanner;

import app.helpers.LoginHelper;
import app.helpers.PasswordHelper;
import app.sqliteConnect.DbConnector;


public class App {
	private DbConnector dbConnector;
	private LoginHelper loginHelper;
	private PasswordHelper passwordHelper;
	private User user;
	

	public App(){
		String path = "C:/Users/oscan/sqlite3/WhiteBoard3.db";
		this.dbConnector = new DbConnector(path);
		this.loginHelper = new LoginHelper();
		this.passwordHelper = new PasswordHelper();
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
				case "change Password":
				app.initiatePasswordChange(userInput);
				break;
				case "write a note":
				app.initiateNewContent(userInput);
				break;
				case "delete a note":
				app.initiateDeleteContent(userInput);
				break;
				case "log out":
				app.logOut();
				break;
				case "quit":
				app.quit();
				isRunning = false;
				break;
			}

		}
	}

	private void initiateDeleteContent(Scanner userInput) {
	}

	private void initiateNewContent(Scanner userInput) {
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
		List<Content> contentList = dbConnector.getContent();
		for (Content content : contentList){
			System.out.println(content.toString());
		}
		dbConnector.disconnect();
	}

	private void initiateHelp() {
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