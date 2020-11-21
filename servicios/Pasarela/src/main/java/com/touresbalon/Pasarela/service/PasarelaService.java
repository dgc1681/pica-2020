package com.touresbalon.Pasarela.service;

import com.touresbalon.Pasarela.exception.CreditCardException;
import com.touresbalon.Pasarela.model.Pasarela;

public interface PasarelaService {
    void validate(final Pasarela card) throws CreditCardException;
}
