import java.util.*;
public class Grade {

    private Course course;
    private int courseGrade;
    
    public Grade(Course course, int courseGrade) {
        this.course = course;
        this.courseGrade = courseGrade;
    }

    public String getLetterGrade(){
	    String letterGrade="";
	    Random random = new Random();
	    if(courseGrade>100 || courseGrade<0){
	        System.out.println("The value for grade is invalid.");
	        System.exit(1);
		}
		else if(courseGrade>=90){
		    letterGrade="AA";
		}
		else if(courseGrade>=85){
		    letterGrade="BA";
		}
		else if(courseGrade>=75){
			letterGrade="BB";
		}
		else if(courseGrade>=65){
			letterGrade="CB";
		}
		else if(courseGrade>=55){
			letterGrade="CC";
		}
		else if(courseGrade>=45){
			letterGrade="DC";
		}
		else if(courseGrade>=35){
			letterGrade="DD";
		}
		else if(courseGrade>=30){
			letterGrade="FD";
		}
		else{
		    boolean isAttend = (random.nextInt(7) == 0) ? false : true;
			boolean isEnteredTheFinal = (random.nextInt(7) == 0) ? false : true;
			letterGrade=isAttend ? (isEnteredTheFinal ? "FF" : "FG") : "DZ";
		}
	    return letterGrade;
	    
	}

    public int getCourseGrade() {
        return courseGrade;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
