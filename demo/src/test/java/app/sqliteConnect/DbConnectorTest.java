package app.sqliteConnect;

import org.junit.jupiter.api.Test;

import app.Content;
import app.User;

import org.springframework.util.Assert;

import java.util.List;



public class DbConnectorTest {
    User testUser1 = new User(1,"John Smith", "abc" , "John Smith", 1);
    Content testContent1 = new Content(1,"John Smith","this is very interesting");
    String path = "C:/Users/oscan/Documents/work/repo/tools/sqlite3/test1.db";
    DbConnector dbConnector = new DbConnector(path);

    @Test
    public void connectTest() throws Exception {
        String path = "C:/Users/oscan/Documents/work/repo/tools/sqlite3/test1.db";
        DbConnector dbConnector = new DbConnector(path);
        dbConnector.connect();
        dbConnector.clearDb();
        dbConnector.disconnect();
    }

    @Test
    public void addAndLoadUserTest() throws Exception{
        dbConnector.connect();
        dbConnector.addUserToDb(testUser1);
        List<User> usersList = dbConnector.getUserDataFromDb(testUser1);
        Assert.isTrue(usersList.get(0).getUsername().equals(testUser1.getUsername()), "Username is not correctly loaded");
        dbConnector.disconnect();
    }

    @Test void addandLoadContentTest() throws Exception{
        dbConnector.connect();
        dbConnector.addContentToDb(testContent1);
        List<Content> contentList = dbConnector.getAllContent();
        Assert.isTrue(contentList.get(0).getId() == (testContent1.getId()), "Content is not correctly loaded.");
        dbConnector.disconnect();
    }
    

}