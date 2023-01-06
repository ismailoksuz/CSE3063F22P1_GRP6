from MandatoryCourse import MandatoryCourse

class Internship(MandatoryCourse):

    def __init__(self, courseName, courseCode, courseCredit, semester, noCollision):
        super().__init__(courseName,courseCode, courseCredit, 0, noCollision, 10000, semester, [])

    def isEligibleToRequest(self, student):
        return student.getSemester() > 4

