package petriDinner.network.bow;

import petriDinner.network.place.Place;

public class Bow {
    private int weight;
    private Place place;

    public Bow(int weight, Place place){
        this.weight = weight;
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public int getWeight() {
        return weight;
    }
}
