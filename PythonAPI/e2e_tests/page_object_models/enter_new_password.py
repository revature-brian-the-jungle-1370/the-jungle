from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By


class Enter_new_password:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def password_text_box(self):
        return self.driver.find_element(By.ID,"passcodeInput")
    
    def create_new_reset_password(self):
        return self.driver.find_element(By.ID,"submitPasscode")