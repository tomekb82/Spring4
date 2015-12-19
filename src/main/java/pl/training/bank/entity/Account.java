package pl.training.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Proxy;

 @Proxy(lazy = false)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQuery(name = Account.GET_BY_NUMBER, query = "select a from Account a where a.number = :number")
@Table(name = "accounts")
@Entity
public class Account implements Serializable {

    public static final String GET_BY_NUMBER = "getByNumber";
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String number;
    private long balance;
    @XmlTransient
    @ManyToMany
    private List<Customer> owners;

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

    @JsonIgnore
    public List<Customer> getOwners() {
        return owners;
    }

    public void setOwners(List<Customer> owners) {
        this.owners = owners;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (balance ^ (balance >>> 32));
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((owners == null) ? 0 : owners.hashCode());
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
        if (owners == null) {
            if (other.owners != null) {
                return false;
            }
        } else if (!owners.equals(other.owners)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", number=" + number + ", balance="
                + balance + ", owners=" + owners + "]";
    }

}
