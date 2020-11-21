package com.touresbalon.Checkout.rest;

import com.touresbalon.Checkout.service.BasicCheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/touresbalon/checkout")
public class CheckoutREST {

    public static final Logger LOG = LoggerFactory.getLogger(CheckoutREST.class);

    @Autowired
    @Qualifier("basicCheckoutService")
    private BasicCheckoutService checkoutService;
}