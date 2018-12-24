package personal.davino.quartzdemo.listeners;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.listeners.JobListenerSupport;

public class SimpleJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "simpleJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println("job is  executed...." + jobDetail.getKey().getName());
    }
}
