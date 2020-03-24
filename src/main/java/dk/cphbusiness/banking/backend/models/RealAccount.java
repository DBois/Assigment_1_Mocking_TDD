package dk.cphbusiness.banking.backend.models;

import java.util.ArrayList;
import java.util.List;

public class RealAccount implements Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance;
    private List<Movement> movements;
    private Clock clock;
    private static long movementId;


    public RealAccount(Bank bank, Customer customer, String number, Clock clock){
        this.bank = bank;
        this.customer = customer;
        this.number = number;
        this.balance = 0;
        movements = new ArrayList<>();
        this.clock = clock;

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
        this.balance -= amount;
        target.updateBalance(amount);
        var date = clock.getTime();
        movements.add(new RealMovement(movementId++, date, -amount, target, this));
        target.getMovements().add(new RealMovement(movementId++, date, amount, target, this));
    }

    @Override
    public void transfer(long amount, String targetNumber){
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target);

        var date = clock.getTime();
        movements.add(new RealMovement(movementId++, date, -amount, target, this));
        target.getMovements().add(new RealMovement(movementId++, date, amount, target, this));
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
