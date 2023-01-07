from MandatoryCourse import MandatoryCourse

class Internship(MandatoryCourse):

    def __init__(self, courseName, courseCode, courseCredit, semester, numberOfWorkDays):
        super().__init__(courseName,courseCode, courseCredit, 0, "", 10000, semester, [])
        self.__numberOfWorkDays = numberOfWorkDays

    def isEligibleToRequest(self, student):
        return student.getSemester() > 4
    