package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class OutcomeEntry extends Entry {

    private  final OutcomeCategory outcomeCategory;
    private final OutcomeType outcomeType;

    public OutcomeEntry(int id, BigDecimal sum, LocalDate date, Person person, TransferStatus transferStatus,
                        String comment, OutcomeCategory outcomeCategory, OutcomeType outcomeType) {
        super(id, sum, date, person, transferStatus, comment);
        this.outcomeCategory = outcomeCategory;
        this.outcomeType = outcomeType;
    }

    public OutcomeCategory getOutcomeCategory() {
        return outcomeCategory;
    }

    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutcomeEntry that)) return false;
        if (!super.equals(o)) return false;
        return getOutcomeCategory() == that.getOutcomeCategory() && getOutcomeType() == that.getOutcomeType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutcomeCategory(), getOutcomeType());
    }

    @Override
    public String toString() {
        return super.toString() +"OutcomeEntry{" +
                "outcomeCategory=" + outcomeCategory +
                ", outcomeType=" + outcomeType +
                '}';
    }
}
