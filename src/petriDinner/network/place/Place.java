package petriDinner.network.place;


public class Place {
    private int tokens;

    public Place(int tokens){
        this.tokens = tokens;
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

}
