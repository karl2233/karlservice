package com.test.app.customer.utils;

public class PhoneResp {
    public String phoneNumber;
    public String countryName;

    public String statusString;

    public boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountryName() {
        return countryName;
    }
}
