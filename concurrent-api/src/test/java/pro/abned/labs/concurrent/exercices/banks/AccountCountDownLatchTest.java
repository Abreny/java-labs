package pro.abned.labs.concurrent.exercices.banks;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountCountDownLatchTest {
    @Test
    void credit() throws InterruptedException {
        Account jane = new Account();
        jane.setSolde(0);

        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(2);

        new Thread(() -> {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + " - started at: " + System.nanoTime());
                for (int i = 0; i < 1000; i++) {
                    jane.credit(1);
                }
                doneSignal.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + " - started at: " + System.nanoTime());
                for (int i = 0; i < 1000; i++) {
                    jane.credit(1);
                }
                doneSignal.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        startSignal.countDown();

        doneSignal.await();

        assertEquals(2000, jane.getSolde());
    }

    @Test
    void debit() throws InterruptedException {
        Account jane = new Account();
        jane.setSolde(2000);

        Thread depot1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                jane.debit(1);
            }
        });

        Thread depot2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                jane.debit(1);
            }
        });

        depot1.start();
        depot2.start();

        depot1.join();
        depot2.join();

        assertEquals(0, jane.getSolde());
    }
}