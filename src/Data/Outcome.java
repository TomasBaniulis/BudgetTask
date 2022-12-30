package Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Outcome {

    private final BigDecimal sum;
    private final LocalDate date;
    private final OutcomeCategory category;
    private final OutcomeType type;
    private final Person person;
    private final TransferStatus transferStatus;

    public Outcome(BigDecimal sum, LocalDate date, OutcomeCategory category, OutcomeType type, Person person,
                   TransferStatus transferStatus) {
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.type = type;
        this.person = person;
        this.transferStatus = transferStatus;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public OutcomeCategory getCategory() {
        return category;
    }

    public OutcomeType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }


}
