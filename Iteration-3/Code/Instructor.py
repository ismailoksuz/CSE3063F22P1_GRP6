from Person import Person
from Course import Course
from typing import List

class Instructor(Person):

    def __init__(self, firstName: str, lastName: str, givenCourses: List[Course]=[]):
        super().__init__(firstName, lastName)
        self.__givenCourses = givenCourses
        print(self.toString() + " named instructor created.") # will be log

    def addGivenCourse(self, course):
        self.__givenCourses.append(course)
        print(self.toString() + ": instructor now gives " + course.getCourseName()) # will be log

    def removeGivenCourse(self, course):
        self.__givenCourses.remove(course)

    def getGivenCourses(self):
        return self.__givenCourses