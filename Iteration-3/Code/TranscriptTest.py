import unittest

from Transcript import Transcript
from MandatoryCourse import MandatoryCourse

class TranscriptTest(unittest.TestCase):
    
        def setUp(self) -> None:
            self.transcript = Transcript()
            self.course = MandatoryCourse("Introduction to Computer Science", "CSE1013", 4, 1, "9:00AM", 30, 1, [])
            self.course2 = MandatoryCourse("Introduction to Computer Graphics", "CSE4067", 4, 1, "11:00AM", 50, 1, [])
    
        def test_calculateCompleteCredit(self):
            self.assertEqual(self.transcript.calculateCompleteCredit(), 0)
            self.transcript.getCompletedCourses().append(self.course)
            self.assertEqual(self.transcript.calculateCompleteCredit(), 4)

        def test_isCourseCompletedOrFailed(self):
            self.assertEqual(len(self.transcript.getCompletedCourses()), 0)
            self.assertEqual(len(self.transcript.getFailedCourses()), 0)
            self.transcript.isCourseCompletedOrFailed(self.course, "AA")
            self.assertEqual(len(self.transcript.getCompletedCourses()), 1)
            self.assertEqual(len(self.transcript.getFailedCourses()), 0)
            self.transcript.isCourseCompletedOrFailed(self.course2, "FF")
            self.assertEqual(len(self.transcript.getCompletedCourses()), 1)
            self.assertEqual(len(self.transcript.getFailedCourses()), 1)

        def test_hasBeenPassed(self):
            self.transcript.isCourseCompletedOrFailed(self.course, "AA")
            self.assertEqual(self.transcript.hasBeenPassedCourses([self.course]), True)

if __name__ == '__main__':
    unittest.main()