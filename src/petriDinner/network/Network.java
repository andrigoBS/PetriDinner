package petriDinner.network;

import petriDinner.network.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Network {
    private final List<Transition> transitions;
    private final Semaphore semaphoreInteractions;
    private int interactions;
    private Semaphore synchronization;

    public Network() {
        this.transitions = new ArrayList<>();
        this.interactions = 0;
        this.semaphoreInteractions = new Semaphore(1);
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public void simulate(int maxInteractions) throws InterruptedException {
        LogNetwork.put(transitions, interactions);

        while (interactions < maxInteractions) {
            List<Transition> activeTransitions = transitions.stream().filter(Transition::isActive).toList();

            if (activeTransitions.size() == 0) break;

            synchronization = new Semaphore(0);

            activeTransitions.forEach(transition -> {
                new TransactionThread(transition).start();
            });

            synchronization.acquire(activeTransitions.size());
        }
    }

    private class TransactionThread extends Thread {
        private final Transition transition;

        public TransactionThread(Transition transition) {
            this.transition = transition;
        }

        @Override
        public void run() {
            boolean isDo = transition.execute();
            if (isDo) {
                try {
                    semaphoreInteractions.acquire();
                    interactions++;
                    LogNetwork.put(transitions, interactions);
                    semaphoreInteractions.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronization.release();
        }
    }
}
