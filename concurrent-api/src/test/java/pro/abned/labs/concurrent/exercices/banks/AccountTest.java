package pro.abned.labs.concurrent.exercices.banks;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void credit() throws InterruptedException, ExecutionException {
        Account jane = new Account();
        jane.setSolde(0);

        Thread depot1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                jane.credit(1);
            }
        });

        Thread depot2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                jane.credit(1);
            }
        });

        depot1.start();
        depot2.start();

        depot1.join();
        depot2.join();

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