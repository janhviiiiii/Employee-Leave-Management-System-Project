package miniprojectpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manager {
    public void approveLeave(LeaveRequest leaveRequest, Connection conn)
    {
        try
        {
            String updateLeaveRequestQuery = "UPDATE leaveRequests SET is_approved ='Y' WHERE employee_id = ? AND leave_type = ? AND days = ?";
            try(PreparedStatement ps = conn.prepareStatement(updateLeaveRequestQuery))
            {
                ps.setInt(1, leaveRequest.getDays());//assuming days are unique for each request in this scenario
                ps.setString(2, leaveRequest.getLeaveType());
                ps.setInt(3, leaveRequest.getDays());   
                ps.executeUpdate();
            }
            leaveRequest.approve();
            System.out.println("Leave request for " + leaveRequest.getEmployeeName() + " approved.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void rejectLeave(LeaveRequest leaveRequest)
    {
        System.out.println("Leave reuqest for " + leaveRequest.getEmployeeName() + " rejected.");
    }
}