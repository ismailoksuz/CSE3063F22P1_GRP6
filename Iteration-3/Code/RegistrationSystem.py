import json
import logging
import os
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
from Internship import Internship


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
        self.__internships: List[Internship] = []
        self.startSimulation()

    def courseIsThereOrNot(self, courseCode: str):
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
            logging.info(mandatoryCourse.getCourseName() +
                         ": Mandatory course is readed from input.json.")

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
            logging.info(nonTechnicalElective.getCourseName(
            ) + ": NonTechnical elective course is readed from input.json.")

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
            techElectiveCourse = TechnicalElective(
                courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, semesterList, requiredCredit, prerequisitesCourse)
            self.__coursesList.append(techElectiveCourse)
            self.__technicalElectives.append(techElectiveCourse)
            logging.info(techElectiveCourse.getCourseName(
            ) + ": Technical elective course is readed from input.json.")

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
            facultyTechnicalElective = FacultyTechnicalElective(
                courseName, courseCode, courseCredit, courseDay, courseHour, courseQuato, semesterList, prerequisitesCourse)
            self.__facultyTechnicalElectives.append(facultyTechnicalElective)
            self.__coursesList.append(facultyTechnicalElective)
            logging.info(facultyTechnicalElective.getCourseName(
            ) + ": Faculty technical elective course is readed from input.json.")

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
            prerequisites = course["preRequisites"]
            for p in prerequisites:
                if self.courseIsThereOrNot(str(p)) is not None:
                    prerequisitesCourse.append(p)
            graduationProject = GraduationProject(courseName, courseCode, courseCredit, courseDay,
                                                  courseHour, courseQuato, courseSemester, prerequisitesCourse, requiredCredit)
            self.__coursesList.append(graduationProject)
            self.__mandatoryCourses.append(graduationProject)
            self.__graduationCourses.append(graduationProject)
            logging.info(graduationProject.getCourseName() +
                         ": Graduation project is readed from input.json.")

    def readInternship(self, input: dict):
        inputInternships = input["Internships"]
        for course in inputInternships:
            courseName = course["courseName"]
            courseCode = course["courseCode"]
            courseCredit = course["credits"]
            courseSemester = course["semester"]
            internship = Internship(
                courseName, courseCode, courseCredit, courseSemester, 30)
            # self.__coursesList.append(internship)
            # self.__mandatoryCourses.append(internship)
            self.__internships.append(internship)
            logging.info(internship.getCourseName() +
                         ": internship is readed from input.json.")

    def readAdvisorInput(self, advisor: dict):
        inputAdvisors = advisor["advisors"]
        i = 1
        for a in inputAdvisors:
            advisors = a
            firstName = advisors["firstName"]
            lastName = advisors["lastName"]
            newAdvisor = Advisor(firstName, lastName)
            newAdvisor.setinstructorId("1501" + str(i))
            i += 1
            self.__advisorList.append(newAdvisor)
            logging.info(newAdvisor.getAdvisorName() +
                         ": Advisor is readed from advisor.json.")

    def assignAdvisor(self, studentList):
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
                    if isinstance(copyStudentList[randomNum], Internship):
                        break
                    self.__advisorList[count].addStudent(
                        copyStudentList[randomNum])
                    # logging.info(self.__advisorList)
                    copyStudentList[randomNum].setAdvisor(
                        self.__advisorList[count])
                    logging.info("Student " + copyStudentList[randomNum].getStudentName() + " is assigned advisor "
                                 + self.__advisorList[count].getAdvisorName() + ".")
                    del copyStudentList[randomNum]
                    count += 1
                    if count == len(self.__advisorList):
                        count = 0
        logging.info("All students are assigned a advisor.")

    def assignInstructor(self, courseList):
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
                    if isinstance(copyCourseList[randomNum], Internship):
                        break
                    self.__advisorList[count].addGivenCourse(
                        copyCourseList[randomNum])
                    copyCourseList[randomNum].setCourseInstructor(
                        self.__advisorList[count])
                    logging.info("Course " + copyCourseList[randomNum].getCourseName() + " is assigned instructor "
                                 + self.__advisorList[count].getAdvisorName() + ".")
                    del copyCourseList[randomNum]
                    count += 1
                    if count == len(self.__advisorList):
                        count = 0
        logging.info("All courses are assigned a instructor.")

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

        logging.info("First year students are readed successfully.")

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2021, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)

        logging.info("Second year students are readed successfully.")

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2020, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)

        logging.info("Third year students are readed successfully.")

        for i in range(1, studentNumberPerYear + 1):
            newStudent = Student(names[random.randint(0, len(names) - 1)],
                                 surnames[random.randint(0, len(surnames) - 1)], 2019, i)
            self.__studentList.append(newStudent)
            newStudent.setSemester(self.calculateSemester(newStudent))
            self.createTranscript(newStudent)

        logging.info("Fourth year students are readed successfully.")
        logging.info("All students are readed from students.json.")

    def readCurrentSemester(self, input: dict):
        self.__currentSemester = input["CurrentSemester"]
        return self.__currentSemester

    def calculateSemester(self, student) -> int:
        # Semester control is done with CurrentSemester information from input.json file
        calculatedSemester = 0
        if self.__currentSemester == "fall":
            # calculatedSemester = 2 * (student.getCurrentYear() - student.getRegistrationYear() + 1)
            calculatedSemester = 2 * \
                (student.getCurrentYear() - student.getRegistrationYear() - 1) + 1

        elif self.__currentSemester == "spring":
            # calculatedSemester = 2 * ((student.getCurrentYear()) - student.getRegistrationYear())
            calculatedSemester = 2 * \
                (student.getCurrentYear() - student.getRegistrationYear())

        logging.info(student.getStudentName() +
                     ": Semester calculated according to the input.")
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
                self.readInternship(input_jsonToDict)
                self.readCurrentSemester(input_jsonToDict)
                logging.info("input.json file successfully readed.")

        except IOError:
            logging.info("input.json file couldn't readed.")
            exit(1)

    def readStudent(self):
        try:
            with open("students.json", encoding="utf-8") as student:
                student_jsonToDict = json.load(student)
                self.readStudentInput(student_jsonToDict)
                logging.info("students.json file successfully readed.")
        except IOError:
            logging.info("students.json file couldn't readed.")
            exit(2)

    def readAdvisors(self):
        try:
            with open("advisor.json", encoding="utf-8") as advisor:
                advisor_jsonToDict = json.load(advisor)
                self.readAdvisorInput(advisor_jsonToDict)
                logging.info("advisor.json file successfully readed.")

        except IOError:
            logging.info("advisor.json file couldn't readed.")
            exit(3)

    def simulateSpringAfterFall(self):
        self.setCurrentSemester("spring")
        for c in self.__coursesList:
            c.getStudents().clear()
            c.setCollisionProblem(0)
            c.setQuotaProblem(0)
            c.setFailedCredits(0)
            c.setFailedPreq(0)
            c.setEnrolledCourseLimit(0)

        for s in self.__studentList:
            for c in s.getTranscript().getEnrolledCourses():
                intRandomGrade = random.randint(0, 99)
                courseGrade = Grade(c, intRandomGrade)
                s.getTranscript().getTakenCourses()[c] = courseGrade
                s.getTranscript().isCourseCompletedOrFailed(c, courseGrade.getLetter())
                s.getTranscript().calculateCompleteCredit()
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

    def createTranscript(self, student: Student):

        for mc in self.__mandatoryCourses:
            if mc.isEligibleToBePreviouslyTaken(student):
                intRandomGrade = random.randint(0, 99)
                courseGrade = Grade(mc, intRandomGrade)
                student.getTranscript().getTakenCourses()[mc] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(mc, courseGrade.getLetter())
                logging.info(student.toString() + " " + mc.getCourseName() +
                             " " + courseGrade.getLetter())

        for i in range(1, student.getSemester()):
            nte = self.__nonTechnicalElectives[random.randint(
                0, len(self.__nonTechnicalElectives) - 1)]
            counter = 0
            while nte in student.getTranscript().getCompletedCourses() or nte in student.getTranscript().getFailedCourses():
                if counter == len(student.getTranscript().getCompletedCourses()) + len(student.getTranscript().getFailedCourses()):
                    break
                nte = self.__nonTechnicalElectives[random.randint(
                    0, len(self.__nonTechnicalElectives) - 1)]
                counter += 1

            if nte.semesterCheck(i):
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 44)
                courseGrade = Grade(nte, intRandomGrade)
                student.getTranscript().getTakenCourses()[nte] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(
                    nte, courseGrade.getLetter())

        for i in range(1, student.getSemester()):
            fte = self.__facultyTechnicalElectives[random.randint(
                0, len(self.__facultyTechnicalElectives) - 1)]
            counter = 0
            while fte in student.getTranscript().getCompletedCourses() or fte in student.getTranscript().getFailedCourses():
                if counter == len(student.getTranscript().getCompletedCourses()) + len(student.getTranscript().getFailedCourses()):
                    break
                fte = self.__facultyTechnicalElectives[random.randint(
                    0, len(self.__facultyTechnicalElectives) - 1)]
                counter += 1
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
        te = self.__technicalElectives[random.randint(
            0, len(self.__technicalElectives) - 1)]
        while i < student.getSemester():
            if te.semesterCheck(i) and student.getTranscript().getCreditCompleted() >= te.getRequiredCredits():
                teCount = 0
                if i == 7:
                    while teCount != 2:
                        counter = 0
                        while te in student.getTranscript().getCompletedCourses() or te in student.getTranscript().getFailedCourses() or not student.getTranscript().hasBeenPassedCourses(te.getPrerequisites()):

                            if counter == len(student.getTranscript().getCompletedCourses()) + len(student.getTranscript().getFailedCourses()):
                                break
                            te = self.__technicalElectives[random.randint(
                                0, len(self.__technicalElectives) - 1)]
                            counter += 1

                        intRandomGrade = random.randint(0, 99)
                        if intRandomGrade >= 90:
                            intRandomGrade = random.randint(
                                0, 100 - 85 - 1) + 85
                        elif intRandomGrade < 30:
                            intRandomGrade = random.randint(0, 44)
                        courseGrade = Grade(te, intRandomGrade)
                        student.getTranscript().getTakenCourses()[
                            te] = courseGrade
                        student.getTranscript().isCourseCompletedOrFailed(te, courseGrade.getLetter())
                        teCount += 1
            i += 1

        student.getTranscript().calculateCompleteCredit()
        for gp in self.__graduationCourses:
            if gp.getSemester() < student.getSemester() and student.getTranscript().getCreditCompleted() >= gp.getRequiredCredits():
                intRandomGrade = random.randint(0, 99)
                if intRandomGrade >= 90:
                    intRandomGrade = random.randint(0, 100 - 85 - 1) + 85
                elif intRandomGrade < 30:
                    intRandomGrade = random.randint(0, 44)
                courseGrade = Grade(gp, intRandomGrade)
                student.getTranscript().getTakenCourses()[gp] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(gp, courseGrade.getLetter())

        for internship in self.__internships:
            if internship.isEligibleToRequest(student) and internship.getSemester() < student.getSemester():
                courseGrade = Grade(internship, 0)
                student.getTranscript().getTakenCourses()[
                    internship] = courseGrade
                student.getTranscript().isCourseCompletedOrFailed(
                    internship, courseGrade.getLetter())
                logging.info(student.toString() + " " + internship.getCourseName() +
                             " " + courseGrade.getLetter())

        logging.info(student.getStudentName(
        ) + ": Student took all courses for his/her past semesters.")
        student.getTranscript().calculateCompleteCredit()
        student.getTranscript().calculateGpa()

    def requestCoursesForAllStudents(self):
        # Courses in the curriculum according to semester information, fall or spring are added to the
        # currentSemesterMandotaryCourse Arraylist
        currentSemesterMandatoryCourses = []
        for c in self.__mandatoryCourses:
            if self.__currentSemester == "fall":
                if c.getSemester() % 2 != 0:
                    currentSemesterMandatoryCourses.append(c)
            elif self.__currentSemester == "spring":
                if c.getSemester() % 2 == 0:
                    currentSemesterMandatoryCourses.append(c)

        for s in self.__studentList:
            for mc in currentSemesterMandatoryCourses:
                if mc.isEligibleToRequest(s):
                    s.getRequestedCourses().append(mc)
            logging.info(s.getStudentName(
            ) + ": Student requested all mandatory courses for his/her semester.")

            # Adding the failed courses to the getRequestedCourse Arraylist by checking the semester
            for c in s.getTranscript().getFailedCourses():
                if isinstance(c, MandatoryCourse) and not isinstance(c, Internship):
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
                # if isinstance(c, Internship):
                #     s.getRequestedCourses().append(c)
            logging.info(s.getStudentName(
            ) + ": Student requested all failed courses this semester(if it is open).")

            for gp in self.__graduationCourses:
                if gp in s.getRequestedCourses():
                    continue
                if gp.isEligibleToRequest(s) and gp.checkRequiredCredit(s):
                    s.getRequestedCourses().append(gp)

            limit = 0
            teList = self.__technicalElectives.copy()
            if s.getSemester() == 7:
                limit = 2
            elif s.getSemester() == 8:
                limit = 3
            for i in range(limit):
                te = teList[random.randint(
                    0, len(teList) - 1)]
                transcript = s.getTranscript()
                counter = 0
                while te in transcript.getCompletedCourses() or te in transcript.getFailedCourses() or te in s.getRequestedCourses():
                    teList.remove(te)
                    if counter == len(transcript.getCompletedCourses()) + len(transcript.getFailedCourses()) + len(s.getRequestedCourses()):
                        break
                    te = teList[random.randint(
                        0, len(teList) - 1)]
                    counter += 1
                if te.isEligibleToRequest(s) and te.checkRequiredCredit(s):
                    s.getRequestedCourses().append(te)
                else:
                    teList.remove(te)

            info = 0
            if s.getSemester() == 7:
                info = 2
            elif s.getSemester() == 8:
                info = 3
            logging.info(s.getStudentName() + ": Student requested " +
                         str(info) + " technical courses this semester.")

            nte = self.__nonTechnicalElectives[random.randint(
                0, len(self.__nonTechnicalElectives) - 1)]
            counter = 0
            while nte in s.getTranscript().getCompletedCourses() or nte in s.getRequestedCourses():
                if counter == len(s.getTranscript().getCompletedCourses()) + len(s.getRequestedCourses()):
                    break
                nte = self.__nonTechnicalElectives[random.randint(
                    0, len(self.__nonTechnicalElectives) - 1)]
                counter += 1
            if nte.isEligibleToRequest(s):
                s.getRequestedCourses().append(nte)

            fte = self.__facultyTechnicalElectives[random.randint(
                0, len(self.__facultyTechnicalElectives) - 1)]
            counter1 = 0
            while fte in s.getTranscript().getCompletedCourses() or fte in s.getRequestedCourses():
                if counter1 == len(s.getTranscript().getCompletedCourses()) + len(s.getRequestedCourses()):
                    break
                fte = self.__facultyTechnicalElectives[random.randint(
                    0, len(self.__facultyTechnicalElectives) - 1)]
                counter1 += 1
            if fte.isEligibleToRequest(s):
                s.getRequestedCourses().append(fte)

            for internship in self.__internships:
                if internship.isEligibleToRequest(s) and internship.getSemester() <= s.getSemester() and internship not in s.getTranscript().getCompletedCourses() and s.willBeMultipleInternshipsInRequestedCourses():
                    s.getRequestedCourses().append(internship)

    def startRegistration(self):
        for s in self.__studentList:
            s.getAdvisor().completeRegistration(s)
        logging.info("All students have completed their course registrations.")

    def createStudentOutput(self, student):

        # Add student info
        studentDict = {
            "StudentName": student.toString(),
            "StudentId": student.getStudentId().toString(),
            "StudentEmail": student.getEmail(),
            "StudentPhoneNumber": student.getPhoneNumber(),
            "SemesterNumber": student.getSemester(),
            "CompletedCredits": student.getTranscript().getCreditCompleted(),
            "Gpa": student.getTranscript().getGpa(),
            "AdvisorName": student.getAdvisor().getAdvisorName(),
            "AdvisorId": student.getAdvisor().getinstructorId()
        }

        # Add taken course
        jsonListPastCourses = []
        for value in student.getTranscript().getTakenCourses().values():
            jsonDictTakenCourse = {
                "LetterGrade": value.getLetter(),
                "CourseName": value.getCourse().getCourseName(),
                "Course": value.getCourse().getCourseCode(),
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

        try:
            with open(f"Output\\Students\\{student.getStudentId().toString()}.json", "w", encoding='utf8') as outfile:
                outfile.write(jsonObject)
        except:
            logging.error("Student output could not be created.")
            exit(4)

    def createDepartmentOutput(self):
        jsonDepartment = {}
        for course in self.__coursesList:
            if course in self.__mandatoryCourses:
                if self.__currentSemester == "fall" and cast(MandatoryCourse, course).getSemester() % 2 != 0:
                    jsonListDepartment = []
                    jsonListDepartment.append(
                        f"{len(course.getStudents())}  Students could register for {course.getCourseCode()}  successfully.")
                    jsonListDepartment.append(
                        f"{course.getQuotaProblem()} Students couldn't register for Students couldn't register for {course.getCourseCode()} due to the quota problems.")
                    jsonListDepartment.append(
                        f"{course.getCollisionProblem()} Students couldn't register for {course.getCourseCode()} due to the collision problems.")
                    jsonListDepartment.append(
                        f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                    jsonListDepartment.append(
                        f"{course.getEnrolledCourseLimit()} Students couldn't register for {course.getCourseCode()} due to exceeding the max enrolled courses.")
                    if course in self.__graduationCourses:
                        jsonListDepartment.append(
                            f"{course.getFailedCredits()} Students couldn't register for {course.getCourseCode()} due to the failed credit problems.")
                    jsonDepartment[f"{course.getCourseName()}"] = jsonListDepartment
                elif self.__currentSemester == "spring" and cast(MandatoryCourse, course).getSemester() % 2 == 0:
                    jsonListDepartment = []
                    jsonListDepartment.append(
                        f"{len(course.getStudents())}  Students could register for {course.getCourseCode()}  successfully.")
                    jsonListDepartment.append(
                        f"{course.getQuotaProblem()} Students couldn't register for Students couldn't register for {course.getCourseCode()} due to the quota problems.")
                    jsonListDepartment.append(
                        f"{course.getCollisionProblem()} Students couldn't register for {course.getCourseCode()} due to the collision problems.")
                    jsonListDepartment.append(
                        f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                    jsonListDepartment.append(
                        f"{course.getEnrolledCourseLimit()} Students couldn't register for {course.getCourseCode()} due to exceeding the max enrolled courses.")
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
                jsonListDepartment.append(
                    f"{course.getEnrolledCourseLimit()} Students couldn't register for {course.getCourseCode()} due to exceeding the max enrolled courses.")
                if course in self.__technicalElectives:
                    jsonListDepartment.append(
                        f"{course.getFailedCredits()}  Students couldn't register for {course.getCourseCode()} due to the failed credit problems.")
                    jsonListDepartment.append(
                        f"{course.getFailedPreq()} Students couldn't register for {course.getCourseCode()} due to the failed prerequisite.")
                jsonDepartment[f"{course.getCourseName()}"] = jsonListDepartment

        jsonObject = json.dumps(jsonDepartment, indent=4, ensure_ascii=False)

        try:
            with open(f"Output\\DEPARTMENT_OUTPUT_{self.__currentSemester.upper()}.json", "w", encoding='utf8') as outfile:
                outfile.write(jsonObject)
        except:
            logging.error("Department output could not be created.")
            exit(5)

    def startSimulation(self) -> None:
        self.readAdvisors()
        self.readInput()
        self.readStudent()
        self.assignInstructor(self.__coursesList)
        self.assignAdvisor(self.__studentList)
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

    def getInternshipList(self):
        return self.__internships
