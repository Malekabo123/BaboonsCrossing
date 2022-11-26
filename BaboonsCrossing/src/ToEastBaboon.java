public class ToEastBaboon extends Thread{
    private CrossingCanyon crossingCanyon;

    public ToEastBaboon(CrossingCanyon crossingCanyon){
        this.crossingCanyon = crossingCanyon;
    }

    @Override
    public void run(){
        super.run();
        while (true){
            try {
                Thread.sleep(2000);

                this.crossingCanyon.acquireGoToEast();

                Thread.sleep(2000);

                this.crossingCanyon.releaseGoToEast();

            }catch (Exception e){

            }
        }
    }
}
