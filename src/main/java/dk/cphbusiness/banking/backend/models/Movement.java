package dk.cphbusiness.banking.backend.models;

public interface Movement  {

    long getTime();
    long getAmount();
    String getTarget();
    String getSource();
    long getId();

}
