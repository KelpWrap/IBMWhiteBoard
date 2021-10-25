package app;

import java.util.Scanner;


public class App {

	public App(){}

	public static void main(String[] args) {
		App app = new App();
		while (true){
			System.out.println("Welcome to the Whiteboard. Type Help to get an overview over commands. Type Login the log in process.");
			// Using Scanner for Getting Input from User
			Scanner userInput = new Scanner(System.in);
			while(!userInput.hasNext());

			String input = "";
			if (userInput.hasNext()) input = userInput.nextLine();
			switch(input.toLowerCase()){
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

	private void initiateLogin(Scanner innput) {

	}

}