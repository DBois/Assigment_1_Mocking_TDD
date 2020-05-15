package dk.cphbusiness.banking.backend.models;
import dk.cphbusiness.banking.backend.exceptions.InvalidAmountException;

import java.util.*;
public interface Account {



    public Bank getBank();
    public Customer getCustomer();
    public String getNumber();
    public long getBalance();
    void transfer(long amount, Account target, long timeStamp) throws InvalidAmountException;
    void transfer(long amount, String targetNumber, long timeStamp) throws InvalidAmountException;
    void updateBalance(long amount);
    List<RealMovement> getMovements();




}
