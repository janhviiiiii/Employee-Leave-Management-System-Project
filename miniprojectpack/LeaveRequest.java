package miniprojectpack;


public class LeaveRequest {
    private String employeeName;
    private String leaveType;
    private int days;
    private boolean isApproved;

    public LeaveRequest(String employeeName, String leaveType, int days)
    {
        this.employeeName=employeeName;
        this.leaveType=leaveType;
        this.days=days;
        this.isApproved=false; //initially false
    }

    //getter and setter methods
    public String getEmployeeName()
    {
        return employeeName;
    }

    public String getLeaveType()
    {
        return leaveType;
    }

    public int getDays()
    {
        return days;
    }

    public boolean isApproved()
    {
        return isApproved;
    }

    public void approve()
    {
        this.isApproved=true;
    }

    public String toString()
    {
        return "LeaveRequest{" +
                "employeeName='" + employeeName + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", days=" + days +
                ", isApproved=" + isApproved +
                '}';
    }
    
}
