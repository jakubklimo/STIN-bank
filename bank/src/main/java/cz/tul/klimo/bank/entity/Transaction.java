package cz.tul.klimo.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private char typ;
    private int prijemce;
    private double castka;
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    public Transaction(){}

    public Transaction(char typ, double castka, Account account){
        this.typ = typ;
        this.castka = castka;
        this.account = account;
        this.datum = LocalDateTime.now();
    }
}
