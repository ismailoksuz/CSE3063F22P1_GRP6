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
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }

            MandatoryCourse mandatoryCourse = new MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisitesCourse);

            coursesList.add(mandatoryCourse);
            mandotoryCourses.add(mandatoryCourse);

        }

    }

    public void readNTE(JSONObject input) {
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

    public void readTE(JSONObject input) {
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

            JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Integer> semesterList = new ArrayList<Integer>();
            for (Object s : semesters) {
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
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            }
            ;
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
    }

    public void readInput() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject input = (JSONObject) parser.parse(new FileReader("input.json"));

            readMandotory(input);
            readGraduationProject(input);
            readNTE(input);
            readTE(input);
            readFTE(input);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void readStudent() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject student = (JSONObject) parser.parse(new FileReader("students.json"));
            readStudentInput(student);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void readAdvisors() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject advisor = (JSONObject) parser.parse(new FileReader("advisor.json"));
            readAdvisorInput(advisor);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void createTranscript(Student student) {
        String[] letterGrades = { "AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "FG", "DZ" };

        for (MandatoryCourse mc : mandotoryCourses) {
            if (mc.isEligibleToBePreviouslyTaken(student)) {
                String letter = letterGrades[new Random().nextInt(letterGrades.length - 3)];
                student.getTranscript().getTakenCouerses().put(mc, letter);
                student.getTranscript().isCourseCompletedOrFailed(mc, letter);
            }
        }

        for (int i = 1; i < student.getSemester(); i++) {
            NonTechnicalElective nte = nonTechnicalElectives.get(new Random().nextInt(nonTechnicalElectives.size()));
            if (nte.semesterCheck(i)) {
                String letter = letterGrades[new Random().nextInt(letterGrades.length - 3)];
                student.getTranscript().getTakenCouerses().put(nte, letter);
                student.getTranscript().isCourseCompletedOrFailed(nte, letter);
            }
        }
        student.getTranscript().calculateCompleteCredit();
    }

    public void requestCoursesForAllStudents() {
        ArrayList<MandatoryCourse> currentSemesterMandatoryCourses = new ArrayList<MandatoryCourse>();
        for (MandatoryCourse c : mandotoryCourses) {
            if (c.getSemester() % 2 != 0) {
                currentSemesterMandatoryCourses.add(c);
            }
        }
        for (Student s : studentList) {
            for (MandatoryCourse mc : currentSemesterMandatoryCourses) {
                if (mc.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(mc);
                }
            }
            /* for (Course c : s.getTranscript().getFailedCourses()) {
                if (c.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(c);
                }
            } */
            for (Course c : s.getTranscript().getFailedCourses()) {
                if (c instanceof MandatoryCourse) {
                    if (((MandatoryCourse) c).getSemester() % 2 != 0) {
                        s.getRequestedCourses().add(c);
                    }
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
            /****************************************************************** *******************************************************************/
            /* 
            if (s.getSemester() == 7) {
                TechnicalElective te = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
                if (te.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(te);
                }
            } else if (s.getSemester() == 8) {
                TechnicalElective te2 = technicalElectives.get(new Random().nextInt(technicalElectives.size()));
                int i = 0;
            
                while (i < 3) {
                    if (s.getRequestedCourses().contains(te2)) {
                        i++;
                        continue;
                    }
                    if (te2.isEligibleToRequest(s)) {
                        s.getRequestedCourses().add(te2);
                    }
                }
            } */
            int i = 0;
            int limit = (s.getSemester() == 7) ? 1 : 3;
            for (TechnicalElective te : technicalElectives) {
                if (s.getRequestedCourses().contains(te)) {
                    i++;
                    continue;
                }
                if (limit == i) {
                    break;
                }
                if (te.isEligibleToRequest(s)) {
                    s.getRequestedCourses().add(te);
                }
            }
            /****************************************************************************************************************** */
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
            }
        }
    }

    public void startRegistration() {
        for (Student s : studentList) {
            s.getAdvisor().completeRegistration(s);
        }
    }

    public void createStudentOutput(Student student) {
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
    }

    public void createDepartmentOutput() {
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
    }

    public void startSimulation() {
        readAdvisors();
        readInput();
        readStudent();
        assignAdvisor(studentList);
        assignInstructor(coursesList);
        requestCoursesForAllStudents();
        startRegistration();
        for (int i = 0; i <= 10; i++) {
            createStudentOutput(studentList.get(new Random().nextInt(studentList.size())));
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

        /*  for (Student s : studentList) {
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
    }

}
