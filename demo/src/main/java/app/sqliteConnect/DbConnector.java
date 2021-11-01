package app.sqliteConnect;


import java.sql.*;

import app.Content;
import app.User;

import java.util.ArrayList;
import java.util.List;



public class DbConnector {
    private String url = null;
    private Connection c = null;
    private DbSetup dbSetup = new DbSetup();

    public DbConnector(String fileName){
        
        url = "jdbc:sqlite:" + fileName;
        try  {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection(url);
            c.setAutoCommit(false);
            if (c != null) {
                dbSetup.setupTables(c);
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
    }

    public void resetDb(){
        clearDb();
        dbSetup.resetData(c);
    }

    public void addContentToDb(Content content) {
        try{
            PreparedStatement stmt = c.prepareStatement("INSERT INTO CONTENT (AUTHOR, BODY) values (?,?)");
            stmt.setString(1, content.getAuthor());
            stmt.setString(2, content.getBody());
            stmt.executeUpdate();
            stmt.close();
            c.commit();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
    public void deleteContent(int contentId) {
        try{
            PreparedStatement stmt = c.prepareStatement("DELETE FROM CONTENT WHERE id =  (?)");
            stmt.setInt(1, contentId);
            stmt.executeUpdate();
            stmt.close();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public List<Content> getAllContent() {
        try{
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM CONTENT");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Content> contentList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String authorId = rs.getString("author");
            String body = rs.getString("body");
            contentList.add(new Content(id, authorId, body));
        }
        rs.close();
        stmt.close();
        c.commit();
        return contentList;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }
    }

    public List<Content> getContentByUser(User user) {
        try{
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM CONTENT WHERE AUTHOR = ?");
        stmt.setString(1, user.getAlias());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Content> contentList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String authorId = rs.getString("author");
            String body = rs.getString("body");
            contentList.add(new Content(id, authorId, body));
        }
        rs.close();
        stmt.close();
        c.commit();
        return contentList;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }
    }

    
    public void addUserToDb(User user){
        try{
            PreparedStatement stmt = c.prepareStatement("INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?,?)");
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getAlias());
            stmt.setInt(3, user.getUserLevel());
            stmt.setString(4, user.getUsername());
            stmt.executeUpdate();
            stmt.close();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void updateUser(User user) {
        try{
            PreparedStatement stmt = c.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE id = ?");
            stmt.setInt(2, user.getId());
            stmt.setString(1, user.getPassword());
            stmt.executeUpdate();
            stmt.close();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }


    public List<User> getUserDataFromDb(User user){
        try{
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM USERS WHERE PASSWORD = ? AND USERNAME = ?");
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> userList = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String alias = rs.getString("alias");
                String password = rs.getString("password");
                int userLevel = rs.getInt("userLevel");
                String userName = rs.getString("userName");
                userList.add(new User(id, userName, password, alias, userLevel));
            }
            rs.close();
            stmt.close();
            c.commit();
            return userList;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }

    }

    public void clearDb() {
        try{
            Statement stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM USERS");
            stmt.close();
            c.commit();
            Statement stmt2 = c.createStatement();
            stmt2.executeUpdate("DELETE FROM CONTENT");
            stmt2.close();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
    public void disconnect(){ 
        try{
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
    }





    
    
}
