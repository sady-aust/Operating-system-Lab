import java.io.*;
import java.util.*;
import java.math.*;
public class ShortestJobFirst {
    static class Process implements Comparable<Process>{
        public int processId;
        public int arrivalTime;
        public int cpuTime;
        public int waitingTime  = 0;
        public int turnAroundTime = 0;

        public Process(int processId,int arrivalTime,int cpuTime){
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;
        }


        @Override
        public int compareTo(Process o) {
            return (this.cpuTime)-o.cpuTime;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Process");
        int t = sc.nextInt();

        List<Process> process = new ArrayList<>();

        for(int i=1;i<=t;i++){
            System.out.println("Enter the Arrival Time for Process "+i);
            int arrival = sc.nextInt();
            System.out.println("Enter the CpuTime Time for Process "+i);
            int cpuTime =  sc.nextInt();

            process.add(new Process((i),arrival,cpuTime));
        }


        calculateWaitingTime(process);
        calculateTurnAroundTime(process);
        double avgWaitingTime = calcualteAverageWaitingTime(process);
        double avgTurnAroundTime = calculateAverageTurnAroundTime(process);

        Collections.sort(process, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.processId-o2.processId;
            }
        });

        for (Process myProcess :process){
            System.out.println("Process "+myProcess.processId+":"+"Waiting Time "+myProcess.waitingTime+" TurnAround Time: "+myProcess.turnAroundTime);
        }

        System.out.println("Average Waiting Time "+avgWaitingTime);
        System.out.println("Average TurnAround Time "+avgTurnAroundTime);



    }

     /*
    * function for calculating waiting time of each process
    * @processes - list of process
    *
    * */

    public static void calculateWaitingTime(List<Process> processes){
        processes.get(0).waitingTime =0;
        int totalTime = processes.get(0).cpuTime;

        for(int i=1;i<processes.size();i++){
            //process.get(i).waitingTime = (process.get(i-1).waitingTime+process.get(i-1).cpuTime)-process.get(i).arrivalTime;
            processes.get(i).waitingTime = totalTime-processes.get(i).arrivalTime;
            totalTime += processes.get(i).cpuTime;

        }
    }

    /*
    * function for calculating turnaroundtime of each process
    * @processes - list of process
    *
    */

    public static void calculateTurnAroundTime(List<Process> processes){

        for(int i=0 ;i<processes.size();i++){
            processes.get(i).turnAroundTime = processes.get(i).waitingTime+processes.get(i).cpuTime;
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
