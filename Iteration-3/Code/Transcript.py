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
            # elif grade.getLetter() in ["FF","FG","DZ"]:
            #     gradeMultiplication += course.getCourseCredit() * 0.0
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
        if letter == "AA" or letter == "BA" or letter == "BB" or letter == "CB" or letter == "CC" or letter == "DC" or letter == "DD":
            self.__completedCourses.append(course)
        else:
            self.__failedCourses.append(course)

    def hasBeenPassedCourse(self, course):
        if course == None:
            return True
        return course in self.__completedCourses

    def hasBeenPassedCourses(self, courses):
        for course in courses:
            if not self.hasBeenPassedCourse(course):
                return False
        return True

    @property
    def gpa(self):
        return self.__gpa

    @gpa.setter
    def gpa(self, gpa):
        self.__gpa = gpa
    
    @property
    def creditCompleted(self):
        return self.__creditCompleted   
    
    @creditCompleted.setter
    def creditCompleted(self, creditCompleted):
        self.__creditCompleted = creditCompleted
    
    @property
    def takenCourses(self):
        return self.__takenCourses
    
    @property
    def completedCourses(self):
        return self.__completedCourses
    
    @property
    def failedCourses(self):
        return self.__failedCourses
    
    @property
    def enrolledCourses(self):
        return self.__enrolledCourses
        
