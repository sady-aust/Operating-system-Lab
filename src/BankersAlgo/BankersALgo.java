package BankersAlgo;
/**
        *In the name of Allah the Most Merciful.
        * Author
        * Md. Toufiqul Islam
        * Dept. Of CSE
        * Ahsanullah University Of Science And Technology
        *
        * Bankers Algo
        * will take input from input.txt file

        @algo
        if(need<=available){
            then execute process
            update available
        }
        else{
            do not execute
            go forward
        }
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class BankersALgo {

    static class Process{
        int pId;
        int[] res;
        int[] max;
        int[] alloc;
        int[] need;
        boolean isTaken;

        public Process(int n,int id){
            this.pId = id;
            res = new int[n];
            max = new int[n];
            alloc = new int[n];
            need = new int[n];
            isTaken = false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\CSE\\3.2\\Oslab\\Codes\\src\\BankersAlgo\\input");
        Scanner sc = new Scanner(new FileInputStream(file));

        int[] mainRes;

        System.out.println("Enter the number of Process");
        int n = sc.nextInt();
        System.out.println(n);

        System.out.println("Enter the number of Resource");
        int r = sc.nextInt();
        System.out.println(r);
        mainRes = new int[r];

        List<Process> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            list.add(new Process(r,(i+1)));
        }

        for(int i=0;i<list.size();i++) {
            System.out.println("Process " + (i + 1));
            Process p = list.get(i);
            for (int j = 0; j < p.res.length; j++) {
                System.out.println("Maximum Value for Resource " + (j + 1) + ":");
                int mv = sc.nextInt();
                System.out.println(mv);
                p.max[j] = mv;
            }
            for (int j = 0; j < p.alloc.length; j++) {
                System.out.println("Maximum Value for Resource " + (j + 1) + ":");
                int av = sc.nextInt();
                System.out.println(av);
                p.alloc[j] = av;
            }
        }

        for(int i=0;i<r;i++){
            System.out.println("Enter Value of resource "+(i+1)+": ");
            mainRes[i] = sc.nextInt();
            System.out.println( mainRes[i]);

        }

        calculateNeedMatrix(list);
        int[] available = calculateAvailable(mainRes,list);

        int currentSize = list.size();

        boolean isSafe = true;

        List<Integer> sequence = new ArrayList<>();

        while (!list.isEmpty()){

           for(int i=0;i<list.size();i++){
               Process p = list.get(i);

               boolean isAllNeedSmallOrEqual = true;

               for(int j=0;j<p.need.length;j++){
                   if(p.need[j]>available[j]){
                       isAllNeedSmallOrEqual = false;
                       break;
                   }
               }

               if(isAllNeedSmallOrEqual){
                   for(int j=0;j<p.alloc.length;j++){
                       available[j]+= p.alloc[j];
                   }
                   p.isTaken = true;
                   sequence.add(p.pId);

                   list.remove(i);
               }
           }

           if(list.size()==currentSize){
               isSafe = false;
               break;
           }
           else{
               currentSize = list.size();
           }
        }

        if(isSafe){
            for(Integer i:sequence){
                System.out.println(i);
            }

            System.out.println("After executing the total value of each Resource");

            for(int i=0;i<available.length;i++){
                System.out.println(available[i]);
            }
        }
        else{
            System.out.println("Not in Safe state");
        }
    }

    static void calculateNeedMatrix(List<Process> list){
        for(Process p :list){
            for(int i=0;i<p.need.length;i++){
                p.need[i] = p.max[i]-p.alloc[i];
            }
        }
    }

    static int[] calculateAvailable(int[] arr,List<Process> list){
        int[] available = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            int total =0;

            for(Process p:list){
                total+= p.alloc[i];
            }

            available[i] = arr[i] - total;
        }

        return available;
    }

}
