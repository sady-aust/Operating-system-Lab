/*
 *In the name of Allah the Most Merciful.
 * Author
 * Md. Toufiqul Islam
 * Dept. Of CSE
 * Ahsanullah University Of Science And Technology
 */

import java.util.*;
import java.math.*;
import java.util.*;

public class RoundRobinScheduling {

    static class Process {
        int processId;
        int arrivalTime;
        int cpuTime;
        int waitingTime;
        int turnAroundTime;
        int lastTime;
        int cpuTimeCopy;
        boolean isTaken;

        public Process(int processId, int arrivalTime, int cpuTime) {
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;
            cpuTimeCopy = cpuTime;
            waitingTime = 0;
            turnAroundTime = 0;
            lastTime = arrivalTime;
            isTaken = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of process: ");
        int n = sc.nextInt();

        List<Process> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter the cpu arrival Time for process " + i);
            int arrivaltime = sc.nextInt();

            System.out.println("Enter the cpu time for process " + i);
            int cpuTime = sc.nextInt();

            list.add(new Process(i, arrivaltime, cpuTime));

        }

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.arrivalTime - o2.arrivalTime;
            }
        });

        System.out.println("Please Enter the time quantum");
        int timeQuantam = sc.nextInt();

        List<Process> finalList = doRoundRobin(list, timeQuantam);
        calculateTurnAroundTime(finalList);

        double avgWaitingTime = calculateAvgWaitingTime(finalList);
        double avgTurnAroundTime = calculateAvgTurnAroundTIme(finalList);

        Collections.sort(finalList, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.processId - o2.processId;
            }
        });

        for (Process p : finalList) {
            System.out.println("Process Id " + p.processId + "  WaitingTime   " + p.waitingTime + "   TurnAroundTime " + p.turnAroundTime);
        }

        System.out.println("Average Waiting Time " + avgWaitingTime);
        System.out.println("Average Turn Around Time " + avgTurnAroundTime);


    }

    public static void calculateTurnAroundTime(List<Process> processes) {
        for (Process p : processes) {
            p.turnAroundTime = p.waitingTime + p.cpuTimeCopy;
        }
    }

    public static double calculateAvgWaitingTime(List<Process> processes) {
        int total = 0;
        for (Process p : processes) {
            total += p.waitingTime;
        }

        return total / (double) processes.size();

    }

    public static double calculateAvgTurnAroundTIme(List<Process> processes) {
        int total = 0;
        for (Process p : processes) {
            total += p.turnAroundTime;
        }

        return total / (double) processes.size();

    }

    public static List<Process> doRoundRobin(List<Process> list, int quantam) {
        List<Process> myList = new ArrayList<>();

        Queue<Process> queue = new LinkedList<>();

        queue.add(list.get(0));
        list.get(0).isTaken = true;

        int totalTime = 0;

        while (!queue.isEmpty()) {
            Process process = queue.poll();

            if (process.cpuTime >= quantam) {
                process.cpuTime -= quantam;
                process.waitingTime += (totalTime - process.lastTime);
                totalTime += quantam;
                process.lastTime = totalTime;

            } else {
                process.waitingTime += (totalTime - process.lastTime);
                totalTime += process.cpuTime;
                process.cpuTime = 0;
                process.lastTime = totalTime;
            }

            for (int i = 0; i < list.size(); i++) {
                Process checkProcess = list.get(i);
                if (!checkProcess.isTaken) {
                    if (checkProcess.arrivalTime <= totalTime) {
                        queue.add(checkProcess);
                        checkProcess.isTaken = true;
                    } else {
                        break;
                    }
                }
            }
            if (process.cpuTime > 0) {
                queue.add(process);
            } else {
                myList.add(process);
            }
        }
        return myList;
    }
}