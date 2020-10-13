package personal.davion.junit5.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME;

/**
 * 重复Tests
 * 自动注入
 */
@DisplayName("Repeat test demo")
public class RepeatTests {

    @RepeatedTest(10)
    public void repeatTests(RepetitionInfo repetitionInfo) {
        System.out.printf("total: %s, current: %s \n", repetitionInfo.getTotalRepetitions(), repetitionInfo.getCurrentRepetition());
    }

//    @RepeatedTest(value = 2, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @RepeatedTest(value = 2, name = LONG_DISPLAY_NAME)
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        assertEquals(String.format("Repeat! :: repetition %s of %s", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions()),
                testInfo.getDisplayName());
    }

}
