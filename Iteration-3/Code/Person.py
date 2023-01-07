from abc import ABC, abstractmethod
import random

class Person(ABC):

    def __init__(self, firstName: str, lastName: str):
        self.__firstName = firstName
        self.__lastName = lastName
        self.__email = self.generateEmail()
        self.__phoneNumber = self.generatePhoneNumber()


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

    def getEmail(self):
        return self.__email

    def getPhoneNumber(self):
        return self.__phoneNumber

    def toString(self):
        return self.getFirstName() + " " + self.getLastName()