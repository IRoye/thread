package synchronizedandVolatileTest;

public class ThreadB extends Thread {
    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        try {
            numRef.addI("b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
