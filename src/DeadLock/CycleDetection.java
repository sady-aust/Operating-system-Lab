package DeadLock;

import java.io.*;
import java.util.*;
public class CycleDetection {


        int v;
        LinkedList<Integer> list[];

        public CycleDetection(int v) {
            this.v = v;
           list = new LinkedList[v];

           for(int i=0;i<v;i++){
               list[i] = new LinkedList<>();
           }
        }

        void addEdge(int u,int v){
            list[u].add(v);
        }

        boolean isCycleExists(boolean[] isVisited,boolean[] recStack,int i){
            if(isVisited[i]){
                return false;
            }

            if(recStack[i]){
                return  true;
            }
            recStack[i] = true;

           Iterator<Integer> it = list[i].listIterator();

           while (it.hasNext()){
               int n  = it.next();

               if(isCycleExists(isVisited,recStack,n)){
                   return true;
               }
           }

           recStack[i] = false;
           return false;

        }


    public static void main(String[] args) {

        CycleDetection cycleDetection = new CycleDetection(4);

        cycleDetection.addEdge(0, 2);
        cycleDetection.addEdge(1, 2);
        cycleDetection.addEdge(2, 0);
        cycleDetection.addEdge(2, 3);
        cycleDetection.addEdge(3, 3);

        boolean[] isVisited = new boolean[4];
        boolean[] recStack = new boolean[4];


        for(int i=0;i<4;i++){
            if(cycleDetection.isCycleExists(isVisited,recStack,i)){
                System.out.println("YEs");
            }

        }
    }


}
