import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RegistrationSystem {
    private Logger log = Logger.getLogger(RegistrationSystem.class);
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
        log.info("Registration system created.");
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
            log.info(mandatoryCourse.getCourseName() + ": Mandatory course is readed from input.json.");
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
            log.info(
                    nonTechnicalElective.getCourseName() + ": NonTechnical elective course is readed from input.json.");
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
            log.info(techElectiveCourse.getCourseName() + ": Technical elective course is readed from input.json.");
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
            log.info(facultyTechnicalElective.getCourseName()
                    + ": Faculty technical elective course is readed from input.json.");
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
            log.info(graduationProject.getCourseName() + ": Graduation project is readed from input.json.");
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
            log.info(newAdvisor.getAdvisorName() + ": Advisor is readed from advisor.json.");
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
                    int randomNum = ThreadLocalRandom.current().nextInt(0, copyStudentList.size());
                    advisorList.get(count).addStudent(copyStudentList.get(randomNum));
                    copyStudentList.get(randomNum).setAdvisor(advisorList.get(count));
                    log.info("Student " + copyStudentList.get(randomNum).getStudentName() + " is assigned advisor "
                            + advisorList.get(count).getAdvisorName() + ".");
                    copyStudentList.remove(randomNum);
                    count++;
                    if (count == advisorList.size()) {
                        count = 0;
                    }
                }
            }
        }
        log.info("All students are assigned a advisor.");
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
                    int randomNum = ThreadLocalRandom.current().nextInt(0, copyCourseList.size());
                    advisorList.get(count).addGivenCourse(copyCourseList.get(randomNum));
                    copyCourseList.get(randomNum).setCourseInstructor(advisorList.get(count));
                    log.info("Course " + copyCourseList.get(randomNum).getCourseName() + " is assigned instructor "
                            + advisorList.get(count).getAdvisorName() + ".");
                    copyCourseList.remove(randomNum);
                    count++;
                    if (count == advisorList.size()) {
                        count = 0;
                    }
                }
            }
        }
        log.info("All courses are assigned a instructor.");
    }

    public void readStudentInput(JSONObject student) {
        JSONArray inputNames = (JSONArray) student.get("names");
        JSONArray inputSurnames = (JSONArray) student.get("surnames");
        int studentNumberPerYear = (int) (long) student.get("studentNumberPerYear");
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> surnames = new ArrayList<String>();
        for (Object n : inputNames) {
            names.add((String) n);
        }
        for (Object sn : inputSurnames) {
            surnames.add((String) sn);
        }
        for (int i = 1; i <= studentNumberPerYear; i++) {
            Student newStudent = new Student(names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())), 2022, i);
            studentList.add(newStudent);
            newStudent.setSemester(calculateSemester(newStudent));
            createTranscript(newStudent);
        }
        log.info("First year students are readed successfully.");
        for (int i = 1; i <= studentNumberPerYear; i++) {
            Student newStudent = new Student(names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())), 2021, i);
            studentList.add(newStudent);
            newStudent.setSemester(calculateSemester(newStudent));
            createTranscript(newStudent);
        }
        log.info("Second year students are readed successfully.");
        for (int i = 1; i <= studentNumberPerYear; i++) {
            Student newStudent = new Student(names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())), 2020, i);
            studentList.add(newStudent);
            newStudent.setSemester(calculateSemester(newStudent));
            createTranscript(newStudent);
        }
        log.info("Third year students are readed successfully.");
        for (int i = 1; i <= studentNumberPerYear; i++) {
            Student newStudent = new Student(names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())), 2019, i);
            studentList.add(newStudent);
            newStudent.setSemester(calculateSemester(newStudent));
            createTranscript(newStudent);
        }
        log.info("Fourth year students are readed successfully.");
        log.info("All students are readed from students.json.");
    }

    /*The process of reading the current semester from the input.json file. */
    public String readCurrentSemester(JSONObject input) {
        currentSemester = (String) input.get("CurrentSemester");
        log.info("Current semester (fall or spring) is readed from input.json.");
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
        log.info(student.getStudentName() + ": Semester calculated according to the input.");
        return calculatedSemester;
    }

    public String getCurrentSemester() {
        return this.currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
        log.info("Current semester (fall or spring) changed.");
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
            log.info("input.json file successfully readed.");

        } catch (IOException | ParseException e) {
            log.error("input.json file couldn't readed.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void readStudent() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject student = (JSONObject) parser.parse(new FileReader("Iteration-2\\src\\students.json"));
            readStudentInput(student);
            log.info("students.json file successfully readed.");
        } catch (IOException | ParseException e) {
            log.error("students.json file couldn't readed.");
            e.printStackTrace();
            System.exit(2);
        }
    }

    public void readAdvisors() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject advisor = (JSONObject) parser.parse(new FileReader("Iteration-2\\src\\advisor.json"));
            readAdvisorInput(advisor);
            log.info("advisor.json file successfully readed.");
        } catch (IOException | ParseException e) {
            log.error("advisor.json file couldn't readed.");
            e.printStackTrace();
            System.exit(3);
        }
    }

    public void simulateSpringAfterFall() {
        this.setCurrentSemester("spring");
        for (Course c : coursesList) {
            c.getStudents().clear();
            c.setCollisionProblem(0);
            c.setQuotaProblem(0);
            c.setFailedCredits(0);
            c.setFailedPreq(0);
        }
        for (Student s : studentList) {
            for (Course c : s.getTranscript().getEnrolledCourses()) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                Grade courseGrade = new Grade(c, intRandomGrade);
                s.getTranscript().getTakenCourses().put(c, courseGrade);
                s.getTranscript().isCourseCompletedOrFailed(c, courseGrade.getLetter());
                s.getTranscript().calculateCompleteCredit();
                s.getTranscript().calculateGpa();
            }
            s.setSemester(s.getSemester() + 1);
            s.getStudentOutput().clear();
            s.getRequestedCourses().clear();
            s.getTranscript().getEnrolledCourses().clear();
        }
        requestCoursesForAllStudents();
        startRegistration();
        for (Student s : studentList) {
            createStudentOutput(s);
        }
        createDepartmentOutput();
    }

    //(Emre) Grade-RegSys connection
    public void createTranscript(Student student) {

        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToBePreviouslyTaken(student)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                Grade courseGrade = new Grade(mc, intRandomGrade);
                student.getTranscript().getTakenCourses().put(mc, courseGrade);
                student.getTranscript().isCourseCompletedOrFailed(mc, courseGrade.getLetter());
                /* log.info(student.getStudentName() + ": Student took " + mc.getCourseName() + "with a grade of "
                        + courseGrade.getLetterGrade() + "."); */
            }
        }
        /* log.info(student.getStudentName() + ": Student took all mandatory courses for his past semesters."); */

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
                student.getTranscript().getTakenCourses().put(nte, courseGrade);
                student.getTranscript().isCourseCompletedOrFailed(nte, courseGrade.getLetter());
                /* log.info(student.getStudentName() + ": Student took " + nte.getCourseName() + "with a grade of "
                        + courseGrade.getLetterGrade() + "."); */
            }
        }
        /* log.info(
                student.getStudentName() + ": Student took all non technical elective courses for his past semesters."); */
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
                        : (intRandomGrade < 30 ? randomGrade.nextInt(89) : intRandomGrade));
                Grade courseGrade = new Grade(fte, intRandomGrade);
                student.getTranscript().getTakenCourses().put(fte, courseGrade);
                student.getTranscript().isCourseCompletedOrFailed(fte, courseGrade.getLetter());
                /* log.info(student.getStudentName() + ": Student took " + fte.getCourseName() + "with a grade of "
                        + courseGrade.getLetterGrade() + "."); */
            }
        }
        /* log.info(
                student.getStudentName()
                        + ": Student took all faculty technical elective courses for his past semesters."); */
        i = 1;
        student.getTranscript().calculateCompleteCredit();
        TechnicalElective te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
        while (i < student.getSemester()) {
            if (te.semesterCheck(i) && te.checkRequiredCredit(student)) {
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
                        student.getTranscript().getTakenCourses().put(te, courseGrade);
                        student.getTranscript().isCourseCompletedOrFailed(te, courseGrade.getLetter());
                        /* log.info(student.getStudentName() + ": Student took " + te.getCourseName() + "with a grade of "
                                + courseGrade.getLetterGrade() + "."); */
                        teCount++;
                    }
                }
            }
            /* log.info(
                    student.getStudentName() + ": Student took all technical elective courses for his past semesters."); */
            i++;
        }
        student.getTranscript().calculateCompleteCredit();
        for (GraduationProject gp : graduationCourses) {
            if (gp.getSemester() < student.getSemester()
                    && gp.checkRequiredCredit(student)) {
                Random randomGrade = new Random();
                int intRandomGrade = randomGrade.nextInt(100);
                //Deneme grade'i
                intRandomGrade = ((intRandomGrade >= 90) ? (randomGrade.nextInt(100 - 85) + 85)
                        : (intRandomGrade < 30 ? randomGrade.nextInt(45) : intRandomGrade));
                Grade courseGrade = new Grade(gp, intRandomGrade);
                student.getTranscript().getTakenCourses().put(gp, courseGrade);
                student.getTranscript().isCourseCompletedOrFailed(gp, courseGrade.getLetter());
                /* log.info(student.getStudentName() + ": Student took " + gp.getCourseName() + "with a grade of "
                        + courseGrade.getLetterGrade() + "."); */
            }
        }
        /* log.info(
                student.getStudentName() + ": Student took all graduation projects for his past semesters."); */
        log.info(
                student.getStudentName() + ": Student took all courses for his/her past semesters.");
        student.getTranscript().calculateCompleteCredit();
        student.getTranscript().calculateGpa();
    }

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
                    /* log.info(s.getStudentName() + ": Student requested mandatory course " + mc.getCourseName()
                            + " from his/her current semester curriculum."); */
                }
            }
            log.info(s.getStudentName() + ": Student requested all mandatory courses for his/her semester.");

            /* Adding the failed courses to the getRequestedCourse Arraylist by checking the semester */
            for (Course c : s.getTranscript().getFailedCourses()) {
                if (c instanceof MandatoryCourse) {
                    if (currentSemester.equals("fall")) {
                        if (((MandatoryCourse) c).getSemester() % 2 != 0) {
                            if (s.getTranscript().getCompletedCourses().contains(c)) {
                                continue;
                            } else {
                                s.getRequestedCourses().add(c);
                                /* log.info(s.getStudentName() + ": Student requested failed mandatory course "
                                        + c.getCourseName()
                                        + " in fall semester."); */
                            }
                        }
                    } else if (currentSemester.equals("spring")) {
                        if (((MandatoryCourse) c).getSemester() % 2 == 0) {
                            s.getRequestedCourses().add(c);
                            /* log.info(s.getStudentName() + ": Student requested failed mandatory course "
                                    + c.getCourseName()
                                    + " in spring semester."); */
                        }
                    }
                }

                if (c instanceof ElectiveCourse) {
                    s.getRequestedCourses().add(c);
                    /* log.info(s.getStudentName() + ": Student requested failed elective course "
                            + c.getCourseName()
                            + " in this semester."); */
                }
            }
            log.info(s.getStudentName() + ": Student requested all failed courses this semester(if it is open).");

            for (GraduationProject gp : graduationCourses) {
                if (s.getRequestedCourses().contains(gp)) {
                    continue;
                }
                if (gp.isEligibleToRequest(s) && gp.checkRequiredCredit(s)) {
                    s.getRequestedCourses().add(gp);
                    /* log.info(s.getStudentName() + ": Student requested graduation project "
                            + gp.getCourseName()
                            + " in this semester."); */
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
                if (te.isEligibleToRequest(s) && te.checkRequiredCredit(s)) {
                    s.getRequestedCourses().add(te);
                    /* log.info(s.getStudentName() + ": Student requested technical elective course "
                            + te.getCourseName()
                            + " in this semester."); */
                }
            }
            log.info(s.getStudentName() + ": Student requested " + ((s.getSemester() == 7) ? "2"
                    : ((s.getSemester() == 8) ? "3" : "0")) + " technical courses this semester.");

            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            while (s.getTranscript().getCompletedCourses().contains(nte)) {
                nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            }
            if (nte.isEligibleToRequest(s)) {
                s.getRequestedCourses().add(nte);
                /* log.info(s.getStudentName() + ": Student requested nontechnical elective course "
                        + nte.getCourseName()
                        + " in this semester."); */
            }

            FacultyTechnicalElective fte = facultyTechnicalElectives
                    .get(new Random().nextInt(facultyTechnicalElectives.size()));
            while (s.getTranscript().getCompletedCourses().contains(fte)) {
                fte = facultyTechnicalElectives.get(new Random().nextInt(facultyTechnicalElectives.size()));
            }
            if (fte.isEligibleToRequest(s)) {
                s.getRequestedCourses().add(fte);
                /* log.info(s.getStudentName() + ": Student requested faculty technical elective course "
                        + fte.getCourseName()
                        + " in this semester."); */
            }
        }
    }

    public void startRegistration() {
        for (Student s : studentList) {
            s.getAdvisor().completeRegistration(s);
        }
        log.info("All students have completed their course registrations.");
    }

    public void createStudentOutput(Student student) {
        LinkedHashMap<String, Object> jsonObject = new LinkedHashMap<String, Object>();
        //Add student info
        jsonObject.put("StudentName", student.toString());
        jsonObject.put("StudentId", student.getStudentId().toString());
        jsonObject.put("StudentEmail", student.getEmail());
        jsonObject.put("StudentPhoneNumber", student.getPhoneNumber());
        jsonObject.put("SemesterNumber", student.getSemester());
        jsonObject.put("CompletedCredits", student.getTranscript().getCreditCompleted());
        jsonObject.put("Gpa", student.getTranscript().getGpa());
        jsonObject.put("AdvisorName", student.getAdvisor().toString());

        //Add taken courses
        JSONArray jsonObjectPastCourses = new JSONArray();
        for (Map.Entry<Course, Grade> set : student.getTranscript().getTakenCourses().entrySet()) {
            JSONObject jsonObjectTakenCourse = new JSONObject();
            jsonObjectTakenCourse.put("LetterGrade", set.getValue().getLetter());
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

        /* JSONObject json = new JSONObject(); */
        try {

            new File("Output").mkdirs();
            new File("Output\\Students").mkdirs();

            FileWriter file = new FileWriter(
                    "Output\\Students\\" + (String) (student.getStudentId().toString()) + ".json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String formattedJson = mapper.writeValueAsString(jsonObject);
            file.write(formattedJson);

            /* file.write(json.toJSONString(jsonObject)); */
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("Student transcript creation failed.");
            e.printStackTrace();
        }
    }

    public void createDepartmentOutput() {
        // JSON FOR DEPERTMENT
        LinkedHashMap<String, Object> jsonDepertment = new LinkedHashMap<String, Object>();

        for (Course course : coursesList) {
            if (course instanceof MandatoryCourse) {
                if (this.getCurrentSemester().equals("fall") && ((MandatoryCourse) course).getSemester() % 2 != 0) {
                    JSONArray jsonArrayDepertment = new JSONArray();
                    /* ArrayList<String> jsonArrayDepertment = new ArrayList<String>(); */
                    jsonArrayDepertment.add(course.getStudents().size() + " Students could register for " +
                            course.getCourseCode() + " successfully.");
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
                    /* ArrayList<String> jsonArrayDepertment = new ArrayList<String>(); */
                    jsonArrayDepertment.add(course.getStudents().size() + " Students could register for " +
                            course.getCourseCode() + " successfully.");
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
                /* ArrayList<String> jsonArrayDepertment = new ArrayList<String>(); */
                jsonArrayDepertment.add(course.getStudents().size() + " Students could register for " +
                        course.getCourseCode() + " successfully.");
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

        try {
            FileWriter file2 = new FileWriter(
                    "Output\\" + "DEPARTMENT_OUTPUT_" + this.getCurrentSemester().toUpperCase() + ".json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String formattedJson = mapper.writeValueAsString(jsonDepertment);
            file2.write(formattedJson);

            /* file2.write(JSONObject.toJSONString(jsonDepertment)); */
            file2.close();
            log.info("Department output successfully created.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("Department output creation failed.");
            e.printStackTrace();
        }
    }

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
            System.out.printf("%s , %s , %d yılında üniversiteye girdi, %d. Semester", s.getStudentName(),
                    s.getStudentId(),
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
            for (Map.Entry<Course, String> set : s.getTranscript().getTakenCourses().entrySet()) {
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
        /* for (Map.Entry<Course, String> set : studentList.get(studentList.size() - 1).getTranscript().getTakenCourses()
                .entrySet()) {
            System.out.println(set.getKey().getCourseName() + " " + set.getValue());
        }
        
        for (Course c : studentList.get(studentList.size() - 1).getTranscript().getEnrolledCourses()) {
            System.out.print(c.getCourseName() + "---");
        }
        */
        /* 
        for (Student s : studentList) {
            System.out.println(s.getStudentName() + " StudentId: " + s.getStudentId() + " Semester: " + s.getSemester()
                    + " TotalCredits: "
                    + s.getTranscript().getCreditCompleted());
            //for (Map.Entry<Course, String> set : s.getTranscript().getTakenCourses()
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
        } */
    }
}
