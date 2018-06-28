import java.util.*;

public class ShortestJobRemainingNext {
    static class Process {
        int processId;
        int arrivalTime;
        int cpuTime;
        int waitingTime;
        int turnAroundTime;
        int lastTime;
        boolean isTaken;
        int savedCpuTime;

        public Process(int processId, int arrivalTime, int cpuTime) {
            this.processId = processId;
            this.arrivalTime = arrivalTime;
            this.cpuTime = cpuTime;
            savedCpuTime = cpuTime;

            waitingTime = 0;
            turnAroundTime = 0;
            lastTime = arrivalTime;

            isTaken = false;

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of Process");
        int p = sc.nextInt();
        List<Process> list = new ArrayList<>();

        int totalTimeTake = 0;

        for (int i = 1; i <= p; i++) {
            System.out.println("Enter the arrival Time for Process " + i);
            int arrivalTime = sc.nextInt();
            System.out.println("Enter the Cpu Time For Process " + i);
            int cputTime = sc.nextInt();
            totalTimeTake +=cputTime;


            list.add(new Process(i, arrivalTime, cputTime));

        }


        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.arrivalTime - o2.arrivalTime;
            }
        });

        shortestJobRemainingNext(list,totalTimeTake);
        calculateTurnAroundTime(list);

        for (Process aProcess : list){
            System.out.println("Process Id "+aProcess.processId+"  waiting Time "+aProcess.waitingTime+"  TurnAroundTime  "+aProcess.turnAroundTime);
        }

        System.out.println("AVG waiting Time  "+calculateAvgWaitingTime(list));
        System.out.println("Avg Turn Around Time "+calculateAvgTurnAroundTIme(list));


    }


    static void shortestJobRemainingNext(List<Process> processList,int maxTime) {
        int totalTime = 0;

        int id = -1;
        while (totalTime!=maxTime){
            List<Process> allProcess = allProcessAtTHisTime(processList,totalTime);
            Process aProcess = extractMinCpuTimeProcess(allProcess);
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

    public static Process extractMinCpuTimeProcess(List<Process> processList){

        List<Process> list = new ArrayList<>();
        for(Process p : processList){
            if(p.cpuTime>0){
                list.add(p);
            }
        }

        Collections.sort(list, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
               if(o1.cpuTime == o2.cpuTime){
                   return o1.arrivalTime - o2.arrivalTime;
               }
               else{
                   return o1.cpuTime - o2.cpuTime;
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
}
