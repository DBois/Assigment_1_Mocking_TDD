package dk.cphbusiness.banking.backend.models;

import java.util.ArrayList;
import java.util.List;

public class RealAccount implements Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance;
    private List<Movement> movements;
    private static long movementId;


    public RealAccount(Bank bank, Customer customer, String number){
        this.bank = bank;
        this.customer = customer;
        this.number = number;
        this.balance = 0;
        movements = new ArrayList<>();
    }

    public RealAccount(Bank bank, Customer customer, String number, long balance){
        this.bank = bank;
        this.customer = customer;
        this.number = number;
        this.balance = balance;
        movements = new ArrayList<>();
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
    public void transfer(long amount, Account target, long timeStamp) {
        this.balance -= amount;
        target.updateBalance(amount);
        movements.add(new RealMovement(movementId++, timeStamp, -amount, this, target));
        target.getMovements().add(new RealMovement(movementId++, timeStamp, amount, this, target));
    }

    @Override
    public void transfer(long amount, String targetNumber, long timeStamp){
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target, timeStamp);
        movements.add(new RealMovement(movementId++, timeStamp, -amount, this, target));
        target.getMovements().add(new RealMovement(movementId++, timeStamp, amount, this, target));
    }

    @Override
    public void updateBalance(long amount) {
        this.balance += amount;
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

}
