from typing import List
from abc import ABC

from Course import Course
class ElectiveCourse(Course, ABC):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str,
                 quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota)
        self.__semesters = semesters

    def semesterControl(self, student):
        isTrue = False
        for semester in self.getSemesters():
            if student.getSemester() == semester:
                isTrue = True
                break
        return isTrue

    def semesterCheck(self, semester: int) -> bool:
        isTrue = False
        for s in self.getSemesters():
            if s == semester:
                isTrue = True
                break
        return isTrue

    # GETTERS AND SETTERS
    def getSemesters(self) -> List[int]:
        return self.__semesters