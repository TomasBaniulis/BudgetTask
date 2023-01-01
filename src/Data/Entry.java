package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Entry {

    private final int id;
    private  BigDecimal sum;
    private  LocalDate date;
    private  Person person;
    private  TransferStatus transferStatus;
    private  String comment;

    public Entry(int id, BigDecimal sum, LocalDate date, Person person, TransferStatus transferStatus, String comment) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.person = person;
        this.transferStatus = transferStatus;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public Person getPerson() {
        return person;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry entry)) return false;
        return getId() == entry.getId() && Objects.equals(getSum(), entry.getSum()) && Objects.equals(getDate(), entry.getDate()) && Objects.equals(getPerson(), entry.getPerson()) && getTransferStatus() == entry.getTransferStatus() && Objects.equals(getComment(), entry.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSum(), getDate(), getPerson(), getTransferStatus(), getComment());
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", sum=" + sum +
                ", date=" + date +
                ", person=" + person +
                ", transferStatus=" + transferStatus +
                ", comment='" + comment + '\'' +
                '}';
    }
}
