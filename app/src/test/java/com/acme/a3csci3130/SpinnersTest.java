package com.acme.a3csci3130;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SpinnersTest {

    Spinners spinners = new Spinners();
    @Test
    public void getPrimaryBusiness() {
        String[] array = {"a", "b", "c"};
        assertArrayEquals(array, spinners.getPrimaryBusiness());
    }

    @Test
    public void getProvince() {
        String[] array = {"a", "b", "c"};
        assertArrayEquals(array, spinners.getProvince());
    }
}