import logging
from ICreditRequirement import ICreditRequirement
from MandatoryCourse import MandatoryCourse


class GraduationProject(MandatoryCourse, ICreditRequirement):

    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour,
                 quota, semester, prerequisites, requiredCredits):
        super().__init__(courseName, courseCode, courseCredit,
                         courseDay, courseHour, quota, semester, prerequisites)
        self.__requiredCredits = requiredCredits
        logging.info(
            f"{self.getCourseName()} ({self.getCourseCode()}) named graduation project created.")

    def isEligibleToRequest(self, student):
        return super().isEligibleToRequest(student) and self.checkRequiredCredit(student)

    def checkRequiredCredit(self, student):
        if student.getTranscript().getCreditCompleted() >= self.__requiredCredits:
            return True
        else:
            newFailedCredit = self.getFailedCredits() + 1
            self.setFailedCredits(newFailedCredit)
            student.getStudentOutput().append(
                f"The advisor didn't approve graduation project {self.getCourseCode()} because student completed credits < {self.__requiredCredits}")
            logging.info(
                f"Student has not enough credits. {student.getStudentName()} cannot enroll {self.getCourseName()}.")
            return False

    def getRequiredCredits(self):
        return self.__requiredCredits