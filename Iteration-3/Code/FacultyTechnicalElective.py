from typing import List

class FacultyTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quato: int, semesters: List[int], prequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semesters)
        self.__prequisites = prequisites

    def isEligibleToRequest(self, student: Student) -> bool:
        return self.semesterControl(student) and student.getTranscript().hasBeenPassedCourses(self.getPrequisites) #look at here again

    #define getter method
    @property
    def getPrequisites(self) -> List[Course]:
        return self.__prequisites

    # define setter method
    def setPrequisites(self, prequisites: List[Course]):
        self.__prequisites = prequisites

