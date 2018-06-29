package com.acme.a3csci3130;

/**
 * Class that set up the data of Primary business and Province
 *
 * @value primaryBusiness the array data of Priamry Business
 * @value Province the array data of avaliable provinces
 * @return the value of array primaryBusiness and Province
 */
public class Spinners {

    public String primaryBusiness[] = {"Fisher", "Distributor", "Processor", "Fish Monger"};
    public String Province[] = {"AB","BC","MB","NB","NL","NS","NT","NU","ON","PE","QC","SK","YT"," "};

    public Spinners () {}

    public String[] getPrimaryBusiness() {
        return primaryBusiness;
    }

    public String[] getProvince() {
        return Province;
    }
}
