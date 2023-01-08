from abc import ABC, abstractmethod
import logging
from Schedule import Schedule


class Course(ABC):

    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour, quota):
        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__courseCredit = courseCredit
        self.__courseSchedule = Schedule(courseDay, courseHour)
        self.__quota = quota
        self.__students = []
        self.__quotaProblem = 0
        self.__collisionProblem = 0
        self.__failedCredits = 0
        self.__failedPreq = 0
        self.__enrolledCourseLimit = 0
        self.__courseInstructor = None
        logging.info(self.getCourseName() + " (" + self.getCourseCode() + ")" +
                     " named course created.")

    @abstractmethod
    def isEligibleToRequest(self, student):
        pass

    def getCourseName(self):
        return self.__courseName

    def getCourseCode(self) -> str:
        return self.__courseCode

    def getQuato(self):
        return self.__quota

    def getCourseInstructor(self):
        return self.__courseInstructor

    def setCourseInstructor(self, courseInstructor):
        self.__courseInstructor = courseInstructor

    def getStudents(self):
        return self.__students

    def getCourseCredit(self):
        return self.__courseCredit

    def getCourseSchedule(self):
        return self.__courseSchedule

    def getQuotaProblem(self):
        return self.__quotaProblem

    def setQuotaProblem(self, quotaProblem):
        self.__quotaProblem = quotaProblem
        logging.info(self.getCourseName() + ": Number of quota problem changed." +
                     "(" + "New: " + str(self.getQuotaProblem()) + ")")

    def getCollisionProblem(self):
        return self.__collisionProblem

    def setCollisionProblem(self, collisionProblem):
        self.__collisionProblem = collisionProblem
        logging.info(self.getCourseName() + ": Number of collision problem changed." +
                     "(" + "New: " + str(self.getCollisionProblem()) + ")")

    def getFailedCredits(self):
        return self.__failedCredits

    def setFailedCredits(self, failedCredits):
        self.__failedCredits = failedCredits
        logging.info(self.getCourseName() + ": Number of failed credits changed." +
                     "(" + "New: " + str(self.getFailedCredits()) + ")")

    def getFailedPreq(self):
        return self.__failedPreq

    def setFailedPreq(self, failedPreq):
        self.__failedPreq = failedPreq
        logging.info(self.getCourseName() + ": Number of failed prerequisite changed." +
                     "(" + "New: " + str(self.getFailedPreq()) + ")")

    def getEnrolledCourseLimit(self):
        return self.__enrolledCourseLimit

    def setEnrolledCourseLimit(self, courseLimit):
        self.__enrolledCourseLimit = courseLimit
        logging.info(self.getCourseName() + ": Number of course limit changed." +
                     "(" + "New: " + str(self.getEnrolledCourseLimit()) + ")")