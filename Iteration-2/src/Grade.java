import java.util.*;
import org.apache.log4j.Logger;

public class Grade {
	private Logger log = Logger.getLogger(Grade.class);
	private Course course;
	private int courseGrade;
	private String letter;

	public Grade(Course course, int courseGrade) {
		this.course = course;
		this.courseGrade = courseGrade;
		this.letter = getLetterGrade();
		log.info("Grade created.");
	}

	public String getLetterGrade() {
		String letterGrade = "";
		Random random = new Random();
		if (this.courseGrade > 100 || this.courseGrade < 0) {
			System.exit(1);
		} else if (this.courseGrade >= 90) {
			letterGrade = "AA";
		} else if (this.courseGrade >= 85) {
			letterGrade = "BA";
		} else if (this.courseGrade >= 75) {
			letterGrade = "BB";
		} else if (this.courseGrade >= 65) {
			letterGrade = "CB";
		} else if (this.courseGrade >= 55) {
			letterGrade = "CC";
		} else if (this.courseGrade >= 45) {
			letterGrade = "DC";
		} else if (this.courseGrade >= 35) {
			letterGrade = "DD";
		} else if (this.courseGrade >= 30) {
			letterGrade = "FD";
		} else {
			boolean isAttend = (random.nextInt(7) == 0) ? false : true;
			boolean isEnteredTheFinal = (random.nextInt(7) == 0) ? false : true;
			letterGrade = isAttend ? (isEnteredTheFinal ? "FF" : "FG") : "DZ";
		}
		/* log.info("Grade converted to letter."); */
		return letterGrade;

	}

	// GETTER & SETTER
	public Course getCourse() {
		return this.course;
	}

	public String getLetter() {
		return this.letter;
	}
}
