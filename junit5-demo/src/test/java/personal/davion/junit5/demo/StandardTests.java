package personal.davion.junit5.demo;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class StandardTests {

    /**
     * 一个类只运行一次
     */
    @BeforeAll
    static void initAll() {
    }

    /**
     * 每个方法都会运行
     */
    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    /**
     * 失败也会运行，除非用disabled
     */
    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    /**
     * 全部都会运行
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("tearDownAll");
    }

}