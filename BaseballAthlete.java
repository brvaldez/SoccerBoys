public class BaseballAthlete implements Athlete, Component {
    private String name;
    private String academicStatus;
    private int attendanceRecord;

    public BaseballAthlete(String name, String academicStatus, int attendanceRecord) {
        this.name = name;
        this.academicStatus = academicStatus;
        this.attendanceRecord = attendanceRecord;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAcademicStatus() {
        return academicStatus;
    }

    @Override
    public int getAttendanceRecord() {
        return attendanceRecord;
    }

    @Override
    public void displayDetails() {
        System.out.println("Baseball Athlete - Name: " + name + ", Academic Status: " + academicStatus + ", Attendance Record: " + attendanceRecord);
    }
}
