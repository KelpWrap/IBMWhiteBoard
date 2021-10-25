package app;

public class Content {
    private int id;
    private int creatorId;
    private String body;

    public Content(int id, int creatorId, String body){
        this.id = id;
        this.creatorId = creatorId;
        this.body = body;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getCreatorId(){
        return this.creatorId;
    }
    public void setCreatorId(int creatorId){
        this.creatorId = creatorId;
    }
    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body = body;
    }
    
}
