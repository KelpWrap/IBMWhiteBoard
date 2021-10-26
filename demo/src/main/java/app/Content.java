package app;

public class Content {
    private int id;
    private String author;
    private String body;

    public Content(int id, String author, String body){
        this.id = id;
        this.author = author;
        this.body = body;
    }

    public Content(String author, String body){
        this.author = author;
        this.body = body;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body = body;
    }

    public String toStringNoId() {
        return String.format("%d: %s",this.author, this.body);
    }

    public String toString() {
        return String.format("ID: %d, %d: %s",this.id, this.author, this.body);
    }
    
}
