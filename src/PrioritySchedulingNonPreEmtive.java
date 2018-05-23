/*
 *In the name of Allah the Most Merciful.
 * Author
 * Md. Toufiqul Islam
 * Dept. Of CSE
 * Ahsanullah University Of Science And Technology
 *
 * Priority Scheduling
 * Non-PreEmtive
 */

import java.util.*;
import java.math.*;
import java.io.*;

public class PrioritySchedulingNonPreEmtive {

    static class Process {
        int processId;
        int arrivalTime;
        int cpuTime;
        int priority;
        int waitingTime;
        int turnAroundTime;
        boolean isTaken;

        public Process(int processId, int arrivalTime, int cpuTime, int priority) {
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;
            this.priority = priority;
            waitingTime = 0;
            turnAroundTime = 0;

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
            System.out.println("Enter the Priority for Process " + i);
            int priority = sc.nextInt();

            list.add(new Process(i, arrivalTime, cputTime, priority));

        }

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.priority - o2.priority;
            }
        });

        doPriorityScheduling(list);
        calculateTurnAroundTime(list);

        double avgWaitingTime = getAvgWaitingTime(list);
        double avgTurnAroundTime = getAvgTurnAroundTime(list);
        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.processId - o2.processId;
            }
        });

        for (Process process : list) {
            System.out.println(process.processId + "  Waiting time " + process.waitingTime + "   TurnAroundTime   " + process.turnAroundTime);
        }

        System.out.println("Avg Waiting Time " + avgWaitingTime);
        System.out.println("Avg Turn Around Time " + avgTurnAroundTime);


    }

    public static void doPriorityScheduling(List<Process> processes) {
        int totalTime = 0;
        processes.get(processes.size() - 1).waitingTime = 0;

        Queue<Process> queue = new LinkedList<>();
        queue.add(processes.get(processes.size() - 1));
        processes.get(processes.size() - 1).isTaken = true;

        while (!queue.isEmpty()) {
            Process front = queue.poll();
            front.waitingTime = totalTime - front.arrivalTime;
            totalTime += front.cpuTime;


            List<Process> myList = new ArrayList<>();

            for (int i = processes.size() - 1; i >= 0; i--) {
                if (!processes.get(i).isTaken) {
                    if (processes.get(i).arrivalTime <= totalTime) {
                        myList.add(processes.get(i));
                    }
                }
            }

            Collections.sort(myList, new Comparator<Process>() {
                @Override
                public int compare(Process o1, Process o2) {
                    return o1.priority - o2.priority;
                }
            });

            for (Process p : myList) {
                p.isTaken = true;
                queue.add(p);
            }


        }

    }

    public static void calculateTurnAroundTime(List<Process> processList) {
        for (Process process : processList) {
            process.turnAroundTime = process.waitingTime + process.cpuTime;
        }
    }

    public static double getAvgTurnAroundTime(List<Process> processList) {
        int total = 0;
        for (Process p : processList) {
            total += p.turnAroundTime;
        }

        return total / (double) processList.size();
    }

    public static double getAvgWaitingTime(List<Process> processList) {
        int total = 0;
        for (Process p : processList) {
            total += p.waitingTime;
        }

        return total / (double) processList.size();
    }


}
