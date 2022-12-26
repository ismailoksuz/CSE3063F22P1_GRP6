import json
import random
from datetime import date
from person import Person
from Transcript import Transcript
from studentId import StudentId
from advisor import Advisor


class Student(Person):
    allStudent = []

    def __init__(self, firstName: str, lastName: str, registrationYear: int, order: int):
        super().__init__(firstName, lastName)
        self.__registrationYear = registrationYear
        self.__order = order
        self.__requestedCourses = []
        # self.__currentYear ---> Burada ne ise yariyor ??
        self.__transcript = Transcript(self)
        self.__studentId = StudentId(registrationYear, order)
        self.__studentOutput = []
        self.__advisor: Advisor

        # Act. to execute
        Student.allStudent.append(self)

    @property
    def registrationYear(self):
        return self.__registrationYear

    @classmethod
    def read_from_json(cls):
        try:
            names = []
            surnames = []

            # Opening JSON file
            input = open('students.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)
            for name in data['names']:
                names.append(name)
            for surname in data['surnames']:
                surnames.append(surname)
            studentNumberPerYear = data['studentNumberPerYear']
            yearList = [2022, 2021, 2020, 2019]
            for year in yearList:
                for i in range(studentNumberPerYear):
                    Student(
                        firstName=names[random.randint(0, len(names)-1)],
                        lastName=surnames[random.randint(0, len(surnames)-1)],
                        registrationYear=year,
                        order=i
                    )


            # close file
            input.close()
        except IOError:
            # Will be log
            exit(1)

    def setAdvisor(self, Advisor):
        self.__advisor = Advisor

    def getAdvisor(self):
        return self.__advisor

    def getStudentName(self):
        return self.getFirstName() + " " + self.getLastName()

    @property
    def advisor(self):
        return self.__advisor