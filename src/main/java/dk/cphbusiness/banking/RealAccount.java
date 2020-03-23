package dk.cphbusiness.banking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealAccount implements Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance;
    private List<RealMovement> movements;
    private Clock clock;


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
        balance -= amount;
        target.updateBalance(amount);
        var date = clock.getTime();
        movements.add(new RealMovement(date, -amount, target, this));
        target.getMovements().add(new RealMovement(date, amount, target, this));
    }

    @Override
    public void transfer(long amount, String targetNumber){
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target);

        var date = clock.getTime();
        movements.add(new RealMovement(date, -amount, target, this));
        target.getMovements().add(new RealMovement(date, amount, target, this));
    }

    @Override
    public void updateBalance(long amount) {
        this.balance += amount;
    }

    @Override
    public List<RealMovement> getMovements() {
        return this.movements;
    }
}
