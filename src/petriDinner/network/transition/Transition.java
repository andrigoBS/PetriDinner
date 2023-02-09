package petriDinner.network.transition;

import petriDinner.network.bow.Bow;

import java.util.ArrayList;
import java.util.List;

public class Transition {
    private List<Bow> bowsIn;
    private List<Bow> bowsOut;
    private String name;

    public Transition(Bow bowIn, Bow bowOut, String name){
        this.bowsIn = new ArrayList<>();
        this.bowsOut = new ArrayList<>();
        this.name = name;

        this.bowsIn.add(bowIn);
        this.bowsOut.add(bowOut);
    }

    public void execute() {

    }

    public boolean isActive(){
        return false;
    }

    public void addBowIn(Bow bowIn){
        this.bowsIn.add(bowIn);
    }

    public void addBowOut(Bow bowOut){
        this.bowsOut.add(bowOut);
    }

    public List<Bow> getBowsIn() {
        return bowsIn;
    }

    public List<Bow> getBowsOut() {
        return bowsOut;
    }

    public String getName() {
        return name;
    }

    private String toStringBows(String label, List<Bow> bows){
        StringBuilder text = new StringBuilder("======== Transition " + label + ": " + this.getName() + "==========");

        for (Bow bow: bows) {
            text.append("place: ").append(bow.getPlaceName());
            text.append("tokens: ").append(bow.getPlaceTokens());
            text.append("weight: ").append(bow.getWeight());
            text.append("bow").append(label).append(": (").append(bow.getPlaceName()).append(" -> ").append(this.getName()).append(") \n");
        }

        return text.toString();
    }

    @Override
    public String toString() {
        String text = this.toStringBows("in", this.getBowsIn());
        text += this.toStringBows("out", this.getBowsOut());
        return text;
    }
}
