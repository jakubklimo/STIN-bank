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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
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
