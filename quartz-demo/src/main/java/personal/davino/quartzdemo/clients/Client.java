package personal.davino.quartzdemo.clients;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import personal.davino.quartzdemo.jobs.HelloJob;

public class Client {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail =
                JobBuilder.newJob(HelloJob.class)
                        .usingJobData("jobSays", "Hello World!")
                        .usingJobData("scheduledCount", 0L )
                        .build();

        CronTrigger simpleTrigger = TriggerBuilder.newTrigger().startNow().withIdentity("simpleTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * ? * *")).build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }
}
