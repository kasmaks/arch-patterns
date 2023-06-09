import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MasterSlavePatternTest {
    @Mock
    private ExecutorService mockExecutorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMasterSlavePattern() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", 2));
        tasks.add(new Task("Task 2", 3));

        List<Future<Result>> mockFutures = new ArrayList<>();
        for (Task task : tasks) {
            Future<Result> mockFuture = Mockito.mock(Future.class);
            Mockito.when(mockFuture.get()).thenReturn(new Result(task.getName(), "Task completed"));
            mockFutures.add(mockFuture);
        }

        Mockito.when(mockExecutorService.submit(Mockito.any(Callable.class))).thenReturn(mockFutures.get(0), mockFutures.get(1));

        List<Result> results = MasterSlavePattern.executeTasks(mockExecutorService, tasks);

        Assertions.assertEquals(2, results.size());
        Assertions.assertEquals("Task 1: Task completed", results.get(0).toString());
        Assertions.assertEquals("Task 2: Task completed", results.get(1).toString());
    }
}