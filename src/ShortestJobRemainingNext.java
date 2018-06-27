import java.io.*;
import java.util.*;
import java.math.*;

public class ShortestJobRemainingNext {
    static class Process {
        int processId;
        int arrivalTime;
        int cpuTime;
        int waitingTime;
        int turnAroundTime;
        int lastTime;
        boolean isTaken;

        public Process(int processId, int arrivalTime, int cpuTimey) {
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;

            waitingTime = 0;
            turnAroundTime = 0;
            lastTime =0;

            isTaken = false;

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of Process");
        int p = sc.nextInt();
        List<Process> list = new ArrayList<>();

        for (int i = 1; i <= p; i++) {
            System.out.println("Enter the arrival Time for Process " + i);
            int arrivalTime = sc.nextInt();
            System.out.println("Enter the Cpu Time For Process " + i);
            int cputTime = sc.nextInt();


            list.add(new Process(i, arrivalTime, cputTime));

        }

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.arrivalTime-o2.arrivalTime;
            }
        });

        shortestJobRemainingNext(list);
    }


    static void shortestJobRemainingNext(List<Process> processes){
        Queue<Process> queue = new LinkedList<>();
        List<Process> myList =  new ArrayList<>();

        queue.add(processes.get(0));
        processes.get(0).isTaken = true;

        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.cpuTime-o2.cpuTime;
            }
        });

        int totalTime = 0;

        while (!queue.isEmpty()){
            Process process = queue.poll();

            for(Process p : processes){
                if(!p.isTaken){
                    if(p.cpuTime<process.cpuTime && p.cpuTime!=0){
                        if(p.arrivalTime<=process.arrivalTime){
                            queue.add(p);
                            p.isTaken = true;
                        }
                    }
                }
            }

            if(!myList.isEmpty()){
                int totalExecutionTime = processes.get(1).arrivalTime;

               if(process.cpuTime>=totalExecutionTime){
                   process.cpuTime -=totalExecutionTime;

                   totalTime += totalExecutionTime;
                   process.waitingTime +=(totalTime-process.lastTime);
                   process.lastTime = totalTime;

               }
               else{
                   totalTime += process.cpuTime;
                   process.lastTime = totalTime;
                   process.cpuTime =0;
               }
            }
            else{
                Process p = queue.poll();
                p.waitingTime +=(totalTime-p.lastTime);

                totalTime +=p.cpuTime;
                p.cpuTime =0;
            }

        }

    }
}
