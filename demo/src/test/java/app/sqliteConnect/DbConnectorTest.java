package app.sqliteConnect;

import org.junit.jupiter.api.Test;
import app.User;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest

public class DbConnectorTest {
    User testUser1 = new User(1,"John Smith", "abc" , "John Smith", 1);
    String path = "C:/Users/oscan/Documents/work/repo/tools/sqlite3/test1.db";
    DbConnector dbConnector = new DbConnector(path);

    @Test
    public void connectTest() throws Exception {
        String path = "C:/Users/oscan/Documents/work/repo/tools/sqlite3/test1.db";
        DbConnector dbConnector = new DbConnector(path);
        dbConnector.connect();
        dbConnector.disconnect();
    }
    @Test
    public void addAndLoadUserTest() throws Exception{
        dbConnector.connect();
        dbConnector.addUserToDb(testUser1);
        List<User> usersList = dbConnector.getUserDataFromDb(testUser1);
        Assert.isTrue(usersList.get(0).getUsername().equals(testUser1.getUsername()), "Username is not correctly loaded");
        dbConnector.clearDb();
        dbConnector.disconnect();
    }

    @Test
    public void

    

    @Test
    public void clearDbTest() throws Exception{
        dbConnector.connect();
        dbConnector.clearDb();
        dbConnector.disconnect();
    }
}