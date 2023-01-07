from ElectiveCourse import ElectiveCourse
from Course import Course
from typing import List

class FacultyTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int], prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)
        self.__prerequisites = prerequisites

    def isEligibleToRequest(self, student) -> bool:
        return self.semesterControl(student) and student.getTranscript().hasBeenPassedCourses(self.getPrerequisites()) #look at here again

    #define getter method

    def getPrerequisites(self) -> List[Course]:
        return self.__prerequisites

    # define setter method
    def setPrerequisites(self, prerequisites: List[Course]):
        self.__prerequisites = prerequisites