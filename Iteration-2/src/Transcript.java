import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class Transcript {
    private Logger log = Logger.getLogger(Transcript.class);
    private double gpa;
    private int creditCompleted;
    private HashMap<Course, Grade> takenCourses;
    private ArrayList<Course> completedCourses;
    private ArrayList<Course> failedCourses;
    private ArrayList<Course> enrolledCourses;

    public Transcript() {
        this.gpa = 0;
        this.creditCompleted = 0;
        this.failedCourses = new ArrayList<Course>();
        this.completedCourses = new ArrayList<Course>();
        this.takenCourses = new LinkedHashMap<Course, Grade>();
        this.enrolledCourses = new ArrayList<Course>();
        log.info("Transcript created.");

    }

    public Transcript(double gpa, int creditCompleted, int creditTaken, HashMap<Course, Grade> takenCourses,

            ArrayList<Course> completedCourses, ArrayList<Course> failedCourses) {
        this.gpa = gpa;
        this.creditCompleted = creditCompleted;
        this.takenCourses = takenCourses;
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        log.info("Transcript created.");
    }

    // https://www.marmara.edu.tr/dosya/www/mevzuat/2021/mu_yonerge_basari_degerlendirme_2020_v204.02.2021.pdf?_t=1612473513
    //https://catalog.arizona.edu/policy/grade-point-average-gpa-calculation-or-averaging-grades
    public double calculateGpa() {
        double gradeMultiplication = 0;
        double totalCredit = 0;
        double studentGpa = 0;

        for (Map.Entry<Course, Grade> set : gettakenCourses().entrySet()) {
            totalCredit += set.getKey().getCourseCredit();
            if (set.getValue().getLetter() == "AA") {
                gradeMultiplication += set.getKey().getCourseCredit() * 4.0;
            } else if (set.getValue().getLetter() == "BA") {
                gradeMultiplication += set.getKey().getCourseCredit() * 3.5;
            } else if (set.getValue().getLetter() == "BB") {
                gradeMultiplication += set.getKey().getCourseCredit() * 3.0;
            } else if (set.getValue().getLetter() == "CB") {
                gradeMultiplication += set.getKey().getCourseCredit() * 2.5;
            } else if (set.getValue().getLetter() == "CC") {
                gradeMultiplication += set.getKey().getCourseCredit() * 2.0;
            } else if (set.getValue().getLetter() == "DC") {
                gradeMultiplication += set.getKey().getCourseCredit() * 1.5;
            } else if (set.getValue().getLetter() == "DD") {
                gradeMultiplication += set.getKey().getCourseCredit() * 1.0;
            } else if (set.getValue().getLetter() == "FD") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.5;
            } else if (set.getValue().getLetter() == "FF") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            } else if (set.getValue().getLetter() == "FG") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            } else if (set.getValue().getLetter() == "DZ") {
                gradeMultiplication += set.getKey().getCourseCredit() * 0.0;
            }
        }
        studentGpa = Math.round(gradeMultiplication / totalCredit * 100.0) / 100.0;
        setGpa(studentGpa);
        /* log.info("Gpa calculated with previously taken courses."); */
        return studentGpa;
    }

    public int calculateCompleteCredit() {
        int completedCredit = 0;
        for (Course c : getCompletedCourses()) {
            completedCredit += c.getCourseCredit();
        }
        setCreditCompleted(completedCredit);
        /* log.info("Credit calculated with completed courses."); */
        return completedCredit;
    }

    public void isCourseCompletedOrFailed(Course course, String letter) {
        if (letter == "AA" || letter == "BA" || letter == "BB" || letter == "CB"
                || letter == "CC" || letter == "DC" || letter == "DD") {
            completedCourses.add(course);
            /* log.info(course.getCourseName() + ": Course grade is " + letter
                    + " therefore this course is successfully passed."); */
        } else {
            /* log.info(course.getCourseName() + ": Course grade is " + letter
                    + " therefore this course is failed."); */
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
                /* log.info("Given prerequisite course(s) is not passed"); */
                return false;
            }
        }
        /* log.info("Given prerequisite course(s) is passed"); */
        return true;
    }

    // GETTER & SETTER
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        /* log.info("Gpa changed."); */
    }

    public int getCreditCompleted() {
        return creditCompleted;
    }

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
        /* log.info("Completed credits changed."); */
    }

    public HashMap<Course, Grade> gettakenCourses() {
        return takenCourses;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }
}