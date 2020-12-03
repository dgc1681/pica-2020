package com.touresbalon.microservices.checkout.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.touresbalon.microservices.checkout.exception.CustomException;
import com.touresbalon.microservices.checkout.model.Checkout;
import com.touresbalon.microservices.checkout.model.CheckoutDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service(value="BasicCheckout")
public class BasicCheckoutService implements CheckoutService{
    private final Logger logger = LoggerFactory.getLogger(BasicCheckoutService.class);

    private static final String TOPIC = "orden";
    public static final String CHECKOUT = "BasicCheckout";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    ObjectMapper mapperObj = new ObjectMapper();

    @Override
    @KafkaListener(topics = "checkout", groupId = "group_id")
    public void consume(final String orden) throws IOException {
        logger.info(String.format("Consuming -> %s", orden));

        JsonNode objTransaccion = mapperObj.readTree(orden);
        //Integer id_orden = objTransaccion.get("id_orden").asInt();
        //Integer id_canasta = objTransaccion.get("id_canasta").asInt();
        //Integer precio_orden = objTransaccion.get("precio_orden").asInt();

        Integer id_orden = objTransaccion.get("card").asInt();
        Integer id_canasta = objTransaccion.get("expiry_date").asInt();
        Double precio_orden = objTransaccion.get("order").asDouble();
        String cardNumber = "5243678934521324";
        String expiryDate = "04/21";

        logger.info(String.format("Orden -> %s", id_orden));
        logger.info(String.format("Canasta -> %s", id_canasta));
        logger.info(String.format("Precio -> %s", precio_orden));
        logger.info(String.format("Tarjeta -> %s", cardNumber));
        logger.info(String.format("Vencimiento -> %s", expiryDate));

        Checkout checkout = new Checkout(cardNumber, expiryDate, id_orden, id_canasta, precio_orden);
        CheckoutDTO sendCheckout = new CheckoutDTO(checkout.getCard(), checkout.getExpiry_date(), checkout.getOrder(), checkout.getCanasta(), checkout.getPrecio_orden());

        this.validatePayment(sendCheckout);
    }

    @Override
    public void produce(CheckoutDTO checkout) {
        logger.info(String.format("Producing -> %s", checkout));
        this.kafkaTemplate.send(TOPIC, checkout);
    }

    @Override
    @CircuitBreaker(name = CHECKOUT)
    @Bulkhead(name = CHECKOUT)
    @Retry(name = CHECKOUT)
    public String validatePayment(CheckoutDTO checkout) {
        final String uri = "http://localhost:8187/api/touresbalon/pasarela/validating/"+checkout.getCard()+"?expiry_date="+checkout.getExpiry_date();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        System.out.println(jsonObject.get("message"));
        return "Response HTTP 201 CREATED, order was create successfully";
    }

    @Override
    @CircuitBreaker(name = CHECKOUT)
    @Bulkhead(name = CHECKOUT)
    @Retry(name = CHECKOUT)
    public String failure() {
        System.out.println("ERRORRRRRRR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception for orders service");
    }

    @Override
    @CircuitBreaker(name = CHECKOUT, fallbackMethod = "fallback")
    public String failureWithFallback() {
        return failure();
    }

    @Override
    @CircuitBreaker(name = CHECKOUT)
    @Bulkhead(name = CHECKOUT)
    @Retry(name = CHECKOUT)
    public String successException() {
        System.out.println("1ERRORRRRRRR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        throw new CustomException("This is a remote exception for orders service, but is welcome");
    }

    @Override
    public String timeOut() {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2ERRORRRRRRR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "Response 200 with slow remote call or method execution";
    }

    private String fallback(HttpServerErrorException ex) {
        System.out.println("3ERRORRRRRRR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "Response 200, fallback method for error:  " + ex.getMessage();
    }
}
