package com.touresbalon.microservices.checkout.service;

import com.touresbalon.microservices.checkout.model.CheckoutDTO;

import java.io.IOException;

public interface CheckoutService {

    void consume(final String orden) throws IOException;
    void produce(final CheckoutDTO checkout);
    String validatePayment(final CheckoutDTO checkout);
    String failure();
    String failureWithFallback();
    String successException();
    String timeOut();

}
