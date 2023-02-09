package petriDinner.network;

import petriDinner.network.transition.Transition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogNetwork {
    private static final LogNetwork instance = new LogNetwork();
    public static void put(List<Transition> transitions, int interactions) {
        instance.putLog(transitions, interactions);
    }
    public static LogNetwork getInstance() {
        return instance;
    }


    private final List<String> logByInteraction;
    public LogNetwork() {
        logByInteraction = new ArrayList<>();
    }
    public void putLog(List<Transition> transitions, int interactions) {
        String text = "";

        for (Transition transition : transitions) {
            text += "======= INTERATIONS = " + interactions + " ======\n\n";
            text += transition.toString();
            text += "==============================================\n\n";
        }

        logByInteraction.add(text);
    }
    public void toFiles(String basePath) throws IOException {
        for (int i = 0; i < logByInteraction.size(); i++) {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(basePath+"interaction"+i+".txt"));
            myWriter.write(logByInteraction.get(i));
            myWriter.flush();
            myWriter.close();
        }
    }

    public List<String> getLogs() {
        return logByInteraction;
    }
}
