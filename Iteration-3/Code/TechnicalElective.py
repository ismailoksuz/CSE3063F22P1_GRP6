from Course import Course
from ElectiveCourse import ElectiveCourse
from ICreditRequirement import ICreditRequirement
from Student import Student
from typing import List


class TechnicalElective(ElectiveCourse, ICreditRequirement):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str, quota: int, semester: List[int], requiredCredits: int, prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit,
                         courseDay, courseHour, quota, semester)
        self.__requiredCredits = requiredCredits
        self.__prerequisites = prerequisites

    def isEligibleToRequest(self, student: Student) -> bool:
        if self.semesterControl(student):
            if not student.getTranscript().hasBeenPassedCourses(self.getPrerequisites()):
                self.setFailedPreq(self.getFailedPreq() + 1)
                student.getStudentOutput().append(
                    f"The system didn't allow {self.getCourseCode()} because student failed prereq. {self.getPrerequisites()[0].getCourseCode()}")
                return False
            else:
                return True
        else:
            return False

    def checkRequiredCredit(self, student: Student) -> bool:
        if student.getTranscript().getCreditCompleted() >= self.__requiredCredits:
            return True
        else:
            self.setFailedCredits(self.getFailedCredits() + 1)
            student.getStudentOutput().append(
                f"The advisor didn't approve TE{self.getCourseCode()} because student completed credits < {self.getRequiredCredits()}")
            return False

    def getRequiredCredits(self) -> int:
        return self.__requiredCredits

    def setRequiredCredits(self, requiredCredits: int):
        self.__requiredCredits = requiredCredits

    def getPrerequisites(self) -> List[Course]:
        return self.__prerequisites

    def setPrerequisites(self, prerequisites: List[Course]):
        self.__prerequisites = prerequisites