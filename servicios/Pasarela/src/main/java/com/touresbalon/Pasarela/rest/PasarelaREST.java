package com.touresbalon.Pasarela.rest;

import com.touresbalon.Pasarela.exception.CreditCardException;
import com.touresbalon.Pasarela.model.Pasarela;
import com.touresbalon.Pasarela.model.PasarelaResponseDTO;
import com.touresbalon.Pasarela.service.BasicPasarelaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/touresbalon/pasarela")
public class PasarelaREST {

    public static final Logger LOG = LoggerFactory.getLogger(PasarelaREST.class);

    @Autowired
    @Qualifier("basicPasarelaService")
    private BasicPasarelaService pasarelaService;

    @RequestMapping(value ="/validating/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> validating(@PathVariable("number") final String number,
                                        @RequestParam(value = "expiry_date", required = true) final String expiryDate) {
        LOG.info("Validating Credit Card, number: {}", number);

        Pasarela pasarela = new Pasarela(number, expiryDate);

        try {
            this.pasarelaService.validate(pasarela);
        } catch(CreditCardException e) {
            return new ResponseEntity<PasarelaResponseDTO>(new PasarelaResponseDTO(pasarela.getNumber(), false, e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PasarelaResponseDTO>(new PasarelaResponseDTO(pasarela.getNumber(), true, "OK"),
                HttpStatus.OK);
    }
}
