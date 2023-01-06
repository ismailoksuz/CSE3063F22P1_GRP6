import random
import logging
import Course
from Internship import Internship
class Grade:

    def __init__(self, course: Course, courseGrade: int):
        self.__course = course
        self.__courseGrade = courseGrade
        self.__letter = self.getLetterGrade()
        logging.info("Grade created.")

    def getLetterGrade(self):
        letterGrade = ""
        random_number = random.Random()
        if isinstance(self.__course, Internship):
            if random.randint(0, 4) != 0:
                letterGrade = "S"
            else:
                letterGrade = "U"

            return letterGrade
        if self.__courseGrade > 100 or self.__courseGrade < 0:
            exit(1)
        elif self.__courseGrade >= 90:
            letterGrade = "AA"
        elif self.__courseGrade >= 85:
            letterGrade = "BA"
        elif self.__courseGrade >= 75:
            letterGrade = "BB"
        elif self.__courseGrade >= 65:
            letterGrade = "CB"
        elif self.__courseGrade >= 55:
            letterGrade = "CC"
        elif self.__courseGrade >= 45:
            letterGrade = "DC"
        elif self.__courseGrade >= 35:
            letterGrade = "DD"
        elif self.__courseGrade >= 30:
            letterGrade = "FD"
        else:
            #isAttend = (random_number.randint(0, 6) == 0)  #edit this part
            #isEnteredTheFinal = (random_number.randint(0, 6) == 0) #edit this part
            #letterGrade = "FF" if isAttend and isEnteredTheFinal else "FG" if isAttend else "DZ"
            if random.randint(0, 6) == 0:
                letterGrade = "DZ"
            elif random.randint(0, 6) == 0:
                letterGrade = "FG"
            else:
                letterGrade = "FF"
        return letterGrade

    # GETTER AND SETTER
    def getCourse(self) -> Course:
        return self.__course

    def getLetter(self) -> str:
        return self.__letter