package pro.abned.labs.concurrent.exercices.databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Connection connection1;
    private Connection connection2;
    @Test
    void getConnection() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(2);
        new Thread(() -> {
            try {
                start.await();
                connection1 = Database.getConnection();
                done.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                start.await();
                connection2 = Database.getConnection();
                done.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        start.countDown();
        done.await();

        assertEquals(connection1, connection2);
    }
}