
public class StudentId {
	private final String cseCode = "1501";
	private int year;
	private int order;
	private String id;

	public StudentId(int year, int order) {
		this.year = year;
		this.order = order;
		this.id = createStudentId();
	}

	public String createStudentId() {
		return this.cseCode + Integer.toString(this.year).substring(2) + Integer.toString(this.order);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String toString() {
		return id;
	}
}
