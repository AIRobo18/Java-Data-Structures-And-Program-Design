import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Robert Aroutiounian
 * @version 10/16/2015
 */
public class SchedulingSimulation
{
    private PriorityQueue<Job> waitingJobs;
    private LinkedBlockingQueue<Job> allJobs;
    private Job currentJob;
    private int priorityMode;
    private final int TIME_SLICE_PER_JOB = 3;
    private final int SIMULATION_DURATION = 100;
    private final int JOB_PROBABILITY = 30;
    private final int JOB_PRIORITY = 4;
    private final int JOB_MIN_TIME = 1;
    private final int JOB_MAX_TIME = 5;
    private static final int SORT_BY_PRIORITY = 0;
    private static final int SORT_BY_LENGTH = 1;

    public SchedulingSimulation(int priority)
    {
        Random ranCre = new Random();
        Random ranPri = new Random();
        Random ranTime = new Random();

        this.waitingJobs = new PriorityQueue<>();
        this.allJobs = new LinkedBlockingQueue<>();

        this.currentJob = new Job(ranCre.nextInt(TIME_SLICE_PER_JOB + 1), ranPri.nextInt(JOB_PRIORITY + 1), ranTime.nextInt(JOB_MAX_TIME - JOB_MIN_TIME) + JOB_MIN_TIME);
        this.priorityMode = priority;

        if (priority == this.SORT_BY_PRIORITY)
        {
            this.waitingJobs = new PriorityQueue<Job>();
        }
        else if (priority == this.SORT_BY_LENGTH)
        {
            this.waitingJobs = new PriorityQueue<Job>(10, new Comparator<Job>()
            {
                public int compare(Job job1, Job job2)
                {
                    return job1.getTimeLeft() - job2.getTimeLeft();
                }
            });
        }

    }

    public void runSimulation()
    {
        Random ran = new Random();
        SchedulingSimulation simulator = new SchedulingSimulation(ran.nextInt(this.JOB_PRIORITY + 1));
        for (int i = 0; i < this.SIMULATION_DURATION; i++)
        {
            simulator.generateJob(ran.nextInt(this.JOB_PROBABILITY + 1));

        }
    }

    public void generateJob(int timeCreated)
    {
        Random ranPri = new Random();
        Random ranTime = new Random();

        int pri;
        int time;

        pri = ranPri.nextInt(JOB_PRIORITY + 1);
        time = ranTime.nextInt((JOB_MAX_TIME - JOB_MIN_TIME) + JOB_MIN_TIME);
        Job job = new Job(timeCreated, pri, time);
        this.allJobs.add(job);
    }

    public void currentReport(int time)
    {
        System.out.println("-->Time Marker " + time + "\t jobs waiting " + this.waitingJobs.size());
        System.out.println();
    }

    public void finalReport()
    {
        System.out.println("\t Active jobs:");



        DecimalFormat df = new DecimalFormat("#0.00");
        int completed = this.allJobs.size() - this.waitingJobs.size();
        int waiting = this.waitingJobs.size();
        int total = this.allJobs.size();
        System.out.println("\t The number of jobs currently executing is " + waiting);
        System.out.println("\t The number of completed jobs is " + completed);
        System.out.println("\t THe total number of jobs is " + total);

        int unfinishedJobsSum = 0;
        Job job = null;
        for (int i = 0; i < waiting; i++)
        {
            job = this.waitingJobs.poll();
            unfinishedJobsSum += job.getTimeLeft();
        }
        System.out.println("The average time left for unfinished jobs is ");
        System.out.println(df.format((double) unfinishedJobsSum / waiting));
    }

    public static void main(String[] args)
    {
        System.out.println("***STARTING THE SIMULATION WITH PRIORITY MODE SET TO SORT_BY_PRIORITY***");

        SchedulingSimulation schedule = new SchedulingSimulation(SchedulingSimulation.SORT_BY_LENGTH);
        schedule.runSimulation();
    }
}
