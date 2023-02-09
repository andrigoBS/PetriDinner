import org.xml.sax.SAXException;
import petriDinner.examples.Examples;
import petriDinner.network.LogNetwork;
import petriDinner.network.Network;
import petriDinner.parserPNML.ParserPNML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, SAXException {

        ParserPNML parserPNML = new ParserPNML();
        Network network = parserPNML.toNetwork("./assets/example2.pnml");
        network.simulate(10);

        LogNetwork.getInstance().toFiles("./assets/logs/");


//        Examples examples = new Examples();
//        Network networkCandyMachine = examples.candyMachine();
//        networkCandyMachine.simulate(10);

    }

    public static String getPathFile(String[] args) {
        if (args != null && args.length > 0) {
            return args[0];
        }

        String path = "";
        do {
            path = new Scanner(System.in).next();
        } while (path.length() < 6 || !path.contains(".pnml"));

        return path;
    }

    public static int getInteractions(String[] args) {
        if (args != null && args.length > 1) {
            return Integer.parseInt(args[1]);
        }

        int interactions = 0;
        do {
            interactions = new Scanner(System.in).nextInt();
        } while (interactions > 0);

        return interactions;
    }
}