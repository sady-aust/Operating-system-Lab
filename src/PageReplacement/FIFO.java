package PageReplacement;

import java.io.*;
import java.math.*;
import java.util.*;
public class FIFO {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String refS = sc.nextLine();
        String [] arr = refS.split(",");
        Queue<String> myQueue = new LinkedList<>();

        List<String> frame = new ArrayList<>();

        int pageFault = 0;
        int pageHit = 0;

        for(String s : arr){
            if(frame.contains(s)){
                pageHit ++;
            }
            else{
                if(frame.size()==3){
                   String head= myQueue.poll();
                   int index = frame.indexOf(head);
                   frame.remove(index);
                   frame.add(index,s);
                   myQueue.add(s);

                }
                else {
                    frame.add(s);
                    myQueue.add(s);
                }
                pageFault++;
            }
        }

        System.out.println("Page Fault is "+pageFault);
    }
}
