package com.touresbalon.Checkout.model;

public class CheckoutResponseDTO {
    private String card;
    private String expiry_date;
    private Integer order;

    public CheckoutResponseDTO() {super();}
    public CheckoutResponseDTO(String card, String expiry_date, Integer order) {
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
}