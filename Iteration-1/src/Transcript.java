import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transcript {
    private double cumulativeGpa;
    private double gpa;
    private int creditCompleted;
    private int creditTaken;
    private HashMap<Course, String> completedCourses;
    private HashMap<Course, String> failedCourses;
    private ArrayList<Course> takenCoueses;

    public Transcript() {
    }

    public Transcript(double cumulativeGpa, double gpa, int creditCompleted, int creditTaken,
            HashMap<Course, String> completedCourses,
            HashMap<Course, String> failedCourses, ArrayList<Course> takenCoueses) {
        this.cumulativeGpa = cumulativeGpa;
        this.gpa = gpa;
        this.creditCompleted = creditCompleted;
        this.creditTaken = creditTaken;
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        this.takenCoueses = takenCoueses;
    }

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

    public HashMap<Course, String> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(HashMap<Course, String> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public HashMap<Course, String> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(HashMap<Course, String> failedCourses) {
        this.failedCourses = failedCourses;
    }

    public ArrayList<Course> getTakenCoueses() {
        return takenCoueses;
    }

    public void setTakenCoueses(ArrayList<Course> takenCoueses) {
        this.takenCoueses = takenCoueses;
    }

    // https://www.marmara.edu.tr/dosya/www/mevzuat/2021/mu_yonerge_basari_degerlendirme_2020_v204.02.2021.pdf?_t=1612473513
    //https://catalog.arizona.edu/policy/grade-point-average-gpa-calculation-or-averaging-grades

    public double calculateGpa() {
        double gradeMultiplication = 0;
        double totalCredit = 0;
        double studentGPA = 0;

        for (Map.Entry<Course, String> set : getCompletedCourses().entrySet()) {
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

        studentGPA = gradeMultiplication / totalCredit;
        setGpa(studentGPA);

        return studentGPA;

    }

    public int calculateComplatedCredit() {
        int complatedCredit = 0;
        for (Map.Entry<Course, String> set : getCompletedCourses().entrySet()) {
            if (set.getValue() == "AA" || set.getValue() == "BA" || set.getValue() == "BB" || set.getValue() == "CB"
                    || set.getValue() == "CC" || set.getValue() == "DC" || set.getValue() == "DD") {
                complatedCredit = set.getKey().getCourseCredit();
            }
        }

        setCreditCompleted(complatedCredit);
        return complatedCredit;
    }

    public int calculateTakeCredit() {
        int takenCredit = 0;
        for (Map.Entry<Course, String> set : getCompletedCourses().entrySet()) {
            takenCredit += set.getKey().getCourseCredit();
        }
        setCreditTaken(takenCredit);
        return takenCredit;
    }

}
