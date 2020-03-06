package dk.cphbusiness.banking;

public interface Account {
    Bank getBank();

    Customer getCustomer();

    String getNumber();

    long getBalance();

    void transfer(long amount, Account target);

    void transfer(long amount, String targetNumber);

    void updateBalance(Long amount);
}
