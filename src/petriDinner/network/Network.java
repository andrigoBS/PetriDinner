package petriDinner.network;

import petriDinner.network.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Transition> transitions;

    public Network(Transition transition){
        this.transitions = new ArrayList<>();
        this.transitions.add(transition);
    }

    public void addTransition(Transition transition){
        this.transitions.add(transition);
    }
}
