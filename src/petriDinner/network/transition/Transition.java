package petriDinner.network.transition;

import petriDinner.network.bow.Bow;

import java.util.ArrayList;
import java.util.List;

public class Transition {
    private List<Bow> bowsIn;
    private List<Bow> bowsOut;

    public Transition(Bow bowIn, Bow bowOut){
        this.bowsIn = new ArrayList<>();
        this.bowsOut = new ArrayList<>();

        this.bowsIn.add(bowIn);
        this.bowsOut.add(bowOut);
    }

    public void execute() {

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
}
