package dk.cphbusiness.banking.backend.models;

public interface Movement  {

    long getTime();
    long getAmount();
    Account getTarget();
    Account getSource();
    long getId();

}
