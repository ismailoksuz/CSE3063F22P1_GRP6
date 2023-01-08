import unittest

from MandatoryCourse import MandatoryCourse
from Instructor import Instructor

class InstructorTest(unittest.TestCase):

    def setUp(self) -> None:
        firstName = "John"
        lastName = "Doe"

        self.instructor = Instructor(firstName, lastName)
        self.course = MandatoryCourse("Introduction to Computer Science", "CSE1013", 4, 1, "9:00AM", 30, 1, [])

    def test_addGivenCourse(self):
        self.assertEqual(len(self.instructor.getGivenCourses()), 0)
        self.instructor.addGivenCourse(self.course)
        self.assertEqual(len(self.instructor.getGivenCourses()), 1)

if __name__ == '__main__':
    unittest.main()
