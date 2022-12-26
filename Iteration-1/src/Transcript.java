import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transcript {
    private double cumulativeGpa;
    private double gpa;
    private int creditCompleted;
    private int creditTaken;
    private HashMap<Course, String> takenCouerses;
    private ArrayList<Course> completedCourses;
    private ArrayList<Course> failedCourses;
    private ArrayList<Course> enrolledCourses;

    public Transcript() {
        this.cumulativeGpa = 0;
        this.gpa = 0;
        this.creditCompleted = 0;
        this.creditTaken = 0;
        this.failedCourses = new ArrayList<Course>();
        this.completedCourses = new ArrayList<Course>();
        this.takenCouerses = new HashMap<Course, String>();
        this.enrolledCourses = new ArrayList<Course>();

    }

    public Transcript(double gpa, int creditCompleted, int creditTaken, HashMap<Course, String> takenCouerses,

            ArrayList<Course> completedCourses, ArrayList<Course> failedCourses) {
        this.gpa = gpa;
        this.creditCompleted = creditCompleted;
        this.creditTaken = creditTaken;
        this.takenCouerses = takenCouerses;
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
    }

    // https://www.marmara.edu.tr/dosya/www/mevzuat/2021/mu_yonerge_basari_degerlendirme_2020_v204.02.2021.pdf?_t=1612473513
    //https://catalog.arizona.edu/policy/grade-point-average-gpa-calculation-or-averaging-grades
    public double calculateGpa() {
        double gradeMultiplication = 0;
        double totalCredit = 0;
        double studentGpa = 0;

        for (Map.Entry<Course, String> set : getTakenCouerses().entrySet()) {
            totalCredit += set.getKey().getCourseCredit();
            if (set.getValue() == "AA") {
                gradeMultiplication += set.getKey().getCourseCredit() * 4.0;
            } else if (set.getValue() == "BA") {
                gradeMultiplication += set.getKey().getCourseCredit() * 3.5;
            } else if (set.getValue() == "BB") {
                gradeMultiplication += set.getKey().getCourseCredit() * 3.0;
            } else if (set.getValue() == "CB") {
                gradeMultiplication += set.getKey().getCourseCredit() * 2.5;
            } else if (set.getValue() == "CC") {
                gradeMultiplication += set.getKey().getCourseCredit() * 2.0;
            } else if (set.getValue() == "DC") {
                gradeMultiplication += set.getKey().getCourseCredit() * 1.5;
            } else if (set.getValue() == "DD") {
                gradeMultiplication += set.getKey().getCourseCredit() * 1.0;
            } else if (set.getValue() == "FD") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.5;
            } else if (set.getValue() == "FF") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            } else if (set.getValue() == "FG") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            } else if (set.getValue() == "DZ") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            }
        }
        studentGpa = gradeMultiplication / totalCredit;

        setGpa(studentGpa);
        return studentGpa;
    }

    /* public int calculateCompleteCredit() {
        int completedCredit = 0;
        for (Map.Entry<Course, String> set : getTakenCouerses().entrySet()) {
            if (set.getValue() == "AA" || set.getValue() == "BA" || set.getValue() == "BB" || set.getValue() == "CB"
                    || set.getValue() == "CC" || set.getValue() == "DC" || set.getValue() == "DD") {
                completedCredit += set.getKey().getCourseCredit();
            }
        }
        setCreditCompleted(completedCredit);
        return completedCredit;
    } */
    public int calculateCompleteCredit() {
        int completedCredit = 0;
        for (Course c : getCompletedCourses()) {
            completedCredit += c.getCourseCredit();
        }
        setCreditCompleted(completedCredit);
        return completedCredit;
    }

    public int calculateTakenCredit() {
        int takenCredit = 0;
        for (Map.Entry<Course, String> set : getTakenCouerses().entrySet()) {
            takenCredit += set.getKey().getCourseCredit();
        }
        setCreditTaken(takenCredit);
        return takenCredit;
    }

    /*  public void isCourseCompletedOrFailed() {
        for (Map.Entry<Course, String> set : getTakenCouerses().entrySet()) {
            if (set.getValue() == "AA" || set.getValue() == "BA" || set.getValue() == "BB" || set.getValue() == "CB"
                    || set.getValue() == "CC" || set.getValue() == "DC" || set.getValue() == "DD") {
                completedCourses.add(set.getKey());
            } else {
                failedCourses.add(set.getKey());
            }
        }
    } */
    public void isCourseCompletedOrFailed(Course course, String letter) {
        if (letter == "AA" || letter == "BA" || letter == "BB" || letter == "CB"
                || letter == "CC" || letter == "DC" || letter == "DD") {
            completedCourses.add(course);
        } else {
            failedCourses.add(course);
        }
    }

    public boolean hasBeenPassedCourse(Course course) {
        if (course == null) {
            return true;
        }
        return getCompletedCourses().contains(course);
    }

    public boolean hasBeenPassedCourses(ArrayList<Course> courses) {
        for (Course course : courses) {
            if (!hasBeenPassedCourse(course)) {
                return false;
            }
        }
        return true;
    }

    // GETTER & SETTER
    public double getCumulativeGpa() {
        return cumulativeGpa;
    }

    public void setCumulativeGpa(double cumulativeGpa) {
        this.cumulativeGpa = cumulativeGpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCreditCompleted() {
        return creditCompleted;
    }

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    public int getCreditTaken() {
        return creditTaken;
    }

    public void setCreditTaken(int creditTaken) {
        this.creditTaken = creditTaken;
    }

    public HashMap<Course, String> getTakenCouerses() {
        return takenCouerses;
    }

    public void setTakenCouerses(HashMap<Course, String> takenCouerses) {
        this.takenCouerses = takenCouerses;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<Course> failedCourses) {
        this.failedCourses = failedCourses;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
