public class ToEastBaboon implements Runnable{
    private int id;
    private CrossingCanyon crossingCanyon;

    public ToEastBaboon(int id,CrossingCanyon crossingCanyon){
        this.id = id;
        this.crossingCanyon = crossingCanyon;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep((long) ((int) 5*Math.random()*1000));
                crossingCanyon.acquireGoToEast();
                System.out.println("I am baboon "+id+" going east");

                Thread.sleep((long) ((int) 5*Math.random()*1000));
                crossingCanyon.releaseGoToEast();
                System.out.println("I am baboon "+id+" arrived east");

            }catch (Exception e){

            }
        }
    }
}
