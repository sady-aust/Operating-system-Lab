package ThreadExample;

public class MyThread {
    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        SecondThread secondThread = new SecondThread();

        Thread thread1 = new Thread(firstThread);
        thread1.start();
        Thread thread2 = new Thread(secondThread);
        thread2.start();
    }
}
