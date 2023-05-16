package cz.tul.klimo.bank.entity;

public class Currency {
    private double kurz;
    private String code;
    private int mnozstvi;

    public Currency(){

    }

    public Currency(String code, int mnozstvi, double kurz){
        this.code = code;
        this.mnozstvi= mnozstvi;
        this.kurz = kurz;
    }

    public double getKurz() {
        return kurz;
    }

    public void setKurz(double kurz) {
        this.kurz = kurz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }
}
