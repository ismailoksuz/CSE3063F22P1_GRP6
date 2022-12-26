import random


class Person:

    allPerson = []

    def __init__(self, firstName: str, lastName: str):
        self.__firstName = firstName
        self.__lastName = lastName
        self.__email = self.generateEmail()
        self.__phoneNumber = self.generatePhoneNumber()

        # Act. to execute
        Person.allPerson.append(self)

    def __repr__(self):
        return f"{self.__class__.__name__}('{self.__firstName}', {self.__lastName}, {self.__email}, {self.__phoneNumber})"

    def generateEmail(self):
        userName = ((self.__firstName + self.__lastName).lower()).replace(" ", "")
        trToing = str.maketrans("çğıöşü", "cgiosu")
        userName = userName.translate(trToing)


        userEmail = userName + "@marun.edu.tr"
        return userEmail

    def generatePhoneNumber(self):
        phoneNumber = "05"
        for i in range(9):
            phoneNumber += str(random.randint(0, 9))
        return phoneNumber

    def getFirstName(self):
        return self.__firstName
    def getLastName(self):
        return self.__lastName