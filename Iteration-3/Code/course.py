import json
class Course:
    allCourses = []

    def __init__(self, courseName:str, courseCode:str, courseCredit: int, courseDay: int, courseHour: str, quota: int):


        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__courseCredit = courseCredit
        self.__courseDay = courseDay
        self.__courseHour = courseHour
        self.__quota = quota


        # Act. to execute
        Course.allCourses.append(self)

    def __repr__(self):
        return f"{self.__class__.__name__}('{self.__courseName}', '{self.__courseCode})'"

