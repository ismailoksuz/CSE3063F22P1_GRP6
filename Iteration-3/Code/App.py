import os
import logging
from RegistrationSystem import RegistrationSystem
class App:
    def __init__(self):
        self.main()
    def main(self):
 
        try:
            if not os.path.exists("Output"):
                os.makedirs("Output")
            if not os.path.exists("Output\\Students"):
                os.makedirs("Output\\Students")
        except:
            logging.error("Output folder could not be created.")

        logging.basicConfig(filename="Output\\Application.log",
                            filemode='w',
                            format='%(asctime)s,%(msecs)d %(name)s %(levelname)s %(message)s',
                            datefmt='%H:%M:%S',
                            level=logging.DEBUG)
        logging.getLogger().addHandler(logging.StreamHandler())
        rs = RegistrationSystem()
app=App()