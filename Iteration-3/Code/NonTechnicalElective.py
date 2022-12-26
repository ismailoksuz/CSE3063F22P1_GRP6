from typing import List
import json
from ElectiveCourse import ElectiveCourse
from student import Student


class NonTechnicalElective(ElectiveCourse):
    allNonTechnicalElective =[]
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)

        NonTechnicalElective.allNonTechnicalElective.append(self)

    def isEligibleToRequest(self, student: Student) -> bool:
        return self.semesterControl(student)

    @classmethod
    def read_from_json(cls):
        try:
            # Opening JSON file
            input = open('input.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)
            for i in data['nonTechnicalElectiveCourses']:
                NonTechnicalElective(
                    courseName=i["courseName"],
                    courseCode = i["courseCode"],
                    quota = i["quota"],
                    semesters = i["semester"],
                    courseCredit = i["credits"],
                    courseDay = i["courseDay"],
                    courseHour = i["courseHour"]

                )

                # close file
                input.close()
        except IOError:
            # Will be log
            exit(1)
