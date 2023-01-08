import unittest
from StudentId import StudentId

class StudentIdTest(unittest.TestCase):
    def test_create_student_id(self):
        student_id = StudentId(2020, 1)
        self.assertEqual(student_id.createStudentId(), "150120001")
        student_id = StudentId(2020, 123)
        self.assertEqual(student_id.createStudentId(), "150120123")


    def test_get_cse_code(self):
        student_id = StudentId(2020, 1)
        self.assertEqual(student_id.getCseCode(), "1501")

    def test_get_year(self):
        student_id = StudentId(2020, 1)
        self.assertEqual(student_id.getYear(), 2020)

    def test_get_order(self):
        student_id = StudentId(2020, 1)
        self.assertEqual(student_id.getOrder(), 1)

    def test_to_string(self):
        student_id = StudentId(2020, 1)
        self.assertEqual(student_id.toString(), "150120001")
        student_id = StudentId(2020, 123)
        self.assertEqual(student_id.toString(), "150120123")

if __name__ == '__main__':
    unittest.main()
