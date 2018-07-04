package ProducerConsumerProblem;

import java.util.concurrent.Semaphore;

public class Operation {

    Semaphore emptyCount = new Semaphore(10);
    Semaphore fullCount = new Semaphore(0);
    int head =0,tail = 0;
    int[] store = new int[100];
    int item;


    public void get(int cid) throws InterruptedException {
       // System.out.println("Consumer "+cid+" is trying to down the fullcount");
        fullCount.acquire();
        item = store[head];
        head++;
       // System.out.println("Consumer "+cid+" Is trying to increase the empty count");
        emptyCount.release();

        System.out.println("Consumer "+cid+" consumed item "+item);

    }

    public void put(int item,int pId) throws InterruptedException {
     //   System.out.println("Producer "+pId+" is trying to down the empty count");
        emptyCount.acquire();
        store[tail] = item;
        tail++;
      //  System.out.println("Producer "+pId+" is trying to incrase fullcount");
        fullCount.release();
        System.out.println("Producer P"+ pId + " put item : " + item);
    }
}
