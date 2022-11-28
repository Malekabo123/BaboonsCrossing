import java.util.concurrent.Semaphore;

public class CrossingCanyon <E>{

    private static final int ROPECAPACITY = 5;
    private Semaphore toWestMutex;
    private Semaphore toEastMutex;
    private Semaphore empty; //1 if empty, 0 if full

    private Semaphore toWestCount;
    private Semaphore toEastCount;
    private int toWestCounter;
    private int toEastCounter;
    //private int onRope;

    public CrossingCanyon(){
        this.toWestMutex = new Semaphore(1);
        this.toEastMutex = new Semaphore(1);
        this.empty = new Semaphore(1);
        this.toEastCounter = 0;
        this.toWestCounter = 0;
        this.toWestCount = new Semaphore(ROPECAPACITY);
        this.toEastCount = new Semaphore(ROPECAPACITY);
    }


    public void goToWest() throws InterruptedException {

        toWestMutex.acquire();
        toWestCounter++;
        if (toWestCounter == 1){ //the first baboon crossing to west
            empty.acquire(); // the way is reserved as to west now
        }
        toWestMutex.release();


        toWestCount.acquire();// the allowed number of baboons going west is decreased by one

        System.out.println("I am on the rope going west, count= "+toWestCounter);
        System.out.println("Direction is: west");

        toWestCount.release();


        toWestMutex.acquire();
        toWestCounter--;
        if(toWestCounter == 0){
            empty.release(); //the way is empty now
        }
        toWestMutex.release();

    }


    public void goToEast() throws InterruptedException {

        toEastMutex.acquire();
        toEastCounter++;
        if (toEastCounter == 1){
            empty.acquire(); // the way is reserved as to east now
        }
        toEastMutex.release();


        toEastCount.acquire();

        System.out.println("I am on the rope going east, count= "+toEastCounter);
        System.out.println("Direction is: east");

        toEastCount.release();


        toEastMutex.acquire();
        toEastCounter--;
        if (toEastCounter == 0){
            empty.release(); //the way is empty now
        }
        toEastMutex.release();
    }

}
