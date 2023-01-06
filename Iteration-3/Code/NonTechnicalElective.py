from ElectiveCourse import ElectiveCourse
from typing import List

class NonTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)

    def isEligibleToRequest(self, student) -> bool:
        return self.semesterControl(student)
