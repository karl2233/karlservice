package com.test.app.customer.controller;
import com.test.app.customer.impl.CustomerProc;
import com.test.app.customer.utils.DeleteCustomer;
import com.test.app.customer.utils.Employee;
import com.test.app.customer.utils.PhoneNumber;
import com.test.app.customer.utils.StatusReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerProc customerProc;

    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        StatusReason status = customerProc.saveUser(employee);
                 return new ResponseEntity<Object>(status,status.getHttpStatus());
    }

    @PostMapping("/employees/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody PhoneNumber phoneNumber) {
        StatusReason status = customerProc.updateEmployee(phoneNumber.getId(),phoneNumber.getPhoneNumber());
           return new ResponseEntity<Object>(status, status.getHttpStatus());
    }
    @DeleteMapping("/employees/delete")
    public ResponseEntity<Object> deleteEmployee(@RequestParam long employeeId)
    {  DeleteCustomer delete =customerProc.deleteEmployee(employeeId);
        if(delete.isDelteStatus()){
            return new ResponseEntity<Object>(delete, HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(delete, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return customerProc.getAllEmployees();
    }
}


