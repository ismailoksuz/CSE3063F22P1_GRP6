from typing import List

class NonTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)

    def is_eligible_to_request(self, student: Student) -> bool:
        return self.semester_control(student)

