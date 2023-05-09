package cz.tul.klimo.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String heslo;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public User(String jmeno, String prijmeni, String email, String heslo){
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.heslo = heslo;
    }

    public User(){}

    public int getKlientNum(){
        return id;
    }

    public String getHeslo(){
        return heslo;
    }

    public String getJmeno(){return jmeno;}

    public String getEmail(){return email;}

    public void setAccount(Account account){
        this.accounts.add(account);
    }

    public List getAccounts(){
        return accounts;
    }
}
