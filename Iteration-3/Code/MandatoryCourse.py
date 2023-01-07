from Course import Course
from typing import List


class MandatoryCourse(Course):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str,
                 quota: int, semester: int, prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota)
        self.__semester = semester
        self.__prerequisites = prerequisites

    def isEligibleToRequest(self, student):
        if (student.getSemester() == self.getSemester()):
            if (not student.getTranscript().hasBeenPassedCourses(self.getPrerequisites())):
                self.setFailedPreq(self.getFailedPreq() + 1)
                # student.getStudentOutput().append("The system didn't allow " + self.getCourseCode() + "because student failed prereq." + self.getPrequisites()[0].getCourseCode())
                student.getStudentOutput().append("The system didn't allow " + self.getCourseCode() + " because student failed prereq.")
                return False
            else:
                return True
        else:
            return False

    def isEligibleToBePreviouslyTaken(self, student):
        if student.getSemester() > self.getSemester():
            if student.getTranscript().hasBeenPassedCourses(self.getPrerequisites()):
                return True
            else:
                return False
        else:
            return False
        # return (student.getSemester() > self.getSemester()) and (student.getTranscript().hasBeenPassedCourses(self.getPrerequisites()))

    # GETTERS AND SETTERS
    def getSemester(self) -> int:
        return self.__semester

    def getPrerequisites(self) -> List[Course]:
        return self.__prerequisites