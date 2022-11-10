public class GraduationProject extends MandatoryCourse {
    private String courseCode;  
    private String courseName;
    public GraduationProject(){

    }
    public boolean isEligibleToRequest(Transcript transcript){
        transcript.creditCompleted=creditCompleted;
        return (creditCompleted>=160) ? true : false;
    }
    public void setCourseCode(String courseCode){
        this.courseCode=courseCode;
    }
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }
    public String getCourseCode(){
        return this.courseCode;
    }
    public String getCourseName(){
        return this.courseName;        
    }   
    
}
