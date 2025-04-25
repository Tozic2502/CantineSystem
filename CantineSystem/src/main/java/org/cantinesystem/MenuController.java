package org.cantinesystem;

import models.Employee;

import java.math.BigDecimal;

public class MenuController
{
    private Employee employee;
    public void setEmployeeData(Employee employee)
    {
        this.employee = employee;
        // display or store the employee data
    }

    private void test()
    {
        int id = employee.getEmployee_id();
    }

}
