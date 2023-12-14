package com.test.app.customer.impl;

import com.test.app.customer.utils.DeleteCustomer;
import com.test.app.customer.utils.Employee;
import com.test.app.customer.utils.PhoneResp;
import com.test.app.customer.utils.StatusReason;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerProcImpl {
    public DeleteCustomer deleteEmployee(long employeeId);
    public List<Employee> getAllEmployees();

    public ResponseEntity<PhoneResp> updateEmployee(long employeeId, String phoneNumber);
    public ResponseEntity<PhoneResp> saveUser(Employee employee);
}
