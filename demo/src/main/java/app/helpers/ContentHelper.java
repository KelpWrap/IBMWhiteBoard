package app.helpers;

import java.util.Scanner;

import app.Content;
import app.sqliteConnect.DbConnector;

public class ContentHelper {
    public ContentHelper(){
    }
    public void addContent(Scanner input, DbConnector dbConnector, String alias){
        dbConnector.connect();
        System.out.println("Enter your note! Finish with enter");
		while(!input.hasNext());
		String body = "";
		if (input.hasNext()) body = input.nextLine();
        Content content = new Content(alias, body);
        dbConnector.addContentToDb(content);
    }

}
