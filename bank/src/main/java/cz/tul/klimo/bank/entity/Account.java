package cz.tul.klimo.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mena;
    private double zustatek;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setMena(String mena) {
        this.mena = mena;
    }

    public void setZustatek(double zustatek) {
        this.zustatek = zustatek;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account(String mena, User user){
        this.user = user;
        this.mena = mena;
        this.zustatek = 0;
    }

    public String getMena(){
        return mena;
    }

    public double getZustatek(){
        return zustatek;
    }

    public int getId(){
        return id;
    }

    public void setTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public List getTransactions(){
        return transactions;
    }

    public void vklad(double castka){
        zustatek += castka;
    }

    public Boolean platba(double castka){
        if((zustatek - castka) >= 0){
            zustatek -= castka;
            return true;
        }else{
            return false;
        }
    }
}
