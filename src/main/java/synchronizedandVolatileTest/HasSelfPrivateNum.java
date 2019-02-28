package synchronizedandVolatileTest;

/****
 *
 */
public class HasSelfPrivateNum {

    // 实例变量
    private int num = 0;

    /**
     * 加入方法synchronized关键字，那个只有当前线程走完该方法之后，下一个线程
     * 才能继续调用该方法；所以打印的结果是正常的；
     * 如果没有加synchronized关键字的话，同时Thread.sleep(200), 第一个线程设置完了100，时间片交给其他线程
     * 此时num设置为200，打印200；接着上一个线程继续执行，继续打印的话200；
     *
     * @param username
     * @throws InterruptedException
     */
    synchronized public void addI(String username) throws InterruptedException {
        if (username.equals("a")) {
            num = 100;
            System.out.println("a set over");
            Thread.sleep(2000);// 为什么加了2s的延迟数据就变了？ current thread
        } else {
            num = 200;
            System.out.println("b is over");
        }
        System.out.println("username:" + num);
    }

    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        ThreadB threadB = new ThreadB(hasSelfPrivateNum);

        threadA.start();
        threadB.start();
    }
}
