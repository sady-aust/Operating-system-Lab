/*
* Round Robin without Arrival Time
*
*/

import java.io.*;
import java.math.*;
import java.util.*;
public class RoundRobinSchedulingWithoutArrivalTime {
    static class Process{
        int processId;
        int cpuTime;
        int waitingTime =0;
        int turnAroundTime =0;
        int saveCpuTime;

        int lastChanceTime =0;

        public Process(int processId, int cpuTime) {
            this.processId = processId;
            this.cpuTime = cpuTime;

            saveCpuTime =cpuTime;

        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Number of process");

        int n = sc.nextInt();
        List<Process> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            System.out.println("Process id ");
            int id = sc.nextInt();
            System.out.println("Cpu Time ");
            int cpuTime = sc.nextInt();

            list.add(new Process(id,cpuTime));
        }

        System.out.println("Time Quantam ");
        int t = sc.nextInt();

        doRoundRobin(list,t);
        calculateTurnAroundTime(list);

        for(Process p : list){
            System.out.println("Process id "+p.processId+" Waiting TIme "+p.waitingTime+"  TurnAroundTime "+p.turnAroundTime);

        }

        System.out.println("Avg waiting Time "+calcualteAverageWaitingTime(list));
        System.out.println("Avg Turn Around Time "+calculateAverageTurnAroundTime(list));

    }

    public static void doRoundRobin(List<Process> processList,int timeQuantam){
        Queue<Process> queue = new LinkedList<>();

        for(Process p: processList){
            queue.add(p);
        }


        int totalTime = 0;

        while (!queue.isEmpty()){
            Process aProcess = queue.poll();

            if(aProcess.cpuTime>timeQuantam){
                aProcess.waitingTime +=(totalTime-aProcess.lastChanceTime);

                aProcess.cpuTime -= timeQuantam;
                totalTime +=timeQuantam;
                aProcess.lastChanceTime = totalTime;
                queue.add(aProcess);
            }
            else{
                aProcess.waitingTime +=(totalTime -aProcess.lastChanceTime);
                totalTime +=aProcess.cpuTime;
                aProcess.cpuTime =0;
            }

        }
    }

    public static void calculateTurnAroundTime(List<Process> processes){

        for(int i=0 ;i<processes.size();i++){
            processes.get(i).turnAroundTime = processes.get(i).saveCpuTime+processes.get(i).waitingTime;
        }
    }

    public static double calcualteAverageWaitingTime(List<Process> processes){
        int total = 0;
        for(int i=0 ;i<processes.size();i++){
            total += processes.get(i).waitingTime;
        }

        return total/(double)processes.size();

    }

    public static double calculateAverageTurnAroundTime(List<Process> processes){
        int total = 0;
        for(int i=0 ;i<processes.size();i++){
            total += processes.get(i).turnAroundTime;
        }

        return total/(double)processes.size();

    }
}
