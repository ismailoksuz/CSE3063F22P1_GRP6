import java.util.ArrayList;

public abstract class Course {
	private String courseCode;
	private String courseName;
	private int courseCredit;
	private String semester;
	private int quota;
	private int section;
	private ArrayList<Course> prequisties;
	private Instructor courseInstructor;
	private ArrayList<Student> students;
	private Schedule courseSchedule;
	private ArrayList<Student> studentsFailedPreq;
	private ArrayList<Student> studentsFailedCredits;
	private ArrayList<Student> studentsQuotaProblem;
	private ArrayList<Student> studentsCollisionProblem;
	
	public Course(String courseCode, String courseName, int courseCredit, String semester, int quota, int section,
		ArrayList<Course> prequisties, Instructor courseInstructor, ArrayList<Student> students, 
		Schedule courseSchedule) {
		
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.semester = semester;
		this.quota = quota;
		this.section = section;
		this.prequisties = prequisties;
		this.courseInstructor = courseInstructor;
		this.students = students;
		this.courseSchedule = courseSchedule;
		this.studentsFailedPreq = new ArrayList<Course>();
		this.studentsFailedCredits = new ArrayList<Course>();
		this.studentsQuotaProblem = new ArrayList<Course>();
		this.studentsCollisionProblem = new ArrayList<Course>();
		
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
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
	public ArrayList<Course> getPrequisties() {
		return prequisties;
	}
	public void setPrequisties(ArrayList<Course> prequisties) {
		this.prequisties = prequisties;
	}
	public Instructor getCourseInstructor() {
		return courseInstructor;
	}
	public void setCourseInstructor(Instructor courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
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
	
}
