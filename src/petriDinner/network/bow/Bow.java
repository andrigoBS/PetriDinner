package petriDinner.network.bow;

import petriDinner.network.place.Place;

public class Bow {
    private final int weight;
    private final Place place;

    public Bow(int weight, Place place){
        this.weight = weight;
        this.place = place;
    }

    public boolean subTokens() {
        return place.subTokens(weight);
    }

    public void addTokens() {
        place.addTokens(weight);
    }

    public boolean canSubTokens() {
        return place.getManyTokens() >= weight;
    }

    public Place getPlace() {
        return place;
    }

    public int getWeight() {
        return weight;
    }

    public String getPlaceName(){
        return this.getPlace().getName();
    }

    public int getPlaceTokens(){
        return this.getPlace().getManyTokens();
    }

}
