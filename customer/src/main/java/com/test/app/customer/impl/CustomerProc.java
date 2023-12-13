package com.test.app.customer.impl;

import com.test.app.customer.dao.EmployeeRepository;
import com.test.app.customer.utils.DeleteCustomer;
import com.test.app.customer.utils.Employee;
import com.test.app.customer.utils.StatusReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public StatusReason updateEmployee(long employeeId, String phoneNumber) {
        StatusReason status = new StatusReason();
        try {
            status = restTemplate.getForObject("http://localhost:8086/api/v1/phone/employees?number={number}", StatusReason.class, Map.of("number", phoneNumber));

        if(status.isStatus()){
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + employeeId));

            employee.setPhoneNumber(phoneNumber);

            employeeRepository.save(employee);
        }
                return status;
            } catch (RuntimeException e) {
            status.setStatusReason("Employee not found for this id");
            status.setStatus(false);
            status.setHttpStatus(HttpStatus.NOT_FOUND);
            return status;
        }
        catch (Exception e) {
            status.setStatusReason(e.toString());
            status.setStatus(false);
            status.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return status;
        }


    }


    @Override
    public StatusReason saveUser(Employee employee) {
        StatusReason status = new StatusReason();
       try {
           status = restTemplate.getForObject("http://localhost:8086/api/v1/phone/employees?number={number}", StatusReason.class, employee.getPhoneNumber());
          if(status.isStatus()){
              employeeRepository.save(employee);
          }
           return status;

       }
       catch (Exception e) {
           status.setStatusReason(e.toString());
           status.setStatus(false);
           status.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
           return status;
       }


    }
}
