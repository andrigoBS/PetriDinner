package petriDinner.network.place;

public class Place {
//    private int weight;
    private int tokens;


    public Place(int weight, int tokens){
//        this.weight = weight;
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
//
//    public int getWeight() {
//        return weight;
//    }
}
