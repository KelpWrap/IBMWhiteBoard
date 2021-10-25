package app;

import java.util.List;
import java.util.Scanner;

import app.sqliteConnect.DbConnector;


public class App {
	private DbConnector dbConnector;
	private LoginHelper loginHelper;
	private User user;

	public App(){
		String path = "C:/Users/oscan/Documents/work/repo/tools/sqlite3/test1.db";
		this.dbConnector = new DbConnector(path);
		this.loginHelper = new LoginHelper();
	}

	public static void main(String[] args) {
		App app = new App();
		while (true){
			System.out.println("Welcome to the Whiteboard! Type Help to get an overview over commands. Type Login to start the log in process.");
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
				case "quit":
				break;
			}

		}
	}

	private void initiateHelp() {
	}

	private void initiateLogin(Scanner input) {
		if (user != null){
			System.out.println("You are already logged in");
			return;
		}
		List<User> userList = loginHelper.logIn(input, dbConnector);
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