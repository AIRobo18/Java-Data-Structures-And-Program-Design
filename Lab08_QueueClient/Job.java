/**
 * A class that represents a job simulaton
 *
 * @author Robert Aroutiounian
 * @version 10/16/2015
 */
public class Job implements Comparable<Job>
{
    private int jobNo;
    private int priority;
    private int createdAtTime;
    private int timeLeft;
    private static int jobsCreated;

    public Job(int timeCreated, int newPriority, int newTimeLeft)
    {
        this.createdAtTime = timeCreated;
        this.priority = newPriority;
        this.timeLeft = newTimeLeft;

        jobsCreated++;
        this.jobNo = jobsCreated;

        System.out.println("\tCreated job " + this.jobNo);
    }

    public int compareTo(Job job)
    {
        if (this.priority == job.priority)
        {
            return 0;
        }
        else if (this.priority < job.priority)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }

    public int getTimeLeft()
    {
        return this.timeLeft;
    }

    public int getJobsCreated()
    {
        return this.jobsCreated;
    }

    public void update(int newTimeLeft)
    {
        this.timeLeft = newTimeLeft;
    }

    public boolean isFinished()
    {
        if (this.getTimeLeft() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "Place holder";
    }
}
