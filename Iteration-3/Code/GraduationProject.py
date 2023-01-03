from MandatoryCourse import MandatoryCourse
class GraduationProject(MandatoryCourse):  # ICreditRequirement will be added

    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour,
                 quota, semester, prerequisites, requiredCredits):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester, prerequisites)
        self.__requiredCredits = requiredCredits
        print(f"{self.getCourseName()} ({self.getCourseCode()}) named graduation project created.")
    #  logging.info(f"{self.courseName} ({self.courseCode}) named graduation project created.")

    def isEligibleToRequest(self, student):
        return super().isEligibleToRequest(student) and self.checkRequiredCredit(student)

    def checkRequiredCredit(self, student):
        if student.transcript.getCreditCompleted() >= self.__requiredCredits:
            return True
        else:
            newFailedCredit = self.getFailedCredits() + 1
            self.setFailedCredits(newFailedCredit)
            student.getStudentOutput().append(
                f"The advisor didn't approve graduation project {self.getCourseCode()} because student completed credits < {self.__requiredCredits}")
            #  logging.info(f"Student has not enough credits. {student.studentName} cannot enroll {self.courseName}.")
            return False


    def getRequiredCredits(self):
        return self.__requiredCredits
