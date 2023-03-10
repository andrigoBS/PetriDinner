package petriDinner.examples;

import petriDinner.network.Network;
import petriDinner.network.bow.Bow;
import petriDinner.network.place.Place;
import petriDinner.network.transition.Transition;

public class Examples {

    public Network candyMachine() {
        Place place1 = new Place(1, "a");
        Place place2 = new Place(0, "b");
        Place place3 = new Place(0, "c");
        Place place4 = new Place(0, "d");
        Place place5 = new Place(0, "e");

        Bow bow1 = new Bow(1, place1);
        Bow bow2 = new Bow(1, place2);
        Transition transition1 = new Transition(bow1, bow2, "t1");

        Bow bow3 = new Bow(1, place1);
        Bow bow4 = new Bow(1, place5);
        Transition transition2 = new Transition(bow3, bow4, "t7");

        Bow bow5 = new Bow(1, place2);
        Bow bow6 = new Bow(1, place3);
        Transition transition3 = new Transition(bow5, bow6, "t2");

        Bow bow7 = new Bow(1, place2);
        Bow bow8 = new Bow(1, place5);
        Transition transition4 = new Transition(bow7, bow8, "t6");

        Bow bow9 = new Bow(1, place5);
        Bow bow10 = new Bow(1, place3);
        Transition transition5 = new Transition(bow9, bow10, "t5");

        Bow bow11 = new Bow(1, place5);
        Bow bow12 = new Bow(1, place4);
        Transition transition6 = new Transition(bow11, bow12, "t4");

        Bow bow13 = new Bow(1, place3);
        Bow bow14 = new Bow(1, place4);
        Transition transition7 = new Transition(bow13, bow14, "t3");

        Bow bow15 = new Bow(1, place3);
        Bow bow16 = new Bow(1, place1);
        Transition transition8 = new Transition(bow15, bow16, "t9");

        Bow bow17 = new Bow(1, place4);
        Bow bow18 = new Bow(1, place1);
        Transition transition9 = new Transition(bow17, bow18, "t8");


        Network network = new Network();
        network.addTransition(transition1);
        network.addTransition(transition2);
        network.addTransition(transition3);
        network.addTransition(transition4);
        network.addTransition(transition5);
        network.addTransition(transition6);
        network.addTransition(transition7);
        network.addTransition(transition8);
        network.addTransition(transition9);

        return network;
    }

    public Network example1() {
        Place place1 = new Place(1, "a");
        Place place2 = new Place(0, "b");
        Place place3 = new Place(0, "c");

        Bow bow1 = new Bow(1, place1);
        Bow bow2 = new Bow(1, place2);
        Transition transition1 = new Transition(bow1, bow2, "t1");
        transition1.addBowOut(bow2);

        Bow bow3 = new Bow(1, place2);
        Bow bow4 = new Bow(1, place3);
        Transition transition2 = new Transition(bow3, bow4, "t2");

        Network network = new Network();
        network.addTransition(transition1);
        network.addTransition(transition2);

        return network;
    }

}
