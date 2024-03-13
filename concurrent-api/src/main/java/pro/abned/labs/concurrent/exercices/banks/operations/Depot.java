package pro.abned.labs.concurrent.exercices.banks.operations;

import pro.abned.labs.concurrent.exercices.banks.Account;

public class Depot implements Credit {
    private final Account account;
    private final int amount;

    public Depot(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void credit() {
        account.credit(amount);
    }
}
