package cz.tul.klimo.bank.entity;

public class User {
    private int klientNum;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String heslo;

    public User(int klientNum, String jmeno, String prijmeni, String email, String heslo){
        this.klientNum = klientNum;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.heslo = heslo;
    }

    public User(){}

    public int getKlientNum(){
        return klientNum;
    }

    public String getHeslo(){
        return heslo;
    }

    public String getJmeno(){return jmeno;}
}
