from typing import cast
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