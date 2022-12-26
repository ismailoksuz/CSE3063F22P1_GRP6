#  import logging
from typing import List

class GraduationProject(MandatoryCourse, ICreditRequirement):
    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour,
                 quota, semester, prequisites, requiredCredits):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester, prequisites)
        self.requiredCredits = requiredCredits
      #  logging.info(f"{self.courseName} ({self.courseCode}) named graduation project created.")

    def isEligibleToRequest(self, student):
        return super().isEligibleToRequest(student) and self.checkRequiredCredit(student)

    def checkRequiredCredit(self, student):
        if student.transcript.CreditCompleted >= self.requiredCredits:
            return True
        else:
            self.FailedCredits += 1
            student.StudentOutput.append(f"The advisor didn't approve graduation project {self.courseCode} because student completed credits < {self.requiredCredits}")
          #  logging.info(f"Student has not enough credits. {student.studentName} cannot enroll {self.courseName}.")
            return False

    @property
    def requiredCredits(self):
        return self.requiredCredits
