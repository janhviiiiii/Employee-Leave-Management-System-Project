package miniprojectpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
    private String name;
    private int leaveBalance;
    private int id;

    public Employee(int id, String name, int leaveBalance)
    {
        this.id=id;
        this.name=name;
        this.leaveBalance=leaveBalance;
    }

    public String getName()
    {
        return name;
    }

    public int getLeaveBalance()
    {
        return leaveBalance;
    }

    public int getId()
    {
        return id;
    }

    public boolean applyForLeave(String leaveType, int days, Connection conn)
    {
        if(days <= leaveBalance)
        {
            leaveBalance -= days;

            try
            {
                //update leave Balance in database
                String updateBalanceQuery = "UPDATE Employees SET leave_balance = ? WHERE employee_id = ?";
                try(PreparedStatement ps = conn.prepareStatement(updateBalanceQuery))
                {
                    ps.setInt(1, leaveBalance);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }

                //insert leave request into database
                String insertLeaveQuery = "INSERT INTO LeaveRequests (employee_id, leave_type, days) VALUES (?, ?, ?)";
                try(PreparedStatement ps = conn.prepareStatement(insertLeaveQuery))
                {
                    ps.setInt(1, id);
                    ps.setString(2, leaveType);
                    ps.setInt(3, days);
                    ps.executeUpdate();
                }
                return true;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Insufficient leave balance.");
            return false;
        }
        return false;
    }

    public int getLeaveRequestsCount(Connection conn)
    {
        int count=0;
        try
        {
            String countRequestsQuery = "SELECT COUNT(*) FROM LeaveRequests WHERE employee_id = ?";
            try(PreparedStatement ps = conn.prepareStatement(countRequestsQuery))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    count=rs.getInt(1);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

}
