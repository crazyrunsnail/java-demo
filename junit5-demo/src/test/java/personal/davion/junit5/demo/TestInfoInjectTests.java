package personal.davion.junit5.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link TestInfo}
 * {@link RepetitionInfo} repeat test 可注入
 * {@link TestReporter}
 */
@DisplayName("TestInfoInjectTests")
public class TestInfoInjectTests {

    TestInfoInjectTests(TestInfo testInfo) {
        assertEquals("TestInfoInjectTests", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }

}
