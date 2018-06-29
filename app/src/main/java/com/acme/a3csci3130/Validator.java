package com.acme.a3csci3130;

import java.util.regex.Pattern;

/**
 * Class for validating input contact infomation
 *
 * @value name the name value in the Name Edit edit view
 * @value number the number value in the Bussines Number Edit edit View
 * @value address the address value in the Address edit view
 * @return boolean value of validating name, number and address
 */

public class Validator {

    public Validator() {}

    public boolean nameValidate(String name) {
        if(name.trim().length() >= 2 && name.trim().length() <= 48) {
            return true;
        } else {
            return false;
        }
    }

    public boolean emailValidate(String email) {
        Pattern emailCheck = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);

        if(emailCheck.matcher(email).find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean businessNumberValidate(String number) {
        Pattern digits = Pattern.compile("[0-9]");

        if(digits.matcher(number).find() && number.trim().length()==9) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addressValidate(String address) {
        if(address.trim().length() != 0 && address.trim().length() < 50) {
            return true;
        } else {
            return false;
        }
    }

}
