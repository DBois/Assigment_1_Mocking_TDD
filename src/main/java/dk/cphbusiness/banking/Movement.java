package dk.cphbusiness.banking;

import java.util.Date;

public interface Movement  {

    long getTime();
    long getAmount();
    Account getTarget();
    Account getSource();
}
