package dk.cphbusiness.banking.backend.models;
import java.util.*;
public interface Account {



    public Bank getBank();
    public Customer getCustomer();
    public String getNumber();
    public long getBalance();
    void transfer(long amount, Account target, long timeStamp);
    void transfer(long amount, String targetNumber, long timeStamp);
    void updateBalance(long amount);
    List<Movement> getMovements();




}
