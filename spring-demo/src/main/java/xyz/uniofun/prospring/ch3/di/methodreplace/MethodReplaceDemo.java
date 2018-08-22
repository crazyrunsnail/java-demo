package xyz.uniofun.prospring.ch3.di.methodreplace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplaceDemo {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("methodinject/spring-context.xml");
        ReplacementTarget replaceTarget = (ReplacementTarget)ctx.getBean("replaceTarget");
        ReplacementTarget standardTarget = (ReplacementTarget)ctx.getBean("standardTarget");

        displayInfo(replaceTarget);
        displayInfo(standardTarget);

    }

    private static void displayInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Thanks for playing, try again!"));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");
        for (int x = 0; x < 1000000; x++) {
            String out = target.formatMessage("No filter in my head");
        }
        stopWatch.stop();
        System.out.println("1000000 invocations took: "
                + stopWatch.getTotalTimeMillis() + " ms");
    }
}
