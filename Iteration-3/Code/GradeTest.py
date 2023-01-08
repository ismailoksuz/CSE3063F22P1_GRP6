import unittest
from Grade import Grade
from MandatoryCourse import MandatoryCourse
from Internship import Internship

class GradeTest(unittest.TestCase):
    def test_get_letter_grade(self):
        # Test letter grade for a regular course
        course = MandatoryCourse("Computer Programming I","CSE1241", 6,3,"09:00",200,1,[""])
        grade = Grade(course, 95)
        self.assertEqual(grade.getLetter(), "AA")


        internship = Internship("Summer Practice I","CSE3000",10,5,30)
        grade = Grade(internship, 95)
        self.assertIn(grade.getLetter(), ["S", "U"])

        # Test letter grade for a failing grade in a regular course
        course = MandatoryCourse("Physics I","PHYS1101", 4,0,"11:00",200,1,[""])
        grade = Grade(course, 20)
        self.assertIn(grade.getLetter(), ["DZ", "FG", "FF"])

if __name__ == '__main__':
    unittest.main()
   

    
