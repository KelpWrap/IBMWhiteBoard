package app.helpers;

import java.util.Scanner;

import app.Content;
import app.User;
import app.sqliteConnect.DbConnector;

public class ContentHelper {
    public ContentHelper(){
    }
    public void addContent(Scanner input, DbConnector dbConnector, String alias){
        System.out.println("Enter your note! Finish with enter");
		while(!input.hasNext());
		String body = "";
		if (input.hasNext()) body = input.nextLine();
        Content content = new Content(alias, body);
        dbConnector.addContentToDb(content);
    }
    public void deleteContent(Scanner userInput, DbConnector dbConnector, User user) {
        //not implemented yet
    }

}
