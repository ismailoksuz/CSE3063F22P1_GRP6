from typing import List

class TechnicalElective(ElectiveCourse, ICreditRequirement):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str, quato: int, semester: List[int], requiredCredits: int, prequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester)
        self.__requiredCredits = requiredCredits
        self.__prequisites = prequisites

    def isEligibleToRequest(self, student: Student) -> bool:
        if self.semesterControl(student):
            if not student.getTranscript().hasBeenPassedCourses(self.getPrequisites()):
                self.setFailedPreq(self.getFailedPreq() + 1)
                student.getStudentOutput().add(
                    f"The system didn't allow {self.getCourseCode()} because student "
                    f"failed prereq. {self.getPrequisites()[0].getCourseCode()}")
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
            student.getStudentOutput.add(f"The advisor didn't approve TE{self.getCourseCode()} because student "
                                         f"completed credits < {self.getRequiredCredits()}")
            return False

    def getRequiredCredits(self) -> int:
        return self.__requiredCredits

    def setRequiredCredits(self, requiredCredits: int):
        self.__requiredCredits = requiredCredits

    def getPrequisites(self) -> List[Course]:
        return self.__prequisites

    def setPrequisites(self, prequisites: List[Course]):
        self.__prequisites = prequisites
