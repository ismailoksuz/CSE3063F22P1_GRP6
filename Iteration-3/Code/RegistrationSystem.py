import json
import random

from typing import List, cast
from Course import Course
from ElectiveCourse import ElectiveCourse
from Grade import Grade
from Advisor import Advisor
from MandatoryCourse import MandatoryCourse
from GraduationProject import GraduationProject
from NonTechnicalElective import NonTechnicalElective
from Student import Student
from TechnicalElective import TechnicalElective
from FacultyTechnicalElective import FacultyTechnicalElective


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
            prerequisitesCourse = []
            prerequisites = course["preRequisites"]
            for p in prerequisites:
                if self.courseIsThereOrNot(p) is not None:
                    prerequisitesCourse.append(self.courseIsThereOrNot(p))

            mandatoryCourse = MandatoryCourse(courseName, courseCode, courseCredit, courseDay,
                    courseHour, courseQuato, courseSemester, prerequisitesCourse)

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
                    prerequisitesCourse.append(p)
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
            # calculatedSemester = 2 * (student.getCurrentYear() - student.getRegistrationYear() + 1)
            calculatedSemester = 2 * (2022 - student.getRegistrationYear() + 1)

        elif self.__currentSemester == "spring":
            # calculatedSemester = 2 * ((student.getCurrentYear()) - student.getRegistrationYear())
            calculatedSemester = 2 * (2022 - student.getRegistrationYear())

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
            counter = 0
            while nte in student.getTranscript().getCompletedCourses() or nte in student.getTranscript().getFailedCourses():
                if counter == len(student.getTranscript().getCompletedCourses()):
                    break
                nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
                counter += 1
            # for nteCourse in student.getTranscript().getCompletedCourses() :
            #     if(nteCourse == nte):
            #         nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
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
            counter = 0
            while fte in student.getTranscript().getCompletedCourses() or fte in student.getTranscript().getFailedCourses():
                if counter == len(student.getTranscript().getCompletedCourses()):
                    break
                fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
                counter +=1
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
                        # deneme
                        counter = 0
                        while te in student.getTranscript().getCompletedCourses() or te in student.getTranscript().getFailedCourses() or student.getTranscript().hasBeenPassedCourses(te.getPrerequisites()):

                            if counter == len(student.getTranscript().getCompletedCourses()):
                                break
                            te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                            counter += 1
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
                student.getTranscript().getTakenCourses()[gp] = courseGrade
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
                            if c in s.getTranscript().getCompletedCourses():
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
                counter = 0
                while te in transcript.getCompletedCourses() or te in transcript.getFailedCourses() or te in s.getRequestedCourses():
                    if counter == len(transcript.getCompletedCourses()):
                        break
                    te = self.__technicalElectives[random.randint(0, len(self.__technicalElectives) - 1)]
                    counter += 1
                if te.isEligibleToRequest(s) and te.checkRequiredCredit(s):
                    s.getRequestedCourses().append(te)
            info = 0
            if s.getSemester() == 7:
                info = 2
            elif s.getSemester() == 8:
                info = 3
            print(s.getStudentName() + ": Student requested " + str(info) + " technical courses this semester.")

            nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
            counter = 0
            while nte in s.getTranscript().getCompletedCourses():
                if counter == len(s.getTranscript().getCompletedCourses()):
                    break
                nte = self.__nonTechnicalElectives[random.randint(0, len(self.__nonTechnicalElectives) - 1)]
                counter +=1
            if nte.isEligibleToRequest(s):
                s.getRequestedCourses().append(nte)

            fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
            counter1 = 0
            while fte in s.getTranscript().getCompletedCourses():
                if counter1 == len(s.getTranscript().getCompletedCourses()):
                    break
                fte = self.__facultyTechnicalElectives[random.randint(0, len(self.__facultyTechnicalElectives) - 1)]
                counter1 += 1
            if fte.isEligibleToRequest(s):
                s.getRequestedCourses().append(fte)
            # log ??? *************



    def startRegistration(self):
        for s in self.__studentList:
            s.getAdvisor().completeRegistration(s)
        print("All students have completed their course registrations.") # will be log

    def createStudentOutput(self, student): # parameter type??

        # Add student info
        studentDict = {
            "StudentName": student.toString(),
            "StudentId": student.getStudentId().toString(),
            "StudentEmail": student.getEmail(),
            "StudentPhoneNumber": student.getPhoneNumber(),
            "SemesterNumber": student.getSemester(),
            "CompletedCredits": student.getTranscript().getCreditCompleted(),
            "Gpa": student.getTranscript().getGpa(),
            "AdvisorName": student.getAdvisor().getAdvisorName()
        }

        # Add taken course
        jsonListPastCourses = []
        for value in student.getTranscript().getTakenCourses().values():
            jsonDictTakenCourse = {
                "LetterGrade": value.getLetter(),
                "CourseName": value.getCourse().getCourseName(),
                "Course": value.getCourse().getCourseCode()
            }
            jsonListPastCourses.append(jsonDictTakenCourse)

        studentDict["Taken Course"] = jsonListPastCourses

        # Add requested courses
        jsonListRequestedCourses = []
        for value in student.getRequestedCourses():
            jsonDictRequestedCourses = {
                "Course": value.getCourseName()
            }
            jsonListRequestedCourses.append(jsonDictRequestedCourses)
        studentDict["Requested Course"] = jsonListRequestedCourses

        # Add enrolled courses
        jsonListEnrolledCourses = []
        for value in student.getTranscript().getEnrolledCourses():
            jsonDictEnrolledCourses = {
                "Course": value.getCourseName()
            }
            jsonListEnrolledCourses.append(jsonDictEnrolledCourses)
        studentDict["Enrolled Course"] = jsonListEnrolledCourses

        # Add student output
        jsonListStudentOutput = []
        for value in student.getStudentOutput():
            jsonListStudentOutput.append(value)
        studentDict["Output"] = jsonListStudentOutput



        jsonObject = json.dumps(studentDict, indent=4, ensure_ascii=False)

        with open(f"{student.getStudentId().toString()}.json", "w", encoding='utf8') as outfile:
            outfile.write(jsonObject)





    def createDepartmentOutput(self):
        jsonDepartment = {}
        for course in self.__coursesList:
            if course in self.__mandatoryCourses:
                if self.__currentSemester == "fall" and course.getSemester() % 2 != 0 :
                    jsonListDepartment = []
                    jsonListDepartment.append(f"{len(course.getStudents())}  Students could register for {course.getCourseCode()}  successfully.")
                    jsonListDepartment.append(f"{course.getQuotaProblem()} Students couldn't register for Students couldn't register for {course.getCourseCode()} due to the quota problems.")
                    jsonListDepartment.append(f"{course.getCollisionProblem()} Students couldn't register for {course.getCourseCode()} due to the collision problems.")
                    jsonListDepartment.append(f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                    if course in self.__graduationCourses:
                        jsonListDepartment.append(f"{course.getFailedCredits()} Students couldn't register for {course.getCourseCode()} due to the failed credit problems.")
                    jsonDepartment[f"{course.getCourseName()}"] = jsonListDepartment
                elif self.__currentSemester == "spring" and course.getSemester() % 2 == 0 :
                    jsonListDepartment = []
                    jsonListDepartment.append(
                        f"{len(course.getStudents())}  Students could register for {course.getCourseCode()}  successfully.")
                    jsonListDepartment.append(
                        f"{course.getQuotaProblem()} Students couldn't register for Students couldn't register for {course.getCourseCode()} due to the quota problems.")
                    jsonListDepartment.append(
                        f"{course.getCollisionProblem()} Students couldn't register for {course.getCourseCode()} due to the collision problems.")
                    jsonListDepartment.append(
                        f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                    if course in self.__graduationCourses:
                        jsonListDepartment.append(
                            f"{course.getFailedCredits()} Students couldn't register for {course.getCourseCode()} due to the failed credit problems.")
                    jsonDepartment[f"{course.getCourseName()}"] = jsonListDepartment

            else:
                jsonListDepartment = []
                jsonListDepartment.append(
                    f"{len(course.getStudents())}  Students could register for {course.getCourseCode()}  successfully.")
                jsonListDepartment.append(
                    f"{course.getQuotaProblem()} Students couldn't register for Students couldn't register for {course.getCourseCode()} due to the quota problems.")
                jsonListDepartment.append(
                    f"{course.getCollisionProblem()} Students couldn't register for {course.getCourseCode()} due to the collision problems.")
                if course in self.__technicalElectives:
                    jsonListDepartment.append(f"{course.getFailedCredits()}  Students couldn't register for {course.getCourseCode()} due to the failed credit problems.")
                    jsonListDepartment.append(f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                jsonDepartment[f"{course.getCourseName()}"] = jsonListDepartment



        jsonObject = json.dumps(jsonDepartment, indent=4, ensure_ascii=False)

        with open(f"DEPARTMENT_OUTPUT_{self.__currentSemester.upper()}.json", "w", encoding='utf8') as outfile:
            outfile.write(jsonObject)

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
        #
        self.createDepartmentOutput()

    def getStudentList(self):
        return self.__studentList

    def getCourseList(self):
        return self.__coursesList

    def getAdvisorList(self):
        return self.__advisorList




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

    # for i in rs.getCourseList():
    #     print(cast(TechnicalElective, i).getPrequisites())

app()