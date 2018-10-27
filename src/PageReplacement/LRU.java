package PageReplacement;

import java.io.*;
import java.util.*;
import java.math.*;

public class LRU {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String refS = sc.nextLine();
        String[] arr = refS.split(",");

        List<String> frame = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();


        int pageFault = 0;
        int pageHit = 0;

        for (int i=0;i<arr.length;i++){

            if(frame.contains(arr[i].trim())){
                pageHit++;
                map.put(arr[i].trim(),i);
            }
            else{
                if(frame.size() == 3){

                    int prev = Integer.MAX_VALUE;
                    String leastPage = null;

                    Set<String> page = map.keySet();

                    Iterator<String> it = page.iterator();

                    while (it.hasNext()){

                        String check = it.next().trim();
                        if(map.get(check)<prev){
                            prev = map.get(check);
                            leastPage = check;
                        }
                    }

                    int index = frame.indexOf(leastPage.trim());
                    map.remove(leastPage.trim());
                    frame.remove(index);

                    frame.add(index,arr[i].trim());
                    map.put(arr[i],i);
                }
                else{
                   frame.add(arr[i]);
                    map.put(arr[i],i);
                }

                pageFault++;
            }
        }

        System.out.println("Page Fault is "+pageFault);
    }

}
