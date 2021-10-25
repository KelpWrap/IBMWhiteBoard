package app;

import java.util.List;
import java.util.Scanner;

import app.sqliteConnect.DbConnector;


public class LoginHelper {
    public LoginHelper(){}
    public List<User> logIn(Scanner input, DbConnector dbConnector){
        System.out.println("Enter your username");
		while(!input.hasNext());
		String username = "";
		if (input.hasNext()) username = input.nextLine();
		System.out.println("Enter your password");
		while(!input.hasNext());
		String password = "";
		if (input.hasNext()) password = input.nextLine();
		User user = new User(username, password);
		dbConnector.connect();
		return dbConnector.getUserDataFromDb(user);
    }
    
}
