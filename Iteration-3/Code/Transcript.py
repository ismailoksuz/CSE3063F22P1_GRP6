from Course import Course
from typing import List, cast


class Transcript:

    def __init__(self):
        self.__gpa = 0
        self.__creditCompleted = 0
        self.__takenCourses = dict()
        self.__completedCourses = list()
        self.__failedCourses = list()
        self.__enrolledCourses = list()

    def calculateGpa(self):
        gradeMultiplication = 0
        totalCredit = 0
        studentGpa = 0

        if len(self.__takenCourses) == 0:
            return 0

        for course, grade in self.__takenCourses.items():
            totalCredit += course.getCourseCredit()
            if grade.getLetter() == "AA":
                gradeMultiplication += course.getCourseCredit() * 4.0
            elif grade.getLetter() == "BA":
                gradeMultiplication += course.getCourseCredit() * 3.5
            elif grade.getLetter() == "BB":
                gradeMultiplication += course.getCourseCredit() * 3.0
            elif grade.getLetter() == "CB":
                gradeMultiplication += course.getCourseCredit() * 2.5
            elif grade.getLetter() == "CC":
                gradeMultiplication += course.getCourseCredit() * 2.0
            elif grade.getLetter() == "DC":
                gradeMultiplication += course.getCourseCredit() * 1.5
            elif grade.getLetter() == "DD":
                gradeMultiplication += course.getCourseCredit() * 1.0
            elif grade.getLetter() == "FD":
                gradeMultiplication += course.getCourseCredit() * 0.5
            elif grade.getLetter() == "FF":
                gradeMultiplication += course.getCourseCredit() * 0.0
            elif grade.getLetter() == "FG":
                gradeMultiplication += course.getCourseCredit() * 0.0
            elif grade.getLetter() == "DZ":
                gradeMultiplication += course.getCourseCredit() * 0.0
        studentGpa = round(gradeMultiplication / totalCredit * 100.0) / 100.0
        self.__gpa = studentGpa
        return studentGpa

    def calculateCompleteCredit(self):
        completedCredit = 0
        for course in self.__completedCourses:
            completedCredit += course.getCourseCredit()
        self.__creditCompleted = completedCredit
        return completedCredit

    def isCourseCompletedOrFailed(self, course, letter):
        if letter == "AA" or letter == "BA" or letter == "BB" or letter == "CB" or letter == "CC" or letter == "DC" or letter == "DD" or letter == "S":
            self.__completedCourses.append(course)
        else:
            self.__failedCourses.append(course)

    def hasBeenPassedCourse(self, course):
        if course == None:
            return True
        if course in self.getCompletedCourses():
            return True
        else:
            return False

    def hasBeenPassedCourses(self, courses):  # infinite loop
        for course in courses:
            if not self.hasBeenPassedCourse(course):
                return False
        return True

    def getGpa(self):
        return self.__gpa

    def setGpa(self, gpa):
        self.__gpa = gpa

    def getCreditCompleted(self):
        return self.__creditCompleted

    def setCreditCompleted(self, creditCompleted):
        self.__creditCompleted = creditCompleted

    def getTakenCourses(self):
        return self.__takenCourses

    def getCompletedCourses(self):
        return self.__completedCourses

    def getFailedCourses(self):
        return self.__failedCourses

    def getEnrolledCourses(self):
        return self.__enrolledCourses