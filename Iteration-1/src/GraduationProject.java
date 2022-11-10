public class GraduationProject extends MandatoryCourse {
    public GraduationProject(){
    }
    public boolean isEligibleToRequest(Transcript transcript){
        transcript.creditCompleted=creditCompleted;
        return (creditCompleted>=160) ? true : false;
    } 
}
