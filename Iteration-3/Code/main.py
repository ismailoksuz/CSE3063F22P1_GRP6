from course import Course
from mandatoryCourse import MandatoryCourse
from person import Person
from instructor import Instructor
from advisor import Advisor
from student import Student
from TechnicalElective import TechnicalElective
from FacultyTechnicalElective import FacultyTechnicalElective
from GraduationProject import GraduationProject
import random

MandatoryCourse.read_from_json()
print(MandatoryCourse.allMandatoryCourses)
TechnicalElective.read_from_json()
print(TechnicalElective.allTechnicalElectives)
FacultyTechnicalElective.read_from_json()
print(FacultyTechnicalElective.allFacultyTechnicalElectives)
GraduationProject.read_from_json()
print(GraduationProject.allGraduationProject)
# person1 = Instructor("onur", "alkurt")
# print(Person.allPerson)
# person1.addGivenCourse(MandatoryCourse.allMandatoryCourses[0])
# print(person1.givenCourses)
# person1.removeGivenCourse(MandatoryCourse.allMandatoryCourses[0])
# print(person1.givenCourses)
Advisor.read_from_json()
# for i in Advisor.allAdvisors:
#     print(i.getAdvisorName())
Student.read_from_json()
# count = 0
# for i in Student.allStudent:
#     if(i.registrationYear == 2021):
#         count += 1
# print(count)
def assignAdvisor():  # parameter type ??
    copyStudentList = Student.allStudent
    # for student in studentList:
    #     copyStudentList.append(student)
    if len(copyStudentList) > 0:
        count = 0
        while len(Advisor.allAdvisors) > count:
            if len(copyStudentList) == 0:
                break
            else:
                randomNum = random.randint(0, len(copyStudentList) - 1)
                Advisor.allAdvisors[count].addStudent(copyStudentList[randomNum])
                copyStudentList[randomNum].setAdvisor(Advisor.allAdvisors[count])
                print("Student " + copyStudentList[randomNum].getStudentName() + " is assigned advisor "
                      + Advisor.allAdvisors[count].getAdvisorName() + ".")  # will be log
                del copyStudentList[randomNum]
                count += 1
                if count == len(Advisor.allAdvisors):
                    count = 0
    print("All students are assigned a advisor.")  # will be log

assignAdvisor()

# for i in Advisor.allAdvisors:
#     print(i.getStudents())

for i in Student.allStudent:
    print((i.advisor()))




