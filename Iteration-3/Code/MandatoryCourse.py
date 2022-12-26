
from typing import List

class MandatoryCourse(Course):
    def __init__(self, course_name: str, course_code: str, course_credit: int, course_day: int, course_hour: str,
                 quota: int, semester: int, prerequisites: List[Course]):
        super().__init__(course_name, course_code, course_credit, course_day, course_hour, quota)
        self.__semester = semester
        self.__prerequisites = prerequisites
    
    def is_eligible_to_request(self, student):
        if student.get_semester() == self.get_semester():
            if (not student.get_transcript().has_been_passed_courses(self.get_prequisites())):
                self.set_failed_preq(self.get_failed_preq() + 1)
                student.get_student_output().add(f"The system didn't allow {self.get_course_code()} because student failed prereq. {self.get_prequisites()[0].get_course_code()}")
                return False
            else:
                return True
        else:
            return False

    def is_eligible_to_be_previously_taken(self, student):
        return student.get_semester() > self.get_semester() and student.get_transcript().has_been_passed_courses(self.get_prequisites())

    # GETTERS AND SETTERS
    def get_semester(self) -> int:
        return self.semester

    def get_prequisites(self) -> List[Course]:
        return self.prerequisites
