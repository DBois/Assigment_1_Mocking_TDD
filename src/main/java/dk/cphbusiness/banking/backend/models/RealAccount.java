package dk.cphbusiness.banking.backend.models;

import dk.cphbusiness.banking.backend.exceptions.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;

public class RealAccount implements Account {
    private Bank bank;
    private Customer customer;
    private String number;
    private long balance;
    private List<RealMovement> movements;
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
    public void transfer(long amount, Account target, long timeStamp) throws InvalidAmountException {
        if(target.getNumber().equals(number)) throw new InvalidAmountException("Can't transfer to same account", 403);
        if (amount <= 0) throw new InvalidAmountException("Invalid amount", 403);
        this.balance -= amount;
        target.updateBalance(amount);
        movements.add(new RealMovement(movementId++, timeStamp, amount, this.number, target.getNumber()));
        target.getMovements().add(new RealMovement(movementId++, timeStamp, amount, this.number, target.getNumber()));
    }

    @Override
    public void transfer(long amount, String targetNumber, long timeStamp) throws InvalidAmountException {
        Account target = bank.getAccount(targetNumber);
        transfer(amount, target, timeStamp);
        movements.add(new RealMovement(movementId++, timeStamp, amount, this.number, target.getNumber()));
        target.getMovements().add(new RealMovement(movementId++, timeStamp, amount, this.number, target.getNumber()));
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
