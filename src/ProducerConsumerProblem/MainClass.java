package ProducerConsumerProblem;
/*
* Problem Description:
* 5 Producer will produce 5 item and then stop.Then 2 consumer consume items untill the items not finishhed
* */
public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Operation op = new Operation();

        boolean isAllProducerProduce = false;
        for(int i=0;i<5;i++){
            new Producer(op,i);

            if(i==4){
                isAllProducerProduce = true;
            }
        }

        if(isAllProducerProduce){
            Consumer c1 =new Consumer(op,0);
            c1.start();

            Consumer c2 = new Consumer(op,1);

           c2.start();


        }
    }
}
