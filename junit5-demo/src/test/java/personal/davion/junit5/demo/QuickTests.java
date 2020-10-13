package personal.davion.junit5.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickTests {
    @Test
    public void test() {
        System.out.println("junit5 test begin...");
    }

    @BeforeAll
    static void beforeAllTest() {
        System.out.println("before all");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("before each....");
    }
}
