import java.util.concurrent.Semaphore;

public class CrossingCanyon <E>{

    private static final int ROPECAPACITY = 5;
    private Semaphore toWestMutex;
    private Semaphore toEastMutex;
    //private Semaphore empty; //1 if empty, 0 if full

    private Semaphore toWestSwitch;
    private Semaphore toEastSwitch;
    private int toWestCounter;
    private int toEastCounter;
    private int onRope;

    public CrossingCanyon(){
        this.toWestMutex = new Semaphore(ROPECAPACITY);
        this.toEastMutex = new Semaphore(ROPECAPACITY);
        //this.empty = new Semaphore(1);
        this.onRope = 0;
        this.toWestSwitch = new Semaphore(1);
        this.toEastSwitch = new Semaphore(1);
    }


    public void acquireGoToWest() throws InterruptedException {
        //empty.acquire();
        toWestMutex.acquire();

        toWestCounter++;
        if (toWestCounter == 1){ //the first baboon crossing to west
            toWestSwitch.acquire(); // the way is reserved as to west now
        }

        System.out.println("I am on the rope going west, count= "+toWestCounter);
        System.out.println("Direction is: "+toWestSwitch.toString());

        toWestMutex.release();

    }


    public void releaseGoToWest() throws InterruptedException {
        toWestMutex.acquire();

        toWestCounter--;
        if(toWestCounter == 0){
            toWestSwitch.release(); //the way is empty now
        }
        System.out.println("I've crossed the rope and in west now, count= "+toWestCounter);

        toWestMutex.release();
    }

    public void acquireGoToEast() throws InterruptedException {
        toEastMutex.acquire();

        toEastCounter++;
        if (toEastCounter == 1){
            toEastSwitch.acquire(); // the way is reserved as to east now
        }
        System.out.println("I am on the rope going east, count= "+toEastCounter);

        toEastMutex.release();
    }

    public void releaseGoToEast() throws InterruptedException {
        toEastMutex.acquire();

        toEastCounter--;
        if (toEastCounter == 1){
            toEastSwitch.release(); //the way is empty now
        }
        System.out.println("I've crossed the rope and in east now, count= "+toEastCounter);

        toEastMutex.release();
    }

}
