package personal.davino.quartzdemo.clients;


import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import personal.davino.quartzdemo.jobs.HelloJob;
import personal.davino.quartzdemo.listeners.SimpleJobListener;

import java.util.concurrent.TimeUnit;


public class SimpleTriggerClient {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail =
                JobBuilder.newJob(HelloJob.class)
                        .withIdentity("job1")
                        .usingJobData("jobSays", "Hello World!")
                        .usingJobData("scheduledCount", 0L ).storeDurably()
                        .build();
        scheduler.addJob(jobDetail, false);
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1").forJob("job1")
                .withSchedule(simpleSchedule().withIntervalInSeconds(1).withRepeatCount(6))
                .startNow().build();
        scheduler.scheduleJob(trigger);


        // Listener
        SimpleJobListener listener = new SimpleJobListener();
        scheduler.getListenerManager().addJobListener(listener, KeyMatcher.keyEquals(JobKey.jobKey("job1")));
        scheduler.start();
        TimeUnit.SECONDS.sleep(3);
        scheduler.pauseJob(JobKey.jobKey("job1"));
        TimeUnit.SECONDS.sleep(1);
        scheduler.resumeJob(JobKey.jobKey("job1"));
    }
}
