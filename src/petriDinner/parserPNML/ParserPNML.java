package petriDinner.parserPNML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import petriDinner.network.Network;
import petriDinner.network.place.Place;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserPNML {


    public Network toNetwork(String filename) throws ParserConfigurationException, IOException, SAXException {
        // https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(filename));
        //doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");


        NodeList listPage = doc.getElementsByTagName("page");


        for (int i = 0; i < listPage.getLength(); i++) {

            Node node = listPage.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                List<Place> places = this.createPlaces(element);
                NodeList listArc = element.getElementsByTagName("arc");
                NodeList transition = element.getElementsByTagName("transition");
//
//                String id = element.getAttribute("id");
//
//                String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
//                String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
//                String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();
//
//                NodeList salaryNodeList = element.getElementsByTagName("salary");
//                String salary = salaryNodeList.item(0).getTextContent();
//
//                String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();
//
//                System.out.println("Current Element :" + node.getNodeName());
//                System.out.println("Staff Id : " + id);
//                System.out.println("First Name : " + firstname);
//                System.out.println("Last Name : " + lastname);
//                System.out.println("Nick Name : " + nickname);
//                System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);
            }
        }

        return null;
    }


    private List<Place> createPlaces(Element element){
        NodeList listPlaces = element.getElementsByTagName("place");
        List<Place> places = new ArrayList<>();

        for (int i = 0; i < listPlaces.getLength(); i++) {
            Node node = listPlaces.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element placeElement = (Element) node;

                Element nameElement = (Element) placeElement.getElementsByTagName("name").item(0);
                String text = nameElement.getElementsByTagName("text").item(0).getTextContent();

                Element initialMarkingElement = (Element) placeElement.getElementsByTagName("initialMarking").item(0);
                int initialMarking = 0;

                if(initialMarkingElement != null){
                   initialMarking = Integer.parseInt(initialMarkingElement.getElementsByTagName("text").item(0).getTextContent());
                }

                Place place = new Place(initialMarking, text);
                places.add(place);
            }
        }
        return places;
    }
}
