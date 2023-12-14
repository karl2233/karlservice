package com.test.app.phone.impl;


import com.test.app.phone.utils.StatusReason;
import com.twilio.Twilio;
import com.twilio.rest.lookups.v2.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

@Component
public class numberProc implements numberProcImpl {
    @Value( "${twilo.account_sid}" )
    public String ACCOUNT_SID;
    @Value( "${twilo.auth_token}" )
    public String AUTH_TOKEN;

    @Override
    public StatusReason getphoneDetails(String number) {
        StatusReason stat = new StatusReason();
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            PhoneNumber phoneNumber = PhoneNumber.fetcher(number).fetch();
            System.out.println(phoneNumber.getPhoneNumber());
            if (phoneNumber.getCountryCode() == null) {
                stat.setStatus(false);
                stat.setStatusReason("wrong phone number");
                return stat;
            }

            stat.setStatus(true);
            stat.setStatusReason("correct phone number");
            stat.setPhoneNumber(String.valueOf(phoneNumber.getPhoneNumber()));
            stat.setCountryName(phoneNumber.getCountryCode());
            return stat;
        } catch (RuntimeException e) {
            stat.setStatus(false);
            stat.setStatusReason("Something went wrong");
            return stat;
        }
    }
}
