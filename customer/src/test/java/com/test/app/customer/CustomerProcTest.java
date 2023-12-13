package com.test.app.customer;

import com.test.app.customer.impl.CustomerProc;
import com.test.app.customer.utils.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class CustomerProcTest {

    @Autowired
    public  CustomerProc customerProc ;
//
    @Test
   public void testSaveUserCorrectPhone(){
        Employee employee = new Employee("karl","samaha","+9613293860");
       Assertions.assertEquals(customerProc.saveUser(employee).isStatus(),true);
    }
//
    @Test
    public void testSaveUserIncorrectPhone(){
       Employee employee = new Employee("karl","samaha","+961");
        Assertions.assertEquals(customerProc.saveUser(employee).isStatus(),false);
       // assertNull(customerProc.saveUser(employee));
    }
//
    @Test
    public void testUpdateUserCorrectPhone(){
        Assertions.assertEquals(customerProc.updateEmployee(19,"+9613293860").isStatus(),true);
    }
//
    @Test
    public void testUpdateUserWrongPhone(){
        Assertions.assertEquals(customerProc.updateEmployee(1,"+961").isStatus(),false);
    }
//
    @Test
    public void testUpdateUserWrongId(){
        Assertions.assertEquals(customerProc.updateEmployee(100,"+9613293860").isStatus(),false);
    }
//
}
