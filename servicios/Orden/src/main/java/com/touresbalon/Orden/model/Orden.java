package com.touresbalon.Orden.model;

public class Orden {
    private String card;
    private String expiry_date;
    private Integer order;

    public Orden() {super();}
    public Orden(String card, String expiry_date, Integer order) {
        super();
        this.card = card;
        this.expiry_date = expiry_date;
        this.order = order;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Checkout [card=" + card + ", expiry_date=" + expiry_date +", order=" + order + "]";
    }
}
