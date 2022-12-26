import json
from instructor import Instructor
class Course:
    allCourses = []

    def __init__(self, courseName:str, courseCode:str, courseCredit: int, courseDay: int, courseHour: str, quota: int):


        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__courseCredit = courseCredit
        self.__courseDay = courseDay
        self.__courseHour = courseHour
        self.__quota = quota
        self.__courseInstructor : Instructor
        self.__students = []
        self.__quotaProblem = 0
        self.__collisionProblem = 0
        self.__failedCredits = 0
        self.__failedPreq = 0



        # Act. to execute
        Course.allCourses.append(self)

    def __repr__(self):
        return f"{self.__class__.__name__}('{self.__courseName}', '{self.__courseCode})'"


    def setCourseInstructor(self, Instructor):
        self.__courseInstructor = Instructor

    def getCourseInstructor(self):
        return self.__courseInstructor

    def getCourseName(self):
        return self.__courseName
