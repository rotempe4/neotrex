package tester.multi;

/**
 * Created by rotem.perets on 6/21/14.
 */
public class MultiT {
    public static void main(String[] args) throws InterruptedException {
        Doit doit = new Doit();
        doit.run();
    }
}

class NotThreadSafe{
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
    }

    public String print(){
        return builder.toString();
    }
}

class MyRunnable implements Runnable{
    NotThreadSafe instance = null;

    public MyRunnable(NotThreadSafe instance){
        this.instance = instance;
    }

    public void run(){
        this.instance.add("some text");
    }
}

class Doit{
    NotThreadSafe sharedInstance = new NotThreadSafe();

    public void run() throws InterruptedException {
        new Thread(new MyRunnable(sharedInstance)).start();
        new Thread(new MyRunnable(sharedInstance)).start();
        Thread.sleep(2000);
        System.out.println(sharedInstance.print());
    }
}
