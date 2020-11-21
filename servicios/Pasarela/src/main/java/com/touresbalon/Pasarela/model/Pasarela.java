package com.touresbalon.Pasarela.model;

public class Pasarela {
    private String number;
    private String expiration;

    public Pasarela() {
        super();
    }

    public Pasarela(String number, String expiration) {
        super();
        this.number = number;
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getExpiration() {
        return expiration;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
