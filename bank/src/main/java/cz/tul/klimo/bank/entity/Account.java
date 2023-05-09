package cz.tul.klimo.bank.entity;

import jakarta.persistence.*;

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

    public Account(){}

    public Account(String mena, User user){
        this.user = user;
        this.mena = mena;
        this.zustatek = 0;
    }
}
