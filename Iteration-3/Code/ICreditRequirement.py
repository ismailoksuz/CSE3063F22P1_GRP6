from abc import ABC, abstractmethod


class ICreditRequirement(ABC):

    @abstractmethod
    def checkRequiredCredit(self, student):
        pass