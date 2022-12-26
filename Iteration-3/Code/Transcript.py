#This will be the code from Transcript.java translated from java into python

import logging


class Transcript:
    def __init__(self, student):
        self.__log = logging.getLogger(Transcript.__name__)
        self.__gpa = 0.0
        self.__creditCompleted = 0
        self.__takenCourses = {}
        self.__completedCourses = []
        self.__failedCourses = []
        self.__enrolledCourses = []

    def calculateGpa(self):
        gradeMultiplication = 0
        totalCredit = 0
        studentGpa = 0

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

        


