package petriDinner.network.transition;

import petriDinner.network.bow.Bow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transition {
    private final List<Bow> bowsIn;
    private final List<Bow> bowsOut;
    private final String name;

    public Transition(Bow bowIn, Bow bowOut, String name){
        this.bowsIn = new ArrayList<>();
        this.bowsOut = new ArrayList<>();
        this.name = name;

        this.bowsIn.add(bowIn);
        this.bowsOut.add(bowOut);
    }

    public Transition(String name){
        this.bowsIn = new ArrayList<>();
        this.bowsOut = new ArrayList<>();
        this.name = name;
    }

    public boolean execute() {
        if(!isActive()) return false;
        for (int i = 0; i < bowsIn.size(); i++) {
            boolean isDo = bowsIn.get(i).subTokens();
            if(!isDo) {
                for (int j = 0; j < i; j++) {
                    bowsIn.get(j).addTokens();
                }
                return false;
            }
        }
        bowsOut.forEach(Bow::addTokens);
        return true;
    }

    public boolean isActive(){
        return bowsIn.stream().allMatch(Bow::canSubTokens);
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
        StringBuilder text = new StringBuilder("------ Transitions " + label + ": " + this.getName() + " ------").append("\n");

        for (Bow bow: bows) {
            text.append("place: ").append(bow.getPlaceName()).append("\n");
            text.append("tokens: ").append(bow.getPlaceTokens()).append("\n");
            text.append("weight: ").append(bow.getWeight()).append("\n");

            if(Objects.equals(label, "in")){
                text.append("bow ").append(label).append(": (").append(bow.getPlaceName()).append(" -> ").append(this.getName()).append(") \n").append("\n");
            }else{
                text.append("bow ").append(label).append(": (").append(this.getName()).append(" -> ").append(bow.getPlaceName()).append(") \n").append("\n");
            }
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
