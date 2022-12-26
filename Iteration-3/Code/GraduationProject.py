import logging
from typing import List

class GraduationProject(MandatoryCourse, ICreditRequirement):
    def __init__(self, course_name, course_code, course_credit, course_day, course_hour,
                 quato, semester, prequisites, required_credits):
        super().__init__(course_name, course_code, course_credit, course_day, course_hour, quato, semester, prequisites)
        self.required_credits = required_credits
        logging.info(f"{self.course_name} ({self.course_code}) named graduation project created.")

    def is_eligible_to_request(self, student):
        return super().is_eligible_to_request(student) and self.check_required_credit(student)

    def check_required_credit(self, student):
        if student.transcript.credit_completed >= self.required_credits:
            return True
        else:
            self.failed_credits += 1
            student.student_output.append(f"The advisor didn't approve graduation project {self.course_code} because student completed credits < {self.required_credits}")
            logging.info(f"Student has not enough credits. {student.student_name} cannot enroll {self.course_name}.")
            return False

    @property
    def required_credits(self):
        return self.required_credits
