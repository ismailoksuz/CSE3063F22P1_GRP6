from typing import List
import json
from ElectiveCourse import ElectiveCourse
from student import Student
from course import Course

class TechnicalElective(ElectiveCourse): # , ICreditRequirement will be added
    allTechnicalElectives = []
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str, quota: int, semester: List[int], requiredCredits: int, prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester)
        self.__requiredCredits = requiredCredits
        self.__prerequisites = prerequisites

        TechnicalElective.allTechnicalElectives.append(self)



    @classmethod
    def read_from_json(cls):
        try:
            # Opening JSON file
            input = open('input.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)
            requiredCredits = data["TERequiredCredits"]
            for i in data['technicalElectiveCourses']:
                TechnicalElective(
                    courseName=i["courseName"],
                    courseCode=i["courseCode"],
                    quota=i["quota"],
                    semester=i["semester"],
                    courseCredit=i["credits"],
                    courseDay=i["courseDay"],
                    courseHour=i["courseHour"],
                    requiredCredits= requiredCredits,
                    prerequisites = i["preRequisites"]

                )

                # close file
                input.close()
        except IOError:
            # Will be log
            exit(1)


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
