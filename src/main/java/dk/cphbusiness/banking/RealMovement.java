package dk.cphbusiness.banking;

import java.util.Date;

public class RealMovement implements Movement {
    long time;
    long amount;
    Account target;
    Account source;

    public RealMovement(long time, long amount, Account target, Account source) {
        this.time = time;
        this.amount = amount;
        this.target = target;
        this.source = source;
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

    @Override
    public Account getSource() {
        return this.source;
    }
}

