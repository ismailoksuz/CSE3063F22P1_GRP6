from person import Person


class Instructor(Person):
    def __init__(self, firstName: str, lastName: str):
        super().__init__(firstName, lastName)
        self.__givenCourses = []

    def addGivenCourse(self, course):
        self.__givenCourses.append(course)

    def removeGivenCourse(self, course):
        self.__givenCourses.remove(course)

    @property
    def givenCourses(self):
        return self.__givenCourses
