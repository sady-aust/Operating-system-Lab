package MemoryManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\CSE\\3.2\\Oslab\\Codes\\src\\MemoryManagement\\input.txt");
        Scanner sc = new Scanner(new FileInputStream(file));
        System.out.println("Number of Holes");
        int n = sc.nextInt();
        System.out.println(n);
        int[] myHole = new int[n];
        for(int i=0;i<n;i++){
            int a = sc.nextInt();
            System.out.println(a);
            myHole[i] = a;
        }
        System.out.println("Number of Request");
        int r = sc.nextInt();
        int[] myReq = new int[r];

        for(int i=0;i<r;i++){
            int a = sc.nextInt();
            System.out.println(a);
            myReq[i] = a;
        }

       boolean isPoss =  BestFit(myHole,myReq);

        if(!isPoss){
            int total = 0;

            for(int i=0;i<myHole.length;i++){
                total += myHole[i];
            }

            System.out.println("External Fragmentation "+total);
        }
        else{
            System.out.println("No External Fragmentation Required");
        }

    }

    /**
    *
    * @return false if external fragmentation required
    * */
    public static boolean FirstFit(int[] hole,int[] request){

        boolean isPoss = true;
        System.out.println("Step by step memory allocation situation in First Fit");
        for(int i=0;i<request.length;i++){

            boolean flag = false;

            for(int j=0;j<hole.length;j++){

                if(request[i]<=hole[j]){
                    flag = true;

                    hole[j] -=request[i];

                    for(int k=0;k<hole.length;k++){
                        System.out.print(hole[k]+" ");
                    }
                    System.out.println();
                    break;
                }


            }
            if(!flag){
                isPoss =false;
            }
        }

       return isPoss;


    }

    /**
     *
     * @return false if external fragmentation required
     * */

    public static boolean BestFit(int[] hole,int[] request ){
        boolean isPoss = true;

        System.out.println("Step by step memory allocation situation in Best Fit");
        for(int i=0;i<request.length;i++){
            boolean flag = false;
            Arrays.sort(hole);
            for(int j=0;j<hole.length;j++){
                if(request[i]<=hole[j]){
                    flag = true;
                    hole[j] -=request[i];
                    for(int k=0;k<hole.length;k++){
                        System.out.print(hole[k]+" ");
                    }
                    System.out.println();
                    break;
                }


            }
            if(!flag){
                System.out.println("For "+ request[i]);
                isPoss = false;
            }

        }

        return isPoss;
    }


}
