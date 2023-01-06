class StudentId:

    def __init__(self, year: int, order: int):
        self.__cseCode: str = "1501"
        self.__year: int = year
        self.__order: int = order
        self.__id: str = self.createStudentId()

    def createStudentId(self) -> str:
        order_length_dict = {1: "00", 2: "0", 3: ""}
        return self.__cseCode + str(self.__year)[-2:] + order_length_dict[len(str(self.__order))] + str(self.__order)

    def getCseCode(self) -> str:
        return self.__cseCode

    def getYear(self) -> int:
        return self.__year

    def getOrder(self) -> int:
        return self.__order

    def toString(self):
        return self.__id