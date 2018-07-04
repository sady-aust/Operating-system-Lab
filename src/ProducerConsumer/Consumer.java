package ProducerConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

// Consumer class
class Consumer implements Runnable
{
    int CID;
    Q q;
    Consumer(Q q, int CID){
        this.q = q;
        new Thread(this, "Consumer C " + CID).start();
    }

    public void run()
    {
        try {
            while(true){
                q.get(CID);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
