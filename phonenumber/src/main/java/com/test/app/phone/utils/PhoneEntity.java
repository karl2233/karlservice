package com.test.app.phone.utils;

public class PhoneEntity {

    public String getCountryName() {
        return countryName;
    }



    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    private String countryName;
    private String phoneNumber;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
