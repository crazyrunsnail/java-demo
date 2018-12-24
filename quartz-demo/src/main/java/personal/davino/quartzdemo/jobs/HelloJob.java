package personal.davino.quartzdemo.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
public class HelloJob implements Job {
    private String jobSays;
    private Long  scheduledCount;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Hello job executing.....{}, count: {}", getJobSays(), getScheduledCount());

    }


    public String getJobSays() {
        return jobSays;
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public Long getScheduledCount() {
        return scheduledCount;
    }

    public void setScheduledCount(Long scheduledCount) {
        this.scheduledCount = scheduledCount;
    }
}
