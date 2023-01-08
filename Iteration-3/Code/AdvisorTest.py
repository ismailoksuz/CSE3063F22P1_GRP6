import unittest

from Advisor import Advisor
from MandatoryCourse import MandatoryCourse
from Student import Student

class AdvisorTest(unittest.TestCase):

        def setUp(self) -> None:
            firstName = "John"
            lastName = "Doe"

            self.advisor = Advisor(firstName, lastName)

        def test_completeRegistration(self):
            student = Student("Ali", "Kaya", 2020, 1)
            course = MandatoryCourse("Introduction to Computer Science", "CSE1013", 4, 1, "9:00AM", 30, 1, [])
            student.getRequestedCourses().append(course)
            self.advisor.completeRegistration(student)
            self.assertEqual(len(student.getTranscript().getEnrolledCourses()), 1)

        def test_checkCollisiont(self):
            student = Student("Ali", "Kaya", 2020, 1)
            course = MandatoryCourse("Introduction to Computer Science", "CSE1013", 4, 1, "9:00AM", 30, 1, [])
            student.getRequestedCourses().append(course)
            self.advisor.completeRegistration(student)
            course2 = MandatoryCourse("Operating Systems", "CSE3044", 6, 1, "9:00AM", 50, 1, [])
            self.assertFalse(self.advisor.checkCollision(student, course2))
            


if __name__ == '__main__':
    unittest.main()