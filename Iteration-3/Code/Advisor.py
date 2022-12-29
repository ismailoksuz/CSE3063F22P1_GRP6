import Instructor
import Course
from typing import List, cast

class Advisor(Instructor):

    def __init__(self, firstName: str, lastName: str, givenCourses: List[Course]=[]):
        super().__init__(firstName, lastName, givenCourses)
        self.__students = []
        print(self.getFirstName() + " " + self.getLastName() + " named advisor created.") # will be log

    def completeRegistration(self, student):
        for i in range(len(student.getRequestedCourses())):
            if self.checkQuotaForRegistration(student.getRequestedCourses()[i], student) and self.checkCollision(student, student.getRequestedCourses()[i]):
                student.getTranscript().getEnrolledCourses().append(student.getRequestedCourses()[i])
                student.getRequestedCourses()[i].getStudents().append(student)
        print(student.getStudentName() + "'s registration successfully completed.") # will be log

    def checkCollision(self, student, course):
        isTrue = True
        for c in student.getTranscript().getEnrolledCourses():
            if course.getCourseSchedule().isCollision(c.getCourseSchedule()):
                course.setCollisionProblem(course.getCollisionProblem() + 1)
                student.getStudentOutput().append("Advisor didn't approve " + course.getCourseCode() + " because of two hours collision with " + c.getCourseCode() + " in schedule")
                print(student.getStudentName() + " couldn't take " + course.getCourseName() + " because of collision with " + c.getCourseName() + ".") # will be log
                isTrue = False
                break
        return isTrue

    def checkQuotaForRegistration(self, course, student):
        if len(course.getStudents()) < course.getQuato():
            return True
        else:
            course.setQuotaProblem(course.getQuotaProblem() + 1)
            student.getStudentOutput().append("The student couldn't register for " + course.getCourseCode() + " because of a quota problem")
            print(student.getStudentName() + " couldn't take " + course.getCourseName() + " because course quota is full ( Quota: " + str(course.getQuato()) + ").") # will be log
            return False

    def getStudents(self):
        return self.students

    def setStudents(self, students):
        self.students = students

    def addStudent(self, student):
        self.students.append(student)

    def removeStudent(self, student):
        self.students.remove(student)

    def getAdvisorName(self):
        return self.getFirstName() + " " + self.getLastName()
