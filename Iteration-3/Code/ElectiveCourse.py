from typing import List

class ElectiveCourse(Course):
    def __init__(self, course_name: str, course_code: str, course_credit: int, course_day: int, course_hour: str,
                 quota: int, semesters: List[int]):
        super().__init__(course_name, course_code, course_credit, course_day, course_hour, quota)
        self.__semesters = semesters

    def semesterControl(self, student):
        is_true = False
        for semester in self.get_semesters():
            if student.get_semester() == semester:
                is_true = True
                break
        return is_true

    def semesterCheck(self, semester: int) -> bool:
        is_true = False
        for s in self.get_semesters():
            if s == semester:
                is_true = True
                break
        return is_true

    # GETTERS AND SETTERS
    def get_semesters(self) -> List[int]:
        return self.semesters
