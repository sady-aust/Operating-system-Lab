package ProducerConsumer;

// Driver class
class PC
{
    public static void main(String args[])
    {
        // creating buffer queue
        Q q = new Q();

        // starting consumer thread
        new Consumer(q,0);




        new Producer(q,0);



        // starting producer thread
    }
}