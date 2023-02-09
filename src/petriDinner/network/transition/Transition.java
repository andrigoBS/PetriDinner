package petriDinner.network.transition;

import petriDinner.network.place.Place;

import java.util.ArrayList;
import java.util.List;

public class Transition {
    private List<Place> placesIn;
    private List<Place> placesOut;

    public Transition(Place placeIn, Place placeOut){
        this.placesIn = new ArrayList<>();
        this.placesOut = new ArrayList<>();

        this.placesIn.add(placeIn);
        this.placesOut.add(placeOut);
    }

    public void execute() {

    }

    public boolean isActive() {
        return false;
    }

    public void addPlaceIn(Place placeIn){
        this.placesIn.add(placeIn);
    }

    public void addPlaceOut(Place placeOut){
        this.placesOut.add(placeOut);
    }

    public List<Place> getPlacesIn() {
        return placesIn;
    }

    public List<Place> getPlacesOut() {
        return placesOut;
    }
}
