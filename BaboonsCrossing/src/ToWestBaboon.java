public class ToWestBaboon extends Thread{
    private CrossingCanyon crossingCanyon;

    public ToWestBaboon(CrossingCanyon crossingCanyon){
        this.crossingCanyon = crossingCanyon;
    }

    @Override
    public void run(){
        super.run();
        while (true){
            try {
                Thread.sleep(1000);

                this.crossingCanyon.acquireGoToWest();

                Thread.sleep(1000);

                this.crossingCanyon.releaseGoToWest();

            }catch (Exception e){

            }
        }
    }
}
