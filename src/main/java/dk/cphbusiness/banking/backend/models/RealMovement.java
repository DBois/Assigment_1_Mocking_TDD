package dk.cphbusiness.banking.backend.models;

public class RealMovement implements Movement {
    private long id;
    long time;
    long amount;
    String source;
    String target;

    public RealMovement(long id, long time, long amount, String source, String target) {
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
    public String getTarget() {
        return this.target;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public long getId() {
        return this.id;
    }
}

