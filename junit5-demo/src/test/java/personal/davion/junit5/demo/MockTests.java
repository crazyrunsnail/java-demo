package personal.davion.junit5.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockTests {

    @Mock
    private List<String> mockList;

    @Test
    void verify_SimpleInvocationOnMock() {
        //mockList.size();
        verify(mockList).size();
    }

    @Test
    void verify_NumberOfInteractionsWithMock() {
        mockList.size();
        mockList.size();
        verify(mockList, times(2)).size();
        verify(mockList, atLeast(1)).size();
        verify(mockList, atMost(10)).size();
    }

    @Test
    void verify_OrderedInvocationsOnMock() {
        mockList.size();
        mockList.add("add a parameter");
        mockList.clear();

        InOrder inOrder = inOrder(mockList);
        inOrder.verify(mockList).size();
        inOrder.verify(mockList).add("add a parameter");
        inOrder.verify(mockList).clear();
    }

}
