package app.sqliteConnect;

import java.sql.*;

public class DbSetup {

    public DbSetup(){
    }
    
    public void setupTables(String url){
        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID integer PRIMARY KEY, PASSWORD text, ALIAS text, USERLEVEL integer, USERNAME text)";
        try (Connection conn = DriverManager.getConnection(url)){
        Statement stmt1 = conn.createStatement();
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
        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?)");
            stmt.setString(1, "");
            stmt.setString(2, "TeamMember");
            stmt.setInt(3, 0);
            stmt.setString(4, "Team1");
            stmt.executeUpdate();
            stmt.close();
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?)");
            stmt2.setString(1, "bestBert");
            stmt2.setString(2, "Bertha");
            stmt2.setInt(3, 1);
            stmt2.setString(4, "Bert1");
            stmt2.executeUpdate();
            stmt2.close();
            PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?)");
            stmt3.setString(1, "superSam");
            stmt3.setString(2, "Sam Wise");
            stmt3.setInt(3, 2);
            stmt3.setString(4, "Sam1");
            stmt3.executeUpdate();
            stmt3.close();
            PreparedStatement stmt4 = conn.prepareStatement("INSERT INTO USERS (PASSWORD, ALIAS, USERLEVEL, USERNAME) values (?,?,?,?)");
            stmt4.setString(1, "admin");
            stmt4.setString(2, "admin");
            stmt4.setInt(3, 3);
            stmt4.setString(4, "admin");
            stmt4.executeUpdate();
            stmt4.close();
            conn.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
}
