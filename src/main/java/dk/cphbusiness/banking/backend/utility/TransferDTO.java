package dk.cphbusiness.banking.backend.utility;
public class TransferDTO {
    private long amount;
    private String source;
    private String target;

    public TransferDTO(long amount, String source, String target) {
        this.amount = amount;
        this.source = source;
        this.target = target;
    }
    public TransferDTO() {

    }

    public long getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "amount=" + amount +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}