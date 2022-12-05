import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RegistrationSystem {

    private String currentSemester;
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

    public Course courseIsThereOrNot(String courseCode) {
        for (Course c : coursesList) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public void readMandotory(JSONObject input) {
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
                if (courseIsThereOrNot((String) p) != null) {
                    /* System.out.println((String) p); */
                    prequisitesCourse.add(courseIsThereOrNot((String) p));
                }
            }

            MandatoryCourse mandatoryCourse = new MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisitesCourse);

            coursesList.add(mandatoryCourse);
            mandotoryCourses.add(mandatoryCourse);

        }

    }

    public void readNTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("nonTechnicalElectiveCourses");
        JSONArray inputNTESemesters = (JSONArray) input.get("NTESemesters");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("coursHour");

            /* JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
                semesterList.add((int) (long) s);
            } */

            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : inputNTESemesters) {
                semesterList.add((int) (long) s);
            }

            NonTechnicalElective nonTechnicalElective = new NonTechnicalElective(courseName, courseCode, courseCredit,
                    courseDay, courseHour, courseQuato, semesterList);
            nonTechnicalElectives.add(nonTechnicalElective);
            coursesList.add(nonTechnicalElective);

        }
    }

    public void readTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("technicalElectiveCourses");
        JSONArray inputTESemesters = (JSONArray) input.get("TESemesters");
        int requiredCredit = (int) (long) input.get("TERequiredCredits");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");
            //int requiredCredit = (int) (long) course.get("requiredCredits");

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                if (courseIsThereOrNot((String) p) != null) {
                    /* System.out.println((String) p); */
                    prequisitesCourse.add(courseIsThereOrNot((String) p));
                }
            }

            /* JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
                semesterList.add((int) (long) s);
            } */

            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : inputTESemesters) {
                semesterList.add((int) (long) s);
            }

            TechnicalElective techElectiveCourse = new TechnicalElective(courseName, courseCode, courseCredit,
                    courseDay,
                    courseHour, courseQuato, semesterList, requiredCredit, prequisitesCourse);

            coursesList.add(techElectiveCourse);
            technicalElectives.add(techElectiveCourse);

        }
    }

    public void readFTE(JSONObject input) {
        JSONArray inputNTECourses = (JSONArray) input.get("facultyTechnicalElectiveCourses");
        JSONArray inputFTESemesters = (JSONArray) input.get("FTESemesters");
        for (Object c : inputNTECourses) {
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato = (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");

            /* JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
                semesterList.add((int) (long) s);
            } */

            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : inputFTESemesters) {
                semesterList.add((int) (long) s);
            }

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p : prequisites) {
                if (courseIsThereOrNot((String) p) != null) {
                    /* System.out.println((String) p); */
                    prequisitesCourse.add(courseIsThereOrNot((String) p));
                }
            }

            FacultyTechnicalElective facultyTechnicalElective = new FacultyTechnicalElective(courseName, courseCode,
                    courseCredit, courseDay, courseHour, courseQuato, semesterList, prequisitesCourse);
            facultyTechnicalElectives.add(facultyTechnicalElective);
            coursesList.add(facultyTechnicalElective);

        }
    }

    public void readGraduationProject(JSONObject input) {
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
                if (courseIsThereOrNot((String) p) != null) {
                    /* System.out.println((String) p); */
                    prequisitesCourse.add(courseIsThereOrNot((String) p));
                }
            }

            GraduationProject graduationProject = new GraduationProject(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisitesCourse, requiredCredit);

            coursesList.add(graduationProject);
            graduationCourses.add(graduationProject);

        }
    }

    public void readAdvisorInput(JSONObject advisor) {
        JSONArray inputAdvisors = (JSONArray) advisor.get("advisors");
        for (Object a : inputAdvisors) {
            JSONObject advisors = (JSONObject) a;
            String firstName = (String) advisors.get("firstName");
            String lastName = (String) advisors.get("lastName");
            Advisor newAdvisor = new Advisor(firstName, lastName);
            advisorList.add(newAdvisor);

        }
    }

    public void assignAdvisor(ArrayList<Student> studentList) {
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
                    /* System.out.println(copyStudentList.size()); */
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

    public void assignInstructor(ArrayList<Course> courseList) {
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
                    /* System.out.println(copyCourseList.size()); */
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

    public void readStudentInput(JSONObject student) {
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

    /*The process of reading the current semester from the input.json file. */
    public String readCurrentSemester(JSONObject input) {
        currentSemester = (String) input.get("CurrentSemester");
        return currentSemester;
    }

    public int calculateSemester(Student student) {

        /* Semester control is done with CurrentSemester information from input.json file*/
        int calculatedSemester = 0;
        if (currentSemester.equals("fall")) {
            calculatedSemester = (2 * (student.getCurrentYear() - student.getRegistrationYear()) + 1);
        } else if (currentSemester.equals("spring")) {
            calculatedSemester = (2 * ((student.getCurrentYear() + 1) - student.getRegistrationYear()));
        }
        return calculatedSemester;
    }
    /*
    public int calculateSemester(Student student) {
        int calculatedSemester = 0;
        if (getCurrentSemester().equals("fall")) {
            calculatedSemester = (2 * (student.getCurrentYear() - student.getRegistrationYear()) + 1);
        } else if (getCurrentSemester().equals("spring")) {
            calculatedSemester = (2 * (student.getCurrentYear() - student.getRegistrationYear()) + 2);
        }
        return calculatedSemester;
    }
     public int calculateSemester(Student student) {
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
    } */

    public String getCurrentSemester() {
        return this.currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void readInput() {

        try {
            JSONParser parser = new JSONParser();
            JSONObject input = (JSONObject) parser.parse(new FileReader("Iteration-2\\src\\input.json"));

            readMandotory(input);
            readGraduationProject(input);
            readNTE(input);
            readTE(input);
            readFTE(input);
            readCurrentSemester(input);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void readStudent() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject student = (JSONObject) parser.parse(new FileReader("Iteration-2\\src\\students.json"));
            readStudentInput(student);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void readAdvisors() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject advisor = (JSONObject) parser.parse(new FileReader("Iteration-2\\src\\advisor.json"));
            readAdvisorInput(advisor);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //(Emre) Grade-RegSys connection
    public void createTranscript(Student student) {

        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToBePreviouslyTaken(student)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                Grade courseGrade = new Grade(mc, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(mc, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(mc, courseGrade.getLetterGrade());
            }
        }
        int i;
        for (i = 1; i < student.getSemester(); i++) {
            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            while (student.getTranscript().getCompletedCourses().contains(nte)
                    || student.getTranscript().getFailedCourses().contains(nte)) {
                nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            }
            if (nte.semesterCheck(i)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                //Deneme grade'i
                intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                        : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                Grade courseGrade = new Grade(nte, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(nte, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(nte, courseGrade.getLetterGrade());
            }
        }
        i = 1;
        for (i = 1; i < student.getSemester(); i++) {
            FacultyTechnicalElective fte = facultyTechnicalElectives
                    .get(new Random().nextInt(facultyTechnicalElectives.size()));
            while (student.getTranscript().getCompletedCourses().contains(fte)
                    || student.getTranscript().getFailedCourses().contains(fte)) {
                fte = facultyTechnicalElectives.get(new Random().nextInt(facultyTechnicalElectives.size()));
            }
            if (fte.semesterCheck(i)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                //Deneme grade'i
                intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                        : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                Grade courseGrade = new Grade(fte, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(fte, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(fte, courseGrade.getLetterGrade());
            }
        }
        i = 1;
        TechnicalElective te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
        while (i < student.getSemester()) {
            if (te.semesterCheck(i)) {
                int teCount = 0;
                if (i == 7) {
                    while (teCount != 2) {
                        while (student.getTranscript().getCompletedCourses().contains(te)
                                || student.getTranscript().getFailedCourses().contains(te)
                                || !student.getTranscript().hasBeenPassedCourses(te.getPrequisites())) {
                            te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
                        }
                        Random randomGrade = new Random();
                        int intRandomGrade = randomGrade.nextInt(100);
                        //Deneme grade'i
                        intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                                : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                        Grade courseGrade = new Grade(te, intRandomGrade);
                        student.getTranscript().getTakenCouerses().put(te, courseGrade.getLetterGrade());
                        student.getTranscript().isCourseComplatedOrFailed(te, courseGrade.getLetterGrade());
                        teCount++;
                    }
                }
            }
            i++;
        }
        student.getTranscript().calculateComplateCredit();
        for (GraduationProject gp : graduationCourses) {
            if (gp.getSemester() < student.getSemester()
                    && gp.getRequiredCredits() <= student.getTranscript().getCreditCompleted()) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                //Deneme grade'i
                intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                        : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                Grade courseGrade = new Grade(gp, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(gp, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(gp, courseGrade.getLetterGrade());
            }
        }
        student.getTranscript().calculateComplateCredit();
        student.getTranscript().calculateGpa();

    }
    /* public void createTranscript(Student student) {
    
        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToBePreviouslyTaken(student)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                Grade courseGrade = new Grade(mc, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(mc, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(mc, courseGrade.getLetterGrade());
            }
        }
    
        for (int i = 1; i < student.getSemester(); i++) {
            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            if (nte.semesterCheck(i)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                //Deneme grade'i
                intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                        : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                Grade courseGrade = new Grade(nte, intRandomGrade);
                student.getTranscript().getTakenCouerses().put(nte, courseGrade.getLetterGrade());
                student.getTranscript().isCourseComplatedOrFailed(nte, courseGrade.getLetterGrade());
            }
        }
        student.getTranscript().calculateComplateCredit();
    } */

    /* public void createTranscript(Student student) {
        String[] letterGrades = { "AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "FG", "DZ" };
    
        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToBePreviouslyTaken(student)) {
                String letter = letterGrades[new Random().nextInt(letterGrades.length - 3)];
                student.getTranscript().getTakenCouerses().put(mc, letter);
                student.getTranscript().isCourseComplatedOrFailed(mc, letter);
            }
        }
    
        for (int i = 1; i < student.getSemester(); i++) {
            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            if (nte.semesterCheck(i)) {
                String letter = letterGrades[new Random().nextInt(letterGrades.length - 3)];
                student.getTranscript().getTakenCouerses().put(nte, letter);
                student.getTranscript().isCourseComplatedOrFailed(nte, letter);
            }
        }
        student.getTranscript().calculateComplateCredit();
    }
    */
    public void requestCoursesForAllStudents() {
        /*Courses in the curriculum according to semester information, fall or spring are added to the
        currentSemesterMandotaryCourse Arraylist */
        ArrayList<MandatoryCourse> currentSemesterMandatoryCourses = new ArrayList<MandatoryCourse>();
        for (MandatoryCourse c : mandotoryCourses) {
            if (currentSemester.equals("fall")) {
                if (c.getSemester() % 2 != 0) {
                    currentSemesterMandatoryCourses.add(c);
                }
            } else if (currentSemester.equals("spring")) {
                if (c.getSemester() % 2 == 0) {
                    /* System.out.println(c.getCourseCode()); */
                    currentSemesterMandatoryCourses.add(c);
                }
            }

        }

        for (Student s : studentList) {
            for (MandatoryCourse mc : currentSemesterMandatoryCourses) {
                if (mc.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(mc);
                }
            }

            /* Adding the failed courses to the getRequestedCourse Arraylist by checking the semester */
            for (Course c : s.getTranscript().getFailedCourses()) {
                if (c instanceof MandatoryCourse) {
                    if (currentSemester.equals("fall")) {
                        if (((MandatoryCourse) c).getSemester() % 2 != 0) {
                            if (s.getTranscript().getCompletedCourses().contains(c)) {
                                continue;
                            } else {
                                s.getRequestedCourses().add(c);
                            }
                        }
                    } else if (currentSemester.equals("spring")) {
                        if (((MandatoryCourse) c).getSemester() % 2 == 0) {
                            s.getRequestedCourses().add(c);
                        }
                    }

                }
                if (c instanceof ElectiveCourse) {
                    s.getRequestedCourses().add(c);
                }
            }

            for (GraduationProject gp : graduationCourses) {
                if (s.getRequestedCourses().contains(gp)) {
                    continue;
                }
                if (gp.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(gp);
                }
            }

            int limit = (s.getSemester() == 7) ? 2 : ((s.getSemester() == 8) ? 3 : 0);
            for (int i = 0; i < limit; i++) {
                TechnicalElective te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
                while (s.getTranscript().getCompletedCourses().contains(te)
                        || s.getTranscript().getFailedCourses().contains(te)
                        || s.getRequestedCourses().contains(te)) {
                    te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
                }
                if (te.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(te);
                }
            }

            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            while (s.getTranscript().getCompletedCourses().contains(nte)) {
                nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            }
            if (nte.isEligibleToRequest(s)) {
                s.getRequestedCourses().add(nte);
            }

            FacultyTechnicalElective fte = facultyTechnicalElectives
                    .get(new Random().nextInt(facultyTechnicalElectives.size()));
            while (s.getTranscript().getCompletedCourses().contains(fte)) {
                fte = facultyTechnicalElectives.get(new Random().nextInt(facultyTechnicalElectives.size()));
            }
            if (fte.isEligibleToRequest(s)) {
                s.getRequestedCourses().add(fte);
            }
            /* 
            for (FacultyTechnicalElective fte : facultyTechnicalElectives) {
                if (fte.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(fte);
                    break;
                }
            }
            for (NonTechnicalElective nte : nonTechnicalElectives) {
                if (s.getRequestedCourses().contains(nte)) {
                    continue;
                }
                if (nte.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(nte);
                    break;
                }
            } */
        }
    }

    public void startRegistration() {
        for (Student s : studentList) {
            s.getAdvisor().completeRegistration(s);
        }
    }

    public void createStudentOutput(Student student) {
        Map jsonObject = new LinkedHashMap();
        JSONArray jsonArray = new JSONArray();
        //Add student info
        jsonObject.put("StudentName", student.toString());
        jsonObject.put("StudentId", student.getStudentId());
        jsonObject.put("SemesterNumber", student.getSemester());
        jsonObject.put("CompletedCredits", student.getTranscript().getCreditCompleted());
        jsonObject.put("Gpa", student.getTranscript().getGpa());
        jsonObject.put("AdvisorName", student.getAdvisor().toString());

        //Add taken courses
        JSONArray jsonObjectPastCourses = new JSONArray();
        for (Map.Entry<Course, String> set : student.getTranscript().getTakenCouerses().entrySet()) {
            JSONObject jsonObjectTakenCourse = new JSONObject();
            jsonObjectTakenCourse.put("LetterGrade", set.getValue());
            jsonObjectTakenCourse.put("CourseName", set.getKey().getCourseName());
            jsonObjectTakenCourse.put("Course", set.getKey().getCourseCode());
            jsonObjectPastCourses.add(jsonObjectTakenCourse);
        }
        jsonObject.put("Taken Course", jsonObjectPastCourses);

        //Add requested courses
        JSONArray jsonObjectRequestedCourses = new JSONArray();
        for (Course course : student.getRequestedCourses()) {
            JSONObject jsonObjectRequestedCouurse = new JSONObject();
            jsonObjectRequestedCouurse.put("Course", course.getCourseName());
            jsonObjectRequestedCourses.add(jsonObjectRequestedCouurse);
        }
        jsonObject.put("Requested Course", jsonObjectRequestedCourses);

        //Add enrolled courses
        JSONArray jsonObjectEnrolled = new JSONArray();
        for (Course course : student.getTranscript().getEnrolledCourses()) {
            JSONObject jsonObjectEnrolledCourse = new JSONObject();
            jsonObjectEnrolledCourse.put("Course", course.getCourseName());
            jsonObjectEnrolled.add(jsonObjectEnrolledCourse);
        }
        jsonObject.put("Enrolled Course", jsonObjectEnrolled);

        //Add student output
        JSONArray jsonOutput = new JSONArray();
        for (String str : student.getStudentOutput()) {
            jsonOutput.add(str);
        }
        jsonObject.put("Output", jsonOutput);

        JSONObject json = new JSONObject();
        try {

            new File("Output").mkdirs();
            new File("Output\\Students").mkdirs();

            FileWriter file = new FileWriter(
                    "Output\\Students\\" + (String) (student.getStudentId().toString()) + ".json");
            file.write(json.toJSONString(jsonObject));

            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /* public void createStudentOutput(Student student) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("StudentName", student.toString());
        jsonObject.put("StudentId", student.getStudentId());
        jsonObject.put("SemesterNumber", student.getSemester());
        jsonObject.put("CompletedCredits", student.getTranscript().getCreditCompleted());
        jsonObject.put("AdvisorName", student.getAdvisor().toString());
        JSONArray jsonObjectRequestedCourses = new JSONArray();
    
        for (Course course : student.getRequestedCourses()) {
            JSONObject jsonObjectRequestedCouurse = new JSONObject();
    
            jsonObjectRequestedCouurse.put("Course", course.getCourseName());
            jsonObjectRequestedCourses.add(jsonObjectRequestedCouurse);
        }
        jsonObject.put("Requested Course", jsonObjectRequestedCourses);
    
        JSONArray jsonObjectPastCourses = new JSONArray();
        for (Map.Entry<Course, String> set : student.getTranscript().getTakenCouerses().entrySet()) {
            JSONObject jsonObjectTakenCourse = new JSONObject();
            jsonObjectTakenCourse.put("LetterGrade", set.getValue());
            jsonObjectTakenCourse.put("CourseName", set.getKey().getCourseName());
            jsonObjectTakenCourse.put("Course", set.getKey().getCourseCode());
            jsonObjectPastCourses.add(jsonObjectTakenCourse);
        }
        jsonObject.put("Taken Course", jsonObjectPastCourses);
    
        JSONArray jsonObjectEnrolled = new JSONArray();
        for (Course course : student.getTranscript().getEnrolledCourses()) {
            JSONObject jsonObjectEnrolledCourse = new JSONObject();
    
            jsonObjectEnrolledCourse.put("Course", course.getCourseName());
            jsonObjectEnrolled.add(jsonObjectEnrolledCourse);
        }
        jsonObject.put("Enrolled Course", jsonObjectEnrolled);
    
        JSONArray jsonOutput = new JSONArray();
        for (String str : student.getStudentOutput()) {
    
            jsonOutput.add(str);
        }
        jsonObject.put("Output", jsonOutput);
    
        try {
    
            new File("Output").mkdirs();
            new File("Output\\Students").mkdirs();
    
            FileWriter file = new FileWriter(
                    "Output\\Students\\" + (String) (student.getStudentId().toString()) + ".json");
            file.write(jsonObject.toJSONString());
    
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } */

    public void createDepartmentOutput() {
        // JSON FOR DEPERTMENT
        Map jsonDepertment = new LinkedHashMap();

        for (Course course : coursesList) {
            if (course instanceof MandatoryCourse) {
                if (this.getCurrentSemester().equals("fall") && ((MandatoryCourse) course).getSemester() % 2 != 0) {
                    JSONArray jsonArrayDepertment = new JSONArray();
                    jsonArrayDepertment.add(course.getQuotaProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the quota problems.");
                    jsonArrayDepertment.add(course.getCollisionProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the collision problems.");
                    jsonArrayDepertment.add(course.getFailedPreq() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the failed prerequisite.");
                    if (course instanceof GraduationProject) {
                        jsonArrayDepertment.add(course.getFailedCredits() + " Students couldn't register for " +
                                course.getCourseCode() + " due to the failed credit problems.");
                    }
                    jsonDepertment.put(course.getCourseName(), jsonArrayDepertment);

                } else if (this.getCurrentSemester().equals("spring")
                        && ((MandatoryCourse) course).getSemester() % 2 == 0) {
                    JSONArray jsonArrayDepertment = new JSONArray();
                    jsonArrayDepertment.add(course.getQuotaProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the quota problems.");
                    jsonArrayDepertment.add(course.getCollisionProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the collision problems.");
                    jsonArrayDepertment.add(course.getFailedPreq() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the failed prerequisite.");
                    if (course instanceof GraduationProject) {
                        jsonArrayDepertment.add(course.getFailedCredits() + " Students couldn't register for " +
                                course.getCourseCode() + " due to the failed credit problems.");
                    }
                    jsonDepertment.put(course.getCourseName(), jsonArrayDepertment);
                }
            } else {

                JSONArray jsonArrayDepertment = new JSONArray();
                jsonArrayDepertment.add(course.getQuotaProblem() + " Students couldn't register for " +
                        course.getCourseCode() + " due to the quota problems.");
                jsonArrayDepertment.add(course.getCollisionProblem() + " Students couldn't register for " +
                        course.getCourseCode() + " due to the collision problems.");
                if (course instanceof TechnicalElective) {
                    jsonArrayDepertment.add(course.getFailedCredits() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the failed credit problems.");
                    jsonArrayDepertment.add(course.getFailedPreq() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the failed prerequisite.");
                }
                jsonDepertment.put(course.getCourseName(), jsonArrayDepertment);
            }
        }
        JSONObject Depertment = new JSONObject();
        try {
            FileWriter file2 = new FileWriter(
                    "Output\\" + "DEPARTMENT_OUTPUT_" + this.getCurrentSemester().toUpperCase() + ".json");

            file2.write(Depertment.toJSONString(jsonDepertment));
            file2.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /* public void createDepartmentOutput() {
        // JSON FOR DEPERTMENT
        JSONObject jsonDepertment = new JSONObject();
    
        for (Course course : coursesList) {
            if (course instanceof MandatoryCourse) {
                if (((MandatoryCourse) course).getSemester() % 2 != 0) {
                    JSONArray jsonArrayDepertment = new JSONArray();
                    jsonArrayDepertment.add(course.getQuotaProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the quota problems.");
                    jsonArrayDepertment.add(course.getCollisionProblem() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the collision problems.");
                    if (course instanceof GraduationProject || course instanceof TechnicalElective) {
                        jsonArrayDepertment.add(course.getFailedCredits() + " Students couldn't register for " +
                                course.getCourseCode() + " due to the failed credit problems.");
                    }
                    jsonDepertment.put(course.getCourseName(), jsonArrayDepertment);
                }
            } else {
    
                JSONArray jsonArrayDepertment = new JSONArray();
                jsonArrayDepertment.add(course.getQuotaProblem() + " Students couldn't register for " +
                        course.getCourseCode() + " due to the quota problems.");
                jsonArrayDepertment.add(course.getCollisionProblem() + " Students couldn't register for " +
                        course.getCourseCode() + " due to the collision problems.");
                if (course instanceof GraduationProject || course instanceof TechnicalElective) {
                    jsonArrayDepertment.add(course.getFailedCredits() + " Students couldn't register for " +
                            course.getCourseCode() + " due to the failed credit problems.");
                }
                jsonDepertment.put(course.getCourseName(), jsonArrayDepertment);
            }
        }
        try {
            FileWriter file2 = new FileWriter("Output\\" + "DEPARTMENT_OUTPUT_FALL.json");
    
            file2.write(jsonDepertment.toJSONString());
            file2.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } */

    public void startSimulation() {
        readAdvisors();
        readInput();
        readStudent();
        assignAdvisor(studentList);
        assignInstructor(coursesList);
        requestCoursesForAllStudents();
        startRegistration();
        for (Student s : studentList) {
            createStudentOutput(s);
        }
        createDepartmentOutput();

        /* for (MandatoryCourse o : mandotoryCourses) {
            System.out.println(o.getCourseName().toString() + " -- " + o.getSemester());
            //System.out.println(o.getCourseHour().toString());
        
        } */
        /*
        for (GraduationProject c : graduationCourses) {
            System.out.println(c.getCourseName());
        
        }
        */
        /* 
        for (TechnicalElective t : technicalElectives) {
            System.out.println(t.getCourseName() + " RequiredCredits: " + t.getRequiredCredits());
            for (int i : t.getSemesters()) {
                System.out.print("Semesters: " + i + " ");
            }
            System.out.println("\n\n");
        
        } */
        /*
        for (FacultyTechnicalElective f : facultyTechnicalElectives) {
            System.out.println(f.getCourseName());
            for (int i : f.getSemesters()) {
                System.out.print("Semesters: " + i + " ");
            }
            System.out.println("\n\n");
        }
        
        for (NonTechnicalElective n : nonTechnicalElectives) {
            System.out.println(n.getCourseName());
            for (int i : n.getSemesters()) {
                System.out.print("Semesters: " + i + " ");
            }
            System.out.println("\n\n");
        } */
        /* 
        for (Student s : studentList) {
            System.out.printf("%s , %d yılında üniversiteye girdi, %d. Semester", s.getStudentName(),
                    s.getRegistrationYear(), s.getSemester());
            System.out.println();
        } */
        /*
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

        /* for (Student s : studentList) {
            System.out.printf("%s - %s ->", s.getStudentName(), s.getRegistrationYear());
            for (Map.Entry<Course, String> set : s.getTranscript().getTakenCouerses().entrySet()) {
                System.out.print(set.getKey().getCourseName() + "-" + set.getValue() + " - ");
            }
            System.out.println("\n\n\n\n");
        } */

        /*        for (Course c : coursesList) {
            System.out.printf("%s - Collision:%d - Quota:%d - Credit:%d - Prereq:%d\n", c.getCourseName(),
                    c.getCollisionProblem(),
                    c.getQuotaProblem(),
                    c.getFailedCredits(), c.getFailedPreq());
            for (Student s : c.getStudents()) {
                System.out.println(s.getStudentName());
            }
        
            System.out.println("\n\n\n");
        }*/
        /* 
        for (Student s : studentList) {
            System.out.printf("%s - %d  ->>>", s.getStudentName(), s.getTranscript().getCreditCompleted());
            for (Course c : s.getRequestedCourses()) {
                System.out.print(c.getCourseName() + "---");
            }
            System.out.println("\n\n");
        } */
        /* 
        for (Student s : studentList) {
            System.out.printf("%s - %s ->", s.getStudentName(), s.getRegistrationYear());
            for (Course c : s.getTranscript().getFailedCourses()) {
                System.out.print(c.getCourseName() + "--");
            }
            System.out.println("\n\n\n\n");
        } */
        /* 
        System.out.println("\n\n\n\n");
        for (Student s : studentList) {
            System.out.printf("%s - %d  ->>>", s.getStudentName(), s.getTranscript().getCreditCompleted());
            for (Course c : s.getTranscript().getEnrolledCourses()) {
                System.out.print(c.getCourseName() + "---");
            }
            System.out.println("\n\n");
        } */
        /* for (Map.Entry<Course, String> set : studentList.get(studentList.size() - 1).getTranscript().getTakenCouerses()
                .entrySet()) {
            System.out.println(set.getKey().getCourseName() + " " + set.getValue());
        }
        
        for (Course c : studentList.get(studentList.size() - 1).getTranscript().getEnrolledCourses()) {
            System.out.print(c.getCourseName() + "---");
        }
        */

        for (Student s : studentList) {
            System.out.println(s.getStudentName() + " StudentId: " + s.getStudentId() + " Semester: " + s.getSemester()
                    + " TotalCredits: "
                    + s.getTranscript().getCreditCompleted());
            //for (Map.Entry<Course, String> set : s.getTranscript().getTakenCouerses()
            //        .entrySet()) {
            //    System.out.println(set.getKey().getCourseName() + " --> " + set.getValue());
            //}
            System.out.println("------------COMPLETED COURSES------------------------------");
            for (Course c : s.getTranscript().getCompletedCourses()) {
                System.out.print(c.getCourseName() + " --- ");
            }
            System.out.println("\n");
            System.out.println("------------FAILED COURSES------------------------------");
            for (Course c : s.getTranscript().getFailedCourses()) {
                System.out.print(c.getCourseName() + " --- ");
            }
            System.out.println("\n");
            System.out.println("------------REQUESTED COURSES------------------------------");
            for (Course c : s.getRequestedCourses()) {
                System.out.print(c.getCourseName() + " --- ");
            }
            System.out.println("\n");
            System.out.println("-------------ENROLLED COURSES------------------------------");
            for (Course c : s.getTranscript().getEnrolledCourses()) {
                System.out.print(c.getCourseName() + " --- ");
            }
            System.out.println("");
            System.out.println("-------------END-------------------------------------------");
            System.out.println("\n\n");
        }
    }
}
