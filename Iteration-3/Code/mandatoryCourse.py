import json
from typing import List

from course import Course


class MandatoryCourse(Course):
    allMandatoryCourses = []

    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str, quota: int,
                 semester: int, prerequisites: List[Course]):
        super().__init__(
            courseName, courseCode, courseCredit, courseDay, courseHour, quota
        )

        self.__semester = semester
        self.__prerequisites = prerequisites

        # Act. to execute
        MandatoryCourse.allMandatoryCourses.append(self)

    @classmethod
    def read_from_json(cls):
        # Opening JSON file
        f = open('input.json', encoding="utf-8")

        # returns JSON object as a dictionary
        data = json.load(f)

        for i in data['MandatoryCourses']:
            MandatoryCourse(
                courseName=i.get('courseName'),
                courseCode=i.get('courseCode'),
                courseCredit=i.get('credits'),
                courseDay=i.get('courseDay'),
                courseHour=i.get('courseHour'),
                quota=i.get('quota'),
                semester=i.get('semester'),
                prerequisites=i.get('preRequisites')
            )

        # close file
        f.close()

        def isEligibleToRequest(self, student):
            if (student.getSemester() == self.getSemester()):
                if (not student.getTranscript().hasBeenPassedCourses(self.getPrequisites())):
                    self.setFailedPreq(self.getFailedPreq() + 1)
                    student.getStudentOutput().add("The system didn't allow " + self.getCourseCode() +
                                                   "because student failed prereq." + self.getPrequisites()[
                                                       0].getCourseCode())
                    return False
                else:
                    return True
            else:
                return False

        def isEligibleToBePreviouslyTaken(self, student):
            return student.getSemester() > self.getSemester() and student.getTranscript().hasBeenPassedCourses(
                self.getPrequisites())

        # GETTERS AND SETTERS
        def getSemester(self) -> int:
            return self.__semester

        def getPrequisites(self) -> List[Course]:
            return self.__prerequisites
