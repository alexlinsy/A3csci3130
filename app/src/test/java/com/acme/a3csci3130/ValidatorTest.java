package com.acme.a3csci3130;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ValidatorTest {

    Validator validator = new Validator();
    @Test
    public void nameValidate() {
        String name = "Ian";
        assertEquals(true, validator.nameValidate(name));
    }

    @Test
    public void businessNumberValidate() {
        String number = "902789123";
        assertEquals(true, validator.businessNumberValidate(number));
    }

    @Test
    public void addressValidate() {
        String address = "666 Sunset Blvd";
        assertEquals(true, validator.addressValidate(address));
    }

    @Test
    public void emailValidate() {
        String email = "Test@gmail.com";
        assertEquals(true, validator.emailValidate(email));
    }
}