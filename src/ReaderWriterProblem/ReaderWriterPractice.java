package ReaderWriterProblem;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ReaderWriterPractice {

    static Semaphore readerSem = new Semaphore(1);
    static Semaphore writterSem = new Semaphore(1);
    static  int readerCount = 0;
    static int index = 0;

  static ArrayList<Integer> list = new ArrayList<>();


   static class Writter extends Thread{
        @Override
        public void run() {
            try {


                System.out.println(Thread.currentThread().getName()+" is Waiting");
                writterSem.acquire();

                Random random = new Random();
                int n = random.nextInt(50)+1;
                System.out.println(Thread.currentThread().getName()+" is Writting "+n);
                list.add(n);
                Thread.sleep(2500);
                System.out.println(Thread.currentThread().getName()+" has finished Writting");
                writterSem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   static class Reader extends Thread{
        @Override
        public void run() {
            try {
                readerSem.acquire();
                readerCount++;
                if(readerCount==1){
                    writterSem.acquire();
                }

                readerSem.release();


                if(index<list.size()){
                    System.out.println(Thread.currentThread().getName()+" is reading "+list.get(index));
                    index++;
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" has finished reading");
                }


                readerSem.acquire();
                readerCount--;
                if(readerCount==0){
                    writterSem.release();
                }
                readerSem.release();
                System.out.println(Thread.currentThread().getName()+" Leaves");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {


       Writter writter1 = new Writter();
       writter1.setName("Writter1");

        Writter writter2 = new Writter();
        writter2.setName("Writter2");

        Reader reader1 = new Reader();
        reader1.setName("reader1");
        Reader reader2 = new Reader();
        reader2.setName("reader2");

        Reader reader3 = new Reader();
        reader3.setName("reader3");

        writter1.start();
        writter2.start();
        reader1.start();
        reader2.start();
        reader3.start();
    }
}
