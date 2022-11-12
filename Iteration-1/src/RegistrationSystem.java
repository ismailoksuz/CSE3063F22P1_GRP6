import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Registration {

    private ArrayList<Course> coursesList = new ArrayList<>();
    private ArrayList<MandatoryCourse> mandotoryCourses = new ArrayList<>();
    private ArrayList<GraduationProject> graduationCourses = new ArrayList<>();
    private ArrayList<TechnicalElective> technicalElectives = new ArrayList<>();
    private ArrayList<FacultyTechnicalElective> facultyTechnicalElectives = new ArrayList<>();
    private ArrayList<NonTechnicalElective> nonTechnicalElectives = new ArrayList<>();

    private Course courseIsThereOrNot(String courseCode){
        for (Course c: coursesList){
            if (c.getCourseCode().equals(courseCode)){
                return c;
            }
        }
        return null;
    }

    private void readMandotory(JSONObject input){
            JSONArray inputCourses = (JSONArray) input.get("MandatoryCourses");
            for (Object c : inputCourses) {
                JSONObject course = (JSONObject) c;
                String courseName = (String) course.get("courseName");
                String courseCode = (String) course.get("courseCode");
                int courseQuato= (int) (long) course.get("quota");
                int courseSemester = (int) (long) course.get("semester");
                int courseCredit = (int) (long) course.get("credits");
                int courseDay = (int) (long) course.get("courseDay");
                String courseHour = (String) course.get("courseHour");

                ArrayList<Course> prequisitesCourse = new ArrayList<>();
                JSONArray prequisites = (JSONArray) course.get("preRequisites");
                for (Object p: prequisites){
                    prequisitesCourse.add(courseIsThereOrNot((String) p));
                }
                MandatoryCourse mandatoryCourse = new MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                        courseHour, courseSemester, courseQuato,prequisitesCourse);

                coursesList.add(mandatoryCourse);
                mandotoryCourses.add(mandatoryCourse);

            }

    }

    private void readNTE(JSONObject input){
        JSONArray inputNTECourses = (JSONArray) input.get("nonTechnicalElectiveCourses");
        for (Object c : inputNTECourses){
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato= (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("coursHour");
            JSONArray semesters = (JSONArray) course.get("semester");

            NonTechnicalElective nonTechnicalElective = new NonTechnicalElective(courseName, courseCode, courseCredit,
                    courseDay, courseHour, courseQuato, semesters);
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
            TechnicalElective techElectiveCourse = new TechnicalElective(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, semesters, requiredCredit, prequisites);

            coursesList.add(techElectiveCourse);
            technicalElectives.add(techElectiveCourse);


        }
    }



    private void readFTE(JSONObject input){
        JSONArray inputNTECourses = (JSONArray) input.get("facultyTechnicalElectiveCourses");
        for (Object c : inputNTECourses){
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato= (int) (long) course.get("quota");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");

            JSONArray semesters = (JSONArray) course.get("semester");
            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p: prequisites){
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            };

            FacultyTechnicalElective facultyTechnicalElective = new FacultyTechnicalElective(courseName, courseCode,
                    courseCredit, courseDay, courseHour, courseQuato, semesters, prequisitesCourse);
            facultyTechnicalElectives.add(facultyTechnicalElective);
            coursesList.add(facultyTechnicalElective);


        }
    }

    private void readGraduationProject(JSONObject input){
        JSONArray inputNTECourses = (JSONArray) input.get("graduationProject");
        for (Object c : inputNTECourses){
            JSONObject course = (JSONObject) c;
            String courseName = (String) course.get("courseName");
            String courseCode = (String) course.get("courseCode");
            int courseQuato= (int) (long) course.get("quota");
            int courseSemester = (int) (long) course.get("semester");
            int courseCredit = (int) (long) course.get("credits");
            int courseDay = (int) (long) course.get("courseDay");
            int requiredCredit = (int) (long) course.get("requiredCredits");
            String courseHour = (String) course.get("courseHour");

            ArrayList<Course> prequisitesCourse = new ArrayList<>();
            JSONArray prequisites = (JSONArray) course.get("preRequisites");
            for (Object p: prequisites){
                prequisitesCourse.add(courseIsThereOrNot((String) p));
            };
            GraduationProject graduationProject = new GraduationProject(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisites, requiredCredit);

            coursesList.add(graduationProject);
            graduationCourses.add(graduationProject);


        }
    }

    private void readInput(){
        try {
            JSONParser parser = new JSONParser();
            JSONObject input = (JSONObject) parser.parse(new FileReader("input.json"));

            readMandotory(input);
            readGraduationProject(input);
            readTE(input);
            readFTE(input);
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    public void startSimulation(){
        readInput();
        for (Course o: mandotoryCourses){
            System.out.println(o.getCourseName().toString());
            //System.out.println(o.getCourseHour().toString());

        }

        for (GraduationProject c: graduationCourses){
            System.out.println(c.getCourseName());


        }

        for (TechnicalElective t: technicalElectives){
            System.out.println(t.getCourseName());
            System.out.println(t.getSemester());
        }

        for (FacultyTechnicalElective f: facultyTechnicalElectives){
            System.out.println(f.getCourseName());
            //System.out.println(f.getSemester());
        }

        for (NonTechnicalElective n: nonTechnicalElectives){
            System.out.println(n.getCourseName());
        }


    }




    }

