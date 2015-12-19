package pl.training.bank.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import pl.training.bank.validation.Funds;

@Table(name = "operations")
@Entity
public class Operation implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public enum Type {
        DEPOSIT, WITHDRAW
    }
    
    @GeneratedValue
    @Id
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @NotNull
    @Pattern(regexp = "\\d{3,}")
    @Column(name = "account_number")
    private String accountNumber;
    @Funds
    @NotNull
    private long funds;
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Operation baseOperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    public Operation getBaseOperation() {
        return baseOperation;
    }

    public void setBaseOperation(Operation baseOperation) {
        this.baseOperation = baseOperation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.timestamp);
        hash = 97 * hash + Objects.hashCode(this.accountNumber);
        hash = 97 * hash + (int) (this.funds ^ (this.funds >>> 32));
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operation other = (Operation) obj;
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!Objects.equals(this.accountNumber, other.accountNumber)) {
            return false;
        }
        if (this.funds != other.funds) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operation{" + "id=" + id + ", timestamp=" + timestamp + ", accountNumber=" + accountNumber + ", funds=" + funds + ", type=" + type + ", baseOperation=" + baseOperation + '}';
    }
    
}
