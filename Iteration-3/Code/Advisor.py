from Internship import Internship
from Instructor import Instructor
from Course import Course
from typing import List
import logging


class Advisor(Instructor):

    def __init__(self, firstName: str, lastName: str, givenCourses: List[Course] = []):
        super().__init__(firstName, lastName, givenCourses)
        self.__students = []
        logging.info(self.getFirstName() + " " + self.getLastName() +
                     " named advisor created.")

    def completeRegistration(self, student):
        for i in range(len(student.getRequestedCourses())):
            if not isinstance(student.getRequestedCourses()[i], Internship):
                if self.checkQuotaForRegistration(student.getRequestedCourses()[i], student) and self.checkCollision(student, student.getRequestedCourses()[i]) and self.checkEnrolledCourseLimit(student.getRequestedCourses()[i], student):
                    student.getTranscript().getEnrolledCourses().append(
                        student.getRequestedCourses()[i])
                    student.getRequestedCourses(
                    )[i].getStudents().append(student)

            if isinstance(student.getRequestedCourses()[i], Internship):
                student.getTranscript().getEnrolledCourses().append(
                    student.getRequestedCourses()[i])
                student.getRequestedCourses()[i].getStudents().append(student)

        logging.info(student.getStudentName() +
                     "'s registration successfully completed.")

    def checkCollision(self, student, course):
        if isinstance(course, Internship):
            return True
        isTrue = True
        for c in student.getTranscript().getEnrolledCourses():
            if course.getCourseSchedule().isCollision(c.getCourseSchedule()):
                course.setCollisionProblem(course.getCollisionProblem() + 1)
                student.getStudentOutput().append("Advisor didn't approve " + course.getCourseCode() +
                                                  " because of two hours collision with " + c.getCourseCode() + " in schedule")
                logging.info(student.getStudentName() + " couldn't take " + course.getCourseName() +
                             " because of collision with " + c.getCourseName() + ".")
                isTrue = False
                break
        return isTrue

    def checkQuotaForRegistration(self, course, student):
        if len(course.getStudents()) < course.getQuato():
            return True
        else:
            course.setQuotaProblem(course.getQuotaProblem() + 1)
            student.getStudentOutput().append("The student couldn't register for " +
                                              course.getCourseCode() + " because of a quota problem")
            logging.info(student.getStudentName() + " couldn't take " + course.getCourseName() +
                         " because course quota is full ( Quota: " + str(course.getQuato()) + ").")
            return False

    def checkEnrolledCourseLimit(self, course, student):
        if len(student.getTranscript().getEnrolledCourses()) <= 10:
            return True
        else:
            course.setEnrolledCourseLimit(course.getEnrolledCourseLimit() + 1)
            student.getStudentOutput().append("The student couldn't register for " +
                                              course.getCourseCode() + " due to exceeding the maximum number of courses that can be registered")
            logging.info(student.getStudentName() + " couldn't take " + course.getCourseName() +
                         " due to exceeding the maximum number of courses that can be registered.")
            return False

    def getStudents(self):
        return self.__students

    def setStudents(self, students):
        self.__students = students

    def addStudent(self, student):
        self.__students.append(student)

    def removeStudent(self, student):
        self.__students.remove(student)

    def getAdvisorName(self):
        return self.getFirstName() + " " + self.getLastName()
