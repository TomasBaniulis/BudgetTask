package Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {

    private final BigDecimal sum;
    private final LocalDate date;
    private final IncomeCategory category;
    private  final boolean isBankTransfer;
    private final Person person;
    private  final TransferStatus transferStatus;

    public Income(BigDecimal sum, LocalDate date, IncomeCategory category, boolean isBankTransfer,
                  Person person, TransferStatus transferStatus) {
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.isBankTransfer = isBankTransfer;
        this.person = person;
        this.transferStatus = transferStatus;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public boolean isBankTransfer() {
        return isBankTransfer;
    }

    public Person getPerson() {
        return person;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }
}
