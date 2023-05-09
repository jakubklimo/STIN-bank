package cz.tul.klimo.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String heslo;

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
}
