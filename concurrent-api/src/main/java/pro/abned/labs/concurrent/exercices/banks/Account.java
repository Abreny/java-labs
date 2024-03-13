package pro.abned.labs.concurrent.exercices.banks;

public class Account {
    private String name;
    private int solde;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void credit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        solde += amount;
    }

    public void debit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Debit amount must be positive");
        }
        if (solde < amount) {
            throw new IllegalArgumentException(String.format("Operation failed because amount %d is greater than solde %d", amount, solde));
        }

        solde -= amount;
    }
}
