package app.sqliteConnect;


import java.sql.*;

import app.Content;
import app.User;

import java.util.ArrayList;
import java.util.List;



public class DbConnector {
    private String path = null;
    private Connection c = null;

    public DbConnector(String fileName){
        this.path = fileName;
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                conn.getMetaData();
                System.out.println("A new database has been created.");
                DbSetup dbSetup = new dbSetup();
                
                
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            String connectString = "jdbc:sqlite:"+path;
            c = DriverManager.getConnection(connectString);
            c.setAutoCommit(false);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
    }
    public void addContentToDb(Content content) {
        try{
            PreparedStatement stmt = c.prepareStatement("INSERT INTO CONTENT (ID, AUTHOR, BODY) values (?,?,?)");
            stmt.setInt(1, content.getId());
            stmt.setInt(2, content.getCreatorId());
            stmt.setString(3, content.getBody());
            stmt.executeUpdate();
            stmt.close();
            c.commit();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public List<Content> getContent() {
        try{
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM CONTENT");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Content> contentList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            int authorId = rs.getInt("author");
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
            PreparedStatement stmt = c.prepareStatement("INSERT INTO USERS (ID, PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?,?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getAlias());
            stmt.setInt(4, user.getUserLevel());
            stmt.setString(5, user.getUsername());
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
