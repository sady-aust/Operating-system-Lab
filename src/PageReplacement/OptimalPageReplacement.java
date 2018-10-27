package PageReplacement;

import java.io.*;
import java.util.*;
import java.math.*;
public class OptimalPageReplacement {

    static class Distance{
        String page;
        int distance;

        public Distance(String page, int distance) {
            this.page = page;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String refS = sc.nextLine();
        String[] arr = refS.split(",");

        List<String> frame = new ArrayList<>();

        int pagefault = 0;
        int pageHit = 0;
        for (int i=0;i<arr.length;i++){
            if(frame.contains(arr[i])){
                pageHit++;
            }
            else{
                if(frame.size()==3){
                    List<Distance> disList = new ArrayList<>();

                    for(int j=0;j<frame.size();j++){
                        int dis = getDistance(frame.get(j),arr,i);
                        disList.add(new Distance(frame.get(j),dis));
                    }

                    Collections.sort(disList, new Comparator<Distance>() {
                        @Override
                        public int compare(Distance o1, Distance o2) {
                            return o1.distance-o2.distance;
                        }
                    });

                    Distance needToremove = null;

                    if( disList.get(0).distance==-1){
                        needToremove = disList.get(0);
                    }
                    else{
                        needToremove = disList.get(disList.size()-1);
                    }


                    int index = frame.indexOf(needToremove.page);
                    frame.remove(index);
                    frame.add(index,arr[i]);
                }
                else {
                    frame.add(arr[i]);
                }
                pagefault++;
            }
        }

        System.out.println("Page fault is "+pagefault);

    }

    static int getDistance(String page,String [] arr,int start){
        int dis = -1;

       for (int i=start+1;i<arr.length;i++){
           if(arr[i].trim().equals(page.trim())){
               dis = i-start;
               break;
           }
       }

       return dis;
    }
}
