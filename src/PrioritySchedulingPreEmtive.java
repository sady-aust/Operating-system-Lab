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
        int totalTime =0;

        for (int i = 1; i <= p; i++) {
            System.out.println("Enter the arrival Time for Process " + i);
            int arrivalTime = sc.nextInt();
            System.out.println("Enter the Cpu Time For Process " + i);
            int cputTime = sc.nextInt();
            totalTime +=cputTime;
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
        doPriorityScheduling(list,totalTime);
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

    static void doPriorityScheduling(List<Process> processList,int maxTime) {
        int totalTime = 0;

        int id = -1;
        while (totalTime!=maxTime){
            List<Process> allProcess = allProcessAtTHisTime(processList,totalTime);
            Process aProcess = extractHigherPriorityProcess(allProcess);
            if(id!=aProcess.processId) {
                aProcess.waitingTime += (totalTime - aProcess.lastTime);

                id = aProcess.processId;
            }

            aProcess.cpuTime--;
            totalTime++;
            aProcess.lastTime = totalTime;


        }

    }


    /*
* Return all the process available at this time
* */
    public static List<Process> allProcessAtTHisTime(List<Process> processList, int time) {
        List<Process> list = new ArrayList<>();

        for (Process myProess : processList) {

            if (myProess.arrivalTime <= time ) {
                list.add((myProess));

            }

        }

        return list;

    }

    /*
    *
    * return the process which has minimum cpuTime from processList
    * */

    public static Process extractHigherPriorityProcess(List<Process> processList){

        List<Process> list = new ArrayList<>();
        for(Process p : processList){
            if(p.cpuTime>0){
                list.add(p);
            }
        }

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if(o1.priority == o2.priority){
                    return o1.arrivalTime - o2.arrivalTime;
                }
                else{
                    return o1.priority - o2.priority;
                }
            }
        });

        if(list.size()>0){
            return list.get(0);

        }
        else{
            return null;
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
