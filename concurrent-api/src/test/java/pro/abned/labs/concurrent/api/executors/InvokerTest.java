package pro.abned.labs.concurrent.api.executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

class InvokerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private  Runnable r;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        r = () -> {
            System.out.println("Task is executed in: " + Thread.currentThread().getName());
        };
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testExecute_thread() throws InterruptedException {
        // 1. Thread is a class
        // 2. Thread can't execute multiple runnable - one runnable per thread
        // 3. Thread is a concrete way to execute a task
        new Thread(r).start();
        Thread.sleep(1000);

        String result = outputStreamCaptor.toString().trim();
        assertEquals("Task is executed in: Thread-0", result);
    }

    @Test
    void testExecute_executor() throws InterruptedException {
        new Invoker().execute(r);
        String result = outputStreamCaptor.toString().trim();
        assertEquals("Task is executed in: main", result);
    }

    @Test
    void testExecute_executor_executor_service() throws InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(r);
        Thread.sleep(1000);
        String result = outputStreamCaptor.toString().trim();
        assertEquals("Task is executed in: pool-1-thread-1", result);
    }
}