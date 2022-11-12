import java.util.ArrayList;

public class Course {
	private String courseCode;
	private int courseCredit;
	private int semester;
	private int quota;
	private ArrayList<Course> prerequisites = new ArrayList<Course>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private Schedule courseSchedule;
	private int minCredit = 0;
	private int failedPreq = 0;
	private int quotaProblem = 0;
	private int failedCredits = 0;
	private int collisionProblem = 0;

	public Course(String courseCode, int courseCredit, int semester, int quota, ArrayList<Course> prerequisites,
			Schedule courseSchedule) {

		this.courseCode = courseCode;
		this.courseCredit = courseCredit;
		this.semester = semester;
		this.quota = quota;
		this.courseSchedule = courseSchedule;
	}

	public void registerStudent(Student student) {
		students.add(student);
	}

	public void addPrerequisite(Course course) {
		prerequisites.add(course);
	}

	public int getStudentNumber() {
		return students.size();
	}

	public int getCourseCredit() {
		return this.courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getQuota() {
		return this.quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getFailedPreq() {
		return this.failedPreq;
	}

	public void setFailedPreq(int failedPreq) {
		this.failedPreq = failedPreq;
	}

	public int getQuotaProblem() {
		return this.quotaProblem;
	}

	public void setQuotaProblem(int quotaProblem) {
		this.quotaProblem = quotaProblem;
	}

	public int getFailedCredits() {
		return this.failedCredits;
	}

	public void setFailedCredits(int failedCredits) {
		this.failedCredits = failedCredits;
	}

	public int getCollisionProblem() {
		return this.collisionProblem;
	}

	public void setCollisionProblem(int collisionProblem) {
		this.collisionProblem = collisionProblem;
	}

	public int getMinCredit() {
		return this.minCredit;
	}

	public void setMinCredit(int minCredit) {
		this.minCredit = minCredit;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getSemester() {
		return this.semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public ArrayList<Course> getPrerequisites() {
		return this.prerequisites;
	}

	public void setPrerequisites(ArrayList<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public Schedule getCourseSchedule() {
		return this.courseSchedule;
	}

	public void setCourseSchedule(Schedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	public String toString() {
		return "CourseCode: " + getCourseCode() + "  CourseCredit: " + getCourseCredit() + " Semester: "
				+ getSemester() + " Quota: "
				+ getQuota() + " Schedule: "
				+ getCourseSchedule().toString();
	}
}
