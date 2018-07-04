package ProducerConsumer;

import java.util.concurrent.Semaphore;

class Q
{
    // an item
    int item;
    int head=0, tail=0;
    int [] store  = new int[100];
    // semCon initialized with 0 permits
    // to ensure put() executes first


    static Semaphore emptyCount = new Semaphore(10);
    static Semaphore fullcount = new Semaphore(0);

/*
    static Semaphore mutex = new Semaphore(1);

    static Semaphore semCon = new Semaphore(0);

    static Semaphore semProd = new Semaphore(1);*/

    // to get an item from buffer
    void get(int CID) throws InterruptedException
    {
        System.out.println("Consumer C" + CID + " is trying to down the fullcount ");
        fullcount.acquire();
        item = store[head];
        head++;
        System.out.println("Consumer C" + CID + " is trying to increase the the Emptycount ");
        emptyCount.release();
        System.out.println("Consumer  C" + CID + "Consumed item : " + item);

    }

    // to put an item in buffer
    void put(int item, int PID) throws InterruptedException
    {

        System.out.println("Producer P"+ PID + " is trying to down the emptycount ");
        emptyCount.acquire();
        store[tail] = item;
        tail++;
        System.out.println("Producer P"+ PID + " is trying to increase the the Fullcount ");
        fullcount.release();
        System.out.println("Producer P"+ PID + " put item : " + item);


    }
}