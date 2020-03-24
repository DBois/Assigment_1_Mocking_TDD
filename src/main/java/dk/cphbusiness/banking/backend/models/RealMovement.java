package dk.cphbusiness.banking.backend.models;

public class RealMovement implements Movement {
    private long id;
    long time;
    long amount;
    Account source;
    Account target;

    public RealMovement(long id, long time, long amount, Account source, Account target) {
        this.id = id;
        this.time = time;
        this.amount = amount;
        this.source = source;
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

    @Override
    public Account getSource() {
        return this.source;
    }

    @Override
    public long getId() {
        return this.id;
    }
}

