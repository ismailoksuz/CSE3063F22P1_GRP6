import json
from instructor import Instructor

class Advisor(Instructor):
    allAdvisors = []

    def __init__(self, firstName: str, lastName: str):
        super().__init__(firstName, lastName)
        self.__students = []

        # Actions to execute
        Advisor.allAdvisors.append(self)

    @classmethod
    def read_from_json(cls):
        try:
            # Opening JSON file
            input = open('advisor.json', encoding="utf-8")

            # returns JSON objest as a dictionary
            data = json.load(input)
            for i in data['advisors']:
                Advisor(
                    firstName=i.get('firstName'),
                    lastName=i.get('lastName')
                )

            # close file
            input.close()
        except IOError:
            # Will be log
            exit(1)


    def addStudent(self, Student):
        self.__students.append(Student)


    def getAdvisorName(self):
        return self.getFirstName() + " " + self.getLastName()

    def getStudents(self):
        return self.__students