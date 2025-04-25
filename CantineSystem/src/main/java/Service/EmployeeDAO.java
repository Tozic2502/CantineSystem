package Service;

import models.Employee;

public interface EmployeeDAO
{
    Employee getEmployeeByEmployeeNR(String employee_NR);
}
