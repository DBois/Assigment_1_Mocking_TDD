package dk.cphbusiness.banking;

public class RealAccount implements Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance = 0;


    public RealAccount(Bank bank, Customer customer, String number){
        this.bank = bank;
        this.customer = customer;
        this.number = number;
    }

    @Override
    public Bank getBank() {
        return bank;
    }
    @Override
    public Customer getCustomer() {
        return customer;
    }
    @Override
    public String getNumber() {
        return number;
    }
    @Override
    public long getBalance(){
        return balance;
    }


    @Override
    public void transfer(long amount, Account target) {
        balance -= amount;
        target.updateBalance(amount);
    }
    @Override
    public void transfer(long amount, String targetNumber){
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target);
    }

    @Override
    public void updateBalance(long amount) {
        this.balance += amount;
    }
}
