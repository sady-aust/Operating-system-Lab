package ProducerConsumer;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// Producer class
class Producer implements Runnable {
    Q q;
    int PID;

    Producer(Q q, int PID) {
        this.q = q;
        new Thread(this, "Producer P" + PID).start();
    }

    public void run() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        try {
            while (true) {

                System.out.println("Producer" + PID + ": Enter a number:  ");
                int n = reader.nextInt(); // Scans the next token of the input as an int.
//once finished

                q.put(n, PID);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }

        reader.close();

    }
}

