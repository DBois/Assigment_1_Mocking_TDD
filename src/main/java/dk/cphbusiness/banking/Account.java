package dk.cphbusiness.banking;

public class Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance = 0;

    public Account(Bank bank, Customer customer, String number){
        this.bank = bank;
        this.customer = customer;
        this.number = number;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getNumber() {
        return number;
    }

    public long getBalance(){
        return balance;
    }



    public void transfer(long amount, Account target) {
        balance -= amount;
        target.balance += amount;
    }

    public void transfer(long amount, String targetNumber){
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target);
    }
}
