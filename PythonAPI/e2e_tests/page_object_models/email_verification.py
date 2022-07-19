from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By


class Email_verification:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def email_text_box(self):
        return self.driver.find_element(By.ID,"emailInput")
    
    def continue_to_reset_password(self):
        return self.driver.find_element(By.ID,"submitEmail")
