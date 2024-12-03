// Factory class to create specific athlete objects
public class AthleteFactory {
    public static Athlete createAthlete(String type, String name, String academicStatus, int attendanceRecord) {
        switch (type.toLowerCase()) {
            case "soccer":
                return new SoccerAthlete(name, academicStatus, attendanceRecord);
            case "basketball":
                return new BasketballAthlete(name, academicStatus, attendanceRecord);
            case "baseball":
                return new BaseballAthlete(name, academicStatus, attendanceRecord);
            default:
                throw new IllegalArgumentException("Invalid athlete type");
        }
    }
}

