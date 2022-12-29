from Transcript import Transcript
from StudentId import StudentId
from Person import Person
import datetime
class Student(Person):

    def __init__(self, firstName: str, lastName: str, registrationYear: int, order: int):
        super().__init__(firstName, lastName)
        self.registrationYear = registrationYear
        self.order = order
        self.requestedCourses = []
        self.currentYear = int(datetime.datetime.now().year)
        self.transcript = Transcript()
        self.studentId = StudentId(registrationYear, order)
        self.studentOutput = []
        self.semester = 1
        print("Student created =>  " + "Name: " + self.getStudentName() + " RegistrationYear: " + str(self.getRegistrationYear()) + " Order: " + str(self.getOrder())) # will be log

    def getStudentOutput(self):
        return self.studentOutput

    def setStudentOutput(self, studentOutput):
        self.studentOutput = studentOutput

    def getStudentName(self):
        return self.getFirstName() + " " + self.getLastName()

    def getTranscript(self):
        return self.transcript

    def getStudentId(self):
        return self.studentId

    def getAdvisor(self):
        return self.advisor

    def setAdvisor(self, advisor):
        self.advisor = advisor

    def getRequestedCourses(self):
        return self.requestedCourses

    def getRegistrationYear(self):
        return self.registrationYear

    def getOrder(self):
        return self.order

    def getCurrentYear(self):
        return self.currentYear

    def getSemester(self):
        return self.semester

    def setSemester(self, semester):
        self.semester = semester
        print(self.getStudentName() + ": student semester changed. as " + str(self.semester)) # will be log