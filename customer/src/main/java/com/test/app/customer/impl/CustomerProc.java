package com.test.app.customer.impl;

import com.test.app.customer.dao.EmployeeRepository;
import com.test.app.customer.utils.DeleteCustomer;
import com.test.app.customer.utils.Employee;
import com.test.app.customer.utils.PhoneResp;
import com.test.app.customer.utils.StatusReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class CustomerProc implements CustomerProcImpl {
    @Autowired
    private EmployeeRepository employeeRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public DeleteCustomer deleteEmployee(long employeeId) {
        DeleteCustomer deleteCustomer = new DeleteCustomer();
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + employeeId));
            employeeRepository.delete(employee);
            deleteCustomer.setDelteStatus(true);
            deleteCustomer.setDeleteReason("Employee  found  and deleted for this id :: " + employeeId);

            return deleteCustomer;
        } catch (RuntimeException r) {
            deleteCustomer.setDelteStatus(false);
            deleteCustomer.setDeleteReason("Employee not found for this id");
            return deleteCustomer;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseEntity<PhoneResp> updateEmployee(long employeeId, String phoneNumber) {
        StatusReason status = new StatusReason();
        PhoneResp response = new PhoneResp();
        try {
            status = restTemplate.getForObject("http://localhost:8086/api/v1/phone/employees?number={number}", StatusReason.class, Map.of("number", phoneNumber));

        if(status.isStatus()){
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + employeeId));
            employee.setPhoneNumber(phoneNumber);
            employeeRepository.save(employee);
            response.setStatus(true);
            response.setCountryName(status.getCountryName());
            response.setPhoneNumber(status.getPhoneNumber());
            response.setStatusString(status.getStatusReason());
            return new ResponseEntity<PhoneResp>(response,HttpStatus.OK);


        }else{ response.setStatus(false);
            response.setStatusString(status.getStatusReason());
            return new ResponseEntity<PhoneResp>(response,HttpStatus.NOT_FOUND);
        }

            } catch (RuntimeException e) {
            response.setStatus(false);
            response.setStatusString("Employee not found for this id");
            return new ResponseEntity<PhoneResp>(response,HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            response.setStatus(false);
            response.setStatusString("Something went wrong");
            return new ResponseEntity<PhoneResp>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @Override
    public ResponseEntity<PhoneResp> saveUser(Employee employee) {
        StatusReason status = new StatusReason();
        PhoneResp response = new PhoneResp();
       try {
           status = restTemplate.getForObject("http://localhost:8086/api/v1/phone/employees?number={number}", StatusReason.class, employee.getPhoneNumber());
          if(status.isStatus()){
              employeeRepository.save(employee);
              response.setCountryName(status.getCountryName());
              response.setPhoneNumber(status.getPhoneNumber());
              response.setStatusString(status.getStatusReason());
              response.setStatus(true);
              return new ResponseEntity<PhoneResp>(response,HttpStatus.OK);
          }else{
              response.setStatus(false);
              response.setStatusString(status.getStatusReason());
              return new ResponseEntity<PhoneResp>(response,HttpStatus.NOT_FOUND);
          }


       }
       catch (Exception e) {
           response.setStatus(false);
           response.setStatusString("Something went wrong");
           return new ResponseEntity<PhoneResp>(response,HttpStatus.INTERNAL_SERVER_ERROR);
       }


    }
}
