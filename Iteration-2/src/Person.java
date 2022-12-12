import org.apache.log4j.Logger;

public abstract class Person {
    static Logger log = Logger.getLogger(Person.class);
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
        userName = userName.toLowerCase();
        userName = userName.replace('ç', 'c');
        userName = userName.replace('ğ', 'g');
        userName = userName.replace('ı', 'i');
        userName = userName.replace('ö', 'o');
        userName = userName.replace('ş', 's');
        userName = userName.replace('ü', 'u');
        userName = userName.replace('ğ', 'g');
        userName = userName.replace('ğ', 'g');
        userName = userName.replaceAll("\\s", "");
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

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        /* log.info(this.toString() + ": Person first name changed."); */
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        /* log.info(this.toString() + ": Person last name changed."); */
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        /* log.info(this.toString() + ": Person email changed."); */
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        /* log.info(this.toString() + ": Person phone number changed."); */
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
