#  import logging
from typing import List
from mandatoryCourse import MandatoryCourse
import json

class GraduationProject(MandatoryCourse): # ICreditRequirement will be added
    allGraduationProject = []
    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour,
                 quota, semester, prerequisites, requiredCredits):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester, prerequisites)
        self.__requiredCredits = requiredCredits
        #  logging.info(f"{self.courseName} ({self.courseCode}) named graduation project created.")
        GraduationProject.allGraduationProject.append(self)



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

    @classmethod
    def read_from_json(cls):
        try:
            # Opening JSON file
            input = open('input.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)

            for i in data['graduationProject']:
                GraduationProject(
                    courseName=i["courseName"],
                    courseCode=i["courseCode"],
                    semester=i["semester"],
                    courseCredit=i["credits"],
                    courseDay=i["courseDay"],
                    courseHour=i["courseHour"],
                    prerequisites=i["preRequisites"],
                    requiredCredits=i["requiredCredits"],
                    quota=i["quota"]

                )

                # close file
                input.close()
        except IOError:
            # Will be log
            exit(1)
