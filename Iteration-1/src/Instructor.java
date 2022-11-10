ArrayList<Course> givenCourses = new ArrayList<Course>();
Schedule instructorSchedule = new Schedule();

public Instructor() {
}

public Instructor(String firstName, String lastName, String email, String phoneNumber, ArrayList givenCourses, Schedule instructorSchedule) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.givenCourses = givenCourses;
    this.instructorSchedule = instructorSchedule;
}

public ArrayList<Course> getGivenCourses() {
    return givenCourses;
}

public void setGivenCourses(ArrayList<Course> givenCourses) {
    this.givenCourses = givenCourses;
}

public Schedule getInstructorSchedule() {
    return instructorSchedule;
}
public void setInstructorSchedule(Schedule instructorSchedule) {
    this.instructorSchedule = instructorSchedule;
}






