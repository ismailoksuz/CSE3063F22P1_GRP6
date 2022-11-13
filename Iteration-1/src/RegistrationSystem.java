import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegistrationSystem {

    private ArrayList<Advisor> advisorList = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> coursesList = new ArrayList<>();
    private ArrayList<MandatoryCourse> mandotoryCourses = new ArrayList<>();
    private ArrayList<GraduationProject> graduationCourses = new ArrayList<>();
    private ArrayList<TechnicalElective> technicalElectives = new ArrayList<>();
    private ArrayList<FacultyTechnicalElective> facultyTechnicalElectives = new ArrayList<>();
    private ArrayList<NonTechnicalElective> nonTechnicalElectives = new ArrayList<>();

    public RegistrationSystem() {
        startSimulation();
    }

    private Course courseIsThereOrNot(String courseCode) {
        for (Course c : coursesList) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    private void readMandotory(JSONObject input) {
        JSONArray inputCourses = (JSONArray) input.get("MandatoryCourses");
        for (Object c : inputCourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseSemester = (int) (long) course.get("semester");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");
            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }

            MandatoryCourse mandatoryCourse = new MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisitesCourse);

            coursesList.add(mandatoryCourse);
            mandotoryCourses.add(mandatoryCourse);

        }

    }

    private void readNTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("nonTechnicalElectiveCourses");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("coursHour");
            JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
                semesterList.add((int) (long) s);
            }

            NonTechnicalElective nonTechnicalElective = new NonTechnicalElective(courseName, courseCode, courseCredit,
                    courseDay, courseHour, courseQuato, semesterList);
            nonTechnicalElectives.add(nonTechnicalElective);
            coursesList.add(nonTechnicalElective);

        }
    }

    private void readTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("technicalElectiveCourses");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");
            int requiredCredit = (int) (long) course.get("requiredCredits");

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }
            ;

            JSONArray semesters = (JSONArray) course.get("semester");
            TechnicalElective techElectiveCourse = new TechnicalElective(courseName, courseCode, courseCredit,
                    courseDay,
                    courseHour, courseQuato, semesters, requiredCredit, prequisites);

            coursesList.add(techElectiveCourse);
            technicalElectives.add(techElectiveCourse);

        }
    }

    private void readFTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("facultyTechnicalElectiveCourses");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");

            JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
                semesterList.add((int) (long) s);
            }

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }
            ;

            FacultyTechnicalElective facultyTechnicalElective = new FacultyTechnicalElective(courseName, courseCode,
                    courseCredit, courseDay, courseHour, courseQuato, semesterList, prequisitesCourse);
            facultyTechnicalElectives.add(facultyTechnicalElective);
            coursesList.add(facultyTechnicalElective);

        }
    }

    private void readGraduationProject(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("graduationProject");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseSemester = (int) (long) course.get("semester");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            int requiredCredit = (int) (long) course.get("requiredCredits");
            String courseHour = (String) course.get("courseHour");

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }
            ;
            GraduationProject graduationProject = new GraduationProject(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisites, requiredCredit);

            coursesList.add(graduationProject);
            graduationCourses.add(graduationProject);

        }
    }

    private void readAdvisorInput(JSONObject advisor) {
        JSONArray inputAdvisors = (JSONArray) advisor.get("advisors");
        for (Object a : inputAdvisors) {
            JSONObject advisors = (JSONObject) a;
            String firstName = (String) advisors.get("firstName");
            String lastName = (String) advisors.get("lastName");
            Advisor newAdvisor = new Advisor(firstName, lastName);
            advisorList.add(newAdvisor);

        }
    }

    private void assignAdvisor(ArrayList<Student> studentList) {
        ArrayList<Student> copyStudentList = new ArrayList<>();
        for (Student a : studentList) {
            copyStudentList.add(a);
        }
        if (copyStudentList.size() > 0) {

            int count = 0;

            while (advisorList.size() > count) {
                if (copyStudentList.size() == 0) {
                    break;
                } else {
                    System.out.println(copyStudentList.size());
                    int randomNum = ThreadLocalRandom.current().nextInt(0, copyStudentList.size());
                    advisorList.get(count).addStudent(copyStudentList.get(randomNum));
                    copyStudentList.get(randomNum).setAdvisor(advisorList.get(count));
                    copyStudentList.remove(randomNum);
                    count++;
                    if (count == advisorList.size()) {
                        count = 0;
                    }
                }
            }
        }
    }

    private void assignInstructor(ArrayList<Course> courseList) {
        ArrayList<Course> copyCourseList = new ArrayList<>();
        for (Course a : courseList) {
            copyCourseList.add(a);
        }
        if (copyCourseList.size() > 0) {

            int count = 0;

            while (advisorList.size() > count) {
                if (copyCourseList.size() == 0) {
                    break;
                } else {
                    System.out.println(copyCourseList.size());
                    int randomNum = ThreadLocalRandom.current().nextInt(0, copyCourseList.size());
                    advisorList.get(count).addGivenCourse(copyCourseList.get(randomNum));
                    copyCourseList.get(randomNum).setCourseInstructor(advisorList.get(count));
                    copyCourseList.remove(randomNum);
                    count++;
                    if (count == advisorList.size()) {
                        count = 0;
                    }
                }
            }
        }
    }

    private void readStudentInput(JSONObject student) {
        JSONArray inputStudents = (JSONArray) student.get("students");
        for (Object s : inputStudents) {
            JSONObject students = (JSONObject) s;
            String studentFirstName = (String) students.get("firstName");
            String studentLastName = (String) students.get("lastName");
            int studentRegistirationYear = (int) (long) students.get("registrationYear");
            int studentOrder = (int) (long) students.get("order");
            Student newStudent = new Student(studentFirstName, studentLastName, studentRegistirationYear, studentOrder);
            studentList.add(newStudent);
            newStudent.setSemester(calculateSemester(newStudent));
            createTranscript(newStudent);
        }

    }

    private int calculateSemester(Student student) {
        ArrayList<String> monthListFirstSemester = new ArrayList<>(Arrays.asList("JANUARY", "FEBRUARY", "SEPTEMBER",
                "OCTOBER", "NOVEMBER", "DECEMBER"));
        ArrayList<String> monthListSecondSemester = new ArrayList<>(Arrays.asList("MARCH", "APRIL", "MAY", "JUNE",
                "JULLY", "AUGUST"));
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        String thisMonth = currentMonth.toString();
        int calculatedSemester = 0;
        if (monthListFirstSemester.contains(thisMonth)) {
            calculatedSemester = (2 * (student.getCurrentYear() - student.getRegistrationYear()) + 1);
        } else if (monthListSecondSemester.contains((thisMonth))) {
            calculatedSemester = (2 * (student.getCurrentYear() - student.getRegistrationYear()));
        }
        return calculatedSemester;
    }

    private void readInput() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject input = (JSONObject) parser.parse(new FileReader("Iteration-1\\src\\input.json"));

            readMandotory(input);
            readGraduationProject(input);
            readNTE(input);
            readTE(input);
            readFTE(input);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void readStudent() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject student = (JSONObject) parser.parse(new FileReader("Iteration-1\\src\\students.json"));
            readStudentInput(student);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void readAdvisors() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject advisor = (JSONObject) parser.parse(new FileReader("Iteration-1\\src\\advisor.json"));
            readAdvisorInput(advisor);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void createTranscript(Student student) {
        String[] letterGrades = { "AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "FG", "DZ" };

        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToRequest(student)) {
                student.getTranscript().getTakenCouerses().put(mc,
                        letterGrades[new Random().nextInt(letterGrades.length)]);
                student.getTranscript().isCourseComplatedOrFailed();
            }
        }

        NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
        if (nte.isEligibleToRequest(student)) {
            System.out.println("******************");
            student.getTranscript().getTakenCouerses().put(nte,
                    letterGrades[new Random().nextInt(letterGrades.length)]);
            student.getTranscript().isCourseComplatedOrFailed();
        }
    }

    public void startSimulation() {
        readAdvisors();
        readInput();
        readStudent();
        assignAdvisor(studentList);
        assignInstructor(coursesList);

        /* for (Course o : mandotoryCourses) {
            System.out.println(o.getCourseName().toString());
            //System.out.println(o.getCourseHour().toString());
        
        }
        
        for (GraduationProject c : graduationCourses) {
            System.out.println(c.getCourseName());
        
        }
        
        for (TechnicalElective t : technicalElectives) {
            System.out.println(t.getCourseName());
            System.out.println(t.getSemester());
        }
        
        for (FacultyTechnicalElective f : facultyTechnicalElectives) {
            System.out.println(f.getCourseName());
            //System.out.println(f.getSemester());
        }
        
        for (NonTechnicalElective n : nonTechnicalElectives) {
            System.out.println(n.getCourseName());
        } */
        /*
        for (Student s : studentList) {
            System.out.printf("%s , %d yılında üniversiteye girdi, %d. Semester", s.getStudentName(),
                    s.getRegistrationYear(), s.getSemester());
            System.out.println();
        }
        
        for (Advisor a : advisorList) {
            System.out.println(a.getFirstName());
            System.out.println(a.getEmail());
            System.out.println(a.getStudents());
        
        }
        
        for (Course a : coursesList) {
            System.out.println(a.getCourseName());
            System.out.println(a.getCourseInstructor().getEmail());
        }
        
        System.out.println(studentList.size());
        System.out.println(advisorList.size()); */

        for (Student s : studentList) {
            System.out.printf("%s - %s ->", s.getStudentName(), s.getRegistrationYear());
            for (Map.Entry<Course, String> set : s.getTranscript().getTakenCouerses().entrySet()) {
                System.out.print(set.getKey().getCourseName() + "-" + set.getValue() + " - ");
            }
            System.out.println("\n\n\n\n");
        }
        /* for (Course c : coursesList) {
            System.out.printf("%s\n", c.getCourseName());
            for (Student s : c.getStudents()) {
                System.out.println(s.getStudentName());
            }
        
            System.out.println("\n\n\n\n");
        } */

    }

}
