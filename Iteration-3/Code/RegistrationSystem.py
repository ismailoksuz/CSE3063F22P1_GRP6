import json
import random
from typing import List, cast
from advisor import Advisor
from student import Student
from course import Course
from GraduationProject import GraduationProject
from TechnicalElective import TechnicalElective
from NonTechnicalElective import NonTechnicalElective
from mandatoryCourse import MandatoryCourse
from FacultyTechnicalElective import FacultyTechnicalElective
from Grade import Grade
from ElectiveCourse import ElectiveCourse
class RegistrationSystem:

    def __init__(self):
        self.__currentSemester: str = ""
        self.__advisorList: List[Advisor] = []
        self.__studentList: List[Student] = []
        self.__coursesList: List[Course] = []
        self.__mandatoryCourses: List[Course] = []
        self.__graduationCourses: List[GraduationProject] = []
        self.__technicalElectives: List[TechnicalElective] = []
        self.__facultyTechnicalElectives: List[TechnicalElective] = []
        self.__nonTechnicalElectives: List[NonTechnicalElective] = []
        self.startSimulation()

    def courseIsThereOrNot(self, courseCode: str): # None ?? return type ??
        c: Course
        for c in self.__coursesList:
            if c.getCourseCode() == courseCode:
                return c
        return None

    # def readMandatory(self, input: dict):
    #     inputCourses: dict = input["MandatoryCourses"]
    #     for c in inputCourses:
    #         course = c
    #         courseName = course["courseName"]
    #         courseCode = course["courseCode"]
    #         courseQuato = course["quota"]
    #         courseSemester = course["semester"]
    #         courseCredit = course[credits]
    #         courseDay = course["courseDay"]
    #         courseHour = course["courseHour"]
    #         prequisitesCourse = []
    #         prequisites = course["preRequisites"]
    #         for p in prequisites:
    #             if self.courseIsThereOrNot(p) is not None:
    #                 prequisitesCourse.append(self.courseIsThereOrNot(p))
    #
    #         mandatoryCourse = MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
    #                 courseHour, courseQuato, courseSemester, prequisitesCourse)
    #
    #         self.__coursesList.append(mandatoryCourse)
    #         self.__mandatoryCourses.append(mandatoryCourse)
    #         print(mandatoryCourse.getCourseName() + ": Mandatory course is readed from input.json.") # will be log

    # def readNTE(self, input: dict):
    #     inputNTECourses = input["nonTechnicalElectiveCourses"]
    #     inputNTESemesters = inputNTECourses["NTESemesters"]
    #     for c in inputNTECourses:
    #         course = c
    #         courseName = course["courseName"]
    #         courseCode = course["courseCode"]
    #         courseQuato = course["quota"]
    #         courseCredit = course["credits"]
    #         courseDay = course["courseDay"]
    #         courseHour = course["coursHour"]
    #         semesterList = []
    #         for s in inputNTESemesters:
    #             semesterList.append(s)
    #
    #         nonTechnicalElective = NonTechnicalElective(courseName, courseCode, courseCredit,
    #                 courseDay, courseHour, courseQuato, semesterList)
    #         self.__nonTechnicalElectives.append(nonTechnicalElective)
    #         self.__coursesList.append(nonTechnicalElective)
    #         print(nonTechnicalElective.getCourseName() + ": NonTechnical elective course is readed from input.json.") # will be log

    # def readTE(self, input: dict):
    #     inputNTECourses = input["technicalElectiveCourses"]
    #     inputTESemesters = input["TESemesters"]
    #     requiredCredit = input["TERequiredCredits"]
    #     for c in inputNTECourses:
    #         course = c
    #         courseName = course["courseName"]
    #         courseCode = course["courseCode"]
    #         courseQuato = course["quota"]
    #         courseCredit = course["credits"]
    #         courseDay = course["courseDay"]
    #         courseHour = course["courseHour"]
    #         prerequisitesCourse = []
    #         prerequisties = course["preRequisites"]
    #         for p in prerequisties:
    #             if self.courseIsThereOrNot(str(p)) is not None:
    #                 prerequisitesCourse.append(self.courseIsThereOrNot(str(p)))
    #         semesterList = []
    #         for s in inputTESemesters:
    #             semesterList.append(int(s))
    #         techElectiveCourse  = TechnicalElective(courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, semesterList, requiredCredit, prerequisitesCourse)
    #         self.__coursesList.append(techElectiveCourse)
    #         self.__technicalElectives.append(techElectiveCourse)
    #         print(techElectiveCourse.getCourseName() + ": Technical elective course is readed from input.json.") # will be log

    # def readFTE(self, input: dict):
    #     inputNTECourses = input["facultyTechnicalElectiveCourses"]
    #     inputFTESemesters = input["FTESemesters"]
    #     for c in inputNTECourses:
    #         course = c
    #         courseName = course["courseName"]
    #         courseCode = course["courseCode"]
    #         courseQuato = course["quota"]
    #         courseCredit = course["credits"]
    #         courseDay = course["courseDay"]
    #         courseHour = course["courseHour"]
    #         semesterList = []
    #         for s in inputFTESemesters:
    #             semesterList.append(int(s))
    #         prerequisitesCourse = []
    #         prerequisites = course["preRequisites"]
    #         for p in prerequisites:
    #             if self.courseIsThereOrNot(str(p)) is not None:
    #                 prerequisitesCourse.append(self.courseIsThereOrNot(str(p)))
    #         facultyTechnicalElective = FacultyTechnicalElective(courseName, courseCode,
    #                 courseCredit, courseDay, courseHour, courseQuato, semesterList, prerequisitesCourse)
    #         self.__facultyTechnicalElectives.append(facultyTechnicalElective)
    #         self.__coursesList.append(facultyTechnicalElective)
    #         print(facultyTechnicalElective.getCourseName() + ": Faculty technical elective course is readed from input.json.") # will be log

    # def readGraduationProject(self, input: dict):
    #     inputNTECourses = input["graduationProject"]
    #     for c in inputNTECourses:
    #         course = c
    #         courseName = course["courseName"]
    #         courseCode = course["courseCode"]
    #         courseQuato = course["quota"]
    #         courseSemester = course["semester"]
    #         courseCredit = course["credits"]
    #         courseDay = course["courseDay"]
    #         requiredCredit = course["requiredCredits"]
    #         courseHour = course["courseHour"]
    #         prerequisitesCourse = []
    #         prerequisites  = course["preRequisites"]
    #         for p in prerequisites:
    #             if self.courseIsThereOrNot(str(p)) is not None:
    #                 prerequisitesCourse.append(str(p))
    #         graduationProject = GraduationProject(courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, courseSemester, prerequisitesCourse, requiredCredit);
    #         self.__coursesList.append(graduationProject)
    #         self.__graduationCourses.append(graduationProject)
    #         print(graduationProject.getCourseName() + ": Graduation project is readed from input.json.") # will be log

    # def readAdvisorInput(self, advisor: dict):
    #     inputAdvisors = advisor["advisors"]
    #     for a in inputAdvisors:
    #         advisors = a
    #         firstName = advisors["firstName"]
    #         lastName = advisors["lastName"]
    #         newAdvisor = Advisor(firstName, lastName)
    #         self.__advisorList.append(newAdvisor)
    #         print(newAdvisor.getAdvisorName() + ": Advisor is readed from advisor.json.") # will be log

    # Done
    def assignAdvisor(self): # parameter type ??
        copyStudentList = []
        for student in Student.allStudent:
            copyStudentList.append(student)
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
                            + self.__advisorList[count].getAdvisorName() + ".") # will be log
                    del copyStudentList[randomNum]
                    count += 1
                    if count == len(Advisor.allAdvisors):
                        count = 0
        print("All students are assigned a advisor.") # will be log
    # Done
    def assignInstructor(self): # parameter type ??
        copyCourseList = []
        for course in Course.allCourses:
            copyCourseList.append(course)
        if len(copyCourseList) > 0:
            count = 0
            while len(Course.allCourses) > count:
                if len(copyCourseList) == 0:
                    break
                else:
                    randomNum = random.randint(0, len(copyCourseList) - 1)
                    Advisor.allAdvisors[count].addGivenCourse(copyCourseList[randomNum])
                    copyCourseList[randomNum].setCourseInstructor(Advisor.allAdvisors[count])
                    print("Course " + copyCourseList[randomNum].getCourseName() + " is assigned instructor "
                            + Advisor.allAdvisors[count].getAdvisorName() + ".") # will be log
                    del copyCourseList[randomNum]
                    count += 1
                    if count == len(Advisor.allAdvisors):
                        count = 0
        print("All courses are assigned a instructor.") # will be log

    # def readStudentInput(self, student: dict):
    #     inputNames = student["names"]
    #     inputSurnames = student["surnames"]
    #     studentNumberPerYear = student["studentNumberPerYear"]
    #     names = []
    #     surnames = []
    #     for n in inputNames:
    #         names.append(str(n))
    #     for sn in inputSurnames:
    #         surnames.append(str(sn))
    #
    #     for i in range(1, studentNumberPerYear + 1):
    #         newStudent = Student(names[random.randint(0, len(names) - 1)], surnames[random.randint(0, len(surnames) - 1)], 2022, i)
    #         self.__studentList.append(newStudent)
    #         newStudent.setSemester(self.calculateSemester(newStudent))
    #         self.createTranscript(student)
    #
    #     print("First year students are readed successfully.") #will be log
    #
    #     for i in range(1, studentNumberPerYear + 1):
    #         newStudent = Student(names[random.randint(0, len(names) - 1)], surnames[random.randint(0, len(surnames) - 1)], 2021, i)
    #         self.__studentList.append(newStudent)
    #         newStudent.setSemester(self.calculateSemester(newStudent))
    #         self.createTranscript(student)
    #     print("Second year students are readed successfully.") # will be log
    #
    #     for i in range(1, studentNumberPerYear + 1):
    #         newStudent = Student(names[random.randint(0, len(names) - 1)], surnames[random.randint(0, len(surnames) - 1)], 2020, i)
    #         self.__studentList.append(newStudent)
    #         newStudent.setSemester(self.calculateSemester(newStudent))
    #         self.createTranscript(student)
    #     print("Third year students are readed successfully.") # will be log
    #
    #     for i in range(1, studentNumberPerYear + 1):
    #         newStudent = Student(names[random.randint(0, len(names) - 1)], surnames[random.randint(0, len(surnames) - 1)], 2019, i)
    #         self.__studentList.append(newStudent)
    #         newStudent.setSemester(self.calculateSemester(newStudent))
    #         self.createTranscript(student)
    #     print("Fourth year students are readed successfully.") # will be log
    #     print("All students are readed from students.json.") # will be log

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

    # def readInput(self):
    #     try:
    #         with open("input.json", encoding="utf-8") as input:
    #             input_jsonToDict = json.load(input)
    #             self.readMandatory(input_jsonToDict)
    #             self.readGraduationProject(input_jsonToDict)
    #             self.readNTE(input_jsonToDict)
    #             self.readTE(input_jsonToDict)
    #             self.readFTE(input_jsonToDict)
    #             self.readCurrentSemester(input_jsonToDict)
    #             print("input.json file successfully readed.") # will be log
    #
    #     except IOError:
    #         print("input.json file couldn't readed.") # will be log
    #         exit(1)

    # def readStudent(self):
    #     try:
    #         with open("students.json", encoding="utf-8") as student:
    #             student_jsonToDict = json.load(student)
    #             self.readStudentInput(student_jsonToDict)
    #             print("students.json file successfully readed.") # will be log
    #     except IOError:
    #         print("students.json file couldn't readed.") # will be log
    #         exit(2)
    #
    # def readAdvisors(self):
    #     try:
    #         with open("advisor.json", encoding="utf-8") as advisor:
    #             advisor_jsonToDict = json.load(advisor)
    #             self.readAdvisorInput(advisor_jsonToDict)
    #             print("advisor.json file successfully readed.") # will be log
    #
    #     except IOError:
    #         print("advisor.json file couldn't readed.")  # will be log
    #         exit(3)

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
                s.getTranscript().getTakenCourses[c] = courseGrade
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

    def createTranscript(self, student): # parameter type ??
        for mc in self.__mandatoryCourses:
            if mc.isEligibleToBePreviouslyTaken(student):
                intRandomGrade = random.randint(0, 99)
                courseGrade = Grade(mc, intRandomGrade)
                student.getTranscript().getTakenCourses[mc] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(mc, courseGrade.getLetter())

        for i in range(1, student.getSemester()):
            nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            while nte in student.getTranscript().getCompletedCourses:
                nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            if nte.semesterCheck(i):
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 44)
                courseGrade = Grade(nte, intRandomGrade)
                student.getTranscript().getTakenCourses[nte] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(nte, courseGrade.getLetter()) # ********** completed

        for i in range(1, student.getSemester()):
            fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            while fte in student.getTranscript().getCompletedCourses or fte in student.getTranscript().getFailedCourses():
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
        student.getTranscript().completeCredit()
        te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
        while i < student.getSemester():
            if te.semesterCheck(i) and te.checkRequiredCredit(student):
                teCount = 0
                if i == 7:
                    while teCount != 2:
                        while te in student.getTranscript().getCompletedCourses() or te in student.getTranscript().getFailedCourses() or student.getTranscript().hasBeenPassedCourses(te.getPrequisties()):
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
                    s.getRequestedCourses.append(mc)
            print(s.getStudentName() + ": Student requested all mandatory courses for his/her semester.") # will be log

            # Adding the failed courses to the getRequestedCourse Arraylist by checking the semester
            for c in s.getTranscript().getFailedCourses():
                if isinstance(c, MandatoryCourse):
                    if self.__currentSemester == "fall":
                        if cast(MandatoryCourse, c).getSemester() % 2 != 0:
                            if c in s.getTranscript().getCompletedCourses:
                                continue
                            else:
                                s.getRequestedCourses.append(c)
                    elif self.__currentSemester == "spring":
                        if cast(MandatoryCourse, c).getSemester() % 2 == 0:
                            s.getRequestedCourses.append(c)
                if isinstance(c, ElectiveCourse):
                    s.getRequestedCourses.append(c)
            print(s.getStudentName() + ": Student requested all failed courses this semester(if it is open).") # will be log

            for gp in self.__graduationCourses:
                if gp in s.getRequestedCourses:
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
                while te in transcript.getCompletedCourses() or te in transcript.getFailedCourses() or te in s.getRequestedCourses():
                    te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                if te.isEligibleToRequest(s) and te.checkRequiredCredit(s):
                    s.getRequestedCourses.append(te)
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
                s.getRequestedCourses.append(fte)
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
        self.readAdvisors()
        self.readInput()
        self.readStudent()
        self.assignAdvisor(self.__studentList)
        self.assignInstructor(self.__coursesList)
        self.requestCoursesForAllStudents()
        self.startRegistration()
        s: Student
        for s in self.__studentList:
            self.createStudentOutput(s)
        self.createDepartmentOutput()