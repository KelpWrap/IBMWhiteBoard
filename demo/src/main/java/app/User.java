package app;

public class User {
    private int id;
    private String password;
    private String alias;
    private int userLevel;
    private String username;

    public User(int id,  String userName, String password, String alias, int userLevel){
        this.id = id;
        this.alias = alias;
        this.userLevel = userLevel;
        this.username = userName;
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setAlias(String alias){
        this.alias = alias;
    }
    
    public String getAlias(){
        return this.alias;
    }

    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setUserLevel(int userLevel){
        this.userLevel = userLevel;
    }
    
    public int getUserLevel(){
        return this.userLevel;
    }

    
    
}
