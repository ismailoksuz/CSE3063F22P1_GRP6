
from typing import List

class MandatoryCourse(Course):
    def __init__(self, course_name: str, course_code: str, course_credit: int, course_day: int, course_hour: str,
                 quota: int, semester: int, prerequisites: List[Course]):
        super().__init__(course_name, course_code, course_credit, course_day, course_hour, quota)
        self.__semester = semester
        self.__prerequisites = prerequisites
    
    def isEligibleToRequest(self, student):
        if (student.getSemester() == self.getSemester()):
            if (not student.getTranscript().hasBeenPassedCourses(self.getPrequisites())):
                self.setFailedPreq(self.getFailedPreq() + 1)
                student.getStudentOutput().add("The system didn't allow " + self.getCourseCode() + 
                "because student failed prereq." + self.getPrequisites()[0].getCourseCode())
                return False
            else:
                return True
        else:
            return False

    def isEligibleToBePreviouslyTaken(self, student):
        return student.getSemester() > self.getSemester() and student.getTranscript().hasBeenPassedCourses(self.getPrequisites())

    # GETTERS AND SETTERS
    def getSemester(self) -> int:
        return self.semester

    def getPrequisites(self) -> List[Course]:
        return self.prerequisites
