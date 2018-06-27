/*
 *In the name of Allah the Most Merciful.
 * Author
 * Md. Toufiqul Islam
 * Dept. Of CSE
 * Ahsanullah University Of Science And Technology
 *
 * Priority Scheduling
 * PreEmtive
 * Lower priority means highest Priority
 */

import java.util.*;

public class PrioritySchedulingPreEmtive {
    static class Process {
        int processId;
        int arrivalTime;
        int cpuTime;
        int priority;
        int waitingTime;
        int turnAroundTime;
        int lastTime;
        boolean isTaken;
        int savedCpuTime;

        public Process(int processId, int arrivalTime, int cpuTime, int priority) {
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;
            this.priority = priority;
            lastTime = arrivalTime;
            waitingTime = 0;
            turnAroundTime = 0;
            isTaken = false;
            savedCpuTime = cpuTime;
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

                return o1.arrivalTime - o2.arrivalTime;

            }
        });
        doPriorityScheduling(list);
        calculateTurnAroundTime(list);
        double avgTurnAroundTime =  calculateAvgTurnAroundTIme(list);
        double avgTWaitingTime = calculateAvgWaitingTime(list);

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.processId - o2.processId;
            }
        });

        for (Process aProcess : list){
            System.out.println(aProcess.processId+"  Waiting Time "+aProcess.waitingTime+"  Turn Around Time "+aProcess.turnAroundTime);
        }

        System.out.println("Avg Turn Around Time "+avgTurnAroundTime);
        System.out.println("Avg Waiting Time "+avgTWaitingTime);


    }

    public static void doPriorityScheduling(List<Process> processList) {
        int totalTime = 0;

        Queue<Process> queue = new LinkedList<>();
        queue.add(processList.get(0));
        processList.get(0).isTaken = true;

        Collections.sort(processList, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.priority == o2.priority) {
                    return o1.arrivalTime - o2.arrivalTime;
                } else {
                    return o1.priority - o2.priority;
                }
            }
        });

        List<Process> remainIngProcess = new ArrayList<>();

        while (!queue.isEmpty()) {
            Process aProcess = queue.poll();
            aProcess.waitingTime += (totalTime - aProcess.lastTime);


            while (true) {
                List<Process> anyProcess = processesArriveWithLowerPriority(processList, aProcess, totalTime);

                if (anyProcess.size() == 0) {
                    totalTime++;
                    aProcess.cpuTime--;

                    if(aProcess.cpuTime ==0){
                        break;
                    }
                } else {
                    break;
                }
            }

            List<Process> anyProcess = processesArriveWithLowerPriority(processList, aProcess, totalTime);

            if (anyProcess.size() > 0) {
                for (Process p : anyProcess) {
                    queue.add(p);
                    p.isTaken = true;
                }
            }

            if (aProcess.cpuTime > 0) {
                aProcess.lastTime = totalTime;
                remainIngProcess.add(aProcess);
            }

        }

        Collections.sort(remainIngProcess, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.priority == o2.priority) {
                    return o1.arrivalTime - o2.arrivalTime;
                } else {
                    return o1.priority - o2.priority;
                }
            }
        });

        for (Process p : remainIngProcess) {
            if (p.cpuTime > 0) {
                p.waitingTime += (totalTime - p.lastTime);
                totalTime += p.cpuTime;
                p.cpuTime = 0;
            }

        }

    }

    public static void calculateTurnAroundTime(List<Process> processList) {
        for (Process process : processList) {
            process.turnAroundTime = process.waitingTime + process.savedCpuTime;
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


    public static List<Process> processesArriveWithLowerPriority(List<Process> processList, Process runningProcess, int time) {
        List<Process> list = new ArrayList<>();
        for (Process myProess : processList) {
            if (!myProess.isTaken) {
                if (myProess.priority < runningProcess.priority) {
                    if (myProess.arrivalTime == time) {
                        list.add((myProess));
                    }
                }
            }
        }

        return list;
    }
}
