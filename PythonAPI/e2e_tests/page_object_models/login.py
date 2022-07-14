from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By


class Login_page:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def username_text_box(self):
        return self.driver.find_element(By.ID,"usernameInput")

    def password_text_box(self):
        return self.driver.find_element(By.ID,"passcodeInput")
    
    def login_button(self):
        return self.driver.find_element(By.ID,"submitLogin")