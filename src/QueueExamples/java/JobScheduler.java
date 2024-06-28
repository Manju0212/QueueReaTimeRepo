package QueueExamples.java;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class JobScheduler {
    private static DelayQueue<DelayedJob> jobQueue = new DelayQueue<>();

    public static void main(String[] args) {
    
        jobQueue.put(new DelayedJob("Job1", 5000)); // 5 seconds
        jobQueue.put(new DelayedJob("Job2", 10000)); // 10 seconds
        jobQueue.put(new DelayedJob("Job3", 2000)); // 2 seconds

        // Consumer thread
        new Thread(() -> {
            while (true) {
                try {
                    DelayedJob job = jobQueue.take();
                    System.out.println("Executing job: " + job.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
       class DelayedJob implements Delayed {
              private String name;
               private long delay;

    public DelayedJob(String name, long delay) {
        this.name = name;
        this.delay = delay;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
    }
}



