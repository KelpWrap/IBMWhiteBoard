package app.sqliteConnect;


import java.sql.*;
import app.User;

import java.util.ArrayList;
import java.util.List;


public class DbConnector {
    private String path = null;
    private Connection c = null;

    public DbConnector(String path){
        this.path = path;
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
            stmt.executeUpdate("VACUUM");
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
