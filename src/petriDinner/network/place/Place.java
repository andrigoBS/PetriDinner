package petriDinner.network.place;


import java.util.concurrent.Semaphore;

public class Place {
    private final Semaphore place;
    private final String name;

    public Place(int initialManyTokens, String name){
        this.place = new Semaphore(initialManyTokens);
        this.name = name;
    }

    public void subTokens(int manyTokens) throws InterruptedException {
        place.acquire(manyTokens);
    }

    public void addTokens(int manyTokens) {
        place.release(manyTokens);
    }

    public int getManyTokens() {
        return place.availablePermits();
    }

    public String getName() {
        return name;
    }
}
