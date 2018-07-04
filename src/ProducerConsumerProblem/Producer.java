package ProducerConsumerProblem;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
    Operation operation;
    int pId;



    public Producer(Operation operation,int pId) throws InterruptedException {
        this.operation = operation;
        this.pId = pId;

       Thread mythread= new Thread(this,"Producer P "+pId);
       mythread.start();
       Thread.sleep(500);

    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            Random ranNumber = new Random();
            int n = ranNumber.nextInt(50)+1;
              // System.out.println(pId+" paise "+n);
                operation.put(n, pId);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
