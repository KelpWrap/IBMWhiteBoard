package app.sqliteConnect;

import java.sql.*;

public class DbSetup {

    public DbSetup(){
    }
    
    public void setupTables(String url){
        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID integer PRIMARY KEY, PASSWORD text, ALIAS text, USERLEVEL integer, USERNAME text)";
        try (Connection conn = DriverManager.getConnection(url);
        Statement stmt1 = conn.createStatement()) {
        // create a new table
        stmt1.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS CONTENT (ID integer PRIMARY KEY, AUTHOR text, BODY text, POSx text, POSy text)";
        Statement stmt2 = conn.createStatement();
        // create a new table
        stmt2.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetData(String url) {
        String sql = "INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) VALUES (?,?,?,?)";
        
    }
    
}
