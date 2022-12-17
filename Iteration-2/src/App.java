import java.util.Scanner;
import org.apache.log4j.Logger;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        RegistrationSystem rs = new RegistrationSystem();
        log.info("Registration for " + rs.getCurrentSemester() + " semester successfully completed.");

        if (rs.getCurrentSemester().equals("spring"))
            System.exit(0);

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to simulate for spring semester with same transcripts? Y/N");
        char userChoice = input.next().charAt(0);

        if (Character.toLowerCase(userChoice) == 'y') {
            rs.simulateSpringAfterFall();
            log.info("Registration for " + rs.getCurrentSemester() + " successfully completed.");
        }
        log.info("------END--------");
        input.close();
    }
}

// public class App {
// public static void main(String[] args) throws Exception {
// RegistrationSystem rs = new RegistrationSystem();
// /* Path currentRelativePath = Paths.get("");
// String s = currentRelativePath.toAbsolutePath().toString();
// System.out.println("Current absolute path is: " + s); */
// }
// }
