import org.apache.log4j.Logger;

public class StudentId {
	private Logger log = Logger.getLogger(StudentId.class);
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
		return this.getCseCode() + Integer.toString(this.getYear()).substring(2) + "0"
				+ ((Integer.toString(this.getOrder()).length() == 2)
						? (Integer.toString(this.getOrder()))
						: ("0" + Integer.toString(this.getOrder())));
	}

	// GETTER & SETTER
	public String getCseCode() {
		return cseCode;
	}

	public int getYear() {
		return this.year;
	}

	public int getOrder() {
		return this.order;
	}

	@Override
	public String toString() {
		return id;
	}
}
