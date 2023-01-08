import logging
from Transcript import Transcript
from StudentId import StudentId
from Person import Person
from Advisor import Advisor
from Internship import Internship
import datetime


class Student(Person):

    def __init__(self, firstName: str, lastName: str, registrationYear: int, order: int):
        super().__init__(firstName, lastName)
        self.__registrationYear = registrationYear
        self.__order = order
        self.__requestedCourses = []
        self.__currentYear = int(datetime.datetime.now().year)
        self.__transcript = Transcript()
        self.__studentId = StudentId(registrationYear, order)
        self.__studentOutput = []
        self.__semester: int
        self.__advisor: Advisor
        logging.info("Student created =>  " + "Name: " + self.getStudentName() + " RegistrationYear: " +
                     str(self.getRegistrationYear()) + " Order: " + str(self.getOrder()))

    def willBeMultipleInternshipsInRequestedCourses(self):
        for course in self.__requestedCourses:
            if isinstance(course, Internship):
                return False
        return True

    def getStudentOutput(self):
        return self.__studentOutput

    def setStudentOutput(self, studentOutput):
        self.__studentOutput = studentOutput

    def getStudentName(self):
        return self.getFirstName() + " " + self.getLastName()

    def getTranscript(self):
        return self.__transcript

    def getStudentId(self):
        return self.__studentId

    def getAdvisor(self) -> Advisor:
        return self.__advisor

    def setAdvisor(self, advisor):
        self.__advisor = advisor

    def getRequestedCourses(self):
        return self.__requestedCourses

    def getRegistrationYear(self):
        return self.__registrationYear

    def getOrder(self):
        return self.__order

    def getCurrentYear(self):
        return self.__currentYear

    def getSemester(self) -> int:
        return self.__semester

    def setSemester(self, semester):
        self.__semester = semester
        logging.info(self.getStudentName() + ": student semester changed as " +
                     str(self.getSemester()))
