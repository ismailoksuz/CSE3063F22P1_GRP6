#  import logging
from typing import List
from ElectiveCourse import ElectiveCourse
from student import Student
from course import Course
import json

class FacultyTechnicalElective(ElectiveCourse):
    allFacultyTechnicalElectives = []
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int], prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)
        self.__prerequisites = prerequisites

        FacultyTechnicalElective.allFacultyTechnicalElectives.append(self)
      #  logging.info(f"{self.courseName} ({self.courseCode}) named faculty technical elective course created.")

    def isEligibleToRequest(self, student: Student) -> bool:
        return self.semesterControl(student) and student.getTranscript().hasBeenPassedCourses(self.getPrerequisites) #look at here again

    #define getter method
    @property
    def getPrerequisites(self) -> List[Course]:
        return self.__prerequisites

    # define setter method
    def setPrerequisites(self, prerequisites: List[Course]):
        self.__prerequisites = prerequisites

    @classmethod
    def read_from_json(cls):
        try:
            # Opening JSON file
            input = open('input.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)
            requiredCredits = data["TERequiredCredits"]
            semester = data["FTESemesters"]
            for i in data['facultyTechnicalElectiveCourses']:
                FacultyTechnicalElective(
                    courseName=i["courseName"],
                    courseCode=i["courseCode"],
                    quota=i["quota"],
                    semesters=semester,
                    courseCredit=i["credits"],
                    courseDay=i["courseDay"],
                    courseHour=i["courseHour"],
                    prerequisites=i["preRequisites"]

                )

                # close file
                input.close()
        except IOError:
            # Will be log
            exit(1)

