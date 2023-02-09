package petriDinner.network.place;


public class Place {
    private int tokens;
    private String name;

    public Place(int tokens, String name){
        this.tokens = tokens;
        this.name = name;
    }

    public void addTokens(int tokens){
        this.tokens += tokens;
    }

    public void subTokens(int tokens){
        this.tokens -= tokens;
    }

    public int getTokens() {
        return tokens;
    }

    public String getName() {
        return name;
    }
}
