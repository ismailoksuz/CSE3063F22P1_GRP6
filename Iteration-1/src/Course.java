import java.util.ArrayList;

public class Course {
	private String courseCode;
	private String courseName;
	private int courseCredit;
	private String semester;
	private int quota;
	private ArrayList<Course> prerequisites;
	private ArrayList<Student> students;
	private Schedule courseSchedule;
	private int studentsFailedPreq;
	private int studentsQuotaProblem;
	private int studentsFailedCredits;
	private int studentsCollisionProblem;

	public Course(String courseCode, String courseName, int courseCredit, String semester, int quota,
			Schedule courseSchedule) {

		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.semester = semester;
		this.quota = quota;
		this.courseSchedule = courseSchedule;

	}
}
