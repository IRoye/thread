package synchronizedandVolatileTest;

public class ThreadA extends Thread {
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        try {
            numRef.addI("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
