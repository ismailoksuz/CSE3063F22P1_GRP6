import java.util.ArrayList;

public class Course {
	private String courseCode;
	private String courseName;
	private int courseCredit;
	private int semester;
	private int quota;
	private int section;
	private ArrayList<Course> prerequisites;
	private Instructor courseInstructor;
	private ArrayList<Student> studentsEnrolledCourse;
	private Schedule courseSchedule;
	private ArrayList<Student> studentsFailedPreq;
	private ArrayList<Student> studentsFailedCredits;
	private ArrayList<Student> studentsQuotaProblem;
	private ArrayList<Student> studentsCollisionProblem;

	public Course(String courseCode, int courseCredit, int semester, int quota,
			ArrayList<Course> prerequisites,
			Schedule courseSchedule) {

		this.courseCode = courseCode;
		this.courseCredit = courseCredit;
		this.semester = semester;
		this.quota = quota;
		this.prerequisites = prerequisites;
		this.courseSchedule = courseSchedule;
		this.studentsEnrolledCourse = new ArrayList<Student>();
		this.studentsFailedPreq = new ArrayList<Student>();
		this.studentsFailedCredits = new ArrayList<Student>();
		this.studentsQuotaProblem = new ArrayList<Student>();
		this.studentsCollisionProblem = new ArrayList<Student>();
	}

	public boolean checkQuotaForRegistration() {
		System.out.println("Checking course quota for registration...");
		if (this.studentsEnrolledCourse.size() < this.quota) {
			return true;
		}
		System.out.println("Quota is full for " + this.courseCode + "(" + this.courseName + ")");
		return false;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(ArrayList<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Instructor getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(Instructor courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public ArrayList<Student> getStudentsEnrolledCourse() {
		return studentsEnrolledCourse;
	}

	public void setStudentsEnrolledCourse(ArrayList<Student> studentsEnrolledCourse) {
		this.studentsEnrolledCourse = studentsEnrolledCourse;
	}

	public Schedule getCourseSchedule() {
		return courseSchedule;
	}

	public void setCourseSchedule(Schedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	public ArrayList<Student> getStudentsFailedPreq() {
		return studentsFailedPreq;
	}

	public void setStudentsFailedPreq(ArrayList<Student> studentsFailedPreq) {
		this.studentsFailedPreq = studentsFailedPreq;
	}

	public ArrayList<Student> getStudentsFailedCredits() {
		return studentsFailedCredits;
	}

	public void setStudentsFailedCredits(ArrayList<Student> studentsFailedCredits) {
		this.studentsFailedCredits = studentsFailedCredits;
	}

	public ArrayList<Student> getStudentsQuotaProblem() {
		return studentsQuotaProblem;
	}

	public void setStudentsQuotaProblem(ArrayList<Student> studentsQuotaProblem) {
		this.studentsQuotaProblem = studentsQuotaProblem;
	}

	public ArrayList<Student> getStudentsCollisionProblem() {
		return studentsCollisionProblem;
	}

	public void setStudentsCollisionProblem(ArrayList<Student> studentsCollisionProblem) {
		this.studentsCollisionProblem = studentsCollisionProblem;
	}

	public String toString() {
		return "CourseCode: " + getCourseCode() + "  CourseCredit: " + getCourseCredit() + " Semester: "
				+ getSemester() + " Quota: "
				+ getQuota() + " Schedule: "
				+ getCourseSchedule().toString();
	}
}
