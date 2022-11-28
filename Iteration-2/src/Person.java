public abstract class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = generateEmail();
        this.phoneNumber = generatePhoneNumber();
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

        return userName + "@marun.edu.tr";
    }

    public String generatePhoneNumber() {
        String phone = "05";
        for (int i = 0; i < 9; i++) {
            phone += (int) (Math.random() * 10);
        }
        return phone;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {

        return firstName + " " + lastName;
    }
}
