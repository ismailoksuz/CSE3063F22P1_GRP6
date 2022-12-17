import org.apache.log4j.Logger;

public abstract class Person {
    private Logger log = Logger.getLogger(Person.class);
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = generateEmail();
        this.phoneNumber = generatePhoneNumber();
        log.info(this.toString() + " named person created.");
    }

    public String generateEmail() {
        String userName = getFirstName() + getLastName();
        userName = userName.replaceAll("\\s", "");
        userName = userName.toLowerCase();
        userName = userName.replace('ç', 'c');
        userName = userName.replace('ğ', 'g');
        userName = userName.replace('ı', 'i');
        userName = userName.replace('ö', 'o');
        userName = userName.replace('ş', 's');
        userName = userName.replace('ü', 'u');
        /* log.info(this.toString() + ": email created."); */
        return userName + "@marun.edu.tr";
    }

    public String generatePhoneNumber() {
        String phone = "05";
        for (int i = 0; i < 9; i++) {
            phone += (int) (Math.random() * 10);
        }
        /* log.info(this.toString() + ": phone number created."); */
        return phone;
    }

    // GETTER & SETTER
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
