public class Driver {

    public static void main(String[] args) {
        CrossingCanyon shey = new CrossingCanyon<>();

        Thread toWestBaboon = new ToWestBaboon(shey);
        Thread toEastBaboon = new ToEastBaboon(shey);

        toWestBaboon.start();
        toEastBaboon.start();
    }
}
