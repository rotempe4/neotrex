package tester.general;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rotem.perets on 6/10/14.
 */
public class Tester {
    public static void main(String[] args) throws InterruptedException {
        Integer c = 3;
        int counter = 0;
        HashSet<String> items = new HashSet<>();
        items.add("1");
        items.add("2");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while (true){
            DummyPrinter printer = new DummyPrinter(items);
            executorService.execute(printer);
            counter++;
            if(counter == 3){
                items.add(c.toString());
                c++;
                counter = 0;
            }
            Thread.sleep(3000);
        }
    }
}

class DummyPrinter implements Runnable{
    String name = new Date().toString();
    HashSet<String> itemsToPrint;
    public DummyPrinter(HashSet<String> itemsToPrint){
        this.itemsToPrint = itemsToPrint;
    }

    @Override
    public void run() {
        Object[] arr = itemsToPrint.toArray();
        for(int i = 0; i < arr.length; i++){
            System.out.println(name + " " + arr[i]);
        }
    }
}
