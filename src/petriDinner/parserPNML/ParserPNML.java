package petriDinner.parserPNML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import petriDinner.network.Network;
import petriDinner.network.bow.Bow;
import petriDinner.network.place.Place;
import petriDinner.network.transition.Transition;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParserPNML {


    public Network toNetwork(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(filename));
        Network network = new Network();

        NodeList listPage = doc.getElementsByTagName("page");

        for (int i = 0; i < listPage.getLength(); i++) {

            Node node = listPage.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                Map<String, Place> places = this.createPlaces(element);
                Map<String, Transition> transitions = this.createTransitions(element);
                Map<String, Transition> transitionsWithArcs = this.createBows(element, transitions, places);
                network = this.createNetwork(transitionsWithArcs);
            }
        }

        return network;
    }


    private Map<String, Place> createPlaces(Element element){
        NodeList listPlaces = element.getElementsByTagName("place");
        Map<String, Place> places = new HashMap<>();

        for (int i = 0; i < listPlaces.getLength(); i++) {
            Node node = listPlaces.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element placeElement = (Element) node;
                String id = placeElement.getAttribute("id");

                Element nameElement = (Element) placeElement.getElementsByTagName("name").item(0);
                String text = nameElement.getElementsByTagName("text").item(0).getTextContent();

                Element initialMarkingElement = (Element) placeElement.getElementsByTagName("initialMarking").item(0);
                int initialMarking = 0;

                if(initialMarkingElement != null){
                   initialMarking = Integer.parseInt(initialMarkingElement.getElementsByTagName("text").item(0).getTextContent());
                }

                Place place = new Place(initialMarking, text);
                places.put(id, place);
            }
        }
        return places;
    }


    private Map<String, Transition> createTransitions(Element element){
        NodeList listTransitions = element.getElementsByTagName("transition");
        Map<String, Transition> transitions = new HashMap<>();

        for (int i = 0; i < listTransitions.getLength(); i++) {
            Node node = listTransitions.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element transitionElement = (Element) node;
                String id = transitionElement.getAttribute("id");

                Element nameElement = (Element) transitionElement.getElementsByTagName("name").item(0);
                String text = nameElement.getElementsByTagName("text").item(0).getTextContent();

                Transition transition = new Transition(text);
                transitions.put(id, transition);
            }
        }
        return transitions;
    }

    private Map<String, Transition> createBows(Element element, Map<String, Transition> transitions, Map<String, Place> places){
        NodeList listBows = element.getElementsByTagName("arc");

        for (int i = 0; i < listBows.getLength(); i++) {
            Node node = listBows.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element bowElement = (Element) node;
                String source = bowElement.getAttribute("source");
                String target = bowElement.getAttribute("target");

                Element weightElement = (Element) bowElement.getElementsByTagName("inscription").item(0);
                int weight = Integer.parseInt(weightElement.getElementsByTagName("text").item(0).getTextContent());

                if(transitions.containsKey(source)){
                    Bow bow = new Bow(weight, places.get(target));
                    transitions.get(source).addBowOut(bow);
                }else{
                    Bow bow = new Bow(weight, places.get(source));
                    transitions.get(target).addBowIn(bow);
                }
            }
        }
        return transitions;
    }

    private Network createNetwork(Map<String, Transition> transitionsWithArcs){
        Network network = new Network();
        transitionsWithArcs.values().forEach(network::addTransition);
        return network;
    }
}
