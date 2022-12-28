import json
import random
import logging
import  datetime
from typing import List, cast
from abc import ABC, abstractmethod

class Person(ABC):

    def __init__(self, firstName: str, lastName: str):
        self.__firstName = firstName
        self.__lastName = lastName
        self.__email = self.generateEmail()
        self.__phoneNumber = self.generatePhoneNumber()

    #def __repr__(self):
    #    return f"{self.__class__.__name__}('{self.__firstName}', {self.__lastName}, {self.__email}, {self.__phoneNumber})"

    def generateEmail(self):
        userName = ((self.__firstName + self.__lastName).lower()).replace(" ", "")
        trToing = str.maketrans("çğıöşü", "cgiosu")
        userName = userName.translate(trToing)


        userEmail = userName + "@marun.edu.tr"
        return userEmail

    def generatePhoneNumber(self):
        phoneNumber = "05"
        for i in range(9):
            phoneNumber += str(random.randint(0, 9))
        return phoneNumber

    def getFirstName(self):
        return self.__firstName

    def getLastName(self):
        return self.__lastName

    def getEmail(self):
        return self.__email

    def getPhoneNumber(self):
        return self.__phoneNumber

    def toString(self):
        return self.getFirstName() + " " + self.getLastName()


class Course(ABC):

    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour, quota):
        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__courseCredit = courseCredit
        self.courseSchedule = Schedule(courseDay, courseHour)
        self.__quota = quota
        self.__students = []
        self.__quotaProblem = 0
        self.__collisionProblem = 0
        self.__failedCredits = 0
        self.__failedPreq = 0
        self.__courseInstructor = None
        print(self.getCourseName() + " (" + self.getCourseCode() + ")" + " named course created.") # will be log

    @abstractmethod
    def isEligibleToRequest(self, student):
        pass

    def getCourseName(self):
        return self.__courseName

    def getCourseCode(self) -> str:
        return self.__courseCode

    def getQuato(self):
        return self.__quota

    def getCourseInstructor(self):
        return self.__courseInstructor

    def setCourseInstructor(self, courseInstructor):
        self.__courseInstructor = courseInstructor

    def getStudents(self):
        return self.__students

    def getCourseCredit(self):
        return self.__courseCredit

    def getCourseSchedule(self):
        return self.courseSchedule

    def getQuotaProblem(self):
        return self.quotaProblem

    def setQuotaProblem(self, quotaProblem):
        self.quotaProblem = quotaProblem
        print(self.getCourseName() + ": Number of quota problem changed." + "(" + "New: " + self.getQuotaProblem() + ")") # will be log

    def getCollisionProblem(self):
        return self.__collisionProblem

    def setCollisionProblem(self, collisionProblem):
        self.__collisionProblem = collisionProblem
        print(self.getCourseName() + ": Number of collision problem changed." + "(" + "New: " + self.getCollisionProblem() + ")") # will be log

    def getFailedCredits(self):
        return self.failedCredits

    def setFailedCredits(self, failedCredits):
        self.failedCredits = failedCredits
        print(self.getCourseName() + ": Number of failed credits changed." + "(" + "New: " + self.getFailedCredits() + ")")

    def getFailedPreq(self):
        return self.__failedPreq

    def setFailedPreq(self, failedPreq):
        self.failedPreq = failedPreq
        print(self.getCourseName() + ": Number of failed prerequisite changed." + "(" + "New: " + self.getFailedPreq() + ")") # will be log


class ElectiveCourse(Course):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str,
                 quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota)
        self.__semesters = semesters

    def semesterControl(self, student):
        isTrue = False
        for semester in self.getSemesters():
            if student.getSemester() == semester:
                isTrue = True
                break
        return isTrue

    def semesterCheck(self, semester: int) -> bool:
        isTrue = False
        for s in self.getSemesters():
            if s == semester:
                isTrue = True
                break
        return isTrue

    # GETTERS AND SETTERS
    def getSemesters(self) -> List[int]:
        return self.__semesters


class Grade:

    def __init__(self, course: Course, courseGrade: int):
        self.__course = course
        self.__courseGrade = courseGrade
        self.__letter = self.getLetterGrade()
        logging.info("Grade created.")

    def getLetterGrade(self):
        letterGrade = ""
        random_number = random.Random()
        if self.__courseGrade > 100 or self.__courseGrade < 0:
            exit(1)
        elif self.__courseGrade >= 90:
            letterGrade = "AA"
        elif self.__courseGrade >= 85:
            letterGrade = "BA"
        elif self.__courseGrade >= 75:
            letterGrade = "BB"
        elif self.__courseGrade >= 65:
            letterGrade = "CB"
        elif self.__courseGrade >= 55:
            letterGrade = "CC"
        elif self.__courseGrade >= 45:
            letterGrade = "DC"
        elif self.__courseGrade >= 35:
            letterGrade = "DD"
        elif self.__courseGrade >= 30:
            letterGrade = "FD"
        else:
            #isAttend = (random_number.randint(0, 6) == 0)  #edit this part
            #isEnteredTheFinal = (random_number.randint(0, 6) == 0) #edit this part
            #letterGrade = "FF" if isAttend and isEnteredTheFinal else "FG" if isAttend else "DZ"
            if random.randint(0, 6) == 0:
                letterGrade = "DZ"
            elif random.randint(0, 6) == 0:
                letterGrade = "FG"
            else:
                letterGrade = "FF"
        return letterGrade

    # GETTER AND SETTER
    def getCourse(self) -> Course:
        return self.__course

    def getLetter(self) -> str:
        return self.__letter


class FacultyTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int], prequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)
        self.__prequisites = prequisites

    def isEligibleToRequest(self, student) -> bool:
        return self.semesterControl(student) and student.getTranscript().hasBeenPassedCourses(self.getPrequisites()) #look at here again

    #define getter method

    def getPrequisites(self) -> List[Course]:
        return self.__prequisites

    # define setter method
    def setPrequisites(self, prequisites: List[Course]):
        self.__prequisites = prequisites



class Instructor(Person):

    def __init__(self, firstName: str, lastName: str, givenCourses: List[Course]=[]):
        super().__init__(firstName, lastName)
        self.givenCourses = givenCourses
        print(self.toString() + " named instructor created.") # will be log

    def addGivenCourse(self, course):
        self.givenCourses.append(course)
        print(self.toString() + ": instructor now gives " + course.getCourseName()) # will be log

    def removeGivenCourse(self, course):
        self.givenCourses.remove(course)

    def getGivenCourses(self):
        return self.givenCourses


class Advisor(Instructor):

    def __init__(self, firstName: str, lastName: str, givenCourses: List[Course]=[]):
        super().__init__(firstName, lastName, givenCourses)
        self.students = []
        print(self.getFirstName() + " " + self.getLastName() + " named advisor created.") # will be log

    def completeRegistration(self, student):
        for i in range(len(student.getRequestedCourses())):
            if self.checkQuotaForRegistration(student.getRequestedCourses()[i], student) and self.checkCollision(student, student.getRequestedCourses()[i]):
                student.getTranscript().getEnrolledCourses().append(student.getRequestedCourses()[i])
                student.getRequestedCourses().get(i).getStudents().add(student)
        print(student.getStudentName() + "'s registration successfully completed.") # will be log

    def checkCollision(self, student, course):
        isTrue = True
        for c in student.getTranscript().getEnrolledCourses():
            if course.getCourseSchedule().isCollision(c.getCourseSchedule()):
                course.setCollisionProblem(course.getCollisionProblem() + 1)
                student.getStudentOutput().append("Advisor didn't approve " + course.getCourseCode() + " because of two hours collision with " + c.getCourseCode() + " in schedule")
                print(student.getStudentName() + " couldn't take " + course.getCourseName() + " because of collision with " + c.getCourseName() + ".") # will be log
                isTrue = False
                break
        return isTrue

    def checkQuotaForRegistration(self, course, student):
        if len(course.getStudents()) < course.getQuato():
            return True
        else:
            course.setQuotaProblem(course.getQuotaProblem() + 1)
            student.getStudentOutput().append("The student couldn't register for " + course.getCourseCode() + " because of a quota problem")
            print(student.getStudentName() + " couldn't take " + course.getCourseName() + " because course quota is full ( Quota: " + course.getQuato() + ").") # will be log
            return False

    def getStudents(self):
        return self.students

    def setStudents(self, students):
        self.students = students

    def addStudent(self, student):
        self.students.append(student)

    def removeStudent(self, student):
        self.students.remove(student)

    def getAdvisorName(self):
        return self.getFirstName() + " " + self.getLastName()


class MandatoryCourse(Course):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str,
                 quota: int, semester: int, prerequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota)
        self.__semester = semester
        self.__prerequisites = prerequisites

    def isEligibleToRequest(self, student):
        if (student.getSemester() == self.getSemester()):
            if (not student.getTranscript().hasBeenPassedCourses(self.getPrequisites())):
                self.setFailedPreq(self.getFailedPreq() + 1)
                student.getStudentOutput().add("The system didn't allow " + self.getCourseCode() +
                                               "because student failed prereq." + self.getPrequisites()[
                                                   0].getCourseCode())
                return False
            else:
                return True
        else:
            return False

    def isEligibleToBePreviouslyTaken(self, student):
        return student.getSemester() > self.getSemester() and student.getTranscript().hasBeenPassedCourses(self.getPrequisites())

    # GETTERS AND SETTERS
    def getSemester(self) -> int:
        return self.__semester

    def getPrequisites(self) -> List[Course]:
        return self.__prerequisites


class GraduationProject(MandatoryCourse):  # ICreditRequirement will be added

    def __init__(self, courseName, courseCode, courseCredit, courseDay, courseHour,
                 quota, semester, prequisites, requiredCredits):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester, prequisites)
        self.__requiredCredits = requiredCredits
        print(f"{self.getCourseName()} ({self.getCourseCode()}) named graduation project created.")
    #  logging.info(f"{self.courseName} ({self.courseCode}) named graduation project created.")

    def isEligibleToRequest(self, student):
        return super().isEligibleToRequest(student) and self.checkRequiredCredit(student)

    def checkRequiredCredit(self, student):
        if student.transcript.getCreditCompleted() >= self.requiredCredits:
            return True
        else:
            newFailedCredit = self.getFailedCredits() + 1
            self.setFailedCredits(newFailedCredit)
            student.StudentOutput.append(
                f"The advisor didn't approve graduation project {self.getCourseCode()} because student completed credits < {self.requiredCredits}")
            #  logging.info(f"Student has not enough credits. {student.studentName} cannot enroll {self.courseName}.")
            return False

    @property
    def requiredCredits(self):
        return self.requiredCredits


class NonTechnicalElective(ElectiveCourse):
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int,
                 courseHour: str, quota: int, semesters: List[int]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semesters)

    def isEligibleToRequest(self, student) -> bool:
        return self.semesterControl(student)


#*************************************&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*****************************

class RegistrationSystem:

    def __init__(self):
        self.__currentSemester: str = ""
        self.__advisorList: List[Advisor] = []
        self.__studentList: List[Student] = []
        self.__coursesList: List[Course] = []
        self.__mandatoryCourses: List[MandatoryCourse] = []
        self.__graduationCourses: List[GraduationProject] = []
        self.__technicalElectives: List[TechnicalElective] = []
        self.__facultyTechnicalElectives: List[FacultyTechnicalElective] = []
        self.__nonTechnicalElectives: List[NonTechnicalElective] = []
        self.startSimulation()

    def courseIsThereOrNot(self, courseCode: str): # None ?? return type ??
        c: Course
        for c in self.__coursesList:
            if c.getCourseCode() == courseCode:
                return c
        return None

    def readMandatory(self, input: dict):
        inputCourses: dict = input["MandatoryCourses"]
        for c in inputCourses:
            course = c
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseQuato = course["quota"]
            courseSemester = course["semester"]
            courseCredit = course["credits"]
            courseDay = course["courseDay"]
            courseHour = course["courseHour"]
            prequisitesCourse = []
            prequisites = course["preRequisites"]
            for p in prequisites:
                if self.courseIsThereOrNot(p) is not None:
                    prequisitesCourse.append(self.courseIsThereOrNot(p))

            mandatoryCourse = MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prequisitesCourse)

            self.__coursesList.append(mandatoryCourse)
            self.__mandatoryCourses.append(mandatoryCourse)
            print(mandatoryCourse.getCourseName() + ": Mandatory course is readed from input.json.") # will be log

    def readNTE(self, input: dict):
        inputNTECourses = input["nonTechnicalElectiveCourses"]
        inputNTESemesters = input["NTESemesters"]
        for c in inputNTECourses:
            course = c
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseQuato = course["quota"]
            courseCredit = course["credits"]
            courseDay = course["courseDay"]
            courseHour = course["courseHour"]
            semesterList = []
            for s in inputNTESemesters:
                semesterList.append(s)

            nonTechnicalElective = NonTechnicalElective(courseName, courseCode, courseCredit,
                    courseDay, courseHour, courseQuato, semesterList)
            self.__nonTechnicalElectives.append(nonTechnicalElective)
            self.__coursesList.append(nonTechnicalElective)
            print(nonTechnicalElective.getCourseName() + ": NonTechnical elective course is readed from input.json.") # will be log

    def readTE(self, input: dict):
        inputNTECourses = input["technicalElectiveCourses"]
        inputTESemesters = input["TESemesters"]
        requiredCredit = input["TERequiredCredits"]
        for c in inputNTECourses:
            course = c
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseQuato = course["quota"]
            courseCredit = course["credits"]
            courseDay = course["courseDay"]
            courseHour = course["courseHour"]
            prerequisitesCourse = []
            prerequisties = course["preRequisites"]
            for p in prerequisties:
                if self.courseIsThereOrNot(str(p)) is not None:
                    prerequisitesCourse.append(self.courseIsThereOrNot(str(p)))
            semesterList = []
            for s in inputTESemesters:
                semesterList.append(int(s))
            techElectiveCourse  = TechnicalElective(courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, semesterList, requiredCredit, prerequisitesCourse)
            self.__coursesList.append(techElectiveCourse)
            self.__technicalElectives.append(techElectiveCourse)
            print(techElectiveCourse.getCourseName() + ": Technical elective course is readed from input.json.") # will be log

    def readFTE(self, input: dict):
        inputNTECourses = input["facultyTechnicalElectiveCourses"]
        inputFTESemesters = input["FTESemesters"]
        for c in inputNTECourses:
            course = c
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseQuato = course["quota"]
            courseCredit = course["credits"]
            courseDay = course["courseDay"]
            courseHour = course["courseHour"]
            semesterList = []
            for s in inputFTESemesters:
                semesterList.append(int(s))
            prerequisitesCourse = []
            prerequisites = course["preRequisites"]
            for p in prerequisites:
                if self.courseIsThereOrNot(str(p)) is not None:
                    prerequisitesCourse.append(self.courseIsThereOrNot(str(p)))
            facultyTechnicalElective = FacultyTechnicalElective(courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, semesterList, prerequisitesCourse)
            self.__facultyTechnicalElectives.append(facultyTechnicalElective)
            self.__coursesList.append(facultyTechnicalElective)
            print(facultyTechnicalElective.getCourseName() + ": Faculty technical elective course is readed from input.json.") # will be log

    def readGraduationProject(self, input: dict):
        inputNTECourses = input["graduationProject"]
        for c in inputNTECourses:
            course = c
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseQuato = course["quota"]
            courseSemester = course["semester"]
            courseCredit = course["credits"]
            courseDay = course["courseDay"]
            requiredCredit = course["requiredCredits"]
            courseHour = course["courseHour"]
            prerequisitesCourse = []
            prerequisites  = course["preRequisites"]
            for p in prerequisites:
                if self.courseIsThereOrNot(str(p)) is not None:
                    prerequisitesCourse.append(str(p))
            graduationProject = GraduationProject(courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, courseSemester, prerequisitesCourse, requiredCredit)
            self.__coursesList.append(graduationProject)
            self.__graduationCourses.append(graduationProject)
            print(graduationProject.getCourseName() + ": Graduation project is readed from input.json.") # will be log

    def readAdvisorInput(self, advisor: dict):
        inputAdvisors = advisor["advisors"]
        for a in inputAdvisors:
            advisors = a
            firstName = advisors["firstName"]
            lastName = advisors["lastName"]
            newAdvisor = Advisor(firstName, lastName)
            self.__advisorList.append(newAdvisor)
            print(newAdvisor.getAdvisorName() + ": Advisor is readed from advisor.json.") # will be log

    def assignAdvisor(self, studentList): # parameter type ??
        copyStudentList = []
        for student in studentList:
            copyStudentList.append(student)
        if len(copyStudentList) > 0:
            count = 0
            while len(self.__advisorList) > count:
                if len(copyStudentList) == 0:
                    break
                else:
                    randomNum = random.randint(0, len(copyStudentList) - 1)
                    self.__advisorList[count].addStudent(copyStudentList[randomNum])
                    #print(self.__advisorList)
                    copyStudentList[randomNum].setAdvisor(self.__advisorList[count])
                    print("Student " + copyStudentList[randomNum].getStudentName() + " is assigned advisor "
                            + self.__advisorList[count].getAdvisorName() + ".") # will be log
                    del copyStudentList[randomNum]
                    count += 1
                    if count == len(self.__advisorList):
                        count = 0
        print("All students are assigned a advisor.") # will be log

    def assignInstructor(self, courseList): # parameter type ??
        copyCourseList = []
        for course in courseList:
            copyCourseList.append(course)
        if len(copyCourseList) > 0:
            count = 0
            while len(self.__advisorList) > count:
                if len(copyCourseList) == 0:
                    break
                else:
                    randomNum = random.randint(0, len(copyCourseList) - 1)
                    self.__advisorList[count].addGivenCourse(copyCourseList[randomNum])
                    copyCourseList[randomNum].setCourseInstructor(self.__advisorList[count])
                    print("Course " + copyCourseList[randomNum].getCourseName() + " is assigned instructor "
                            + self.__advisorList[count].getAdvisorName() + ".") # will be log
                    del copyCourseList[randomNum]
                    count += 1
                    if count == len(self.__advisorList):
                        count = 0
        print("All courses are assigned a instructor.") # will be log

    def readStudentInput(self, student: dict):
        inputNames = student["names"]
        inputSurnames = student["surnames"]
        studentNumberPerYear = student["studentNumberPerYear"]
        names = []
        surnames = []
        for n in inputNames:
            names.append(str(n))
        for sn in inputSurnames:
            surnames.append(str(sn))

        #yearList = [2022, 2021, 2020, 2019]
        #for year in yearList:
        #    for i in range(studentNumberPerYear):
        #        self.__studentList.append(Student(names[random.randint(0, len(names) - 1)], surnames[random.randint(0, len(surnames) - 1)], year, i))
        #    print(f"{year} year students are readed successfully.") # will be log

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2022, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)

        print("First year students are readed successfully.")  # will be log

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2021, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)
        print("Second year students are readed successfully.")  # will be log

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2020, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)
        print("Third year students are readed successfully.")  # will be log

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2019, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)
        print("Fourth year students are readed successfully.")  # will be log
        print("All students are readed from students.json.")  # will be log

    def readCurrentSemester(self, input: dict):
        self.__currentSemester = input["CurrentSemester"]
        return self.__currentSemester


    def calculateSemester(self, student) -> int: # parameter type ??
        # Semester control is done with CurrentSemester information from input.json file
        calculatedSemester = 0
        if self.__currentSemester == "fall":
            calculatedSemester = 2 * (student.getCurrentYear() - student.getRegistrationYear() + 1)
        elif self.__currentSemester == "spring":
            calculatedSemester = 2 * ((student.getCurrentYear() + 1) - student.getRegistrationYear())
        print(student.getStudentName() + ": Semester calculated according to the input.") # will be log
        return calculatedSemester

    def getCurrentSemester(self) -> str:
        return self.__currentSemester

    def setCurrentSemester(self, currentSemester: str):
        self.__currentSemester = currentSemester

    def readInput(self):
        try:
            with open("input.json", encoding="utf-8") as input:
                input_jsonToDict = json.load(input)
                self.readMandatory(input_jsonToDict)
                self.readGraduationProject(input_jsonToDict)
                self.readNTE(input_jsonToDict)
                self.readTE(input_jsonToDict)
                self.readFTE(input_jsonToDict)
                self.readCurrentSemester(input_jsonToDict)
                print("input.json file successfully readed.") # will be log

        except IOError:
            print("input.json file couldn't readed.") # will be log
            exit(1)

    def readStudent(self):
        try:
            with open("students.json", encoding="utf-8") as student:
                student_jsonToDict = json.load(student)
                self.readStudentInput(student_jsonToDict)
                print("students.json file successfully readed.") # will be log
        except IOError:
            print("students.json file couldn't readed.") # will be log
            exit(2)

    def readAdvisors(self):
        try:
            with open("advisor.json", encoding="utf-8") as advisor:
                advisor_jsonToDict = json.load(advisor)
                self.readAdvisorInput(advisor_jsonToDict)
                print("advisor.json file successfully readed.") # will be log

        except IOError:
            print("advisor.json file couldn't readed.")  # will be log
            exit(3)

    def simulateSpringAfterFall(self):
        self.setCurrentSemester("spring")
        for c in self.__coursesList:
            c.getStudents().clear()
            c.setCollisionProblem(0)
            c.setQuotaProblem(0)
            c.setFailedCredits(0)
            c.setFailedPreq(0)

        for s in self.__studentList:
            for c in s.getTranscript().getEnrolledCourses():
                intRandomGrade = random.randint(0, 99)
                courseGrade = Grade(c, intRandomGrade)
                s.getTranscript().getTakenCourses()[c] = courseGrade
                s.getTranscript().isCourseCompletedOrFailed(c, courseGrade.getLetter())
                s.getTranscript().calculateCompleteCredit() # ********************************** complete
                s.getTranscript().calculateGpa()

            s.setSemester(s.getSemester() + 1)
            s.getStudentOutput().clear()
            s.getRequestedCourses().clear()
            s.getTranscript().getEnrolledCourses().clear()

        self.requestCoursesForAllStudents()
        self.startRegistration()
        for s in self.__studentList:
            self.createStudentOutput(s)
        self.createDepartmentOutput()

    def createTranscript(self, student): # parameter type ?? # infinite loop....
        for mc in self.__mandatoryCourses:
            if mc.isEligibleToBePreviouslyTaken(student):
                intRandomGrade = random.randint(0, 99)
                courseGrade = Grade(mc, intRandomGrade)
                student.getTranscript().getTakenCourses()[mc] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(mc, courseGrade.getLetter())

        #********************************
        for i in range(1, student.getSemester()):
            nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            while nte in student.getTranscript().getCompletedCourses():

                nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            if nte.semesterCheck(i):
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 44)
                courseGrade = Grade(nte, intRandomGrade)
                student.getTranscript().getTakenCourses()[nte] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(nte, courseGrade.getLetter()) # ********** completed
        # ************************************
        for i in range(1, student.getSemester()):
            fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            while fte in student.getTranscript().getCompletedCourses() or fte in student.getTranscript().getFailedCourses():
                fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            if fte.semesterCheck(i):
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 88)
                courseGrade = Grade(fte, intRandomGrade)
                student.getTranscript().getTakenCourses()[fte] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(fte, courseGrade.getLetter())
        i = 1
        student.getTranscript().calculateCompleteCredit()
        te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
        while i < student.getSemester():
            if te.semesterCheck(i) and te.checkRequiredCredit(student):
                teCount = 0
                if i == 7:
                    while teCount != 2:
                        while te in student.getTranscript().getCompletedCourses() or te in student.getTranscript().getFailedCourses() or student.getTranscript().hasBeenPassedCourses(te.getPrequisites()):
                            te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                        intRandomGrade = random.randint(0, 99)
                        if intRandomGrade >= 90:
                            intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                        elif intRandomGrade < 30:
                            intRandomGrade = random.randint(0, 44)
                        courseGrade = Grade(te, intRandomGrade)
                        student.getTranscript().getTakenCourses()[te] = courseGrade
                        student.getTranscript().isCourseCompletedOrFailed(te, courseGrade.getLetter())
                        teCount += 1
            i += 1

        student.getTranscript().calculateCompleteCredit()
        for gp in self.__graduationCourses:
            if gp.getSemester() < student.getSemester() and gp.checkRequiredCredit(student):
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 44)
                courseGrade = Grade(gp, intRandomGrade)
                student.getTranscript().getTakenCourses[gp] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(gp, courseGrade.getLetter())

        print(student.getStudentName() + ": Student took all courses for his/her past semesters.")  # will be log
        student.getTranscript().calculateCompleteCredit()
        student.getTranscript().calculateGpa()

    def requestCoursesForAllStudents(self):
        # Courses in the curriculum according to semester information, fall or spring are added to the
        # currentSemesterMandotaryCourse Arraylist
        currentSemesterMandatoryCourses = []
        for c in self.__mandatoryCourses:
            if self.__currentSemester == "fall":
                if c.getSemester() % 2 == 0:
                    currentSemesterMandatoryCourses.append(c)
            elif self.__currentSemester == "spring":
                if c.getSemester() % 2 == 0:
                    currentSemesterMandatoryCourses.append(c)
        for s in self.__studentList:
            for mc in currentSemesterMandatoryCourses:
                if mc.isEligibleToRequest(s):
                    s.getRequestedCourses().append(mc)
            print(s.getStudentName() + ": Student requested all mandatory courses for his/her semester.") # will be log

            # Adding the failed courses to the getRequestedCourse Arraylist by checking the semester
            for c in s.getTranscript().getFailedCourses():
                if isinstance(c, MandatoryCourse):
                    if self.__currentSemester == "fall":
                        if cast(MandatoryCourse, c).getSemester() % 2 != 0:
                            if c in s.getTranscript().getCompletedCourses:
                                continue
                            else:
                                s.getRequestedCourses().append(c)
                    elif self.__currentSemester == "spring":
                        if cast(MandatoryCourse, c).getSemester() % 2 == 0:
                            s.getRequestedCourses().append(c)
                if isinstance(c, ElectiveCourse):
                    s.getRequestedCourses().append(c)
            print(s.getStudentName() + ": Student requested all failed courses this semester(if it is open).") # will be log

            for gp in self.__graduationCourses:
                if gp in s.getRequestedCourses():
                    continue
                if gp.isEligibleToRequest(s) and gp.checkRequiredCredit(s):
                    s.getRequestedCourses().append(gp)

            limit = 0
            if s.getSemester() == 7:
                limit = 2
            elif s.getSemester() == 8:
                limit = 3
            for i in range(limit):
                te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                transcript = s.getTranscript()
                while te in transcript.getCompletedCourses or te in transcript.getFailedCourses or te in s.getRequestedCourses():
                    te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                if te.isEligibleToRequest(s) and te.checkRequiredCredit(s):
                    s.getRequestedCourses().append(te)
            info = 0
            if s.getSemester() == 7:
                info = 2
            elif s.getSemester() == 8:
                info = 3
            print(s.getStudentName() + ": Student requested " + str(info) + " technical courses this semester.")

            nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            while nte in s.getTranscript().getCompletedCourses():
                nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            if nte.isEligibleToRequest(s):
                s.getRequestedCourses().append(nte)

            fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            while fte in s.getTranscript().getCompletedCourses():
                fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            if fte.isEligibleToRequest(s):
                s.getRequestedCourses().append(fte)
            # log ??? *************

    def startRegistration(self):
        for s in self.__studentList:
            s.getAdvisor().completeRegistration(s)
        print("All students have completed their course registrations.") # will be log

    def createStudentOutput(self, student): # parameter type??
        pass # ***** after run the program without an error, fill this function *****

    def createDepartmentOutput(self):
        pass # ***** after run the program without an error, fill this function *****

    def startSimulation(self) -> None:
        self.readAdvisors() # successfull
        self.readInput() # successfull
        self.readStudent() # successfull
        self.assignAdvisor(self.__studentList) # successfull
        self.assignInstructor(self.__coursesList) # successfull
        self.requestCoursesForAllStudents()
        self.startRegistration()
        s: Student
        for s in self.__studentList:
            self.createStudentOutput(s)
        self.createDepartmentOutput()

    def getStudentList(self):
        return self.__studentList

    def getCourseList(self):
        return self.__coursesList

    def getAdvisorList(self):
        return self.__advisorList


#***********************************&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&******************************************

class Schedule:

    def __init__(self, courseDay: int, courseHour: str):
        self.__courseDay = courseDay
        self.__courseHour = courseHour
        self.days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"] # instead of enum ????

    def isCollision(self, scheduleOther: object) -> bool: ## look at here ?????? object or Schedule??
        return self.toString() == cast(Schedule, scheduleOther).toString()

    def getCourseDay(self) -> str: # it will be Day or str ??????
        return self.days[self.__courseDay]

    def getCourseHour(self) -> str:
        return self.__courseHour

    def toString(self) -> str:
        return "Day: " + self.getCourseDay() + " Hour: " + self.getCourseHour()


class Student(Person):

    def __init__(self, firstName: str, lastName: str, registrationYear: int, order: int):
        super().__init__(firstName, lastName)
        self.registrationYear = registrationYear
        self.order = order
        self.requestedCourses = []
        self.currentYear = int(datetime.datetime.now().year)
        self.transcript = Transcript()
        self.studentId = StudentId(registrationYear, order)
        self.studentOutput = []
        self.semester = 1
        print("Student created =>  " + "Name: " + self.getStudentName() + " RegistrationYear: " + str(self.getRegistrationYear()) + " Order: " + str(self.getOrder())) # will be log

    def getStudentOutput(self):
        return self.studentOutput

    def setStudentOutput(self, studentOutput):
        self.studentOutput = studentOutput

    def getStudentName(self):
        return self.getFirstName() + " " + self.getLastName()

    def getTranscript(self):
        return self.transcript

    def getStudentId(self):
        return self.studentId

    def getAdvisor(self):
        return self.advisor

    def setAdvisor(self, advisor):
        self.advisor = advisor

    def getRequestedCourses(self):
        return self.requestedCourses

    def getRegistrationYear(self):
        return self.registrationYear

    def getOrder(self):
        return self.order

    def getCurrentYear(self):
        return self.currentYear

    def getSemester(self):
        return self.semester

    def setSemester(self, semester):
        self.semester = semester
        print(self.getStudentName() + ": student semester changed. as " + str(self.semester)) # will be log

class StudentId:

    def __init__(self, year: int, order: int):
        self.__cseCode: str = "1501"
        self.__year: int = year
        self.__order: int = order
        self.id: str = self.createStudentId()

    def createStudentId(self) -> str:
        order_length_dict = {1: "00", 2: "0", 3: ""}
        return self.__cseCode + str(self.__year)[-2:] + order_length_dict[len(str(self.__order))] + str(self.__order)

    def getCseCode(self) -> str:
        return self.__cseCode

    def getYear(self) -> int:
        return self.__year

    def getOrder(self) -> int:
        return self.__order


class TechnicalElective(ElectiveCourse): # ICreditRequirement will be added.****
    def __init__(self, courseName: str, courseCode: str, courseCredit: int, courseDay: int, courseHour: str, quota: int, semester: List[int], requiredCredits: int, prequisites: List[Course]):
        super().__init__(courseName, courseCode, courseCredit, courseDay, courseHour, quota, semester)
        self.__requiredCredits = requiredCredits
        self.__prequisites = prequisites

    def isEligibleToRequest(self, student: Student) -> bool:
        if self.semesterControl(student):
            if not student.getTranscript().hasBeenPassedCourses(self.getPrequisites()):
                self.setFailedPreq(self.getFailedPreq() + 1)
                student.getStudentOutput().append(f"The system didn't allow {self.getCourseCode()} because student failed prereq. {self.getPrequisites()[0].getCourseCode()}")
                return False
            else:
                return True
        else:
            return False

    def checkRequiredCredit(self, student: Student) -> bool:
        if student.getTranscript().getCreditCompleted() >= self.__requiredCredits:
            return True
        else:
            self.setFailedCredits(self.getFailedCredits() + 1)
            student.getStudentOutput().append(f"The advisor didn't approve TE{self.getCourseCode()} because student completed credits < {self.getRequiredCredits()}")
            return False

    def getRequiredCredits(self) -> int:
        return self.__requiredCredits

    def setRequiredCredits(self, requiredCredits: int):
        self.__requiredCredits = requiredCredits

    def getPrequisites(self) -> List[Course]:
        return self.__prequisites

    def setPrequisites(self, prequisites: List[Course]):
        self.__prequisites = prequisites




class Transcript:

    def __init__(self, gpa: float=0.0, creditCompleted: int=0, creditTaken: int=0, takenCourses: dict={}, completedCourses: List[Course]=[], failedCourses: List[Course]=[]):
        self.__gpa = gpa
        self.__creditCompleted = creditCompleted
        self.__creditTaken = creditTaken
        self.__takenCourses = takenCourses
        self.__completedCourses = completedCourses
        self.__failedCourses = failedCourses
        self.__enrolledCourses = []

    def calculateGpa(self):
        gradeMultiplication = 0
        totalCredit = 0
        studentGpa = 0

        for course, grade in self.__takenCourses.items():
            totalCredit += course.getCourseCredit()
            if grade.getLetter() == "AA":
                gradeMultiplication += course.getCourseCredit() * 4.0
            elif grade.getLetter() == "BA":
                gradeMultiplication += course.getCourseCredit() * 3.5
            elif grade.getLetter() == "BB":
                gradeMultiplication += course.getCourseCredit() * 3.0
            elif grade.getLetter() == "CB":
                gradeMultiplication += course.getCourseCredit() * 2.5
            elif grade.getLetter() == "CC":
                gradeMultiplication += course.getCourseCredit() * 2.0
            elif grade.getLetter() == "DC":
                gradeMultiplication += course.getCourseCredit() * 1.5
            elif grade.getLetter() == "DD":
                gradeMultiplication += course.getCourseCredit() * 1.0
            elif grade.getLetter() == "FD":
                gradeMultiplication += course.getCourseCredit() * 0.5
            elif grade.getLetter() == "FF":
                gradeMultiplication += course.getCourseCredit() * 0.0
            elif grade.getLetter() == "FG":
                gradeMultiplication += course.getCourseCredit() * 0.0
            elif grade.getLetter() == "DZ":
                gradeMultiplication += course.getCourseCredit() * 0.0
        studentGpa = round(gradeMultiplication / totalCredit * 100.0) / 100.0
        self.__gpa = studentGpa
        return studentGpa

    def calculateCompleteCredit(self):
        completedCredit = 0
        for course in self.__completedCourses:
            completedCredit += course.getCourseCredit()
        self.__creditCompleted = completedCredit
        return completedCredit

    def isCourseCompletedOrFailed(self, course, letter):
        if letter == "AA" or letter == "BA" or letter == "BB" or letter == "CB" or letter == "CC" or letter == "DC" or letter == "DD":
            self.__completedCourses.append(course)
        else:
            self.__failedCourses.append(course)

    def hasBeenPassedCourse(self, course):
        if course == None:
            return True
        return course in self.__completedCourses

    def hasBeenPassedCourses(self, courses): # infinite loop
        for course in courses:
            if not self.hasBeenPassedCourse(course):
                return False
        return True

    def getGpa(self):
        return self.__gpa

    def setGpa(self, gpa):
        self.__gpa = gpa

    def getCreditCompleted(self):
        return self.__creditCompleted

    def setCreditCompleted(self, creditCompleted):
        self.__creditCompleted = creditCompleted

    def getTakenCourses(self):
        return self.__takenCourses

    def getCompletedCourses(self):
        return self.__completedCourses

    def getFailedCourses(self):
        return self.__failedCourses

    def getEnrolledCourses(self):
        return self.__enrolledCourses



def app():
    # a = StudentId(2015, 1)
    # s = Schedule(0, "09:30")
    # print(a.id)
    # print(s.toString())
    rs = RegistrationSystem()
    #print("Advisor Size:", len(rs.getAdvisorList()))
    #print("Course Size:", len(rs.getCourseList()))
    #print("Student Size:", len(rs.getStudentList()))
    #total = 0
    #for i in rs.getAdvisorList():
    #    total += len(i.students)
    #print(total)

    for i in rs.getCourseList():
        print(cast(TechnicalElective, i).getPrequisites())

app()
