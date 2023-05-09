package cz.tul.klimo.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typ;
    private int prijemce;
    private double castka;
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    public Transaction(){}

    public Transaction(String typ, double castka, Account account){
        this.typ = typ;
        this.castka = castka;
        this.account = account;
        this.datum = LocalDateTime.now();
    }

    public Transaction(String typ, double castka, int prijemce, Account account){
        this.typ = typ;
        this.castka = castka;
        this.prijemce = prijemce;
        this.account = account;
        this.datum = LocalDateTime.now();
    }

    public String getTyp(){
        return typ;
    }

    public double getCastka(){
        return castka;
    }

    public int getPrijemce(){
        return prijemce;
    }

    public LocalDateTime getDatum(){
        return datum;
    }
}
