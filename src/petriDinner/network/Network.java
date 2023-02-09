package petriDinner.network;

import petriDinner.network.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Network {
    private List<Transition> transitions;
    private int interactions;
    private Semaphore semaphoreInteractions;

    public Network(){
        this.transitions = new ArrayList<>();
        this.interactions = 0;
        this.semaphoreInteractions = new Semaphore(1);
    }

    public void addTransition(Transition transition){
        this.transitions.add(transition);
    }

    private class TransactionThread extends Thread{
        private Transition transition;

        public TransactionThread(Transition transition) {
            this.transition = transition;
        }

        @Override
        public void run() {
            transition.execute();
            try {
                semaphoreInteractions.acquire();
                interactions++;
                semaphoreInteractions.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
