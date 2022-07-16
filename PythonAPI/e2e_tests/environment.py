from behave.runner import Context
from selenium import webdriver
from PythonAPI.e2e_tests.page_object_models.login import Login_page
from page_object_models.email_verification import Email_verification
from page_object_models.enter_new_password import Enter_new_password

def before_all(context: Context):
    context.driver = webdriver.Chrome("../e2e_tests/chromedriver.exe")
    context.enter_email_page = Email_verification(context.driver)
    context.create_new_password_page = Enter_new_password(context.driver)
    context.login_page = Login_page(context.driver)

    context.driver.implicitly_wait(10)


def after_all(context):
    context.driver.quit()