import java.util.concurrent.Semaphore;

public class CrossingCanyon <E>{

    private static final int ROPECAPACITY = 5; // at value 0 the semaphore stops
    private Semaphore toWestMutex;
    private Semaphore toEastMutex;
    private Semaphore empty; //1 if empty, 0 if full

    private Semaphore toWestCount;
    private Semaphore toEastCount;
    private Semaphore blockingEntrance; //for starvation
    private int toWestCounter; //ensure that just 5 baboons can be at the rope at the same time
    private int toEastCounter;

    public CrossingCanyon(){
        this.toWestMutex = new Semaphore(1);
        this.toEastMutex = new Semaphore(1);
        this.empty = new Semaphore(1);
        this.toWestCounter = 0;
        this.toEastCounter = 0;
        this.toWestCount = new Semaphore(ROPECAPACITY);
        this.toEastCount = new Semaphore(ROPECAPACITY);
        this.blockingEntrance = new Semaphore(1);
    }


    public void acquireGoToWest() throws InterruptedException {

        blockingEntrance.acquire(); //changing its position leads to deadlock
        toWestCount.acquire();
        toWestMutex.acquire();
        blockingEntrance.release(); //changing its position leads to deadlock

        this.toWestCounter++;
        if (this.toWestCounter == 1){ //the first baboon crossing to west
            empty.acquire(); // the way is reserved as to west now
        }

        toWestMutex.release();

    }


    public void releaseGoToWest() throws InterruptedException {

        toWestCount.release();
        toWestMutex.acquire();

        this.toWestCounter--;
        if(this.toWestCounter == 0){
            empty.release(); //the way is empty now
        }

        toWestMutex.release();

    }

    public void acquireGoToEast() throws InterruptedException {

        blockingEntrance.acquire();
        toEastCount.acquire();
        toEastMutex.acquire();
        blockingEntrance.release();

        this.toEastCounter++;
        if (this.toEastCounter == 1){
            empty.acquire(); // the way is reserved as to east now
        }

        toEastMutex.release();

    }

    public void releaseGoToEast() throws InterruptedException {

        toEastCount.release();
        toEastMutex.acquire();

        this.toEastCounter--;
        if (this.toEastCounter == 0){
            empty.release(); //the way is empty now
        }

        toEastMutex.release();

    }

}
