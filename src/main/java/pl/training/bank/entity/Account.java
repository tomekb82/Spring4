package pl.training.bank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "accounts")
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String number;
    private long balance;

    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }

    public void depositFunds(long funds) {
        balance += funds;
    }

    public void withdrawFunds(long funds) {
        balance -= funds;
    }

    public void confirmFunds(long funds) {
        if (balance < funds) {
            throw new InsufficientFundsException();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (balance ^ (balance >>> 32));
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Account other = (Account) obj;
        if (balance != other.balance) {
            return false;
        }
        if (number == null) {
            if (other.number != null) {
                return false;
            }
        } else if (!number.equals(other.number)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", number=" + number + ", balance="
                + balance + "]";
    }

}