package br.com.bicmsystems.payments.enums;

import lombok.*;

@ToString
public enum NegotiationType {

    FREE_AMOUNT,
    ENCUMBRANCE,
    ASSIGNMENT;

//    public NegotiationType getByName(String name) {
//        return NegotiationType.valueOf(name);
//    }

}
