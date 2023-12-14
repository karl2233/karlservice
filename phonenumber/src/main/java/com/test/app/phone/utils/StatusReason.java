package com.test.app.phone.utils;

import org.springframework.http.HttpStatus;

public class StatusReason {
    public boolean status;
    public String statusReason;

    private String countryName;
    private String phoneNumber;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String isStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }


}
