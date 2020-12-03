package com.touresbalon.microservices.checkout.model;

public class Checkout {
    private String card;
    private String expiry_date;
    private Integer order;
    private Integer canasta;
    private Double precio_orden;

    public Checkout() {super();}
    public Checkout(String card, String expiry_date, Integer order, Integer canasta, Double precio_orden) {
        super();
        this.card = card;
        this.expiry_date = expiry_date;
        this.order = order;
        this.canasta = canasta;
        this.precio_orden = precio_orden;
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

    public Integer getCanasta() {
        return canasta;
    }

    public void setCanasta(Integer canasta) {
        this.canasta = canasta;
    }

    public Double getPrecio_orden() {
        return precio_orden;
    }

    public void setPrecio_orden(Double precio_orden) {
        this.precio_orden = precio_orden;
    }

    @Override
    public String toString() {
        return "Checkout [card=" + card + ", expiry_date=" + expiry_date +", order=" + order + "]";
    }
}
