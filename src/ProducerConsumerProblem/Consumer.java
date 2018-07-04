package ProducerConsumerProblem;

public class Consumer extends Thread {
    Operation operation;
    int cId;

    public Consumer(Operation operation, int cId) throws InterruptedException {
        this.operation = operation;
        this.cId = cId;



    }

    @Override
    public void run() {
        while (true){
            try {
                operation.get(cId);


                if(operation.head ==5){
                    break;
                }




            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
