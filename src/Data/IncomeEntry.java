package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class IncomeEntry extends Entry {

    private final IncomeCategory incomeCategory;
    private final IncomeType incomeType;

    public IncomeEntry(int id, BigDecimal sum, LocalDate date, Person person, TransferStatus transferStatus,
                       String comment, IncomeCategory incomeCategory, IncomeType incomeType) {
        super(id, sum, date, person, transferStatus, comment);
        this.incomeCategory = incomeCategory;
        this.incomeType = incomeType;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomeEntry that)) return false;
        if (!super.equals(o)) return false;
        return getIncomeCategory() == that.getIncomeCategory() && getIncomeType() == that.getIncomeType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIncomeCategory(), getIncomeType());
    }

    @Override
    public String toString() {
        return super.toString() + "IncomeEntry{" +
                "incomeCategory=" + incomeCategory +
                ", incomeType=" + incomeType +
                '}';
    }
}
