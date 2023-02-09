import petriDinner.PetriDinner;
import petriDinner.network.Network;
import petriDinner.network.transition.Transition;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        String filePath = getPathFile(args);
//        int interactions = getInteractions(args);

//        ArrayList<Transition> transitions = new ArrayList<>();
//        transitions.add(new Transition());
//
//        Network network = new Network(transitions);
//        new PetriDinner(network).simulate(interactions);
    }

    public static String getPathFile(String[] args) {
        if(args != null && args.length > 0){
            return args[0];
        }

        String path = "";
        do {
            path = new Scanner(System.in).next();
        }while (path.length() < 6 || !path.contains(".pnml"));

        return path;
    }

    public static int getInteractions(String[] args) {
        if(args != null && args.length > 1){
            return Integer.parseInt(args[1]);
        }

        int interactions = 0;
        do {
            interactions = new Scanner(System.in).nextInt();
        }while (interactions > 0);

        return interactions;
    }
}