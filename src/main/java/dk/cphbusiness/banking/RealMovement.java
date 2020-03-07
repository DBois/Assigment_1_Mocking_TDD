package dk.cphbusiness.banking;

import java.util.Date;

public class RealMovement implements Movement {
    long time;
    long amount;
    Account target;

    public RealMovement(long time, long amount, Account target) {
        this.time = time;
        this.amount = amount;
        this.target = target;
    }

    @Override
    public long getTime() {
        return this.time;
    }

    @Override
    public long getAmount() {
        return this.amount;
    }

    @Override
    public Account getTarget() {
        return this.target;
    }
}
