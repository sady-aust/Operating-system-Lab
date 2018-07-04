package ReaderWriterProblem;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ReaderWriterproblem {
    static Semaphore readerSem = new Semaphore(1);
    static Semaphore writerSem = new Semaphore(1);

    static ArrayList<Integer> list = new ArrayList<>();

   static int index =0;


   static int readerCount = 0;

    static class Reader implements Runnable {

        @Override
        public void run() {
            try {
                readerSem.acquire();
                readerCount++;
                if(readerCount ==1){
                    writerSem.acquire();
                }

                readerSem.release();
              //  System.out.println(Thread.currentThread().getName()+" is Reading");

                if(index<list.size()){
                    System.out.println(Thread.currentThread().getName()+" is Reading "+list.get(index));
                    index++;
                }

                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+" has finished Reading");

                readerSem.acquire();
                readerCount--;
                if(readerCount ==0){
                    writerSem.release();
                }

                readerSem.release();



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer implements Runnable{

        @Override
        public void run() {
            try {

                writerSem.acquire();

                Random rand = new Random();
                int n = rand.nextInt(50)+1;
                list.add(n);
                System.out.println(Thread.currentThread().getName()+" is Writing "+n);
                Thread.sleep(2500);
                System.out.println(Thread.currentThread().getName()+" has finished Writing");
                writerSem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Reader read = new Reader();
        Writer write = new Writer();
        Thread t1 = new Thread(read);
        t1.setName("Process 1");
        Thread t2 = new Thread(read);
        t2.setName("Process 2");
        Thread t3 = new Thread(write);
        t3.setName("Process 3");
        Thread t4 = new Thread(read);
        t4.setName("Process 4");
        Thread t5 = new Thread(write);
        t5.setName("Process 5");
        Thread t6 = new Thread(read);
        t6.setName("Process 6");
        Thread t7 = new Thread(write);
        t7.setName("Process 7");
        t1.start();
        t3.start();
        t2.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}
