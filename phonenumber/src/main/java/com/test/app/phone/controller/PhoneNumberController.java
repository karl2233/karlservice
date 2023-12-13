package com.test.app.phone.controller;


import com.test.app.phone.impl.numberProc;
import com.test.app.phone.utils.PhoneEntity;
import com.test.app.phone.utils.StatusReason;
import com.twilio.Twilio;
import com.twilio.rest.lookups.v2.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/phone")
public class PhoneNumberController {

@Autowired
numberProc numerberProc;


    @GetMapping("/employees")
    @ResponseBody
    public ResponseEntity<Object> getAllEmployees(@RequestParam String number) {
        StatusReason status= numerberProc.getphoneDetails(number);
        return new ResponseEntity<Object>(status, HttpStatus.OK);
    }
}

