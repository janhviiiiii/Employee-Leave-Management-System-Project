package miniprojectpack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaveManagement {
    private List<Employee> employees;
    private Connection conn;

    public LeaveManagement(Connection conn)
    {
        this.conn=conn;
        employees = new ArrayList<>();
        loadEmployees();
    }

    public void loadEmployees()
    {
        try
        {
            String query = "SELECT employee_id, name, leave_balance FROM Employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                int id = rs.getInt("employee_id");
                String name = rs.getString("name");
                int leaveBalance= rs.getInt("leave_balance");
                employees.add(new Employee(id, name, leaveBalance));                
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void applyLeave(String employeeName, String leaveType, int days)
    {
        for(Employee employee : employees)
        {
            if(employee.getName().equalsIgnoreCase(employeeName))
            {
                if(employee.applyForLeave(leaveType, days, conn))
                {
                    System.out.println("Leave request submitted.");
                }
                return;
            }
        }
        System.out.println("Employee not found.");
    }
    
    public void viewLeaveRequests()
    {
        try
        {
            String query = "SELECT e.name, lr.leave_type, lr.days, lr.is_approved FROM LeaveRequests lr JOIN Employees e ON lr.employee_id = e.employee_id = e.employee_id";
            Statement stml = conn.createStatement();
            ResultSet rs = stml.executeQuery(query);
            while(rs.next())
            {
                String empName = rs.getString("name");
                String leaveType = rs.getString("leave_type");
                int days = rs.getInt("days");
                String isApproved = rs.getString("is_approved");
                System.out.println("Employee: " + empName + ", Leave Type: " + leaveType + ", Days: " + days +  ", Approved: " + isApproved);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
