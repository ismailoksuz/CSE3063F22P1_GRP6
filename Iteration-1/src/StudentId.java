
public class StudentId {
	private int cseCode;
	private int year;
	private int order;

	public StudentId(int cseCode, int year, int order) {
		this.cseCode = cseCode;
		this.year = year;
		this.order = order;
	}

	public String createStudentId() {
		return Integer.toString(this.cseCode) + Integer.toString(this.year) + Integer.toString(this.order);
	}

	public int getCseCode() {
		return cseCode;
	}

	public void setCseCode(int cseCode) {
		this.cseCode = cseCode;
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
}
