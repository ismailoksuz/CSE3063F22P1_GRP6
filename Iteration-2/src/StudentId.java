import org.apache.log4j.Logger;

public class StudentId {
	static Logger log = Logger.getLogger(StudentId.class);
	private String cseCode;
	private int year;
	private int order;
	private String id;

	public StudentId(int year, int order) {
		this.cseCode = "1501";
		this.year = year;
		this.order = order;
		this.id = createStudentId();
		log.info("Student ID created.");
	}

	public String createStudentId() {
		return this.cseCode + Integer.toString(this.year).substring(2) + "0"
				+ ((Integer.toString(this.order).length() == 2)
						? (Integer.toString(this.order))
						: ("0" + Integer.toString(this.order)));
	}

	public String getCseCode() {
		return cseCode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
		/* log.info("Registration year for student ID changed."); */
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
		/* log.info("Registration order for student ID changed."); */
	}

	public String toString() {
		return id;
	}
}
