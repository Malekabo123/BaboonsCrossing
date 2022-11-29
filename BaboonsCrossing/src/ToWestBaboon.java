public class ToWestBaboon implements Runnable{
    private int id;
    private CrossingCanyon crossingCanyon;

    public ToWestBaboon(int id,CrossingCanyon crossingCanyon){
        this.id = id;
        this.crossingCanyon = crossingCanyon;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep((long) ((int) 5*Math.random()*1000));
                crossingCanyon.acquireGoToWest();
                System.out.println("I am baboon "+id+" going west");

                Thread.sleep((long) ((int) 5*Math.random()*1000));
                crossingCanyon.releaseGoToWest();
                System.out.println("I am baboon "+id+" arrived west");

            }catch (Exception e){

            }
        }
    }
}
