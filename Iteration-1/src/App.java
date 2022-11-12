public class App {
    public static void main(String[] args) throws Exception {
        RegistrationSystem rs = new RegistrationSystem();
        System.out.println("\n\n");

        for (Student s : rs.getStudentsList()) {
            System.out.println(s.toString());
        }

        for (Course c : rs.getCoursesList()) {
            System.out.println(c.toString());
        }
    }

}
