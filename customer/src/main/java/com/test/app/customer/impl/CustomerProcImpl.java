package com.test.app.customer.impl;

import com.test.app.customer.utils.DeleteCustomer;
import com.test.app.customer.utils.Employee;
import com.test.app.customer.utils.StatusReason;

import java.util.List;

public interface CustomerProcImpl {
    public DeleteCustomer deleteEmployee(long employeeId);
    public List<Employee> getAllEmployees();

    public StatusReason updateEmployee(long employeeId,String phoneNumber);
    public StatusReason saveUser(Employee employee);
}
