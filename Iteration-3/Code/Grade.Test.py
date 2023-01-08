import unittest
from grade import Grade
from course import Course
from internship import Internship

class TestGrade(unittest.TestCase):
    def test_get_letter_grade(self):
        # Test letter grade for a regular course
        course = Course("CSE1141", 6)
        grade = Grade(course, 95)
        self.assertEqual(grade.getLetter(), "AA")

        # Test letter grade for an internship
        internship = Internship("Software Development Internship")
        grade = Grade(internship, 95)
        self.assertIn(grade.getLetter(), ["S", "U"])

        # Test letter grade for a failing grade in a regular course
        course = Course("PHYS1101", 6)
        grade = Grade(course, 20)
        self.assertIn(grade.getLetter(), ["DZ", "FG", "FF"])

if __name__ == '__main__':
    unittest.main()

    deneme
    