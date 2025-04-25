package Service;

import Utils.SqlConnection;
import models.Employee;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService implements EmployeeDAO
{
    /***
     * Takes employee number to check if it matches with one in the database.
     * On a match it will return the employee info, otherwise you get null
     * @param employee_NR
     * @return
     */
    @Override
    public Employee getEmployeeByEmployeeNR(String employee_NR)
    {
        Connection con = null;
        try
        {
            con = SqlConnection.getConnection();
            assert con != null;
            CallableStatement stmt = con.prepareCall("{Call Get_Employee_By_Employee_nr (?)}");
            stmt.setString(1, employee_NR);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getInt("Employee_id"));
                employee.setName(rs.getString("Name"));
                employee.setEmployee_NR(rs.getString("Employee_Nr"));
                employee.setPhone(rs.getString("Phone_Nr"));
                employee.setSaldo(rs.getFloat("Saldo"));
                employee.setUsername(rs.getString("Username"));
                employee.setEmail(rs.getString("Email"));
                return employee;
            }
            else
            {
                System.out.println("User not found");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}