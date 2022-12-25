from typing import List

class NonTechnicalElective(ElectiveCourse):
    def __init__(self, course_name: str, course_code: str, course_credit: int, course_day: int,
                 course_hour: str, quota: int, semesters: List[int]):
        super().__init__(course_name, course_code, course_credit, course_day, course_hour, quota, semesters)

    def is_eligible_to_request(self, student: Student) -> bool:
        return self.semester_control(student)

